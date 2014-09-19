package com.brevy.core.shiro.service.impl;

import java.awt.Color;
import java.awt.Font;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import nl.captcha.Captcha;
import nl.captcha.Captcha.Builder;
import nl.captcha.backgrounds.TransparentBackgroundProducer;
import nl.captcha.servlet.CaptchaServletUtil;
import nl.captcha.text.producer.DefaultTextProducer;
import nl.captcha.text.producer.TextProducer;
import nl.captcha.text.renderer.DefaultWordRenderer;
import nl.captcha.text.renderer.WordRenderer;

import org.apache.shiro.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.brevy.core.shiro.service.CaptchaService;
import com.brevy.core.shiro.util.ShiroUtils;
import com.brevy.core.support._enum.CaptchaStyle;

/**
 * @Description 验证码Service类
 * @author caobin
 * @date 2013-12-19
 * @version 1.0
 */
@Service
public class DefaultCaptchaService implements CaptchaService {

	@Override
	public void generateCaptcha(CaptchaStyle captchaStyle, HttpServletResponse response) {
		Captcha captcha = null;
		switch(captchaStyle){
			case STYLE_DEFAULT:
				captcha = generateDefaultStyleCaptcha();
				break;
		}
		CaptchaServletUtil.writeImage(response, captcha.getImage());
		Session session = ShiroUtils.getSession();
		log.trace("binding captcha to session with session id: [{}]", new Object[]{session.getId()});
		session.setAttribute(session.getId(), captcha);	
	}



	@Override
	public boolean validateCaptchaForID(String actual){
		if(actual == null){
			log.error("actual captcha is null");
			return false;
		}
		Serializable sessionID = ShiroUtils.getSession().getId();
		Captcha captcha = (Captcha)ShiroUtils.getSession().getAttribute(sessionID);
		if(captcha != null && actual.equalsIgnoreCase(captcha.getAnswer())){
			return true;
		}
		log.error("actual captcha : [{}]", new Object[]{actual});
		return false;
	}
	
	
	/**
	 * @Description 生成默认样式验证码
	 * @return
	 * @author caobin
	 */
	private Captcha generateDefaultStyleCaptcha(){	
		TextProducer textProducer = new DefaultTextProducer();
		List<Color> colors = new ArrayList<Color>(2);
		colors.add(Color.BLACK);
		colors.add(Color.BLUE);
		List<Font> fonts = new ArrayList<Font>(2);
		fonts.add(new Font("Courier", Font.ITALIC | Font.BOLD, 52));
		fonts.add(new Font("Arial", Font.ITALIC | Font.BOLD, 52));
		WordRenderer wordRenderer = new DefaultWordRenderer(colors, fonts);
		return(new Builder(200, 50)).addText(textProducer, wordRenderer).gimp().addNoise().addBackground(new TransparentBackgroundProducer()).build();
	}

	private transient Logger log = LoggerFactory.getLogger(this.getClass());
}
