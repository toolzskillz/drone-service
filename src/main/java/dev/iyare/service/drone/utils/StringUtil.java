package dev.iyare.service.drone.utils;

public class StringUtil
{
	public static String generateUniqueId(long lengthOfId)
	{
		String value = "1";
		for (int i = 1; i <= lengthOfId; i++)
		{
			value = value.concat("0");
		}
		value.concat("L");

		long a = (long) (Math.random() * Long.parseLong(value));
		String uId = String.valueOf(a);

		if (uId.length() < 6)
		{
			uId = uId.concat("9");
		}

		return uId;
	}

}
