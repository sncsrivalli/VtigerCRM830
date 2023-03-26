package practice;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;

public class XmlParameterizationTest {

	@Test
	public void loginTest(XmlTest xml) {
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get(xml.getParameter("url"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		String username = xml.getParameter("user");
		String password = xml.getParameter("password");
		
		driver.findElement(By.id("username")).sendKeys(username+Keys.TAB+password+Keys.ENTER);
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		boolean status = wait.until(ExpectedConditions.titleContains("Enter Time-Track"));
		
		Assert.assertTrue(status);
		
		driver.quit();
	}
}
