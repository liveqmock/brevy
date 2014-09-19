package com.brevy.commons.vfs.core.ftp;

import java.io.UnsupportedEncodingException;
import java.text.MessageFormat;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSelectInfo;
import org.apache.commons.vfs2.FileSelector;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.FileType;
import org.apache.commons.vfs2.Selectors;
import org.apache.commons.vfs2.impl.StandardFileSystemManager;

import com.brevy.commons.lang.LocaleUtils;
import com.brevy.commons.vfs.AbstractVfs;
import com.brevy.commons.vfs.VfsRuntimeException;

/**
 * @Description FTP客户端工具类
 * @author caobin
 * @date 2014-5-15
 * @version 1.0
 */
public class FtpClient extends AbstractVfs{

	private final static String I01 = "vfs.ftpclient.i01";
	private final static String I02 = "vfs.ftpclient.i02";
	private final static String W01 = "vfs.ftpclient.w01";
	
	/**
	 * FTP协议URI模式
	 */
	private final static String FTP_URI_PATTERN = "ftp://{0}:{1}@{2}:{3}{4}";
	
	/**
	 * FTP虚拟文件对象
	 */
	private FileObject ftpFO;
	
	/**
	 * @param fsMgr 标准文件系统管理器
	 * @throws FileSystemException
	 */
	public FtpClient(StandardFileSystemManager fsMgr) throws FileSystemException{
		super(fsMgr);
	}
	
	/**
	 * @Description 连接到FTP(使用默认端口：21， 用户起始目录: /)
	 * @param ipAddr IP地址(xxx.xxx.xxx.xxx)
	 * @param username 用户名
	 * @param password 密码
	 * @author caobin
	 */
	public void connect(String ipAddr, String username, String password){
		connect(ipAddr, username, password, "/");
	}
	
	/**
	 * @Description 连接到FTP(使用默认端口：21)
	 * @param ipAddr IP地址(xxx.xxx.xxx.xxx)
	 * @param username 用户名
	 * @param password 密码
	 * @param userDir 用户初始目录(/xx/xx)
	 * @author caobin
	 */
	public void connect(String ipAddr, String username, String password, String userDir){
		connect(ipAddr, 21, username, password, userDir);
	}
	
	/**
	 * @Description 连接到FTP
	 * @param ipAddr IP地址(xxx.xxx.xxx.xxx)
	 * @param port 端口号
	 * @param username 用户名
	 * @param password 密码
	 * @param userDir 用户初始目录(/xx/xx)
	 * @author caobin
	 */
	public void connect(String ipAddr, int port, String username, String password, String userDir){
		try {
			if(userDir != null){
				userDir = userDir.replace("\\", "/");
			}
			ftpFO = fsMgr.resolveFile(MessageFormat.format(FTP_URI_PATTERN, username, password, ipAddr, port, userDir));
		} catch (FileSystemException e) {
			throw new VfsRuntimeException(e);
		}
	}
	
	
	/**
	 * @Description 上传文件到FTP
	 * @param localFilePath 本地文件路径(x:/xx/)
	 * @param localFilename 本地文件名
	 * @return boolean true-成功获取文件/false-未获取到文件
	 * @author caobin
	 */
	public boolean putFile(String localFilePath, String localFilename){
		return putFile(localFilePath, localFilename, localFilename, DEFAULT_CHARSET);
	};
	
	
	/**
	 * @Description 上传文件到FTP
	 * @param localFilePath 本地文件路径(x:/xx/)
	 * @param localFilename 本地文件名
	 * @param remoteFilename 远程文件名
	 * @return boolean true-成功获取文件/false-未获取到文件
	 * @author caobin
	 */
	public boolean putFile(String localFilePath, String localFilename, final String remoteFilename){
		return putFile(localFilePath, localFilename, remoteFilename, DEFAULT_CHARSET);
	};
	
