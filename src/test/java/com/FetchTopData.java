/********************************************************************************
Description: Test 1 : verify the title of the Top 250 movies page
			Test 2 : truncate the database, populate it with new data 
			and fetch print the data from database to console
*******************************************************************************
*/
package com;

import org.testng.annotations.Test;

import com.common.AppBaseClass;
import com.common.Assertions;
import com.constant.pageElement.HomePageUIDs;
import com.constant.pageElement.Top250PageUIDs;
import com.database.SQLiteOperations;

public class FetchTopData extends AppBaseClass {

	
	HomePageUIDs objHomePage;
	Top250PageUIDs objTop250Page;
	
	//Test1
	@Test(description = "Verify the title of Top 250 chart page")
	public void getFirst250MoviesFromIMDB(){
		
	objHomePage = new HomePageUIDs(getDriver());
	objTop250Page = new Top250PageUIDs(getDriver());
	
	//Expected Title
	String expTitle = "IMDb Top 250 - IMDb";
	
		
	//Navigate to Top250 Movies page	
	objHomePage.clickTop250();	
	
	//Verify the title
	String titleAct = objTop250Page.getTop250PageTitle();
	
	Assertions.verifyEqual(titleAct, expTitle);
	
	System.out.println("*****************************************************");
	System.out.println("***** Test 1 is passed successfully");
	System.out.println("*****************************************************");
	
	}
	
	//Test2
	@Test(dependsOnMethods = "getFirst250MoviesFromIMDB" , description = "dependent on verify title, truncate database, populate database and print the data to console from database")	
	public void saveAndPrintMoviesDetails(){

		//Delete all records imdbmoviesDB	
		SQLiteOperations.deleteAllRecordsFromDataBase();
		
		//Populate data in imdbmoviesDB	
		SQLiteOperations.populateDataBase(getDriver());
		
		//Print the data of imdbmoviesDB to console
		SQLiteOperations.printDataFromDBToConsole();

		System.out.println("*****************************************************");
		System.out.println("***** Test 2 is passed successfully");
		System.out.println("*****************************************************");

	}
}

