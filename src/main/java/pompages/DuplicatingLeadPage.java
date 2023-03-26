package pompages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DuplicatingLeadPage {

	//Declaration
	@FindBy(xpath = "//span[@class='lvtHeaderText']")
	private WebElement pageHeader;
	@FindBy(name = "lastname")
	private WebElement lastNameTF;
	@FindBy(xpath = "//input[normalize-space(@value)='Save']")
	private WebElement saveButton;
	@FindBy(xpath = "//a[@class='hdrLink']")
	private WebElement leadsLink;

	// Initialization
	public DuplicatingLeadPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// Utilization
	public String getPageHeaderText() {
		return pageHeader.getText();
	}
	
	public void setLastName(String leadName) {
		lastNameTF.clear();
		lastNameTF.sendKeys(leadName);
	}
	
	public void clickSaveButton() {
		saveButton.click();
	}
	
	public void clickLeads() {
		leadsLink.click();
	}
}