	/**
	 * @Description 上传文件到FTP
	 * @param localFilePath 本地文件路径(x:/xx/)
	 * @param localFilename 本地文件名
	 * @param remoteFilename 远程文件名
	 * @param charset 字符集
	 * @return boolean true-成功获取文件/false-未获取到文件
	 * @author caobin
	 */
	public boolean  putFile(String localFilePath, String localFilename, final String remoteFilename,  final String charset){
		return putFiles(localFilePath, localFilename, remoteFilename, charset, FileType.FILE);
	};
	
	
	/**
	 * @Description 上传文件/文件夹到FTP
	 * @param localFilePath 本地文件(夹)路径(x:/xx/)
	 * @param localFilename 本地文件(夹)名
	 * @return boolean true-成功获取文件(夹)/false-未获取到文件(夹)
	 * @author caobin
	 */
	public boolean putFiles(String localFilePath, String localFilename){
		return putFiles(localFilePath, localFilename, localFilename, DEFAULT_CHARSET);
	}
	
	/**
	 * @Description 上传文件/文件夹到FTP
	 * @param localFilePath 本地文件(夹)路径(x:/xx/)
	 * @param localFilename 本地文件(夹)名
	 * @param remoteFilename 远程文件名
	 * @param charset 字符集
	 * @return boolean true-成功获取文件(夹)/false-未获取到文件(夹)
	 * @author caobin
	 */
	public boolean putFiles(String localFilePath, String localFilename, final String remoteFilename,  final String charset){
		return putFiles(localFilePath, localFilename, remoteFilename, charset, FileType.FILE_OR_FOLDER);
	}
	
	/**
	 * @Description 上传文件/文件夹到FTP
	 * @param localFilePath  本地文件(夹)路径(x:/xx/)
	 * @param localFilename 本地文件(夹)名
	 * @param remoteFilename 远程文件名
	 * @param charset 字符集
	 * @param filetype 文件类型
	 * @return boolean true-成功获取文件(夹)/false-未获取到文件(夹)
	 * @author caobin
	 */
	public boolean putFiles(String localFilePath, String localFilename, final String remoteFilename,  final String charset, final FileType filetype){
		try {
			if(!localFilePath.endsWith("/") && !localFilePath.endsWith("\\")){
				localFilePath = localFilePath.concat("/");
			}
			FileObject localFO = fsMgr.resolveFile(localFilePath.concat(localFilename));
			FileObject rfo = null;
			
			if(filetype == FileType.FILE_OR_FOLDER || localFO.getType() == filetype){
				rfo = fsMgr.resolveFile(ftpFO.getURL().toString().concat("/").concat(new String(remoteFilename.getBytes(charset), ISO8859_CHARSET)));
				//Selectors.SELECT_ALL： 文件，文件夹及所有子文件，子文件夹
				this.copyFrom(localFO, rfo, Selectors.SELECT_ALL, charset, ISO8859_CHARSET);
				return true;
			}
			log.warn(LocaleUtils.getMessage(W01, localFO.getName().getBaseName()));
			return false;
		} catch (FileSystemException e) {
			throw new VfsRuntimeException(e);
		} catch (UnsupportedEncodingException e) {
			throw new VfsRuntimeException(e);
		} 
	};
	
	
	/**
	 * @Description 上传所有文件/文件夹到FTP
	 * @param localFilePath 本地文件路径(x:/xx/)
	 * @author caobin
	 */
	public void putAllFiles(String localFilePath){
		putAllFiles(localFilePath, DEFAULT_CHARSET, FileType.FILE_OR_FOLDER);		
	}
	
	
	/**
	 * @Description 上传所有文件/文件夹到FTP
	 * @param localFilePath 本地文件路径(x:/xx/)
	 * @param charset 字符集
	 * @param filetype 需要获取文件类型(文件|文件夹|文件或文件夹)
	 * @author caobin
	 */
	public void putAllFiles(String localFilePath, String charset, FileType filetype){
		if(!localFilePath.endsWith("/") && !localFilePath.endsWith("\\")){
			localFilePath = localFilePath.concat("/");
		}
		try {
			
			FileObject localFO = fsMgr.resolveFile(localFilePath);	
			FileObject rfo = null;
			for(FileObject fo : localFO.getChildren()){			
				if(filetype == FileType.FILE_OR_FOLDER || fo.getType() == filetype){
					rfo = fsMgr.resolveFile(ftpFO.getURL().toString().concat("/").concat(new String(fo.getName().getBaseName().getBytes(charset), ISO8859_CHARSET)));
					//Selectors.SELECT_ALL： 文件，文件夹及所有子文件，子文件夹
					this.copyFrom(fo, rfo, Selectors.SELECT_ALL, charset, ISO8859_CHARSET);
				}	
			}
		} catch (FileSystemException e) {
			throw new VfsRuntimeException(e);
		} catch (UnsupportedEncodingException e) {
			throw new VfsRuntimeException(e);
		}
		
	}
	
	
	/**
	 * @Description 从FTP上获取单个文件
	 * @param remoteFilename 远程文件名(xx.xx)
	 * @param localFilePath 本地文件路径(x:/xx/)
	 * @return boolean true-成功获取文件/false-未获取到文件
	 * @author caobin
	 */
	public boolean getFile(String remoteFilename, String localFilePath){
		return getFile(remoteFilename, localFilePath, remoteFilename);
	}
	
