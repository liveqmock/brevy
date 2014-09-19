package com.brevy.fw.common.support.web.controller;

import static com.brevy.fw.common.support.web.view.ImageView.BUFFERED_IMAGE_KEY;
import static com.brevy.fw.common.support.web.view.ImageView.IMAGE_ENCODER_KEY;

import java.awt.image.BufferedImage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.brevy.fw.common.support.web.view.ImageView.ImageEncoder;
import com.octo.captcha.service.image.ImageCaptchaService;

/**
 * @Description 验证码控制器
 * @author caobin
 * @date 2013-7-11
 * @version 1.0
 */
@Controller
public class CaptchaController extends BaseController{


	/**
	 * 输出验证码
	 * @Description
	 * @param request
	 * @param response
	 * @param session
	 * @param modelMap
	 * @return
	 * @throws Exception
	 * @author caobin
	 */
	@RequestMapping("/captcha/displayCodeImage")
	public ModelAndView handleCaptcha(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {	
		String captchaId = session.getId();
		log.debug("captcha id: {}", new Object[]{captchaId});
		BufferedImage challenge = captchaService.getImageChallengeForID(captchaId, request.getLocale());	
		ModelAndView mav = createMav();
		mav.addObject(IMAGE_ENCODER_KEY, ImageEncoder.JPEG);
		mav.addObject(BUFFERED_IMAGE_KEY, challenge);
		return mav;
	}

	
	
	@Autowired
	private ImageCaptchaService captchaService;

	public void setCaptchaService(ImageCaptchaService captchaService) {
		this.captchaService = captchaService;
	}
}
