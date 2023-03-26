package hardcodedTests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateAndDuplicateLeadTest {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://localhost:8888/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		WebElement loginButton = driver.findElement(By.id("submitButton"));
		if(loginButton.isDisplayed())
			System.out.println("Login page displayed");
		else
			System.out.println("Login page not found");
		
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		loginButton.click();
		
		String headerXpath = "//a[@class='hdrLink']";
		WebElement homePageHeader = driver.findElement(By.xpath(headerXpath));
		if(homePageHeader.getText().trim().equals("Home"))
			System.out.println("Home page Displayed");
		else
			System.out.println("Home page not found");
		
		driver.findElement(By.xpath("//a[.='Leads']")).click();
		
		WebElement leadsPageHeader = driver.findElement(By.xpath(headerXpath));
		if(leadsPageHeader.getText().trim().equals("Leads"))
			System.out.println("Leads page Displayed");
		else
			System.out.println("Leads page not found");
		
		driver.findElement(By.xpath("//img[@title='Create Lead...']")).click();
		
		String subPageHeader = "//span[@class='lvtHeaderText']";
		WebElement createLeadHeader = driver.findElement(By.xpath(subPageHeader));
		if(createLeadHeader.getText().equals("Creating New Lead"))
			System.out.println("Creating New Lead is displayed");
		else
			System.out.println("Creating New Lead is not displayed");
		
		driver.findElement(By.name("lastname")).sendKeys("PQR");
		driver.findElement(By.name("company")).sendKeys("Microsoft");
		driver.findElement(By.xpath("//input[normalize-space(@value)='Save']")).click();
		
		WebElement newLeadInfoHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']"));
		if(newLeadInfoHeader.getText().contains("PQR"))
			System.out.println("Lead created");
		else
			System.out.println("Lead not found");
		
		driver.findElement(By.xpath("//input[@value='Duplicate']")).click();
		
		WebElement duplicatingHeader = driver.findElement(By.xpath(subPageHeader));
		if(duplicatingHeader.getText().contains("Duplicating"))
			System.out.println("Duplicating page displayed");
		else
			System.out.println("Duplicating page not displayed");
		
		WebElement lastNameTF = driver.findElement(By.name("lastname"));
		lastNameTF.clear();
		lastNameTF.sendKeys("MNO");
		driver.findElement(By.xpath("//input[normalize-space(@value)='Save']")).click();
		WebElement duplicateLeadInfoHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']"));
		if(duplicateLeadInfoHeader.getText().contains("MNO"))
			System.out.println("Lead duplicated");
		else
			System.out.println("Lead not duplicated");
		
		driver.findElement(By.xpath(headerXpath)).click();
		
		WebElement newlead = driver.findElement(By.xpath("//table[@class='lvt small']"
				+ "/descendant::tr[last()]/td[3]"));
		if(newlead.getText().equals("MNO"))
			System.out.println("Lead added to data base");
		else
			System.out.println("Lead not added to data base");
		
		Actions a = new Actions(driver);
		WebElement adminIcon = driver.findElement(By.xpath(
				"//img[@src=\"themes/softed/images/user.PNG\"]"));
		a.moveToElement(adminIcon).perform();
		
		driver.findElement(By.xpath("//a[.='Sign Out']")).click();
		
		driver.quit();

	}

}
