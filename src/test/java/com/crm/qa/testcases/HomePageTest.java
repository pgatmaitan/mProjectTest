package com.crm.qa.testcases;

import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
//Ctrl shift O to import all the imports


public class HomePageTest extends TestBase {
	LoginPage loginPage;
	HomePage homePage;
	TestUtil testUtil;
	ContactsPage contactsPage;
	//Ctrl + Space 
	public HomePageTest() {
		super();

	}

	// Best Practice
	// Before each test cases launch the browser and login
	// @test execute your test cases.
	// after each test cases close the browser.
	// Asserts if it fails it will skipped the next line of code and no longer
	// executed it.
	// "Home Page Title not matched will only be printed when the assert failed use
	// in reporting"
	// There is a frame so we need to add a utility method "switchToFrame"
	// Why do you close the browser for every test case, th//e reasons is 
	// that the browser will get exhausted if you try to execute 100 testcase on a single browser
	// Test cases should be Independent to one another.
	
	@BeforeMethod
	public void setUp() {
		initialization();
		loginPage = new LoginPage();
		testUtil = new TestUtil();
		contactsPage = new ContactsPage();
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test(priority = 1)
	public void verifyHomePageTitleTest() {
		String homePageTitle = homePage.verifyHomePageTitle();
		Assert.assertEquals(homePageTitle, "CRMPRO", "Home Page Title not matched");

	}

	@Test(priority = 2)
	public void verifyUserNameTest() {
		testUtil.switchToFrame();
		Assert.assertTrue(homePage.verifyCorrectUserName());
	}

	@Test(priority = 1)
	public void verifyContactsLinkTest() {
		testUtil.switchToFrame();
		contactsPage = homePage.clickOnContactsLink();
	}

	@AfterMethod
	public void teardown() {
		driver.quit();
	}
}