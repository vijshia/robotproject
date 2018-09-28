package com.KTOC.TRB.testautomation.Utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.python.icu.impl.Assert;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;

public class KTOCTRBUtils {
	private static final int TIME_OUT_IN_SECONDS = 300;
	protected static WebDriver driver;
	public static final String ROBOT_LIBRARY_SCOPE = "GLOBAL";

	public static WebElement element = null;
	// public static String
	// ScreenshotPath="/Users/roja/git/nemo-testautomation/src/main/java/com/nemo/testautomation/Screenshots/Screenshots-Failure/
	// ";
	public static String CurrentDir = System.getProperty("user.dir");
	public static String ScreenshotPath = CurrentDir.concat("/Screenshots/Screenshots-Failure/\\");
	// public static WebDriverWait wait = new WebDriverWait(driver,
	// TIME_OUT_IN_SECONDS);

	// private final static Logger logger =
	// Logger.getLogger(String.valueOf(KTOCNEBUtils.class));
	// Launch Browser

	// Custom Reporting
	/*
	 * public void StartReporting(String Testcase){ DateTimeFormatter dtf =
	 * DateTimeFormatter.ofPattern("HH:mm:ss"); LocalDateTime now =
	 * LocalDateTime.now(); String time = Testcase+dtf.format(now).toString();
	 * report=new ExtentReports("C:\\Report\\"+Testcase+".html");
	 * logger=report.startTest(Testcase); }
	 * 
	 * public void EndReport(){ report.endTest(logger); report.flush(); }
	 * 
	 * 
	 * public void MainReport(String Reportname) { System.out.println(Reportname); }
	 * 
	 * public void StartReporting(String Testcase) { DateTimeFormatter dtf =
	 * DateTimeFormatter.ofPattern("HH:mm:ss"); LocalDateTime now =
	 * LocalDateTime.now(); String time = Testcase + dtf.format(now); }
	 * 
	 * public void EndReport() { }
	 */

	// Instantiate the webdriver and launch the KCC browser
	public void LaunchBrowser(String os) throws InterruptedException {
		if (os.equalsIgnoreCase("ios")) {
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.setBinary(new File(CurrentDir));
			String headless = System.getProperty("headless");
			if (headless != null && headless.equalsIgnoreCase("true")) {
				// String PROXY = "23.23.23.23:3128"; //# IP:PORT or HOST:PORT
				chromeOptions.addArguments("--headless");
				chromeOptions.addArguments("--no-proxy-server");
				// chromeOptions.addArguments("--proxy-server=http://%s' % PROXY");
				chromeOptions.addArguments("--ignore-certificate-errors");
				chromeOptions.addArguments("--disable-gpu");
				chromeOptions.addArguments("window-size=2000,2000");
			}
				driver = new ChromeDriver(chromeOptions);
			} else if (os.equalsIgnoreCase("windows")) {
				DesiredCapabilities CHDes = DesiredCapabilities.chrome();
				ChromeOptions CHOpt = new ChromeOptions();
				CHDes.setCapability(ChromeOptions.CAPABILITY, CHOpt);
				File CHPath = new File(CurrentDir + "\\chromedriver.exe"); // C:\Backups\Vijay S\DownloadFolder\chromedriver_win32 (2.42)
				System.setProperty("webdriver.chrome.driver", CHPath.getAbsolutePath());
				driver = new ChromeDriver();
		}
		driver.manage().window().maximize();
	}

	public void SwitchToFrame(String FrameId) {
		driver.switchTo().frame(FrameId);
	}

//Find Elements
	public static WebElement FindTheElement(String locatorType, String locatorValue) {
		// String TextLink = null;
		if (locatorType.equalsIgnoreCase("cssSelector")) {
			element = driver.findElement(By.cssSelector(locatorValue));
		} else if (locatorType.equalsIgnoreCase("xpath")) {
			element = driver.findElement(By.xpath(locatorValue));
		} else if (locatorType.equalsIgnoreCase("id")) {
			element = driver.findElement(By.id(locatorValue));
		}
		/*
		 * else if (locatorType.equalsIgnoreCase("linkText")){
		 * driver.findElement(By.linkText(TextLink)); }
		 */
		return element;
	}

