package com.brevy.commons.lang;

import java.io.File;
import java.io.RandomAccessFile;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

/**
 * @Description 文件类型区分工具类
 * @author caobin
 * @date 2014-5-21
 * @version 1.0
 */
public class FileTypeUtils {
	
	public final static String E01 = "lang.filetype.e01";
	
	public final static String E02 = "lang.filetype.e02";

	/**
	 * @Description 检测文件并获取文件类型
	 * @param file 待检测文件
	 * @return
	 * @author caobin
	 */
	public static FileType testFileType(File file){
		//获取文件后缀名
		String fileExtension = FilenameUtils.getExtension(file.getName().toUpperCase());
		
		FileType type = null;
		try{
			type = FileType.valueOf(fileExtension);
		}catch(IllegalArgumentException e){
			throw new RuntimeException(LocaleUtils.getMessage(E01, fileExtension));
		}
		
		if(!type.getFeature().equals(hexdump4(file))){
			throw new RuntimeException(LocaleUtils.getMessage(E02));
		}
		return type;
	}

	/**
	 * @Description 读取文件开头4字节数据并转换为十六进制形式字符串
	 * @param file 待读取文件
	 * @return
	 * @author caobin
	 */
	private static String hexdump4(File file){
		byte[] feature = new byte[4];
		RandomAccessFile raf = null;
		try {
			raf = new RandomAccessFile(file, "r");
			raf.read(feature);
			return Hex.encodeHexString(feature);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally{
			IOUtils.closeQuietly(raf);
		}
	}
	
	public static enum FileType {
		/**
		 * zip
		 */
		ZIP ("zip", "504b0304"),
		
		/**
		 * jar
		 */
		JAR ("jar", "504b0304"),
		
		/**
		 * rar
		 */
		RAR ("rar", "52617221"),
		
		/**
		 * Java class
		 */
		CLASS ("class", "cafebabe"),
		
		
		
		/**
		 * Microsoft Word 97-2003
		 */
		DOC ("xls", "d0cf11e0"),
		
		/**
		 * Microsoft Excel 97-2003
		 */
		XLS ("xls", "d0cf11e0"),
		
		/**
		 * Microsoft Word 2003+
		 */
		DOCX ("docx", "504b0304"),
		
		/**
		 * Microsoft Excel 2003+
		 */
		XLSX ("xlsx", "504b0304"),
		
		/**
		 * Adobe pdf
		 */
		PDF ("pdf", "25504446");

		private final String extension;
		
		private final String feature;
		
		FileType(String extension, String feature){
			this.extension = extension;
			this.feature = feature;
		}

		/**
		 * 获取扩展名
		 * @return the extension
		 */
		public String getExtension() {
			return extension;
		}

		/**
		 * 获取特性码
		 * @return the feature
		 */
		public String getFeature() {
			return feature;
		}
	}
}
