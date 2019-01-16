package com.bankguru;

import org.openqa.selenium.WebDriver;

import com.abstractPage.CommonFuntions;
import com.abstractPage.PageManagement;
import com.bankguru.ui.LoginPageUI;

public class LoginPage extends CommonFuntions {

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	public String getLoginPageUrl() {
		return getCurrentUrl();
	}

	public HomePage clickLogInButton() {
		waitForControlVisible(LoginPageUI.LOGIN_BTN);
		clickToElement(LoginPageUI.LOGIN_BTN);
		return PageManagement.getHomePage(driver);
	}

	public void inputEmail(String username) {
		waitForControlVisible(LoginPageUI.EMAIL_TXT);
		sendKeyToElement(LoginPageUI.EMAIL_TXT, username);
	}

	public void inputPassword(String password) {
		waitForControlVisible(LoginPageUI.PASSWORD_TXT);
		sendKeyToElement(LoginPageUI.PASSWORD_TXT, password);
	}

}
