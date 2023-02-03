package dev.iyare.service.drone.utils;

import java.util.regex.Pattern;

public class RegExPatternUtil
{
	public static String MED_NAME = "[a-zA-Z0-9\\s.-[._]]+"; // allowed only letters, numbers, ‘-‘, ‘_’
	public static String MED_CODE = "[A-Z0-9\\s._]+"; // allowed only upper case letters, underscore and numbers’

	public static boolean matchMedName(String input)
	{
		return Pattern.matches(MED_NAME, input);
	}

	public static boolean matchMedCode(String input)
	{
		return Pattern.matches(MED_CODE, input);
	}
}
