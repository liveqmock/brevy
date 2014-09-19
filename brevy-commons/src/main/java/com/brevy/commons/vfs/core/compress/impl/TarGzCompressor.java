package com.brevy.commons.vfs.core.compress.impl;

import java.io.File;
import java.text.MessageFormat;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.FileType;
import org.apache.commons.vfs2.impl.StandardFileSystemManager;

import com.brevy.commons.vfs.VfsFactory;
import com.brevy.commons.vfs.VfsRuntimeException;
import com.brevy.commons.vfs.core.compress.Compressor;

/**
 * @Description TAR.GZ压缩/解压缩工具
 * @author caobin
 * @date 2014-5-28
 * @version 1.0
 */
public class TarGzCompressor extends TarCompressor {

	/**
	 * TAR.GZ协议URI模式
	 */
	private final static String TGZ_URI_PATTERN = "tgz:file://{0}!{1}";
	
	private Compressor gzCompressor;
	
	
	public TarGzCompressor(StandardFileSystemManager fsMgr) throws FileSystemException {
		super(fsMgr);
		gzCompressor = VfsFactory.createGzCompressor();
	}
	
	@Override
	public void decompress(File compressedFile, String internalFilePath,
			File targetDir, FileType filetype) {
		try {
			compressedFO = fsMgr.resolveFile(
					MessageFormat.format(TGZ_URI_PATTERN, StringUtils.defaultString(compressedFile.getAbsolutePath()), StringUtils.defaultString(internalFilePath))
			);
			super.decompress(compressedFile, internalFilePath, targetDir, filetype);
		} catch (FileSystemException e) {
			throw new VfsRuntimeException(e);
		}
	}

	
	@Override
	public void compress(File[] sourceFileOrDirs, File targetFile,
			String charset) {
		super.compress(sourceFileOrDirs, targetFile, charset);
		try {
			gzCompressor.compress(targetFile);
			log.trace("gzip the tar file: {}", new Object[]{targetFile.getAbsolutePath()});
		} catch (Throwable e) {
			throw new VfsRuntimeException(e);
		}
	}


	@Override
	public void compress(File sourceFileOrDir, File targetFile, String charset) {
		super.compress(sourceFileOrDir, targetFile, charset);
		try {
			gzCompressor.compress(targetFile);
			log.trace("gzip the tar file: {}", new Object[]{targetFile.getAbsolutePath()});
		} catch (Throwable e) {
			throw new VfsRuntimeException(e);
		}
	}	
	
	
}
