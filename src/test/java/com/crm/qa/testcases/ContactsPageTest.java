package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class ContactsPageTest extends TestBase {
	HomePage homePage;
	ContactsPage contactsPage;
	LoginPage loginPage;
	TestUtil testUtil;
	
	String sheetName = "contacts";

	public ContactsPageTest() {
		super();

	}

	@BeforeMethod
	public void setUp() {
		initialization();
		loginPage = new LoginPage();
		testUtil = new TestUtil();
		contactsPage = new ContactsPage();
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		testUtil.switchToFrame();
		contactsPage = homePage.clickOnContactsLink();
	}

	@Test()
	public void verifyContactLabelTest() {
		Assert.assertTrue(contactsPage.verifyContactsLabel(), "Contacts label is missing on the Page");
	}
/*	@Test()
	public void selectSingleContactTest() {
		contactsPage.selectContactsByName("Maria Gatmaitan");
	}
*/	
/*	@Test()
	public void selectMultipleContactTest() {
		contactsPage.selectContactsByName("Maria Gatmaitan");
		contactsPage.selectContactsByName("Dongyi G");
		contactsPage.selectContactsByName("Kendra G");

	}
*/	
	@DataProvider
	public Object[][] getCRMTestData(){
		Object data[][] = TestUtil.getTestData(sheetName);
		return data;
	}
	//data driven + page object model 
	//keyword driven is the worst approach specially for maintenance point of view is really difficult.
	@Test(dataProvider="getCRMTestData")
	public void validateCreateNewContactTest(String title, String firstName, String lastName, String company) {
		homePage.clickOnNewContactLink();
	//	contactsPage.createNewContact("Mr.","Alsimmon","Spawn","GCompany");
		contactsPage.createNewContact(title, firstName, lastName, company);
	}
	
	@AfterMethod
	public void teardown() {
		driver.quit();
	}

}
//github


//git status
//git add
//git commit -m "added minor changes"
//git push origin master