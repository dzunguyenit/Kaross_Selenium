package com.abstractPage;

import org.openqa.selenium.WebDriver;

import com.bankguru.HomePage;
import com.bankguru.LoginPage;

public class PageManagement {
	private static LoginPage loginPage;
	private static HomePage homePage;

	public static LoginPage getLoginPage(WebDriver driver) {
		return (loginPage == null) ? new LoginPage(driver) : loginPage;
	}

	public static HomePage getHomePage(WebDriver driver) {
		return (homePage == null) ? new HomePage(driver) : homePage;
	}

}