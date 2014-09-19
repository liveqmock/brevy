package com.brevy.commons.vfs;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSelector;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.FileSystemOptions;
import org.apache.commons.vfs2.FileUtil;
import org.apache.commons.vfs2.NameScope;
import org.apache.commons.vfs2.Selectors;
import org.apache.commons.vfs2.impl.StandardFileSystemManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description 虚拟文件系统抽象基类
 * @author caobin
 * @date 2014-5-20
 * @version 1.0
 */
public abstract class AbstractVfs {
	
	protected final static String E01 = "vfs.compressor.e01";
	
	protected final static String E02 = "vfs.compressor.e02";
	
	protected final static String UNIX_SEPARATOR = "/";
	
	protected transient Logger log = LoggerFactory.getLogger(this.getClass());
	
	protected final String DEFAULT_CHARSET = "GBK";
	
	protected final String ISO8859_CHARSET = "ISO8859-1";
	
	/**
	 * 标准文件系统管理器
	 */
	protected StandardFileSystemManager fsMgr;
	
	/**
	 * 文件系统选项
	 */
	protected FileSystemOptions opts;

	public AbstractVfs(StandardFileSystemManager fsMgr) throws FileSystemException{
		this.fsMgr = fsMgr;
		fsMgr.init();	
	}
	
	public AbstractVfs(StandardFileSystemManager fsMgr, FileSystemOptions opts) throws FileSystemException{
		this.fsMgr = fsMgr;
		this.opts = opts;
		fsMgr.init();
	}

	/**
	 * @Description targetfile content copy from source file
	 * @param sourceFile
	 * @param targetFile
	 * @param selector
	 * @param srcFilenameEncoding
	 * @param targetFilenameEncoding
	 * @throws FileSystemException
	 * @author caobin
	 */
	protected void copyFrom(final FileObject sourceFile, final FileObject targetFile, final FileSelector selector, final String srcFilenameEncoding, final String targetFilenameEncoding)
			throws FileSystemException {
		if (!sourceFile.exists()) {
			throw new FileSystemException(
					"vfs.provider/copy-missing-file.error", sourceFile);
		}
		/*
		 * we do not alway know if a file is writeable if (!isWriteable()) {
		 * throw new FileSystemException("vfs.provider/copy-read-only.error",
		 * new Object[]{file.getType(), file.getName(), this}, null); }
		 */

		// Locate the files to copy across
		final ArrayList<FileObject> files = new ArrayList<FileObject>();
		sourceFile.findFiles(selector, false, files);

		// Copy everything across
		final int count = files.size();
		for (int i = 0; i < count; i++) {
			final FileObject srcFile = files.get(i);

			// Determine the destination file
			String relPath;
			try {
				relPath = new String(sourceFile.getName().getRelativeName(
						srcFile.getName()).getBytes(srcFilenameEncoding), targetFilenameEncoding);
			} catch (UnsupportedEncodingException e1) {
				throw new FileSystemException(e1);
			}
			final FileObject destFile = targetFile.resolveFile(relPath,
					NameScope.DESCENDENT_OR_SELF);

			// Clean up the destination file, if necessary
			if (destFile.exists() && destFile.getType() != srcFile.getType()) {
				// The destination file exists, and is not of the same type,
				// so delete it
				// TODO - add a pluggable policy for deleting and overwriting
				// existing files
				destFile.delete(Selectors.SELECT_ALL);
			}

			// Copy across
			try {
				if (srcFile.getType().hasContent()) {
					FileUtil.copyContent(srcFile, destFile);
				} else if (srcFile.getType().hasChildren()) {
					destFile.createFolder();
				}
			} catch (final IOException e) {
				throw new FileSystemException("vfs.provider/copy-file.error",
						new Object[] { srcFile, destFile }, e);
			}
		}
	}
	
	
	
	/**
	 * @Description 关闭VFS Manager
	 * @author caobin
	 */
	public void close(){
		if(fsMgr != null){
			fsMgr.close();
		}
	}
}
