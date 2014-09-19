package com.brevy.commons.test.vfs;

import org.apache.commons.vfs2.FileType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import com.brevy.commons.vfs.VfsFactory;
import com.brevy.commons.vfs.core.ftp.FtpClient;

/**
 * @Description FtpClient测试案例
 * @author caobin
 * @date 2014-5-15
 * @version 1.0
 */
public class FtpClientTestcase {
	
	private transient Logger log = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * ftp的IP地址
	 */
	private String ipAddr = "192.168.12.216";
	
	/**
	 * ftp登录用户名
	 */
	private String username = "tech";
	
	/**
	 * ftp登录密码
	 */
	private String password = "tech123";
	
	/**
	 * 远程用户目录
	 */
	private String remoteUserDir = "/home/tech";
	
	/**
	 * 远程文件名
	 */
	private String remoteFilename1 = "信息流-招商银行(00104)20140220.txt";
	
	private String remoteFilename2 = "批量补单00124工商银行文件.txt";
	
	private String remoteFilename3 = "YUI测试包-v.2.4.7.jar";
	
	private String remoteFoldername = "测试";
	
	private String localFilename1 = "YUI测试包-2.4.7.jar";
	
	private String localFilename2 = "YUI测试文件夹";
	
	
	/**
	 * 本地文件路径
	 */
	private String localFilePath = "C:/COMMONS_TEST/VFS/FTP/";
	
	
	
	/**
	 * @Description 从本地上传文件到ftp
	 * @author caobin
	 */
	@Test
	public void putFile(){
		FtpClient ftpClient = VfsFactory.createFtpClient();		
		ftpClient.connect(ipAddr, username, password, remoteUserDir);
		ftpClient.putFile(localFilePath, localFilename1);
		ftpClient.close();
	}
	
	/**
	 * @Description 从本地上传文件(夹)到ftp
	 * @author caobin
	 */
	@Test
	public void putFiles_fileAndDir(){
		FtpClient ftpClient = VfsFactory.createFtpClient();		
		ftpClient.connect(ipAddr, username, password, remoteUserDir);
		ftpClient.putFiles(localFilePath, localFilename1, remoteFilename3,  "GBK", FileType.FILE_OR_FOLDER);	
		ftpClient.putFiles(localFilePath, localFilename2, localFilename2,  "GBK", FileType.FILE_OR_FOLDER);
		ftpClient.close();
	}
	
	/**
	 * @Description 从本地上传所有文件(夹)到ftp
	 * @author caobin
	 */
	@Test
	public void putAllFiles(){
		FtpClient ftpClient = VfsFactory.createFtpClient();		
		ftpClient.connect(ipAddr, username, password, remoteUserDir);
		ftpClient.putAllFiles(localFilePath);	
		ftpClient.close();
	}
	

	/**
	 * @Description 从ftp获取单个文件
	 * @author caobin
	 */
	@Test
	public void getFile(){
		FtpClient ftpClient = VfsFactory.createFtpClient();		
		for(String remoteFilename : new String[]{remoteFilename1, remoteFilename2}){
			ftpClient.connect(ipAddr, username, password, remoteUserDir);
			boolean success = ftpClient.getFile(remoteFilename, localFilePath);	
			log.info("Get File: {}", new Object[]{success});
		}
		ftpClient.close();
	}
	
	
	/**
	 * @Description 从ftp获取文件(夹)
	 * @author caobin
	 */
	@Test
	public void getFiles(){
		FtpClient ftpClient = VfsFactory.createFtpClient();		
		ftpClient.connect(ipAddr, username, password, remoteUserDir);
		boolean success = ftpClient.getFiles(remoteFoldername, localFilePath);	
		log.info("Get File: {}", new Object[]{success});
		ftpClient.close();
	}
	
	
	/**
	 * @Description 从ftp获取所有文件(夹)
	 * @author caobin
	 */
	@Test
	public void getAllFiles_fileAndDir(){
		FtpClient ftpClient = VfsFactory.createFtpClient();		
		ftpClient.connect(ipAddr, username, password, remoteUserDir);
		ftpClient.getAllFiles(localFilePath);	
		ftpClient.close();
	}
	
	
	/**
	 * @Description 从ftp获取所有文件
	 * @author caobin
	 */
	@Test
	public void getAllFiles_onlyFile(){
		FtpClient ftpClient = VfsFactory.createFtpClient();		
		ftpClient.connect(ipAddr, username, password, remoteUserDir);
		ftpClient.getAllFiles(localFilePath, "GBK", FileType.FILE);	
		ftpClient.close();
	}
	
	
	/**
	 * @Description 从ftp获取所有文件夹
	 * @author caobin
	 */
	@Test
	public void getAllFiles_onlyFolder(){
		FtpClient ftpClient = VfsFactory.createFtpClient();		
		ftpClient.connect(ipAddr, username, password, remoteUserDir);
		ftpClient.getAllFiles(localFilePath, "GBK", FileType.FOLDER);	
		ftpClient.close();
	}
}
