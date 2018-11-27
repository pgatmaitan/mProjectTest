package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class ContactsPageTest extends TestBase{
	HomePage homePage;
	ContactsPage contactsPage;
	LoginPage loginPage;
	TestUtil testUtil;
	
	
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

	
	@Test(priority=1)
	public void verifyContactLabelTest() {
		Assert.assertTrue(contactsPage.verifycontactsLabel(),"Contacts label is missing on the Page");
		
	}
	
	@Test(priority=2)
	public void selectSingleContactTest() {
		contactsPage.selectContactsByName("Maria Gatmaitan");
	}
	
	@Test(priority=3)
	public void selectMultipleContactTest() {
		contactsPage.selectContactsByName("Maria Gatmaitan");
		contactsPage.selectContactsByName("Dongyi G");
		contactsPage.selectContactsByName("Kendra G");
		
	}
	
	
	@AfterMethod
	public void teardown() {
		driver.quit();
	}
	

}
