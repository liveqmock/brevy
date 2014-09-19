package com.brevy.commons.vfs.core.compress.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
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
 * @Description tar打包/解包工具(无压缩处理)
 * @author caobin
 * @date 2014-5-27
 * @version 1.0
 */
public class TarCompressor extends AbstractVfs implements Compressor {
	
	/**
	 * TAR协议URI模式
	 */
	private final static String TAR_URI_PATTERN = "tar:file://{0}!{1}";
	
	/**
	 * compressed虚拟文件对象
	 */
	protected FileObject compressedFO;


	/**
	 * @param fsMgr 标准文件系统管理器
	 * @throws FileSystemException
	 */
	public TarCompressor(StandardFileSystemManager fsMgr) throws FileSystemException{
		super(fsMgr);
	}
	
	@Override
	public void decompress(File compressedFile) {
		decompress(compressedFile, null);
	}

	@Override
	public void decompress(File compressedFile, File targetDir) {
		decompress(compressedFile, null, targetDir);
	}

	@Override
	public void decompress(File compressedFile, String internalFilePath, File targetDir) {
		decompress(compressedFile, internalFilePath, targetDir, FileType.FILE_OR_FOLDER);
	}

	@Override
	public void decompress(File compressedFile, String internalFilePath,
			File targetDir, FileType filetype) {
		try {
			if(compressedFO == null){
				compressedFO = fsMgr.resolveFile(
						MessageFormat.format(TAR_URI_PATTERN, StringUtils.defaultString(compressedFile.getAbsolutePath()), StringUtils.defaultString(internalFilePath))
				);
			}
			
			if(targetDir == null){
				targetDir = compressedFile.getParentFile();
				log.trace("target directory: {}", new Object[]{targetDir.getAbsolutePath()});
			}
			
			if(compressedFO.getType() == FileType.FILE){
				FileObject targetFile = fsMgr.resolveFile(targetDir.getAbsolutePath().concat(UNIX_SEPARATOR).concat(compressedFO.getName().getBaseName()));
				targetFile.copyFrom(compressedFO, Selectors.SELECT_ALL);
				return;
			}
			
			for(FileObject fo : compressedFO.getChildren()){
				FileObject targetFile = fsMgr.resolveFile(targetDir.getAbsolutePath().concat(UNIX_SEPARATOR).concat(fo.getName().getBaseName()));
				if(filetype == FileType.FILE_OR_FOLDER || fo.getType() == filetype){
					targetFile.copyFrom(fo, Selectors.SELECT_ALL);
				}		
			}
		} catch (FileSystemException e) {
			throw new VfsRuntimeException(e);
		}

	}

	@Override
	public void compress(File[] sourceFileOrDirs, File targetFile) {
		compress(sourceFileOrDirs, targetFile, DEFAULT_CHARSET);
	}

	@Override
	public void compress(File[] sourceFileOrDirs, File targetFile,
			String charset) {
		try {		
			FileUtils.forceMkdir(targetFile.getParentFile());
			TarArchiveOutputStream taos = new TarArchiveOutputStream(new BufferedOutputStream(new FileOutputStream(targetFile)), charset);
			taos.setLongFileMode(TarArchiveOutputStream.LONGFILE_GNU);
			TarArchiveEntry tarArchiveEntry = null;
			
			for(File sourceFileOrDir : sourceFileOrDirs){
				if(sourceFileOrDir.isDirectory()){
					File[] fileOrDirs = sourceFileOrDir.listFiles();				
					for(File fileOrDir : fileOrDirs){	
						writeTarArchiveEntry(taos, tarArchiveEntry, fileOrDir, sourceFileOrDir.getName());
						if(fileOrDir.isDirectory()){
							compress(fileOrDir, taos, sourceFileOrDir.getName().concat(UNIX_SEPARATOR).concat(fileOrDir.getName()));
						}					
					}				
				}else{
					writeTarArchiveEntry(taos, tarArchiveEntry, sourceFileOrDir, null);
				}	
			}
			taos.finish();
			taos.close();
		} catch (IOException e) {
			throw new VfsRuntimeException(e);
		} 
	}

