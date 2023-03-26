package pompages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewLeadPage {

	// Declaration
	@FindBy(xpath = "//span[@class='lvtHeaderText']")
	private WebElement pageHeader;
	@FindBy(name = "lastname")
	private WebElement lastNameTF;
	@FindBy(name = "company")
	private WebElement companyNameTF;
	@FindBy(xpath = "//input[normalize-space(@value)='Save']")
	private WebElement saveButton;

	// Initialization
	public CreateNewLeadPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// Utilization
	public String getPageHeaderText() {
		return pageHeader.getText();
	}
	
	public void setLastName(String leadName) {
		lastNameTF.sendKeys(leadName);
	}
	
	public void setCompanyName(String companyName) {
		companyNameTF.sendKeys(companyName);
	}
	
	public void clickSaveButton() {
		saveButton.click();
	}
}
