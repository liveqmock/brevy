package com.brevy.fw.common.support.captcha;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import org.springframework.beans.factory.InitializingBean;

import com.brevy.fw.common.support.captcha.gimpy.CustomGimpyFactory;
import com.octo.captcha.CaptchaException;
import com.octo.captcha.CaptchaFactory;
import com.octo.captcha.component.image.backgroundgenerator.BackgroundGenerator;
import com.octo.captcha.component.image.fontgenerator.FontGenerator;
import com.octo.captcha.component.image.textpaster.TextPaster;
import com.octo.captcha.component.image.wordtoimage.ComposedWordToImage;
import com.octo.captcha.component.word.wordgenerator.WordGenerator;
import com.octo.captcha.engine.CaptchaEngineException;
import com.octo.captcha.engine.image.ImageCaptchaEngine;
import com.octo.captcha.image.ImageCaptcha;
import com.octo.captcha.image.ImageCaptchaFactory;


/**
 * @Description 自定义图片验证码引擎
 * (由于继承ListImageCaptchaEngine，会导致在注入前就buildInitialFactories，所以此类基于ListImageCaptchaEngine重写)
 * @author caobin
 * @date 2013-2-26
 * @version 1.0
 */
@SuppressWarnings("unchecked")
public class CustomImageCaptchaEngine extends ImageCaptchaEngine implements
		InitializingBean {

	
	public boolean addFactory(ImageCaptchaFactory factory) {
		return factories.add(factory);
	}

	public void addFactories(ImageCaptchaFactory factories[]) {
		for (int i = 0; i < factories.length; i++)
			this.factories.add(factories[i]);

	}

	public CaptchaFactory[] getFactories() {
		return (CaptchaFactory[]) (CaptchaFactory[]) factories
				.toArray(new CaptchaFactory[factories.size()]);
	}

	public void setFactories(CaptchaFactory factories[])
			throws CaptchaEngineException {
		if (factories == null || factories.length == 0)
			throw new CaptchaEngineException(
					"impossible to set null or empty factories");
		@SuppressWarnings("rawtypes")
		ArrayList tempFactories = new ArrayList();
		for (int i = 0; i < factories.length; i++) {
			if ((com.octo.captcha.image.ImageCaptchaFactory.class)
					.isAssignableFrom(factories[i].getClass()))
				throw new CaptchaEngineException(
						"This factory is not an image captcha factory "
								+ factories[i].getClass());
			tempFactories.add(factories[i]);
		}

		this.factories = tempFactories;
	}

	public ImageCaptchaFactory getImageCaptchaFactory() {
		return (ImageCaptchaFactory) factories.get(myRandom.nextInt(factories
				.size()));
	}

	public ImageCaptcha getNextImageCaptcha() {
		return getImageCaptchaFactory().getImageCaptcha();
	}

	public ImageCaptcha getNextImageCaptcha(Locale locale) {
		return getImageCaptchaFactory().getImageCaptcha(locale);
	}

	private void checkFactoriesSize() {
		if (factories.size() == 0)
			throw new CaptchaException(
					"This gimpy has no factories. Please initialize it properly with the buildInitialFactory() called by the constructor or the addFactory() mehtod later!");
		else
			return;
	}

	protected void buildInitialFactories() {
		this.addFactory(new CustomGimpyFactory(wordGenerator,
				new ComposedWordToImage(fontGenerator, backgroundGenerator,
						textPaster)));
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void afterPropertiesSet() throws Exception {
		factories = new ArrayList();
		myRandom = new SecureRandom();
		buildInitialFactories();
		checkFactoriesSize();
	}

	@SuppressWarnings("rawtypes")
	List factories;
	
	private Random myRandom;

	/**
	 * 背景生成器
	 */
	private BackgroundGenerator backgroundGenerator;

	/**
	 * 字符生成器
	 */
	private WordGenerator wordGenerator;

	/**
	 * 文字色彩及个数生成器
	 */
	private TextPaster textPaster;

	/**
	 * 文字大小及字体生成器
	 */
	private FontGenerator fontGenerator;

	public void setBackgroundGenerator(BackgroundGenerator backgroundGenerator) {
		this.backgroundGenerator = backgroundGenerator;
	}

	public void setWordGenerator(WordGenerator wordGenerator) {
		this.wordGenerator = wordGenerator;
	}

	public void setTextPaster(TextPaster textPaster) {
		this.textPaster = textPaster;
	}

	public void setFontGenerator(FontGenerator fontGenerator) {
		this.fontGenerator = fontGenerator;
	}

}
