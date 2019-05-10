/**
 * 
 */
package com.KTOC.TRB.testautomation.ObjectRepository;

import org.openqa.selenium.By;

/**
 * @author CON_SVIJAY02
 *
 */
public class pg_SF_Login {
	
	//Salesforce Login
	public static By txt_userName = By.id("username");
	public static By txt_password = By.id("password");
	public static By btn_login = By.id("Login");

	//Salesforce Login (re-work)
	public static String txt_userName_New = "username";
	public static String txt_password_New = "password";
	public static String btn_login_New = "Login";
}