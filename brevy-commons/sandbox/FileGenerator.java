package com.ips.commons.support.util;

import java.io.OutputStreamWriter;

import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ips.bmps.support.constant.Constants;

/**
 * @Description CSV生成
 * @author caobin
 * @date 2013-3-14
 * @version 1.0
 */
public class FileGenerator {

	private static Logger log = LoggerFactory.getLogger(FileGenerator.class);

	public static final char DELIMITER_COMMA_SEPERATED = ',';

	public static void generateFile(HttpServletResponse response, FileInfo fileInfo) throws Exception {
		String fileType = fileInfo.getFileType();
		if (Constants.EXCEL.equals(fileType)) {
			generateExcelFile(response, fileInfo);
		} else if ((Constants.CSV.equals(fileType))) {
			generateCsvFile(response, fileInfo);
		} else {
			generateTxtFile(response, fileInfo);
		}
	}

	/**
	 * @Description 生成xls
	 * @param response
	 * @param charset
	 * @param fileName
	 * @param data
	 * @throws Exception
	 * @author wangying
	 */
	public static void generateExcelFile(HttpServletResponse response, FileInfo fileInfo) throws Exception {
		// 设置response头
		WritableWorkbook wbook = null;
		WritableSheet sheet = null;
		try {
			response.reset();
			response.setContentType("text/html;charset=UTF-8");
			response.setContentType("application/x-msdownload");
			response.setHeader("Content-disposition",
					"attachment; filename=".concat(fileInfo.getFileName()).concat(".").concat(fileInfo.getFileType()));
			wbook = Workbook.createWorkbook(response.getOutputStream());
			sheet = wbook.createSheet("sheet1", 0);
			String[][] data = fileInfo.getFileDate();
			int row = 0;
			int[] widthArray = null;
			if (data.length > 0) {
				widthArray = new int[data[0].length];
			}

			WritableFont wf = new WritableFont(WritableFont.TIMES, 10);
			WritableCellFormat wcfF = new WritableCellFormat(wf);

			String split = fileInfo.getSplit();

			if (split != null) {
				//招行特殊格式
				// 下面开始添加单元格
				for (String[] strs : data) {
					int col = 0;
					StringBuffer buffer = new StringBuffer();
					for (String str : strs) {
						buffer.append(str);
						buffer.append(split);
					}

					buffer = buffer.deleteCharAt(buffer.length() - 1);
					widthArray[col] = buffer.length();

					// 这里需要注意的是，在Excel中，第一个参数表示列，第二个表示行
					Label labelC = new Label(col, row, buffer.toString(), wcfF);
					// 将生成的单元格添加到工作表中
					sheet.addCell(labelC);
					row++;
				}
			} else {
				// 下面开始添加单元格
				for (String[] strs : data) {
					int col = 0;
					for (String str : strs) {
						int length = str.length();
						if (widthArray[col] < length) {
							widthArray[col] = length;
						}
						// 这里需要注意的是，在Excel中，第一个参数表示列，第二个表示行
						Label labelC = new Label(col, row, str, wcfF);
						// 将生成的单元格添加到工作表中
						sheet.addCell(labelC);
						col++;
					}
					row++;
				}
			}

			int i = 0;
			for (int width : widthArray) {
				sheet.setColumnView(i, width + width / 3);
				i++;
			}

			// 从内存中写入文件中
			wbook.write();
		} catch (Exception e) {
			log.error("error:" , e);
		} finally {
			if (wbook != null)
				wbook.close();
		}

	}

	/**
	 * @Description 生成csv
	 * @param response
	 * @param charset
	 * @param fileName
	 * @param data
	 * @throws Exception
	 * @author wangying
	 */
	public static void generateCsvFile(HttpServletResponse response, FileInfo fileInfo) throws Exception {
		OutputStreamWriter writer = null;
		// 设置response头
		try {
			response.reset();
			response.setContentType("text/html;charset=UTF-8");
			response.setContentType("application/x-msdownload");
			response.setHeader("Content-disposition",
					"attachment; filename=".concat(fileInfo.getFileName()).concat(".").concat(fileInfo.getFileType()));
			writer = new OutputStreamWriter(response.getOutputStream(), "gbk");
			String[][] data = fileInfo.getFileDate();
			String split = fileInfo.getSplit();
			for (String[] strArray : data) {
				StringBuffer buffer = new StringBuffer();
				for (String str : strArray) {
					if (str != null) {
						// buffer.append("\"");
						buffer.append(str);
						// buffer.append("\"");
						buffer.append(split);
					}
				}
				buffer = buffer.deleteCharAt(buffer.length() - 1).append("\n");
				writer.write(buffer.toString());
			}

		} catch (Exception e) {
			log.error("error:" , e);
			throw e;
		} finally {
			if (writer != null) {
				writer.close();
			}
		}

	}

	/**
	 * @Description 生成txt
	 * @param response
	 * @param charset
	 * @param fileName
	 * @param data
	 * @throws Exception
	 * @author wangying
	 */
	public static void generateTxtFile(HttpServletResponse response, FileInfo fileInfo) throws Exception {
		OutputStreamWriter writer = null;
		// 设置response头
		try {
			response.reset();
			response.setContentType("text/html;charset=UTF-8");
			response.setContentType("application/x-msdownload");
			response.setHeader("Content-disposition",
					"attachment; filename=".concat(fileInfo.getFileName()).concat(".").concat(fileInfo.getFileType()));
			writer = new OutputStreamWriter(response.getOutputStream());
			String[][] data = fileInfo.getFileDate();
			String split = fileInfo.getSplit();
			for (String[] strArray : data) {
				StringBuffer buffer = new StringBuffer();
				for (String str : strArray) {
					if (str != null) {
						buffer.append(str);
						buffer.append(split);
					}
				}
				buffer = buffer.deleteCharAt(buffer.length() - 1).append("\r\n");
				writer.write(buffer.toString());
			}

		} catch (Exception e) {
			log.error("error:" , e);
			throw e;
		} finally {
			if (writer != null) {
				writer.close();
			}
		}

	}
}
