package com.brevy.commons.test.vfs;

import java.io.File;

import org.testng.annotations.Test;

import com.brevy.commons.vfs.VfsFactory;
import com.brevy.commons.vfs.core.compress.Compressor;

/**
 * @Description CompressedHelper测试案例
 * @author caobin
 * @date 2014-5-26
 * @version 1.0
 */
public class CompressorTestcase {
	
	private String unzipFilePath = "c:/zip/005设计组.zip";
	
	private String zipFilePath = "c:/zip";
	
	@Test
	public void unzip(){
		Compressor compressor = VfsFactory.createZipCompressor();
		compressor.decompress(new File(unzipFilePath));
		//compressor.decompress(new File("c:/zip/005设计组.zip"), "/备付金", new File("c:/zip/aaa"), FileType.FILE_OR_FOLDER);		
		compressor.close();
	}
	
	@Test
	public void unzip2() throws Exception{
		//Compressor compressor = VfsFactory.createZipCompressor();
		//compressor.decompress(new File("e:/3.0_DATA_20140826.zip"), File.createTempFile("BREVY", "0827"));
		System.out.println(">>>>>>>>> " + System.getProperty("java.io.tmpdir"));
	}
	
	@Test
	public void zipFile(){
		Compressor compressor = VfsFactory.createZipCompressor();
		compressor.compress(new File(zipFilePath), new File("c:/aaa/bbb/测试zip/打包.zip"));
		//compressor.compress(new File(zipFilePath));
		compressor.close();
	}
	
	@Test
	public void zipFiles(){
		Compressor compressor = VfsFactory.createZipCompressor();
		compressor.compress(new File[]{new File("c:/dos2unix.exe"),new File("c:/zip/备付金")}, new File("c:/aaa/bbb/测试zip/打包2.zip"));
		//compressor.compress(new File(zipFilePath));
		compressor.close();
	}
	
	
	@Test
	public void untar(){
		Compressor compressor = VfsFactory.createTarCompressor();
		compressor.decompress(new File("c:/t11t/测试/tars.tar"));
		//"/tar/备付金"
		//compressor.decompress(new File("c:/tar测试.tar"), "/tar/备付金", new File("c:/tar备付金"), FileType.FILE_OR_FOLDER);		
		compressor.close();
	}
	
	@Test
	public void tarFile(){
		Compressor compressor = VfsFactory.createTarCompressor();
		compressor.compress(new File("c:/tar"), new File("c:/t11t/测试/tars.tar"), "GBK");
		compressor.close();
	}
	
	@Test
	public void tarFiles(){
		Compressor compressor = VfsFactory.createTarCompressor();
		compressor.compress(new File[]{new File("c:/dos2unix.exe"),new File("c:/zip/备付金")}, new File("c:/t11t/测试/打包2.tar"));
		compressor.close();
	}
	
	
	@Test
	public void untgz(){
		Compressor compressor = VfsFactory.createTarGzCompressor();
		compressor.decompress(new File("c:/t11t/测试/tars.tar.gz"));
		//"/tar/备付金"
		//compressor.decompress(new File("c:/tar测试.tar"), "/tar/备付金", new File("c:/tar备付金"), FileType.FILE_OR_FOLDER);		
		compressor.close();
	}
	
	@Test
	public void tgzFile(){
		Compressor compressor = VfsFactory.createTarGzCompressor();
		compressor.compress(new File("c:/tar"), new File("c:/t11t/测试/tars.tar.gz"), "GBK");
		compressor.close();
	}
	
	@Test
	public void tgzFiles(){
		Compressor compressor = VfsFactory.createTarGzCompressor();
		compressor.compress(new File[]{new File("c:/dos2unix.exe"), new File("c:/zip/备付金")}, new File("c:/t11t/测试/tars.files.tar.gz"));
		compressor.close();
	}
	
	@Test
	public void unGz(){
		Compressor compressor = VfsFactory.createGzCompressor();
		compressor.decompress(new File("c:/gzip/日期更新.txt.gz"), new File("c:/gzip/"));
		compressor.close();
	}
	
	
	@Test
	public void gzFile(){
		Compressor compressor = VfsFactory.createGzCompressor();
		compressor.compress(new File("c:/tar/日期更新.txt"), new File("c:/gzip/日期更新.txt.gz"));
		compressor.close();
	}
	
	@Test
	public void gzFile2(){
		Compressor compressor = VfsFactory.createGzCompressor();
		compressor.compress(new File("F:/temp/code/HintFilterX509KeyManager.java"));
		compressor.close();
	}
}
