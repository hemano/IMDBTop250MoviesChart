/********************************************************************************
Description: Page Factory class to declare the page objects of IMDb Top 250 page and method for operations
*******************************************************************************
*/

package com.constant.pageElement;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Top250PageUIDs {

	WebDriver driver;
	
	@FindBy(className = "lister-list")
	WebElement objMovieTableBody;
	
	
	public Top250PageUIDs(WebDriver driver){
		this.driver = driver;
		
		PageFactory.initElements(driver, this);
	}
	
	public String getTop250PageTitle(){		
		return driver.getTitle();	
	}
	
	
	public ArrayList<ArrayList<String>> getMoviesDataInArrayList(){
		
		List<WebElement> objMovieRow = objMovieTableBody.findElements(By.tagName("tr"));
		
		ArrayList<ArrayList<String>> array = new ArrayList<ArrayList<String>>();
		int arrayIndex = 0;
		
		for(WebElement e: objMovieRow){
			
			WebElement movieName = e.findElement(By.className("titleColumn")).findElement(By.tagName("a"));
			WebElement movieYear = e.findElement(By.className("titleColumn")).findElement(By.className("secondaryInfo"));
			WebElement movieRating = e.findElement(By.className("imdbRating")).findElement(By.tagName("strong"));
			
			
			array.add(new ArrayList<String>());
			
			array.get(arrayIndex).add(movieName.getText());
			array.get(arrayIndex).add(movieYear.getText().replace("(", "").replace(")", ""));
			array.get(arrayIndex).add(movieRating.getText());
			arrayIndex = arrayIndex + 1;
			
		}
		
		
		return array;
	}
}

