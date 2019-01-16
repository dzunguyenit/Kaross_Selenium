package com.kaross;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.abstractPage.PageManagement;
import com.bankguru.HomePage;
import com.bankguru.LoginPage;

import commons.AbstractTest;

public class Testcase extends AbstractTest {
	WebDriver driver;
	String email, password;
	private LoginPage loginPage;
	private HomePage homePage;
	String pathData = "/Data/";
	String userPath = System.getProperty("user.dir");

	@Parameters({ "browser", "url", "version" })
	@BeforeClass
	public void beforeClass(String browser, String url, String version) {

		driver = openMultiBrowser(browser, url, version);
		loginPage = PageManagement.getLoginPage(driver);
		email = "admin@test.com";
		password = "test123";
		loginPage.inputEmail(email);
		loginPage.inputPassword(password);
		homePage = loginPage.clickLogInButton();

	}

	@Test
	public void TC_01_NameCannotEmpty() throws Exception {
		// Assert at Home Page with url:
		// http://ktvn-test.s3-website.us-east-1.amazonaws.com/
		Thread.sleep(2000);
		verifyEquals("http://ktvn-test.s3-website.us-east-1.amazonaws.com/", homePage.getUrlHomePage());

	}

	@AfterClass
	public void afterClass() {
		closeBrowser();
	}

}
