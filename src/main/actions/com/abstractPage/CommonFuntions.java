package com.abstractPage;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

public class CommonFuntions {

	protected WebDriver driver;

	public CommonFuntions(WebDriver driver) {
		this.driver = driver;
	}

	private int timeouts = 20;
	String jsColorBorderElement = "arguments[0].style='border: 2px solid; border-color: red'";

	// Web Browser
	public void openUrl(String url) {
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	public String getTitle() {
		return driver.getTitle();
	}

	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

	public String getPageSource() {
		return driver.getPageSource();
	}

	public void backToPage() {
		driver.navigate().back();
	}

	public void forwardToPage() {
		driver.navigate().forward();
	}

	public void refreshPage() {
		driver.navigate().refresh();
	}

	public void waitForIEBrowser(int Timeouts) {
		try {
			Thread.sleep(Timeouts);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// Web Element
	public void clickToElement(String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		((JavascriptExecutor) driver).executeScript(jsColorBorderElement, element);
		element.click();
	}

	public void clickToElement(String locator, String value) {
		String dynamicLocator = String.format(locator, value);
		WebElement element = driver.findElement(By.xpath(dynamicLocator));
		element.click();
	}

	public void clickToElement(String locator, String value1, String value2) {
		String dynamicLocator = String.format(locator, value1, value2);
		WebElement element = driver.findElement(By.xpath(dynamicLocator));
		element.click();
	}

	public String getTextElement(String locator, String value) {
		String dynamicLocator = String.format(locator, value);
		return getTextElement(dynamicLocator);
	}

	// Clear and sendkey
	public void sendKeyToElement(String locator, String value) {
		WebElement element = driver.findElement(By.xpath(locator));
		element.clear();
		element.sendKeys(value);
	}

	public void sendKeyToElement2(String locator, String value) {
		WebElement element = driver.findElement(By.xpath(locator));
		element.sendKeys(value);
	}

	public void selectItemInDropdown(String locator, String value) {
		Select element = new Select(driver.findElement(By.xpath(locator)));
		element.selectByVisibleText(value);
	}

	public void selectItemInDropdownSpecial(String locatorDropdown, String locator, String value) {
		WebElement dropdown = driver.findElement(By.xpath(locatorDropdown));
		dropdown.click();
		List<WebElement> list = driver.findElements(By.xpath(locator));
		for (WebElement element : list) {
			if (element.getText().equalsIgnoreCase(value)) {
				element.click();
				break;
			}
		}

	}

	public String getFirstItemSelected(String locator) {
		Select element = new Select(driver.findElement(By.xpath(locator)));
		return element.getFirstSelectedOption().getText();
	}

	public String getAtribute(String locator, String attribute) {
		WebElement element = driver.findElement(By.xpath(locator));
		return element.getAttribute(attribute);
	}

	public String getTextElement(String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		return element.getText();
	}

	public int getSize(String locator) {
		List<WebElement> element = driver.findElements(By.xpath(locator));
		return element.size();
	}

	public void uncheckTheCheckbox(String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		if (element.isSelected())
			element.click();
	}

	public boolean isControlDisplayed(String locator, String value) {
		String dynamicLocator = String.format(locator, value);
		WebElement element = driver.findElement(By.xpath(dynamicLocator));
		return element.isDisplayed();
	}

	public boolean isControlDisplayed(String locator, String value1, String value2) {
		String dynamicLocator = String.format(locator, value1, value2);
		WebElement element = driver.findElement(By.xpath(dynamicLocator));
		return element.isDisplayed();
	}

	public boolean isControlDisplayed(String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		return element.isDisplayed();
	}

	public boolean isControlSelected(String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		return element.isSelected();
	}

	public boolean isControlEnabled(String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		return element.isEnabled();
	}
	// Alert

	public void acceptAlert() {
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}

	public void cancelAlert() {
		Alert alert = driver.switchTo().alert();
		alert.dismiss();
	}

	public String getTextAlert() {
		Alert alert = driver.switchTo().alert();
		return alert.getText();
	}

	public void sendKeyAlert(String value) {
		Alert alert = driver.switchTo().alert();
		alert.sendKeys(value);
	}

	// Windows
	public void switchWindowByID(String parent) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String childWindows : allWindows) {
			if (!childWindows.equals(parent)) {
				driver.switchTo().window(childWindows);
				break;
			}
		}
	}

	public void switchWindowByTitle(String title) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String childWindows : allWindows) {
			driver.switchTo().window(childWindows);
			String childTitle = driver.getTitle();
			if (childTitle.equals(title)) {
				break;
			}
		}
	}

	// Bonus
	public boolean closeAllWindowsWithoutParent(String parent) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String childWindows : allWindows) {
			if (!childWindows.equals(parent)) {
				driver.switchTo().window(childWindows);
				driver.close();
			}
		}
		driver.switchTo().window(parent);

		if (driver.getWindowHandles().size() == 1)
			return true;
		else
			return false;
	}

	public String getWindowParentID() {
		return driver.getWindowHandle();
	}

	// Iframe
	public void switchToIframe(String locator) {
		WebElement iframe = driver.findElement(By.xpath(locator));
		driver.switchTo().frame(iframe);
	}

	public void switchToDefaultContent() {
		driver.switchTo().defaultContent();
	}

	// Actions
	public void doubleClick(String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		Actions action = new Actions(driver);
		action.doubleClick(element).perform();
	}

	public void hoverMouse(String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		Actions action = new Actions(driver);
		action.moveToElement(element).perform();
	}

	public void hoverMouse(String locator, String value1, String value2) {
		String dynamicLocator = String.format(locator, value1, value2);
		WebElement element = driver.findElement(By.xpath(dynamicLocator));
		Actions action = new Actions(driver);
		action.moveToElement(element).perform();
	}

	public void rightClick(String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		Actions action = new Actions(driver);
		action.contextClick(element).perform();
	}

	public void dragAndDrop(String locatorFrom, String locatorTarget) {
		WebElement dragFrom = driver.findElement(By.xpath(locatorFrom));
		WebElement target = driver.findElement(By.xpath(locatorTarget));
		Actions builder = new Actions(driver);
		Action dragAndDropAction = builder.clickAndHold(dragFrom).moveToElement(target).release(target).build();
		dragAndDropAction.perform();
	}

	// Bonus
	public void clickAndHold(int itemFrom, int itemTarget, String locator) {
		List<WebElement> listItems = driver.findElements(By.xpath(locator));
		Actions action = new Actions(driver);
		action.clickAndHold(listItems.get(itemFrom)).clickAndHold(listItems.get(itemTarget)).click().perform();
		action.release();
	}

	// Key Press
	public void keyDownElement(String locator, Keys pressKeyDown) {
		WebElement element = driver.findElement(By.xpath(locator));
		Actions action = new Actions(driver);
		action.keyDown(element, pressKeyDown);
	}

	public void keyUpElement(String locator, Keys pressKeyUp) {
		WebElement element = driver.findElement(By.xpath(locator));
		Actions action = new Actions(driver);
		action.keyUp(element, pressKeyUp);
	}

	public void sendKeyPress(String locator, Keys key) {
		WebElement element = driver.findElement(By.xpath(locator));
		element.sendKeys(key);
	}

	public void clearAndSendKeyPress(String locator, Keys key) {
		WebElement element = driver.findElement(By.xpath(locator));
		element.clear();
		element.sendKeys(key);
	}

	// Upload
	public void uploadFile(String locator, String firePath) {
		WebElement element = driver.findElement(By.xpath(locator));
		element.sendKeys(firePath);
	}

	// Javascript
	public Object executeJavascriptToBrowser(String javaSript) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			return js.executeScript(javaSript);
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}

	public Object executeJavascriptToElement(String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			return js.executeScript("arguments[0].click();", element);
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}

	public Object executeJavascriptToElement(String locator, String value1, String value2) {
		String dynamicLocator = String.format(locator, value1, value2);
		WebElement element = driver.findElement(By.xpath(dynamicLocator));
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			return js.executeScript("arguments[0].click();", element);
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}

	public Object scrollToBottomPage() {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			return js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}

	public Object scrollToElement(String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			return js.executeScript("arguments[0].scrollIntoView(true);", element);
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}

	// Bonus
	public void highlightElement(String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(jsColorBorderElement, element);
	}

	// Bonus
	public Object removeAttributeInDOM(String locator, String attribute) {
		WebElement element = driver.findElement(By.xpath(locator));
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			return js.executeScript("arguments[0].removeAttribute('" + attribute + "');", element);
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}

	// Wait
	public void waitForControlPresence(String locator) {
		WebDriverWait wait = new WebDriverWait(driver, timeouts);
		By by = By.xpath(locator);
		wait.until(ExpectedConditions.presenceOfElementLocated(by));
	}

	public void waitForControlVisible(String locator) {
		WebDriverWait wait = new WebDriverWait(driver, timeouts);
		By by = By.xpath(locator);
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}

	public void waitForIframeVisible(String locator) {
		WebDriverWait wait = new WebDriverWait(driver, timeouts);
		By by = By.xpath(locator);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(by));
	}

	public void waitForControlVisible(String locator, String value) {
		String dynamicLocator = String.format(locator, value);
		WebDriverWait wait = new WebDriverWait(driver, timeouts);
		By by = By.xpath(dynamicLocator);
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}

	public void waitForControlVisible(String locator, String value1, String value2) {
		String dynamicLocator = String.format(locator, value1, value2);
		WebDriverWait wait = new WebDriverWait(driver, timeouts);
		By by = By.xpath(dynamicLocator);
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}

	public void waitForControlClickable(String locator) {
		WebDriverWait wait = new WebDriverWait(driver, timeouts);
		By by = By.xpath(locator);
		wait.until(ExpectedConditions.elementToBeClickable(by));
	}

	public void waitForControlInvisible(String locator) {
		WebDriverWait wait = new WebDriverWait(driver, timeouts);
		By by = By.xpath(locator);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
	}

	public void waitForAlertPresence() {
		WebDriverWait wait = new WebDriverWait(driver, timeouts);
		wait.until(ExpectedConditions.alertIsPresent());
	}

	public boolean sortElementAcsending(String locator) {

		List<WebElement> list = driver.findElements(By.xpath(locator));
		int length = list.size();
		for (int i = 0; i < length; i++) {
			if (Integer.parseInt(list.get(i).getText()) <= Integer.parseInt(list.get(i + 1).getText())) {
				return true;
			}
		}
		return false;
	}

	public boolean sortElementDescending(String locator) {

		List<WebElement> list = driver.findElements(By.xpath(locator));
		int length = list.size();
		for (int i = 0; i < length; i++) {
			if (Integer.parseInt(list.get(i).getText()) >= Integer.parseInt(list.get(i + 1).getText())) {
				return true;
			}
		}
		return false;
	}

	public void swapElement(String locator) {

		List<WebElement> list = driver.findElements(By.xpath(locator));
		int temp;
		int position1 = Integer.parseInt(list.get(0).getText());
		int position2 = Integer.parseInt(list.get(1).getText());
		temp = position1;
		position1 = position2;
		position2 = temp;

	}

	protected boolean verifyEquals(Object actual, Object expected, boolean flag) {
		boolean pass = true;
		if (flag == false) {
			try {
				Assert.assertEquals(actual, expected);
			} catch (Throwable e) {
				pass = false;
				Reporter.getCurrentTestResult().setThrowable(e);
			}
		} else {
			Assert.assertEquals(actual, expected);
		}
		return pass;
	}

	protected boolean verifyEquals(Object actual, Object expected) {
		return verifyEquals(actual, expected, false);
	}

}
