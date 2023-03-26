package pomImplementation;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import genericUtilities.AutoConstantPath;
import genericUtilities.ExcelUtility;
import genericUtilities.JavaUtility;
import genericUtilities.PropertiesUtility;
import genericUtilities.WebDriverUtility;
import pompages.CreateNewLeadPage;
import pompages.DuplicatingLeadPage;
import pompages.HomePage;
import pompages.LeadsPage;
import pompages.LoginPage;
import pompages.NewLeadInfoPage;

public class CreateAndDuplicateLeadTest {

	public static void main(String[] args) {
		PropertiesUtility property = new PropertiesUtility();
		ExcelUtility excel = new ExcelUtility();
		JavaUtility javaUtil = new JavaUtility();
		WebDriverUtility web = new WebDriverUtility();
		
		property.propertiesInitialization(AutoConstantPath.PROPERTIES_PATH);
		excel.excelInitialization(AutoConstantPath.EXCEL_PATH);
				
//		WebDriverManager.chromedriver().setup();
//		WebDriver driver = new ChromeDriver();
//		driver.manage().window().maximize();
//		driver.get("http://localhost:8888/");
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		String url = property.getDataFromProperties("url");
		String browser = property.getDataFromProperties("browser");
		long time = Long.parseLong(property.getDataFromProperties("timeouts"));
		
		WebDriver driver = web.openApplication(browser, url, time);
		
		LoginPage login = new LoginPage(driver);
		HomePage home = new HomePage(driver);
		LeadsPage leads = new LeadsPage(driver);
		CreateNewLeadPage createLead = new CreateNewLeadPage(driver);
		NewLeadInfoPage leadInfo = new NewLeadInfoPage(driver);
		DuplicatingLeadPage duplicateLead = new DuplicatingLeadPage(driver);
		
		//WebElement loginButton = driver.findElement(By.id("submitButton"));
		if(login.getLoginButton().isDisplayed())
			System.out.println("Login page displayed");
		else
			System.out.println("Login page not found");
		
//		driver.findElement(By.name("user_name")).sendKeys(property.getDataFromProperties("username"));
//		driver.findElement(By.name("user_password")).sendKeys(property.getDataFromProperties("password"));
//		loginButton.click();
		
		login.loginToVtiger(property.getDataFromProperties("username"), property.getDataFromProperties("password"));
		
//		String headerXpath = "//a[@class='hdrLink']";
//		WebElement homePageHeader = driver.findElement(By.xpath(headerXpath));
		if(home.getPageHeaderText().equals("Home"))
			System.out.println("Home page Displayed");
		else
			System.out.println("Home page not found");
		
		home.clickLeadsTab();
		//driver.findElement(By.xpath("//a[.='Leads']")).click();
		
		//WebElement leadsPageHeader = driver.findElement(By.xpath(headerXpath));
		if(leads.getPageHeaderText().equals("Leads"))
			System.out.println("Leads page Displayed");
		else
			System.out.println("Leads page not found");
		
		leads.clickPlusButton();
		//driver.findElement(By.xpath("//img[@title='Create Lead...']")).click();
		
//		String subPageHeader = "//span[@class='lvtHeaderText']";
//		WebElement createLeadHeader = driver.findElement(By.xpath(subPageHeader));
		if(createLead.getPageHeaderText().equals("Creating New Lead"))
			System.out.println("Creating New Lead is displayed");
		else
			System.out.println("Creating New Lead is not displayed");
		
		Map<String,String> map = excel.readDataFromExcel("LeadsTestData", "Create and Duplicate Lead");
		String lastName = map.get("Last Name")+javaUtil.generateRandomNumber(100);
		String company = map.get("Company")+ javaUtil.generateRandomNumber(100);
		
		createLead.setLastName(lastName);
		createLead.setCompanyName(company);
		createLead.clickSaveButton();
//		driver.findElement(By.name("lastname")).sendKeys(lastName);
//		driver.findElement(By.name("company")).sendKeys(company);
//		driver.findElement(By.xpath("//input[normalize-space(@value)='Save']")).click();
		
		//WebElement newLeadInfoHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']"));
		if(leadInfo.getPageHeaderText().contains(lastName))
			System.out.println("Lead created");
		else
			System.out.println("Lead not found");
		
		leadInfo.clickDuplicateButton();
		//driver.findElement(By.xpath("//input[@value='Duplicate']")).click();
		
		//WebElement duplicatingHeader = driver.findElement(By.xpath(subPageHeader));
		if(duplicateLead.getPageHeaderText().contains("Duplicating"))
			System.out.println("Duplicating page displayed");
		else
			System.out.println("Duplicating page not displayed");
		
		String newLastName = map.get("New Last Name")+javaUtil.generateRandomNumber(100);
//		WebElement lastNameTF = driver.findElement(By.name("lastname"));
//		lastNameTF.clear();
//		lastNameTF.sendKeys(newLastName);
		duplicateLead.setLastName(newLastName);
		duplicateLead.clickSaveButton();
		//driver.findElement(By.xpath("//input[normalize-space(@value)='Save']")).click();
		//WebElement duplicateLeadInfoHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']"));
		if(leadInfo.getPageHeaderText().contains(newLastName))
			System.out.println("Lead duplicated");
		else
			System.out.println("Lead not duplicated");
		
		duplicateLead.clickLeads();
		//driver.findElement(By.xpath(headerXpath)).click();
		
		//WebElement newlead = driver.findElement(By.xpath("//table[@class='lvt small']/descendant::tr[last()]/td[3]"));
		if(leads.getNewLeadName().equals(newLastName)) {
			System.out.println("Lead added to data base");
			excel.writeToExcel("LeadsTestData", "Pass", "Create and Duplicate Lead", AutoConstantPath.EXCEL_PATH);
		}
		else {
			System.out.println("Lead not added to data base");
			excel.writeToExcel("LeadsTestData", "Fail", "Create and Duplicate Lead", AutoConstantPath.EXCEL_PATH);
		}
		
//		//Actions a = new Actions(driver);
//		WebElement adminIcon = driver.findElement(By.xpath(
//				"//img[@src=\"themes/softed/images/user.PNG\"]"));
//		web.mouseHover(adminIcon);
//		//a.moveToElement(adminIcon).perform();
//		
//		driver.findElement(By.xpath("//a[.='Sign Out']")).click();
//		
		//driver.quit();
		home.signOutOfVtiger(web);
		web.closeBrowser();
		excel.closeWorkbook();

	}

}
