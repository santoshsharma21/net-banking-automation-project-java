/**
 * 
 */
package com.webbanking.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

/**
 * @author Santosh Sharma
 *
 */
public class ReadConfig {

	public static Properties pro;

	public ReadConfig() {
		File fileSource = new File(System.getProperty("user.dir") + "./configuration/config.properties");

		try {
			FileInputStream ip = new FileInputStream(fileSource);
			pro = new Properties();
			pro.load(ip);

		} catch (Exception e) {
			System.out.println("Exception is " + e.getMessage());
		}
	}

	public String baseUrl() {
		String url = pro.getProperty("url");
		return url;
	}

	public String uName() {
		String uname = pro.getProperty("user_name");
		return uname;
	}
	
}
