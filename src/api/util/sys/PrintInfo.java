/**
 * 
 */
package api.util.sys;

import java.util.Date;

import api.Constants;

/**
 * @author Nguyen Tien Duong
 * @since Jul 25, 2018
 * 
 */
public class PrintInfo {

	/**
	 * 
	 */
	public static void out(String msg) {
		Date timeNow = new java.util.Date();
		String appFullName = "[" +Constants.APP_NAME + " " + Constants.APP_VER +"]";
		System.out.println(timeNow + " " + appFullName + ":" + msg);
	}

}