	/**
	 * @Description 从FTP上获取单个文件
	 * @param remoteFilename 远程文件名(xx.xx)
	 * @param localFilePath 本地文件路径(x:/xx/)
	 * @param localFilename 本地文件名(xx.xx)
	 * @return boolean true-成功获取文件/false-未获取到文件
	 * @author caobin
	 */
	public boolean getFile(String remoteFilename, String localFilePath, String localFilename){
		return getFile(remoteFilename, localFilePath, localFilename, DEFAULT_CHARSET);
	}
	
	/**
	 * @Description 从FTP上获取单个文件
	 * @param remoteFilename 远程文件名(xx.xx)
	 * @param localFilePath 本地文件路径(x:/xx/)
	 * @param localFilename 本地文件名(xx.xx)
	 * @param charset 字符集
	 * @return boolean true-成功获取文件/false-未获取到文件
	 * @author caobin
	 */
	public boolean getFile(final String remoteFilename, String localFilePath, String localFilename, final String charset){
		return getFiles(remoteFilename, localFilePath, localFilename, charset, FileType.FILE);
	}
	

	/**
	 * @Description 从FTP上获取文件/文件夹
	 * @param remoteFilename 远程文件(夹)名
	 * @param localFilePath 本地文件路径(x:/xx/)
	 * @return boolean true-成功获取文件(夹)/false-未获取到文件(夹)
	 * @author caobin
	 */
	public boolean getFiles(final String remoteFilename, String localFilePath){
		return getFiles(remoteFilename, localFilePath, remoteFilename, DEFAULT_CHARSET);
	}
	
	/**
	 * @Description 从FTP上获取文件/文件夹
	 * @param remoteFilename 远程文件(夹)名
	 * @param localFilePath 本地文件(夹)路径(x:/xx/)
	 * @param localFilename 本地文件(夹)名
	 * @param charset 字符集
	 * @return boolean true-成功获取文件(夹)/false-未获取到文件(夹)
	 * @author caobin
	 */
	public boolean getFiles(final String remoteFilename, String localFilePath, String localFilename, final String charset){
		return getFiles(remoteFilename, localFilePath, localFilename, charset, FileType.FILE_OR_FOLDER);
	}
	
