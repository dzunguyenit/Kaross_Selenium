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

	// Testcase_010: Login with valid username and password
	@Test
	public void TC_010_LogInSuccessFully() throws Exception {
		// Assert at Home Page with url: ktvn-test.s3-website.us-east-1.amazonaws.com
		verifyEquals("http://ktvn-test.s3-website.us-east-1.amazonaws.com/", homePage.getUrlHomePage());
	}

	// Testcase_037: Back to Home Page when user click on label: [ Parent Portal ]
	@Test
	public void TC_037_ClickCancelFilter() throws Exception {
		// Assert at back to Home Page with url
		// ktvn-test.s3-website.us-east-1.amazonaws.com when user click cancel filter
		homePage.clickFilterButton();
		Thread.sleep(2000);
		homePage.clickCancelFilterButton();
		verifyEquals("http://ktvn-test.s3-website.us-east-1.amazonaws.com/", homePage.getUrlHomePage());
	}

	// Testcase_038: Back to Home Page when user click on label: [ Parent Portal ]
	@Test
	public void TC_038_ClickParentPortal() throws Exception {
		// Assert at back to Home Page with url
		// ktvn-test.s3-website.us-east-1.amazonaws.com when user click on label: [
		// Parent Portal ]
		homePage.clickParentPortal();
		Thread.sleep(2000);
		verifyEquals("http://ktvn-test.s3-website.us-east-1.amazonaws.com/", homePage.getUrlHomePage());
	}

	// Testcase_041: Check 25 records/page
	@Test
	public void TC_041_Check25RecordsOnePage() throws Exception {
		// Assert 25 records/page
		int sizeRecords = homePage.sizeRecord();
		verifyEquals(25, sizeRecords);
	}

	// Testcase_047: Check navigate the first page when user click icon <<
	@Test
	public void TC_047_CheckNavigateFirstPage() throws Exception {
		// Assert 25 records/page
		homePage.clickDoubleRightIcon();
		Thread.sleep(2000);
		homePage.clickDoubleLeftIcon();
		String firstPage = homePage.getActiveFirstPage();
		verifyEquals("active", firstPage);
	}

	// Testcase_050: Check Log out
	@Test
	public void TC_050_LogOut() throws Exception {
		// Check Log out successfully
		homePage.clickOnUserName();
		homePage.clickSignOut();
		verifyEquals("http://ktvn-test.s3-website.us-east-1.amazonaws.com/signin", homePage.getUrlLogInPage());
	}

	@AfterClass
	public void afterClass() {
		closeBrowser();
	}

}
