package testngImplementation;

import java.util.Map;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import genericUtilities.AutoConstantPath;
import genericUtilities.BaseClass;
@Listeners(genericUtilities.ListenerImplementation.class)
public class CreateAndDuplicateLeadTest extends BaseClass{

	@Test
	public void createAndDuplicateLeadTest() {
		SoftAssert soft = new SoftAssert();
		
		home.clickLeadsTab();
		soft.assertTrue(leads.getPageHeaderText().equals("Leads"));
		
		leads.clickPlusButton();
		soft.assertTrue(createLead.getPageHeaderText().equals("Creating New Lead"));
		
		Map<String,String> map = excel.readDataFromExcel("LeadsTestData", "Create and Duplicate Lead");
		String lastName = map.get("Last Name")+javaUtil.generateRandomNumber(100);
		String company = map.get("Company")+ javaUtil.generateRandomNumber(100);
		
		createLead.setLastName(lastName);
		createLead.setCompanyName(company);
		createLead.clickSaveButton();
		soft.assertTrue(leadInfo.getPageHeaderText().contains(lastName));
		
		leadInfo.clickDuplicateButton();
		soft.assertTrue(duplicateLead.getPageHeaderText().contains("Duplicating"));
		
		String newLastName = map.get("New Last Name")+javaUtil.generateRandomNumber(100);
		duplicateLead.setLastName(newLastName);
		duplicateLead.clickSaveButton();
		soft.assertTrue(leadInfo.getPageHeaderText().contains(newLastName));
		
		duplicateLead.clickLeads();
		soft.assertTrue(leads.getNewLeadName().equals(newLastName));
		if(leads.getNewLeadName().equals(newLastName)) {
			System.out.println("Lead added to data base");
			excel.writeToExcel("LeadsTestData", "Pass", "Create and Duplicate Lead", AutoConstantPath.EXCEL_PATH);
		}
		else {
			System.out.println("Lead not added to data base");
			excel.writeToExcel("LeadsTestData", "Fail", "Create and Duplicate Lead", AutoConstantPath.EXCEL_PATH);
		}
		soft.assertAll();
	}

}