	@Override
	public void compress(File sourceFileOrDir) {
		compress(sourceFileOrDir, new File(sourceFileOrDir.getAbsolutePath().replace(File.separator, "").concat(".tar")));
	}

	@Override
	public void compress(File sourceFileOrDir, File targetFile) {
		compress(sourceFileOrDir, targetFile, this.DEFAULT_CHARSET);
	}

	@Override
	public void compress(File sourceFileOrDir, File targetFile, String charset) {
		try {		
			FileUtils.forceMkdir(targetFile.getParentFile());
			TarArchiveOutputStream taos = new TarArchiveOutputStream(new BufferedOutputStream(new FileOutputStream(targetFile)), charset);
			taos.setLongFileMode(TarArchiveOutputStream.LONGFILE_GNU);
			TarArchiveEntry tarArchiveEntry = null;
			if(sourceFileOrDir.isDirectory()){
				File[] fileOrDirs = sourceFileOrDir.listFiles();				
				for(File fileOrDir : fileOrDirs){	
					writeTarArchiveEntry(taos, tarArchiveEntry, fileOrDir, sourceFileOrDir.getName());
					if(fileOrDir.isDirectory()){
						compress(fileOrDir, taos, sourceFileOrDir.getName().concat(UNIX_SEPARATOR).concat(fileOrDir.getName()));
					}					
				}				
			}else{
				writeTarArchiveEntry(taos, tarArchiveEntry, sourceFileOrDir, null);
			}	
			taos.finish();
			taos.close();
		} catch (IOException e) {
			throw new VfsRuntimeException(e);
		} 
	}
	
	/**
	 * @Description 将指定文件夹打包为tar
	 * @param sourcDir 待打包文件（夹）
	 * @param zaos tarArchive输出流
	 * @param parentDir 父目录
	 * @author caobin
	 */
	private void compress(File sourcDir, TarArchiveOutputStream taos, String parentDir){
		parentDir = StringUtils.defaultString(parentDir);
		if(sourcDir.isDirectory()){										
			File[] fileOrDirs = sourcDir.listFiles();
			TarArchiveEntry tarArchiveEntry = null;
			for(File fileOrDir : fileOrDirs){		
				writeTarArchiveEntry(taos, tarArchiveEntry, fileOrDir, parentDir);
				if(fileOrDir.isDirectory()){	
					compress(fileOrDir, taos, parentDir.concat(UNIX_SEPARATOR).concat(fileOrDir.getName()));
				}			
			}
		}else{
			throw new VfsRuntimeException(E01, sourcDir.getAbsoluteFile());
		}	
		
	}
	
	/**
	 * @Description 写入Entry
	 * @param zaos tarArchive输出流
	 * @param tarArchiveEntry tar存档项 
	 * @param sourceFile 读入文件
	 * @param parentDir 父目录
	 * @author caobin
	 */
	private void writeTarArchiveEntry(TarArchiveOutputStream taos, TarArchiveEntry tarArchiveEntry, File sourceFile, String parentDir){
		try {
			
			if(StringUtils.isNotBlank(parentDir)){
				parentDir = parentDir.concat(UNIX_SEPARATOR);
			}else{
				parentDir = StringUtils.defaultString(parentDir);
			}
			tarArchiveEntry = new TarArchiveEntry(sourceFile, parentDir.concat(sourceFile.getName()));
			taos.putArchiveEntry(tarArchiveEntry);
			if(sourceFile.isFile()){
				InputStream is = new FileInputStream(sourceFile);
				IOUtils.copy(is, taos);
				IOUtils.closeQuietly(is);
			}		
			taos.closeArchiveEntry();
		} catch (FileNotFoundException e) {
			throw new VfsRuntimeException(e);
		} catch (IOException e) {
			throw new VfsRuntimeException(e);
		}	
	}
}
