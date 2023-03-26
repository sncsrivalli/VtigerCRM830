package pompages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LeadsPage {

	// Declaration
	@FindBy(xpath = "//a[@class='hdrLink']")
	private WebElement pageHeader;
	@FindBy(xpath = "//img[@title='Create Lead...']")
	private WebElement plusButton;
	@FindBy(xpath = "//table[@class='lvt small']/descendant::tr[last()]/td[3]")
	private WebElement newLeadLastName;

	// Initialization
	public LeadsPage(WebDriver driver) {
			PageFactory.initElements(driver, this);
		}

	// Utilization
	public String getPageHeaderText() {
		return pageHeader.getText().trim();
	}
	
	public void clickPlusButton() {
		plusButton.click();
	}
	
	public String getNewLeadName() {
		return newLeadLastName.getText();
	}
}
