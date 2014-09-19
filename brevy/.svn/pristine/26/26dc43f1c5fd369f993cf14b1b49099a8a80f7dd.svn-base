package com.brevy.fw.common.support.web.view;

import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.Map;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.Validate;
import org.springframework.web.servlet.view.AbstractView;

/**
 * @Description 图片视图
 * @author caobin
 * @date 2013-2-26
 * @version 1.0
 */
public class ImageView extends AbstractView {
	
	
	
	public ImageView(){
		//候选view需要从中获取匹配视图
		setContentType(DEFAULT_CONTENT_TYPE);
	}

	@Override
	protected void prepareResponse(HttpServletRequest request, HttpServletResponse response) {
		setResponseContentType(request, response);
		if (this.disableCaching) {
			response.addHeader("Pragma", "no-cache");
			response.addHeader("Cache-Control", "no-cache, no-store, max-age=0");
			response.addDateHeader("Expires", 1L);
		}
	}
	
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ImageEncoder imageEncoder = (ImageEncoder)model.get(IMAGE_ENCODER_KEY);
		BufferedImage bufferedImage = (BufferedImage)model.get(BUFFERED_IMAGE_KEY);
		Validate.notNull(imageEncoder, "image encoder is required");
		Validate.notNull(bufferedImage, "buffered image is required");	
		response.setContentType("image/".concat(imageEncoder.toString().toLowerCase()));
		imageOutput(imageEncoder, bufferedImage, response);
	}
	
	/**
	 * @Description 图像输出
	 * @param imageEncoder 图像编码
	 * @param bufferedImage 图像缓冲
	 * @param response
	 * @author caobin
	 * @throws Exception 
	 */
	private void imageOutput(ImageEncoder imageEncoder, BufferedImage bufferedImage, HttpServletResponse response) throws Exception{
		Iterator<ImageWriter> writerIter = ImageIO.getImageWritersByFormatName(imageEncoder.toString());
		if(writerIter.hasNext()){
			ImageWriter imageWriter = writerIter.next();
			ServletOutputStream out = response.getOutputStream();
			ImageOutputStream imageOutputStream = ImageIO.createImageOutputStream(out);
			imageWriter.setOutput(imageOutputStream);
		    imageWriter.write(null, new IIOImage(bufferedImage, null, null), null);
		    imageOutputStream.close();
		    imageWriter.dispose();
		}else{
			throw new Exception(String.format("cannot get specified image writer for %s from system", imageEncoder.toString()));
		}
	}
	
	/**
	 * @Description 图片编码
	 * @author caobin
	 * @date 2013-7-15
	 * @version 1.0
	 */
	public static enum ImageEncoder{
		
		JPEG,
		
		PNG;
	}
	
	public static final String DEFAULT_CONTENT_TYPE = "image/*";
	
	private boolean disableCaching = true;
	
	public static final String IMAGE_ENCODER_KEY = "ImageEncoder";
	
	public static final String BUFFERED_IMAGE_KEY = "BufferedImage";

	/**
	 * @param disableCaching the disableCaching to set
	 */
	public void setDisableCaching(boolean disableCaching) {
		this.disableCaching = disableCaching;
	}
}
