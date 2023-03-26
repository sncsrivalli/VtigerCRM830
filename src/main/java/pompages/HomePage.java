package pompages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericUtilities.WebDriverUtility;

public class HomePage {

	//Declaration
	@FindBy(xpath = "//a[@class='hdrLink']")
	private WebElement pageHeader;
	@FindBy(xpath = "//a[.='Leads']")
	private WebElement leadsTab;
	@FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']")
	private WebElement adminIcon;
	@FindBy(xpath = "//a[.='Sign Out']")
	private WebElement signOutLink;
	
	//Initialization
	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	//Utilization
	public String getPageHeaderText() {
		return pageHeader.getText().trim();
	}
	
	public void clickLeadsTab() {
		leadsTab.click();
	}
	
	public void signOutOfVtiger(WebDriverUtility web) {
		web.mouseHover(adminIcon);
		signOutLink.click();
	}
}
