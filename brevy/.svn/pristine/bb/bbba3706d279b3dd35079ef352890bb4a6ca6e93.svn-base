package com.brevy.fw.common.support.captcha.backgroundGenerator;

import java.security.SecureRandom;
import java.util.Random;

import com.octo.captcha.component.image.backgroundgenerator.BackgroundGenerator;

public abstract class CustomAbstractBackgroundGenerator implements
		BackgroundGenerator {

	protected CustomAbstractBackgroundGenerator(Integer width, Integer height) {
		this.height = 100;
		this.width = 200;
		myRandom = new SecureRandom();
		this.width = width == null ? this.width : width.intValue();
		this.height = height == null ? this.height : height.intValue();
	}

	public int getImageHeight() {
		return height;
	}

	public int getImageWidth() {
		return width;
	}

	private int height;
	private int width;
	Random myRandom;
}
