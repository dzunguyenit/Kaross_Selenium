package com.bankguru;

import org.openqa.selenium.WebDriver;

import com.abstractPage.CommonFuntions;
import com.abstractPage.PageManagement;
import com.bankguru.ui.HomePageUI;
import com.bankguru.ui.LoginPageUI;

public class HomePage extends CommonFuntions {

	public HomePage(WebDriver driver) {
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

	public void clickFilterButton() {
		waitForControlVisible(HomePageUI.FILTER_BTN);
		clickToElement(HomePageUI.FILTER_BTN);
	}

	public void clickParentPortal() {
		waitForControlVisible(HomePageUI.PARENTPORTAL_LBL);
		executeJavascriptToElement(HomePageUI.PARENTPORTAL_LBL);
	}

	public void clickCancelFilterButton() {
		waitForControlVisible(HomePageUI.CANCEL_BTN);
		executeJavascriptToElement(HomePageUI.CANCEL_BTN);
	}

	public void clickDoubleLeftIcon() {
		waitForControlVisible(HomePageUI.DOUBLE_LEFT_ICON);
		clickToElement(HomePageUI.DOUBLE_LEFT_ICON);
	}

	public void clickSignOut() {
		waitForControlVisible(HomePageUI.SIGNOUT_LBL);
		clickToElement(HomePageUI.SIGNOUT_LBL);
	}

	public void clickDoubleRightIcon() {
		waitForControlVisible(HomePageUI.DOUBLE_RIGHT_ICON);
		clickToElement(HomePageUI.DOUBLE_RIGHT_ICON);
	}

	public void clickOnUserName() {
		waitForControlVisible(HomePageUI.USERNAME_LBL);
		clickToElement(HomePageUI.USERNAME_LBL);
	}

	public void inputEmail(String username) {
		waitForControlVisible(LoginPageUI.EMAIL_TXT);
		sendKeyToElement(LoginPageUI.EMAIL_TXT, username);
	}

	public void inputPassword(String password) {
		waitForControlVisible(LoginPageUI.PASSWORD_TXT);
		sendKeyToElement(LoginPageUI.PASSWORD_TXT, password);
	}

	public String getUrlHomePage() {
		waitForControlVisible(HomePageUI.PARENTPORTAL_LBL);
		return getCurrentUrl();
	}

	public String getUrlLogInPage() {
		waitForControlVisible(LoginPageUI.EMAIL_TXT);
		return getCurrentUrl();
	}

	public int sizeRecord() {
		waitForControlVisible(HomePageUI.LIST_SIZE_RECORD);
		return getSize(HomePageUI.LIST_SIZE_RECORD);
	}

	public String getActiveFirstPage() {
		waitForControlVisible(HomePageUI.FIRSTPAGE_LBL);
		return getAtribute(HomePageUI.FIRSTPAGE_LBL, "class");
	}

}
