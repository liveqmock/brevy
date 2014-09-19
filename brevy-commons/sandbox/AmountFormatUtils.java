package com.ips.commons.support.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * @Description 金额格式化工具类
 * @author caobin
 * @date 2013-3-21
 * @version 1.0
 */
public class AmountFormatUtils {
	
	/**
	 * @Description 格式化金额为9,999,999.99的形式
	 * @param inputAmt
	 * @return
	 * @author caobin
	 */
	public static String formatAmount(BigDecimal inputAmt){
		DecimalFormat df = new DecimalFormat("#,##0.00");
		return df.format(inputAmt);
	}
	
	/**
	 * @Description 格式化金额为9,999,999.99的形式
	 * @param inputAmt
	 * @return
	 * @author caobin
	 */
	public static String formatAmount(String inputAmt){
		DecimalFormat df = new DecimalFormat("#,##0.00");
		return df.format(new BigDecimal(inputAmt));
	}

}
