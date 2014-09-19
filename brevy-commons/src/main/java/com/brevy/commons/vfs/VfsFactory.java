package com.brevy.commons.vfs;

import java.io.File;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.URL;

import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.FileSystemOptions;
import org.apache.commons.vfs2.impl.StandardFileSystemManager;
import org.apache.commons.vfs2.provider.sftp.SftpFileSystemConfigBuilder;

import com.brevy.commons.vfs.core.compress.Compressor;
import com.brevy.commons.vfs.core.compress.impl.GzCompressor;
import com.brevy.commons.vfs.core.compress.impl.TarCompressor;
import com.brevy.commons.vfs.core.compress.impl.TarGzCompressor;
import com.brevy.commons.vfs.core.compress.impl.ZipCompressor;
import com.brevy.commons.vfs.core.ftp.FtpClient;
import com.brevy.commons.vfs.core.sftp.SftpClient;

/**
 * @Description vfs工厂类
 * @author caobin
 * @date 2014-5-15
 * @version 1.0
 */
public class VfsFactory {
	
	
	/**
	 * @Description 创建ZIP压缩/解压缩器
	 * @return
	 * @author caobin
	 */
	public static Compressor createZipCompressor(){
		try {
			StandardFileSystemManager mgr = new StandardFileSystemManager();
			mgr.setConfiguration(loadProviders());
			return new ZipCompressor(mgr);
		} catch (FileSystemException e) {
			throw new VfsRuntimeException(e);
		}
	}
	
	
	/**
	 * @Description 创建TAR打包/解包器
	 * @return
	 * @author caobin
	 */
	public static Compressor createTarCompressor(){
		try {
			StandardFileSystemManager mgr = new StandardFileSystemManager();
			mgr.setConfiguration(loadProviders());
			return new TarCompressor(mgr);
		} catch (FileSystemException e) {
			throw new VfsRuntimeException(e);
		}
	}
	
	/**
	 * @Description 创建TAR.GZ压缩/解压缩器
	 * @return
	 * @author caobin
	 */
	public static Compressor createTarGzCompressor(){
		try {
			StandardFileSystemManager mgr = new StandardFileSystemManager();
			mgr.setConfiguration(loadProviders());
			return new TarGzCompressor(mgr);
		} catch (FileSystemException e) {
			throw new VfsRuntimeException(e);
		}
	}
	
	/**
	 * @Description 创建GZ压缩/解压缩器
	 * @return
	 * @author caobin
	 */
	public static Compressor createGzCompressor(){
		try {
			StandardFileSystemManager mgr = new StandardFileSystemManager();
			mgr.setConfiguration(loadProviders());
			
			final Compressor gzCompressor = new GzCompressor(mgr);
			
			Compressor proxiedGzCompressor = (Compressor)Proxy.newProxyInstance(VfsFactory.class.getClassLoader(), new Class[]{Compressor.class}, new InvocationHandler() {			
				@Override
				public Object invoke(Object proxy, Method method, Object[] args)
						throws Throwable {
					if(args != null && args[0] instanceof File){
						File fileOrDir = (File)args[0];
						if(fileOrDir.isDirectory()){
							throw new VfsRuntimeException(AbstractVfs.E02, fileOrDir.getAbsoluteFile());
						}
					}		
					return method.invoke(gzCompressor, args);
				}
			});
			return proxiedGzCompressor;
		} catch (FileSystemException e) {
			throw new VfsRuntimeException(e);
		}
	}

	/**
	 * @Description 创建FTP客户端
	 * @return
	 * @author caobin
	 */
	public static FtpClient createFtpClient(){
		try {
			StandardFileSystemManager mgr = new StandardFileSystemManager();
			mgr.setConfiguration(loadProviders());
			return new FtpClient(mgr);
		} catch (FileSystemException e) {
			throw new VfsRuntimeException(e);
		}
	}
	
	
	/**
	 * @Description 创建SFTP客户端
	 * @return
	 * @author caobin
	 */
	public static SftpClient createSftpClient(){
		try {
			FileSystemOptions opts = new FileSystemOptions();
			SftpFileSystemConfigBuilder.getInstance().setStrictHostKeyChecking(opts, "no");
			//非常重要
			SftpFileSystemConfigBuilder.getInstance().setRootURI(opts, "/");
			SftpFileSystemConfigBuilder.getInstance().setUserDirIsRoot(opts, false);
			SftpFileSystemConfigBuilder.getInstance().setTimeout(opts, 10000);	
			return createSftpClient(opts);
		} catch (FileSystemException e) {
			throw new VfsRuntimeException(e);
		}
	}
	
	/**
	 * @Description 创建SFTP客户端
	 * @param opts 文件系统选项
	 * @return
	 * @author caobin
	 */
	public static SftpClient createSftpClient(FileSystemOptions opts){
		try {
			StandardFileSystemManager mgr = new StandardFileSystemManager();
			mgr.setConfiguration(loadProviders());
			return new SftpClient(mgr, opts);
		} catch (FileSystemException e) {
			throw new VfsRuntimeException(e);
		}
	}
	
	/**
	 * @Description 加载Providers
	 * @return
	 * @author caobin
	 */
	private static URL loadProviders(){
		return Thread.currentThread().getContextClassLoader().getResource("_commons/vfs/vfs-providers.xml");
	}
}
