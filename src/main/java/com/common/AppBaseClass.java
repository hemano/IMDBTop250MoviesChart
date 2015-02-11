/********************************************************************************
Description: This class handle the the task of application launch and teardown
 *******************************************************************************
 */
package com.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class AppBaseClass {

	WebDriver driver = null;

	//launch the application in firefox
	@BeforeClass
	public void start(){

		try{
			driver = new FirefoxDriver();		
			driver.get("http://www.imdb.com");
			driver.manage().window().maximize();
			System.out.println("*****************************************************");
			System.out.println("***** Application has been launched successfully");
			System.out.println("*****************************************************");
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	//teardown the browser
	@AfterClass
	public void tearDown(){

		try{

			driver.quit();
			System.out.println("*****************************************************");
			System.out.println("***** Browsers has been closed successfully");
			System.out.println("*****************************************************");

		}catch(Exception e){
			e.printStackTrace();
		}




	}

	//function returns the webdriver instance 
	public WebDriver getDriver(){
		return this.driver;
	}

}
