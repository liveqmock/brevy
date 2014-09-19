package com.brevy.commons.test.vfs;

import org.apache.commons.vfs2.FileType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import com.brevy.commons.vfs.VfsFactory;
import com.brevy.commons.vfs.core.sftp.SftpClient;

/**
 * @Description SFtpClient测试案例
 * @author caobin
 * @date 2014-5-19
 * @version 1.0
 */
public class SFtpClientTestcase {
private transient Logger log = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * ftp的IP地址
	 */
	private String ipAddr = "192.168.12.216";
	
	/**
	 * ftp登录用户名
	 */
	private String username = "root";
	
	/**
	 * ftp登录密码
	 */
	private String password = "qunimade";
	
	/**
	 * 远程用户目录
	 */
	private String remoteUserDir = "/home/test";
	
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
	private String localFilePath = "C:/COMMONS_TEST/VFS/SFTP/";
	
	
	/**
	 * @Description 从本地上传文件到sftp
	 * @author caobin
	 */
	@Test
	public void putFile(){
		SftpClient sftpClient = VfsFactory.createSftpClient();		
		sftpClient.connect(ipAddr, username, password, remoteUserDir);
		sftpClient.putFile(localFilePath, localFilename1);
		sftpClient.close();
	}
	
	/**
	 * @Description 从本地上传文件(夹)到sftp
	 * @author caobin
	 */
	@Test
	public void putFiles_fileAndDir(){
		SftpClient sftpClient = VfsFactory.createSftpClient();		
		sftpClient.connect(ipAddr, username, password, remoteUserDir);
		sftpClient.putFiles(localFilePath, localFilename1, remoteFilename3,  "GBK", FileType.FILE_OR_FOLDER);	
		sftpClient.putFiles(localFilePath, localFilename2, localFilename2,  "GBK", FileType.FILE_OR_FOLDER);
		sftpClient.close();
	}
	
	/**
	 * @Description 从本地上传所有文件(夹)到sftp
	 * @author caobin
	 */
	@Test
	public void putAllFiles(){
		SftpClient sftpClient = VfsFactory.createSftpClient();		
		sftpClient.connect(ipAddr, username, password, remoteUserDir);
		sftpClient.putAllFiles(localFilePath);	
		sftpClient.close();
	}
	
	
	/**
	 * @Description 从sftp获取单个文件
	 * @author caobin
	 */
	@Test
	public void getFile(){
		SftpClient sftpClient = VfsFactory.createSftpClient();		
		for(String remoteFilename : new String[]{remoteFilename1, remoteFilename2}){
			sftpClient.connect(ipAddr, username, password, remoteUserDir);
			boolean success = sftpClient.getFile(remoteFilename, localFilePath);	
			log.info("Get File: {}", new Object[]{success});
		}
		sftpClient.close();
	}
	
	/**
	 * @Description 从sftp获取文件(夹)
	 * @author caobin
	 */
	@Test
	public void getFiles(){
		SftpClient sftpClient = VfsFactory.createSftpClient();		
		sftpClient.connect(ipAddr, username, password, remoteUserDir);
		boolean success = sftpClient.getFiles(remoteFoldername, localFilePath);	
		log.info("Get File: {}", new Object[]{success});
		sftpClient.close();
	}
	
	
	
	/**
	 * @Description 从sftp获取所有文件(夹)
	 * @author caobin
	 */
	@Test
	public void getAllFiles(){
		SftpClient sftpClient = VfsFactory.createSftpClient();		
		sftpClient.connect(ipAddr, username, password, remoteUserDir);
		sftpClient.getAllFiles(localFilePath);	
		sftpClient.close();
	}
	
	/**
	 * @Description 从sftp获取所有文件
	 * @author caobin
	 */
	@Test
	public void getAllFiles_onlyFile(){
		SftpClient sftpClient = VfsFactory.createSftpClient();		
		sftpClient.connect(ipAddr, username, password, remoteUserDir);
		sftpClient.getAllFiles(localFilePath, "GBK", FileType.FILE);	
		sftpClient.close();
	}
	
	
	/**
	 * @Description 从sftp获取所有文件夹
	 * @author caobin
	 */
	@Test
	public void getAllFiles_onlyFolder(){
		SftpClient sftpClient = VfsFactory.createSftpClient();		
		sftpClient.connect(ipAddr, username, password, remoteUserDir);
		sftpClient.getAllFiles(localFilePath, "GBK", FileType.FOLDER);	
		sftpClient.close();
	}
}
