package pompages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NewLeadInfoPage {

	// Declaration
	@FindBy(xpath = "//span[@class='dvHeaderText']")
	private WebElement pageHeader;
	@FindBy(xpath = "//input[@value='Duplicate']")
	private WebElement duplicateButton;

	// Initialization
	public NewLeadInfoPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// Utilization
	public String getPageHeaderText() {
		return pageHeader.getText();
	}
	
	public void clickDuplicateButton() {
		duplicateButton.click();
	}

}
