/********************************************************************************
Description: Page Factory class to declare the page objects and method for home page operation
*******************************************************************************
*/
package com.constant.pageElement;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePageUIDs {
	
	WebDriver driver;
	
	//Declare locators of HomePage
	@FindBy(id="navTitleMenu")
	WebElement TITLE_MENU;
	
	@FindBy(linkText = "Top 250")
	WebElement LINK_TOP_250;
	
	
	//constructor
	public HomePageUIDs(WebDriver driver){
		this.driver = driver;
		//Create WebElements
		PageFactory.initElements(driver, this);
	}

	//method to click on Title Menu
	public void clickTitleMenu(){
		TITLE_MENU.click();		
	}

	//method to click Top 250 link from the menu
	public void clickTop250(){
		TITLE_MENU.click();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement objTop250 = wait.until(ExpectedConditions.visibilityOf(LINK_TOP_250));
		objTop250.click();
		
	}
	
	
}

