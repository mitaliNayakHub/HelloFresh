package com.hellofresh.reporter;

import org.apache.log4j.Logger;

public class TestLogger {
	// Initialize Log4j logs

	private static Logger Log = Logger.getLogger(TestLogger.class.getName());

	//To run at start of tests
	public static void startTestCase(String testCaseName) {
		Log.info("---------X-------X---------X     " + "****" + testCaseName + " S T A R T ****" + "   X---------X-------X---------");
	}

	// Print in the end of test
	public static void endTestCase(String testCaseName) {
		Log.info("---------X-------X---------X     " + "**** " + testCaseName + " E N D ****" + "   X---------X-------X---------");
	}

	// Methods for possible cases

	public static void info(String message) {
		Log.info(message);
	}

	public static void warn(String message) {
		Log.warn(message);
	}

	public static void error(String message) {
		Log.error(message);
	}

	public static void fatal(String message) {
		Log.fatal(message);
	}

	public static void debug(String message) {
		Log.debug(message);
	}

}
