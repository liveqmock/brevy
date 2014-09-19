package com.brevy.commons.vfs.core.compress.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.zip.GZIPOutputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.FileType;
import org.apache.commons.vfs2.Selectors;
import org.apache.commons.vfs2.impl.StandardFileSystemManager;

import com.brevy.commons.vfs.AbstractVfs;
import com.brevy.commons.vfs.VfsRuntimeException;
import com.brevy.commons.vfs.core.compress.Compressor;

/**
 * @Description GZip压缩/解压缩器（单文件支持）
 * @author caobin
 * @date 2014-5-28
 * @version 1.0
 */
public class GzCompressor extends AbstractVfs implements Compressor{
	
	/**
	 * TAR.GZ协议URI模式
	 */
	private final static String GZ_URI_PATTERN = "gz:file://{0}";
	
	private final static String GZ_PATTERN = "(?:\\.[Gg][Zz])$";
	
	/**
	 * compressed虚拟文件对象
	 */
	protected FileObject compressedFO;

	public GzCompressor(StandardFileSystemManager fsMgr) throws FileSystemException {
		super(fsMgr);
	}

	@Override
	public void decompress(File compressedFile) {
		decompress(compressedFile, null);
	}

	@Override
	public void decompress(File compressedFile, File targetDir) {
		try {
			compressedFO = fsMgr.resolveFile(
					MessageFormat.format(GZ_URI_PATTERN, StringUtils.defaultString(compressedFile.getAbsolutePath()))
			);		
			
			if(targetDir == null){
				targetDir = compressedFile.getParentFile();
				log.trace("target directory: {}", new Object[]{targetDir.getAbsolutePath()});
			}
			
			FileObject targetFile = fsMgr.resolveFile(targetDir.getAbsolutePath().concat(UNIX_SEPARATOR).concat(compressedFO.getName().getBaseName()));
			targetFile.copyFrom(compressedFO, Selectors.SELECT_ALL);
					
		} catch (FileSystemException e) {
			throw new VfsRuntimeException(e);
		}
	}

	@Override
	public void decompress(File compressedFile, String internalFilePath,
			File targetDir) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void decompress(File compressedFile, String internalFilePath,
			File targetDir, FileType filetype) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void compress(File[] sourceFileOrDirs, File targetFile) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void compress(File[] sourceFileOrDirs, File targetFile,
			String charset) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void compress(File sourceFileOrDir) {
		compress(sourceFileOrDir, sourceFileOrDir);
	}

	@Override
	public void compress(File sourceFileOrDir, File targetFile) {
		try {
			targetFile = new File(targetFile.getAbsolutePath().replaceAll(GZ_PATTERN, ""));
			FileUtils.copyFile(sourceFileOrDir, targetFile);		
			gzip(targetFile, true);
			log.trace("gzip the file: {}", new Object[]{targetFile.getAbsolutePath()});
		} catch (Throwable e) {
			throw new VfsRuntimeException(e);
		}
	}

	@Override
	public void compress(File sourceFileOrDir, File targetFile, String charset) {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * @Description gzip压缩指定文件
	 * @param deleteSourceFile 是否删除
	 * @param sourceFile 源文件
	 * @author caobin
	 * @throws Throwable 
	 */
	private void gzip(File sourceFile, boolean deleteSourceFile) throws Throwable {
		File targetFile = new File(sourceFile.getAbsolutePath().concat(".gz"));
		InputStream is = new FileInputStream(sourceFile);
		GZIPOutputStream gzipos = new GZIPOutputStream(new BufferedOutputStream(new FileOutputStream(targetFile)));
		try{
			byte[] bArr = new byte[1024];
			int number = -1;
			while((number = is.read(bArr, 0, bArr.length)) != -1){
				gzipos.write(bArr, 0, number);
			}			
		}catch(Throwable t){
			deleteSourceFile = false;
			throw t;
		}finally{
			IOUtils.closeQuietly(is);
			IOUtils.closeQuietly(gzipos);
			if(deleteSourceFile){
				if(sourceFile.canWrite()){
					boolean r = sourceFile.delete();
					if(!r){
						throw new IOException(String.format("file [%s] delete failed.", sourceFile.getAbsolutePath()));
					}
				}else{
					throw new IOException(String.format("file [%s] cannot be written.", sourceFile.getAbsolutePath()));
				}
			}	
		}	
	}
}