	/**
	 * @Description 从FTP上获取文件/文件夹
	 * @param remoteFilename 远程文件(夹)名
	 * @param localFilePath 本地文件(夹)路径(x:/xx/)
	 * @param localFilename 本地文件(夹)名
	 * @param charset 字符集
	 * @param filetype 需要获取文件类型(文件|文件夹|文件或文件夹)
	 * @return boolean true-成功获取文件(夹)/false-未获取到文件(夹)
	 * @author caobin
	 */
	public boolean getFiles(final String remoteFilename, String localFilePath, String localFilename, final String charset, final FileType filetype){
		try {
			//是否继续搜索标志位
			final AtomicBoolean continueSearch = new AtomicBoolean(true);
			
			FileObject[] fos = ftpFO.findFiles(new FileSelector(){
				@Override
				public boolean includeFile(FileSelectInfo fileInfo)
						throws Exception {
					if(!continueSearch.get())return false;
					if(filetype == FileType.FILE_OR_FOLDER || fileInfo.getFile().getType() == filetype){
						String basefilename  = new String(fileInfo.getFile().getName().getBaseName().getBytes(ISO8859_CHARSET), charset);
						log.trace(LocaleUtils.getMessage(I02, basefilename));
						if(basefilename.equals(remoteFilename)){
							continueSearch.set(false);
							return true;
						}
					}			
					return false;
				}
				
				@Override
				public boolean traverseDescendents(FileSelectInfo fileInfo)
						throws Exception {		
					if(!continueSearch.get())return false;
					return fileInfo.getDepth() == 0;
				}
				
			});
			
			//未找到文件
			if(ArrayUtils.isEmpty(fos)){
				log.warn(LocaleUtils.getMessage(W01, remoteFilename));
				return false;
			}
			
			if(!localFilePath.endsWith("/") && !localFilePath.endsWith("\\")){
				localFilePath = localFilePath.concat("/");
			}
			log.trace(LocaleUtils.getMessage(I01, localFilename));
			FileObject destFO = fsMgr.resolveFile(localFilePath.concat(localFilename));
			this.copyFrom(fos[0], destFO, Selectors.SELECT_ALL, ISO8859_CHARSET, charset);
			return true;
			
		} catch (FileSystemException e) {
			throw new VfsRuntimeException(e);
		} 
	}
	
	
	/**
	 * @Description 从FTP上获取所有文件/文件夹
	 * @param localFilePath 本地文件路径(x:/xx/)
	 * @author caobin
	 */
	public void getAllFiles(String localFilePath){
		getAllFiles(localFilePath, DEFAULT_CHARSET, FileType.FILE_OR_FOLDER);
	}
	
	/**
	 * @Description 从FTP上获取所有文件/文件夹
	 * @param localFilePath 本地文件(夹)路径(x:/xx/)
	 * @param charset 字符集
	 * @param filetype 需要获取文件类型(文件|文件夹|文件或文件夹)
	 * @author caobin
	 */
	public void getAllFiles(String localFilePath, String charset, FileType filetype){
		try {
			FileObject[] ftpFOList = ftpFO.getChildren();
			if(ArrayUtils.isNotEmpty(ftpFOList)){
				for(FileObject fo : ftpFOList){
					if(filetype == FileType.FILE_OR_FOLDER || fo.getType() == filetype){
						if(!localFilePath.endsWith("/") && !localFilePath.endsWith("\\")){
							localFilePath = localFilePath.concat("/");
						}
						String localFilename = localFilePath.concat(new String(fo.getName().getBaseName().getBytes(ISO8859_CHARSET), charset));
						log.trace(LocaleUtils.getMessage(I01, localFilePath.concat(localFilename)));
	
						FileObject destFO = fsMgr.resolveFile(localFilename);
						//Selectors.SELECT_ALL： 文件，文件夹及所有子文件，子文件夹
						this.copyFrom(fo, destFO, Selectors.SELECT_ALL, ISO8859_CHARSET, charset);
					}
				}
			}
		} catch (FileSystemException e) {
			throw new VfsRuntimeException(e);
		} catch (UnsupportedEncodingException e) {
			throw new VfsRuntimeException(e);
		}
	}

}
