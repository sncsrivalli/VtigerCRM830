package genericUtilities;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility {

	private WebDriver driver;
	private Actions actions;
	private Select select;
	
	public WebDriver launchBrowser(String browser) {
		switch(browser) {
		case "chrome": ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(options); break;
		case "firefox": driver = new FirefoxDriver();break;
		case "edge": driver = new EdgeDriver(); break;
		default: System.out.println("Invalid browser data");
		}
		return driver;
	}
	
	public void maximizeBrowser(WebDriver driver) {
		driver.manage().window().maximize();
	}
	
	public void navigateToApplication(String url, WebDriver driver) {
		driver.get(url);
	}
	
	public void waitTillElementFound(long time, WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
	}
	
	public WebDriver openApplication(String browser, String url, long time) {
		WebDriver driver = launchBrowser(browser);
		maximizeBrowser(driver);
		navigateToApplication(url, driver);
		waitTillElementFound(time, driver);
		return driver;
	}
	
	public void explicitWait(WebDriver driver, long time, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(time));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void explicitWait(WebDriver driver, WebElement element, long time) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(time));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public void explicitWait(WebDriver driver, String title, long time) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(time));
		wait.until(ExpectedConditions.titleContains(title));
	}
	
	public void mouseHover(WebElement element) {
		actions = new Actions(driver);
		actions.moveToElement(element).perform();
	}
	
	public void doubleClickElement(WebElement element) {
		actions = new Actions(driver);
		actions.doubleClick(element).perform();
	}
	
	public void rightClick(WebElement element) {
		actions = new Actions(driver);
		actions.contextClick(element).perform();
	}
	
	public void dragAndDrop(WebElement src, WebElement dest) {
		actions = new Actions(driver);
		actions.dragAndDrop(src,dest).perform();
	}
	
	public void dropdown(WebElement element, int index) {
		select = new Select(element);
		select.selectByIndex(index);
	}
	
	public void dropdown(WebElement element, String value) {
		select = new Select(element);
		select.selectByValue(value);
	}
	
	public void dropdown(String text, WebElement element) {
		select = new Select(element);
		select.selectByVisibleText(text);
	}
	
	public void switchToFrame(int index) {
		driver.switchTo().frame(index);
	}
	
	public void switchToFrame(WebElement frameElement) {
		driver.switchTo().frame(frameElement);
	}
	
	public void switchToFrame(String idOrNameAttribute) {
		driver.switchTo().frame(idOrNameAttribute);
	}
	
	public void switchBackFromFrame() {
		driver.switchTo().defaultContent();
	}
	
	public void scrollToElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true)", element);
	}
	
	public String getScreenshot(JavaUtility javaUtil, String testName, WebDriver driver) {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File("./Screenshot/"+testName+"_"+javaUtil.getCurrentTime()+".png");
		try {
			FileUtils.copyFile(src,dest);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dest.getAbsolutePath();
	}
	
	public void handleAlert(String status) {
		if(status.equals("OK"))
			driver.switchTo().alert().accept();
		else
			driver.switchTo().alert().dismiss();
	}
	
	public void handleChildBrowser(String expectedTitle) {
		Set<String> windows = driver.getWindowHandles();
		for (String windowID : windows) {
			driver.switchTo().window(windowID);
			String title = driver.getTitle();
			if(title.contains(expectedTitle))
				break;
		}
	}
	
	public void switchToParentWindow() {
		String parentID = driver.getWindowHandle();
		driver.switchTo().window(parentID);
	}
	
	public void closeBrowser() {
		driver.quit();
	}
	
}
