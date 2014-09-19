package com.brevy.commons.vfs.core.compress.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;

import org.apache.commons.compress.archivers.zip.Zip64Mode;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
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
 * @Description ZIP压缩/解压缩工具类
 * @author caobin
 * @date 2014-5-21
 * @version 1.0
 */
public class ZipCompressor extends AbstractVfs implements Compressor{

	/**
	 * ZIP协议URI模式
	 */
	private final static String ZIP_URI_PATTERN = "zip:file://{0}!{1}";
	
	/**
	 * compressed虚拟文件对象
	 */
	private FileObject compressedFO;
	
	/**
	 * @param fsMgr 标准文件系统管理器
	 * @throws FileSystemException
	 */
	public ZipCompressor(StandardFileSystemManager fsMgr) throws FileSystemException{
		super(fsMgr);
	}
	
	
	@Override
	public void decompress(File compressedFile){
		decompress(compressedFile, null);
	}
	
	@Override
	public void decompress(File compressedFile, File targetDir){
		decompress(compressedFile, null, targetDir);
	}
	
	@Override
	public void decompress(File compressedFile, String internalFilePath, File targetDir){
		decompress(compressedFile, internalFilePath, targetDir, FileType.FILE_OR_FOLDER);
	}
	
	@Override
	public void decompress(File compressedFile, String internalFilePath, File targetDir, FileType filetype){
		try {
			compressedFO = fsMgr.resolveFile(
					MessageFormat.format(ZIP_URI_PATTERN, StringUtils.defaultString(compressedFile.getAbsolutePath()), StringUtils.defaultString(internalFilePath))
			);
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
	public void compress(File[] sourceFileOrDirs, File targetFile){
		compress(sourceFileOrDirs, targetFile, DEFAULT_CHARSET);
	}
	
	
	@Override
	public void compress(File[] sourceFileOrDirs, File targetFile, String charset){
		try {		
			FileUtils.forceMkdir(targetFile.getParentFile());
			ZipArchiveOutputStream zaos = new ZipArchiveOutputStream(targetFile);
			zaos.setEncoding(charset);
			zaos.setUseZip64(Zip64Mode.AsNeeded);
			ZipArchiveEntry zipArchiveEntry = null;
			
			for(File sourceFileOrDir : sourceFileOrDirs){
				if(sourceFileOrDir.isDirectory()){
					File[] fileOrDirs = sourceFileOrDir.listFiles();				
					for(File fileOrDir : fileOrDirs){	
						writeZipArchiveEntry(zaos, zipArchiveEntry, fileOrDir, sourceFileOrDir.getName());
						if(fileOrDir.isDirectory()){
							compress(fileOrDir, zaos, sourceFileOrDir.getName().concat(UNIX_SEPARATOR).concat(fileOrDir.getName()));
						}					
					}				
				}else{
					writeZipArchiveEntry(zaos, zipArchiveEntry, sourceFileOrDir, null);
				}	
			}
			zaos.finish();
			zaos.close();
		} catch (IOException e) {
			throw new VfsRuntimeException(e);
		} 
	}
	
	@Override
	public void compress(File sourceFileOrDir){
		compress(sourceFileOrDir, new File(sourceFileOrDir.getAbsolutePath().replace(File.separator, "").concat(".zip")));
	}
	
	@Override
	public void compress(File sourceFileOrDir, File targetFile){
		compress(sourceFileOrDir, targetFile, this.DEFAULT_CHARSET);
	}
	
	@Override
	public void compress(File sourceFileOrDir, File targetFile, String charset){
		try {		
			FileUtils.forceMkdir(targetFile.getParentFile());
			ZipArchiveOutputStream zaos = new ZipArchiveOutputStream(targetFile);
			zaos.setEncoding(charset);
			zaos.setUseZip64(Zip64Mode.AsNeeded);
			ZipArchiveEntry zipArchiveEntry = null;
			if(sourceFileOrDir.isDirectory()){
				File[] fileOrDirs = sourceFileOrDir.listFiles();				
				for(File fileOrDir : fileOrDirs){	
					writeZipArchiveEntry(zaos, zipArchiveEntry, fileOrDir, sourceFileOrDir.getName());
					if(fileOrDir.isDirectory()){
						compress(fileOrDir, zaos, sourceFileOrDir.getName().concat(UNIX_SEPARATOR).concat(fileOrDir.getName()));
					}					
				}				
			}else{
				writeZipArchiveEntry(zaos, zipArchiveEntry, sourceFileOrDir, null);
			}	
			zaos.finish();
			zaos.close();
		} catch (IOException e) {
			throw new VfsRuntimeException(e);
		} 
	}

	/**
	 * @Description 将指定文件夹打包为zip
	 * @param sourcDir 待打包文件（夹）
	 * @param zaos zipArchive输出流
	 * @param parentDir 父目录
	 * @author caobin
	 */
	private void compress(File sourcDir, ZipArchiveOutputStream zaos, String parentDir){
		parentDir = StringUtils.defaultString(parentDir);
		if(sourcDir.isDirectory()){										
			File[] fileOrDirs = sourcDir.listFiles();
			ZipArchiveEntry zipArchiveEntry = null;
			for(File fileOrDir : fileOrDirs){		
				writeZipArchiveEntry(zaos, zipArchiveEntry, fileOrDir, parentDir);
				if(fileOrDir.isDirectory()){	
					compress(fileOrDir, zaos, parentDir.concat(UNIX_SEPARATOR).concat(fileOrDir.getName()));
				}			
			}
		}else{
			throw new VfsRuntimeException(E01, sourcDir.getAbsoluteFile());
		}	
		
	}
	
	/**
	 * @Description 写入Entry
	 * @param zaos zipArchive输出流
	 * @param zipArchiveEntry zip存档项 
	 * @param sourceFile 读入文件
	 * @param parentDir 父目录
	 * @author caobin
	 */
	private void writeZipArchiveEntry(ZipArchiveOutputStream zaos, ZipArchiveEntry zipArchiveEntry, File sourceFile, String parentDir){
		try {
			
			if(StringUtils.isNotBlank(parentDir)){
				parentDir = parentDir.concat(UNIX_SEPARATOR);
			}else{
				parentDir = StringUtils.defaultString(parentDir);
			}
			zipArchiveEntry = new ZipArchiveEntry(sourceFile, parentDir.concat(sourceFile.getName()));
			zaos.putArchiveEntry(zipArchiveEntry);
			if(sourceFile.isFile()){
				InputStream is = new FileInputStream(sourceFile);
				IOUtils.copy(is, zaos);
				IOUtils.closeQuietly(is);
			}		
			zaos.closeArchiveEntry();
		} catch (FileNotFoundException e) {
			throw new VfsRuntimeException(e);
		} catch (IOException e) {
			throw new VfsRuntimeException(e);
		}	
	}
}
