package genericUtilities;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import pompages.CreateNewLeadPage;
import pompages.DuplicatingLeadPage;
import pompages.HomePage;
import pompages.LeadsPage;
import pompages.LoginPage;
import pompages.NewLeadInfoPage;

public class BaseClass {

	protected PropertiesUtility property;
	protected ExcelUtility excel;
	protected JavaUtility javaUtil;
	protected WebDriverUtility web;
	protected WebDriver driver;
	protected long time;
	protected LoginPage login;
	protected HomePage home;
	protected LeadsPage leads;
	protected CreateNewLeadPage createLead;
	protected NewLeadInfoPage leadInfo;
	protected DuplicatingLeadPage duplicateLead;
	public static JavaUtility sjavaUtil;
	public static WebDriver sdriver;
	
	//@BeforeSuite
	//@BeforeTest
	
	@BeforeClass
	public void classSetup() {
		property = new PropertiesUtility();
		excel = new ExcelUtility();
		javaUtil = new JavaUtility();
		sjavaUtil = javaUtil;
		web = new WebDriverUtility();
		
		property.propertiesInitialization(AutoConstantPath.PROPERTIES_PATH);
		String url = property.getDataFromProperties("url");
		String browser = property.getDataFromProperties("browser");
		time = Long.parseLong(property.getDataFromProperties("timeouts"));
		
		driver = web.openApplication(browser, url, time);
		sdriver = driver;
	}
	
	@BeforeMethod
	public void methodSetup() {
		login = new LoginPage(driver);
		Assert.assertTrue(login.getLoginButton().isDisplayed());
		home = new HomePage(driver);
		leads = new LeadsPage(driver);
		createLead = new CreateNewLeadPage(driver);
		leadInfo = new NewLeadInfoPage(driver);
		duplicateLead = new DuplicatingLeadPage(driver);
		
		excel.excelInitialization(AutoConstantPath.EXCEL_PATH);
		
		login.loginToVtiger(property.getDataFromProperties("username"), property.getDataFromProperties("password"));
		Assert.assertEquals(home.getPageHeaderText(), "Home");
	}
	
	@AfterMethod
	public void methodTeardown() {
		home.signOutOfVtiger(web);
		excel.closeWorkbook();
	}
	
	@AfterClass
	public void classTeardown() {
		web.closeBrowser();
	}
	
	//@AfterTest
	//@AfterSuite
}
