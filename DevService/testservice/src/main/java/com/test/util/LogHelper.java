package com.test.util;

public class LogHelper {

	public static void info(String message)
	{
		tgtools.util.LogHelper.info("", message, "testservice");
	}
	
	public static void error(String message,Throwable ex)
	{
		tgtools.util.LogHelper.error("", message, "testservice", ex);
	}
}
