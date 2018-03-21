package com.util;

import java.util.regex.Pattern;

public class UtilApp {

	private static boolean isTimeStampValid(String timeStamp) {
		boolean isFeb = timeStamp.split("/")[0].matches("(0?[2])");
		System.out.println("Is Feb :: " + isFeb);
		if (isFeb) {
			boolean isValidFebDate = timeStamp.split("/")[1].matches("(0?[1-9]|1[1-9]|2[0-8])");
			System.out.println("Is Valid Feb Date :: " + isValidFebDate);
			if (!isValidFebDate) return false;
		}
		return TIMESTAMP_PATTERN.matcher(timeStamp).matches();
	}

	private static Pattern TIMESTAMP_PATTERN = Pattern.compile("(0?[1-9]|1[012])/(0?[1-9]|[1-2][0-9]|3[0-1])/"
			+ "([1-9][0-9]{3}) ([0-1]?[0-9]|2[0-3]):([0-5]?[0-9]):([0-5]?[0-9])");

	public static void main(String[] args) {
		String timeStamp = "02/20/2018 14:30:11";
		/*for (int i = 0; i < 61; i++) {
			String tempTimeStamp = MessageFormat.format(timeStamp, i);
			System.out.println(tempTimeStamp + " :: Is given time stamp is valid :: " + isTimeStampValid(tempTimeStamp));
		}*/
		System.out.println(timeStamp + " :: Is given time stamp is valid :: " + isTimeStampValid(timeStamp));
	}
}