	// Wait Till Element To Be Clickable
	protected static void WaitTillElementToBeClickable(String locatorType, String locatorValue) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, TIME_OUT_IN_SECONDS);

			if (locatorType.equalsIgnoreCase("cssSelector")) {
				wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(locatorValue)));
			} else if (locatorType.equalsIgnoreCase("xpath")) {
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locatorValue)));
			} else if (locatorType.equalsIgnoreCase("id")) {
				wait.until(ExpectedConditions.elementToBeClickable(By.id(locatorValue)));
			}
		} catch (Exception e) {
			System.out.println("Webdriver Locator Error" + e);
		}
	}

	// Wait Till Element To Be Clickable with values
	protected static void WaitTillClickable(String locatorType, String locatorValue) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10000);

			if (locatorType.equalsIgnoreCase("cssSelector")) {
				wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(locatorValue)));
			} else if (locatorType.equalsIgnoreCase("xpath")) {
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locatorValue)));
			} else if (locatorType.equalsIgnoreCase("id")) {
				wait.until(ExpectedConditions.elementToBeClickable(By.id(locatorValue)));
			}
		} catch (Exception e) {
			System.out.println("Webdriver Locator Error" + e);
		}
	}

	// Wait And Click Element Directly
	protected void WaitAndClickElementDirectly(WebElement element) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 100);
			wait.until(ExpectedConditions.elementToBeClickable(element)).click();
			;
		} catch (Exception e) {
			Assert.fail("Unable to wait for the element clickable more than 100secs" + e);
		}
	}

	// Wait Till Element To Be Displayed
	protected static void WaitTillElementToBeDisplayed(String locatorType, String locatorVaue) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, TIME_OUT_IN_SECONDS);

			if (locatorType.equalsIgnoreCase("cssSelector")) {
				wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(locatorVaue)));
			} else if (locatorType.equalsIgnoreCase("xpath")) {
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locatorVaue)));
			} else if (locatorType.equalsIgnoreCase("id")) {
				wait.until(ExpectedConditions.presenceOfElementLocated(By.id(locatorVaue)));
			}
		} catch (Exception e) {
			System.out.println("Webdriver Locator Error" + e);
		}
	}

	protected void WaitTillElementDisplayed(String locatorType, String locatorVaue) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 100);

			if (locatorType.equalsIgnoreCase("cssSelector")) {
				wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(locatorVaue)));
			} else if (locatorType.equalsIgnoreCase("xpath")) {
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locatorVaue)));
			} else if (locatorType.equalsIgnoreCase("id")) {
				wait.until(ExpectedConditions.presenceOfElementLocated(By.id(locatorVaue)));
			}
		} catch (Exception e) {
			System.out.println("Webdriver Locator Error" + e);
		}
	}

	// Wait And Click On Element
	protected static void WaitAndClickOnElement(String locatorType, String locatorVaue) {
		WebDriverWait wait = new WebDriverWait(driver, TIME_OUT_IN_SECONDS);

		if (locatorType.equalsIgnoreCase("cssSelector")) {
			wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(locatorVaue))).click();
		} else if (locatorType.equalsIgnoreCase("xpath")) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locatorVaue))).click();
		} else if (locatorType.equalsIgnoreCase("id")) {
			wait.until(ExpectedConditions.elementToBeClickable(By.id(locatorVaue))).click();
		}
	}

	// Wait And Click On Element with values
	protected void WaitAndClick(String locatorType, String locatorValue) {
		WebDriverWait wait = new WebDriverWait(driver, 100);

		if (locatorType.equalsIgnoreCase("cssSelector")) {
			wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(locatorValue))).click();
		} else if (locatorType.equalsIgnoreCase("xpath")) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locatorValue))).click();
		} else if (locatorType.equalsIgnoreCase("id")) {
			wait.until(ExpectedConditions.elementToBeClickable(By.id(locatorValue))).click();
		}
	}

	// Wait till the element is visible
	protected void WaitTillElementVisible(String locatorType, String locatorValue) {
		// int seconds = 100;
		WebDriverWait wait = new WebDriverWait(driver, TIME_OUT_IN_SECONDS);
		if (locatorType.equalsIgnoreCase("cssSelector")) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locatorValue)));
		} else if (locatorType.equalsIgnoreCase("xpath")) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue)));
		} else if (locatorType.equalsIgnoreCase("id")) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorValue)));
		}
	}

	protected static void WaitTillVisible(String locatorType, String locatorValue) {
		// int seconds = 100;
		WebDriverWait wait = new WebDriverWait(driver, 10000);
		if (locatorType.equalsIgnoreCase("cssSelector")) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locatorValue)));
		} else if (locatorType.equalsIgnoreCase("xpath")) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue)));
		} else if (locatorType.equalsIgnoreCase("id")) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorValue)));
		}
	}

	protected void WaitAndClear(String locatorValue) {
		WebDriverWait wait = new WebDriverWait(driver, TIME_OUT_IN_SECONDS);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue))).clear();

	}

	protected void FluentWaitClick(WebElement element) {
		new WebDriverWait(driver, TIME_OUT_IN_SECONDS).ignoring(StaleElementReferenceException.class)
				.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
		/*
		 * Wait<WebDriver> stubbornWait = new FluentWait<WebDriver>(driver)
		 * .withTimeout(30, TimeUnit.SECONDS) .pollingEvery(5, TimeUnit.SECONDS)
		 * .ignoring(NoSuchElementException.class)
		 * .ignoring(StaleElementReferenceException.class);
		 * 
		 * element = stubbornWait.until(new Function<WebDriver, WebElement>() { public
		 * WebElement apply(WebDriver driver) { return driver.findElement(By.id("foo"));
		 * } });
		 */
	}

	protected void FluentWait(WebElement element) {
		new WebDriverWait(driver, TIME_OUT_IN_SECONDS).ignoring(StaleElementReferenceException.class)
				.until(ExpectedConditions.elementToBeClickable(element));
	}

	protected void RightClick(String locatorType, String locatorValue) {
		WebElement element = getWebElement(locatorType, locatorValue);
		Actions action = null;
		if (element != null) {
			action = new Actions(driver).contextClick(element);
		}
		if (action != null) {
			action.build().perform();
		}
	}

	public void checkPageIsReady() {
		try {

			JavascriptExecutor js = (JavascriptExecutor) driver;
			// Initially bellow given if condition will check ready state of page.
			if (js.executeScript("return document.readyState").toString().equals("complete")) {
				System.out.println("Page Is loaded.");
				return;
			}

			// This loop will rotate for 25 times to check If page Is ready after every 1
			// second.
			// You can replace your value with 25 If you wants to Increase or decrease wait
			// time.
			for (int i = 0; i < 25; i++) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				}
				// To check page ready state.
				if (js.executeScript("return document.readyState").toString().equals("complete")) {
					break;
				}
			}
		} catch (Exception e) {
			Assert.fail("The page is not ready");
		}
	}

	public void SelectOptionWithIndex(String AutoCompleteTeBoxlocator, String ListItemsLocator, int IndexToSelect) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, TIME_OUT_IN_SECONDS);
			WebElement autoOptions = driver.findElement(By.xpath(AutoCompleteTeBoxlocator));
			wait.until(ExpectedConditions.visibilityOf(autoOptions));
			List<WebElement> optionsToSelect = autoOptions.findElements(By.xpath(ListItemsLocator));
			if (IndexToSelect <= optionsToSelect.size()) {
				System.out.println("Trying to select based on index: " + IndexToSelect);
				optionsToSelect.get(IndexToSelect).click();
			}
		} catch (NoSuchElementException e) {
			System.out.println(e.getStackTrace());
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
	}

	public void SelectOptionWithText(String AutoCompleteTeBoxlocator, String ListItemsLocator, String TextToSelect) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, TIME_OUT_IN_SECONDS);
			WebElement autoOptions = driver.findElement(By.xpath(AutoCompleteTeBoxlocator));
			wait.until(ExpectedConditions.visibilityOf(autoOptions));
			List<WebElement> optionsToSelect = autoOptions.findElements(By.xpath(ListItemsLocator));
			for (WebElement option : optionsToSelect) {
				if (option.getText().equals(TextToSelect)) {
					System.out.println("Trying to select: " + TextToSelect);
					option.click();
					break;
				}
			}

		} catch (NoSuchElementException e) {
			System.out.println(e.getStackTrace());
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
	}

	protected static void EnterValues(String locator, String locatorValue, String text) {

		element = getWebElement(locator, locatorValue);

		if (element != null) {
			element.sendKeys(text);
		}
		/*
		 * if(element.isEnabled()) { element.sendKeys(text); logger.log(LogStatus.PASS,
		 * "The Text"+text+"is typed successfully"); }else { logger.log(LogStatus.FAIL,
		 * "Error typing the Text:"+ text); }
		 */
		/*
		 * if(element.getText()!=text) { throw new FailToEnterValues(); }
		 */
	}

	private static WebElement getWebElement(String locator, String locatorValue) {
		if (locator.equalsIgnoreCase("cssSelector")) {
			element = driver.findElement(By.cssSelector(locatorValue));
		} else if (locator.equalsIgnoreCase("xpath")) {
			element = driver.findElement(By.xpath(locatorValue));
		} else if (locator.equalsIgnoreCase("id"))
			element = driver.findElement(By.id(locatorValue));
		return element;
	}

	protected void EnterValuesByIndex(String locator, String locatorValue, String text, int indexvalue)
			throws InterruptedException {
		// WebDriverWait wait = new WebDriverWait(driver, TIME_OUT_IN_SECONDS);
		element = getWebElement(locator, locatorValue, indexvalue);
		if (element != null) {
			element.sendKeys(text);
			element.sendKeys(Keys.UP);
		}

	}

	protected void EnterValuesNoIndex(String locator, String locatorValue, String text) throws InterruptedException {
		// WebDriverWait wait = new WebDriverWait(driver, TIME_OUT_IN_SECONDS);
		element = getWebElement(locator, locatorValue);
		if (element != null) {
			element.sendKeys(text);
			// element.sendKeys(Keys.UP);
		}

	}

	public void EnterValuesUsingJavaScript(String idlocator, String text) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("document.getElementById('idlocator').value = text;");
	}

	// Enter values character by character
	public static void EnterTextbyChar(String locator, String locatorValue, String text, int indexvalue)
			throws Exception {
		try {
			element = getWebElement(locator, locatorValue, indexvalue);
			if (element != null && element.isEnabled()) {
				element.clear();
				for (int i = 0; i < text.length(); i++) {
					char c = text.charAt(i);
					String s = String.valueOf(c);
					element.sendKeys(s);
				}
				element.sendKeys(Keys.DOWN);
			} else {
			}
		} catch (Exception e) {
			Assert.fail("Entering text failed");
		}
	}

	private static WebElement getWebElement(String locator, String locatorValue, int indexvalue) {
		if (locator.equalsIgnoreCase("cssSelector")) {
			// wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(locatorValue)));
			element = driver.findElements(By.cssSelector(locatorValue)).get(indexvalue - 1);
		} else if (locator.equalsIgnoreCase("xpath")) {
			/*
			 * WebDriverWait wait = new WebDriverWait(driver, 500);
			 * wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locatorValue)));
			 */
			element = driver.findElements(By.xpath(locatorValue)).get(indexvalue - 1);

		} else if (locator.equalsIgnoreCase("id")) {
			/*
			 * WebDriverWait wait = new WebDriverWait(driver, 500);
			 * wait.until(ExpectedConditions.elementToBeClickable(By.id(locatorValue)));
			 */
			element = driver.findElements(By.id(locatorValue)).get(indexvalue - 1);
			// element = driver.findElement(By.id(CustomerPageData.CustomerSearch));

		}
		return element;
	}

	// Enter Values using Robot Class
	public void EnterTextByKeyboard(String locator, String locatorValue, String text, int indexvalue) throws Exception {
		Robot robot = new Robot();
		element = getWebElement(locator, locatorValue, indexvalue);
		if (element != null && element.isEnabled()) {
			element.clear();
			for (char c : text.toCharArray()) {
				int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);
				if (KeyEvent.CHAR_UNDEFINED == keyCode) {
					throw new RuntimeException("Key code not found for character '" + c + "'");
				}
				robot.keyPress(keyCode);
				robot.delay(200);
				robot.keyRelease(keyCode);
				robot.delay(200);
			}
			robot.keyPress(KeyEvent.VK_TAB);
		} else {
			System.out.println(text);
		}
	}

	public void PressEnter() throws AWTException {
		Robot robot = new Robot();
		// WebElement element = getWebElement(locator, locatorValue);
		robot.keyPress(KeyEvent.VK_ENTER);

	}

	// Scroll Up To An Element
	protected static void ScrollUptoElement(String locator, String LocatorValue) {
		try {
			element = getWebElement(locator, LocatorValue);
			if (element.isDisplayed()) {
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

			}
		} catch (Exception e) {
			Assert.fail("Scrolling is not successful");
		}
	}

	protected static void ScrollToElementDirectly(WebElement element) throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}

	protected void ScrollToElementDirectlyAndWait(WebElement element) throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element).wait();
	}

	public void Display(String str) {
		System.out.println(str);
	}

	public void ScrollPageDown() {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	public static void ScrollPageUP() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("scroll(0, -10000);");
	}

	public void ScrollRight() {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(10000,0)", "");
		// driver.executeScript("window.scrollByLines(2),"")
	}

	public void ScrollLeft() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(-3000,0)", "");
	}

	protected void ScrollAndClickOnElement(String locator, String LocatorValue) {
		element = getWebElement(locator, LocatorValue);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		if (element != null) {
			element.click();
		}
	}

	protected static void ClickOnElement(String locator, String locatorValue) {
		element = getWebElement(locator, locatorValue);
		if (element != null) {
			element.click();
		}

	}

	protected void ClickOnElementByIndex(String locator, String locatorValue, int indexvalue) {
		element = getWebElement(locator, locatorValue, indexvalue);
		if (element != null) {
			element.click();
		}

	}

	public void ClearTextField(String xpath) {
		driver.findElement(By.xpath(xpath)).clear();
	}

	protected static void SelectDropDownValues(String locator, String locatorValue, String value) {
		Select select = null;
		if (locator.equalsIgnoreCase("cssSelector")) {
			select = new Select(driver.findElement(By.cssSelector(locatorValue)));
		} else if (locator.equalsIgnoreCase("xpath")) {
			select = new Select(driver.findElement(By.xpath(locatorValue)));
		} else if (locator.equalsIgnoreCase("id")) {
			select = new Select(driver.findElement(By.id(locatorValue)));
		}
		if (select != null) {
			select.selectByValue(value);
			WaitTillElementToBeDisplayed(locator, locatorValue);
		}
	}

	protected static void SelectDropDownText(String locator, String locatorValue, String value) {
		WebElement mySelectElement = null;
		if (locator.equalsIgnoreCase("cssSelector")) {
			mySelectElement = driver.findElement(By.cssSelector(locatorValue));
		} else if (locator.equalsIgnoreCase("xpath")) {
			mySelectElement = driver.findElement(By.xpath(locatorValue));
		} else if (locator.equalsIgnoreCase("id")) {
			mySelectElement = driver.findElement(By.id(locatorValue));
		}
		Select dropdown = new Select(mySelectElement);
		dropdown.selectByVisibleText(value);
	}

	protected void SelectBootStrap(String textval) {
		try {
			WebElement listElement = driver
					.findElement(By.xpath("//div[@class='Select-menu-outer']//div[text()='" + textval + "']"));
			// ((JavascriptExecutor)
			// driver).executeScript("arguments[0].scrollIntoView(true);", listElement);
			// listElement.click();
			if (listElement.isDisplayed()) {
				WaitAndClickElementDirectly(listElement);
				// listElement.click();
			} else {
				Assert.fail("ListElement could not be selected.");
			}
		} catch (Exception e) {
			Assert.fail(e);
		}
	}

	protected void SelectBootStrapCSS(String textval) {
		WebElement listElement = driver
				.findElement(By.cssSelector("div.Select-menu-outer and text()='" + textval + "'"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", listElement);
		listElement.click();

	}

	// iterate text value from dropdown - KTOC specific
	protected void SelectTextValueFromDropdown(String locator, String locatorValue, String node, String text) {
		if (locator.equalsIgnoreCase("cssSelector")) {
			element = driver.findElement(By.cssSelector(locatorValue));
		} else if (locator.equalsIgnoreCase("xpath")) {
			element = driver.findElement(By.xpath(locatorValue));
		} else if (locator.equalsIgnoreCase("id")) {
			element = driver.findElement(By.id(locatorValue));
		}
		element.click();
		List<WebElement> TextValues = driver.findElements(By.xpath("//" + node + "[text()='" + text + "']"));
		for (WebElement TextValue : TextValues) {
			TextValue.click();
			break;
		}
	}

	// Select the first text of the list from a dropdown - KTOC Specific
	protected String SelectFirstTextFromDropdown(String locatorType, String locatorValue, String item1LocatorType,
			String itemLocatorValue) {
		if (locatorType.equalsIgnoreCase("cssSelector")) {
			element = driver.findElement(By.cssSelector(locatorValue));
		} else if (locatorType.equalsIgnoreCase("xpath")) {
			element = driver.findElement(By.xpath(locatorValue));
		} else if (locatorType.equalsIgnoreCase("id")) {
			element = driver.findElement(By.id(locatorValue));
		}
		element.click();
		WebElement Item = FindTheElement(item1LocatorType, itemLocatorValue);
		Item.click();
		return Item.getText();
	}

	// Get The Text Of An Element
	public String GetTextOf(String locator, String locatorValue) {
		String TextofTheElement = null;
		if (locator.equalsIgnoreCase("cssSelector")) {
			element = driver.findElement(By.cssSelector(locatorValue));
		} else if (locator.equalsIgnoreCase("xpath")) {
			element = driver.findElement(By.xpath(locatorValue));
		} else if (locator.equalsIgnoreCase("id")) {
			element = driver.findElement(By.id(locatorValue));
		}
		TextofTheElement = element.getText();
		return TextofTheElement;
	}

	// Mouse Hover On An Element
	public void MouseHover(String locator, String locatorValue) {
		if (locator.equalsIgnoreCase("cssSelector")) {
			element = driver.findElement(By.cssSelector(locatorValue));
		} else if (locator.equalsIgnoreCase("xpath")) {
			element = driver.findElement(By.xpath(locatorValue));
		} else if (locator.equalsIgnoreCase("id")) {
			element = driver.findElement(By.id(locatorValue));
		}
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
	}

	// Click Calendar Date

	protected static void ClickCalendarValue(String selVal) {
		WebElement cal = driver.findElement(By.xpath("//div[@class='calendar-weeks']"));
		WebElement selecval = cal.findElement(By.xpath("//div[text()='" + selVal + "']"));
		selecval.click();

	}

	protected static void ClickDate(String CalenderField, String GivenValue) {
		driver.findElement(By.xpath(CalenderField)).click();
		driver.findElement(By.xpath("//td[text()='" + GivenValue + "']")).click();
	}

	public static void TakeSnapShot(String PageName) throws Exception {

		// Convert web driver object to TakeScreenshot
		TakesScreenshot scrShot = ((TakesScreenshot) driver);
		String TimeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		// Call getScreenshotAs method to create image file
		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
		// Move image file to new destination
		// File DestFile=new File(ScreenshotPath);
		// Copy file at destination
		// FileUtils.copyFile(SrcFile, DestFile);
		FileUtils.copyFile(SrcFile, new File(ScreenshotPath + '-' + PageName + TimeStamp + ".png"));
	}
}
