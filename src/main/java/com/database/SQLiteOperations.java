/********************************************************************************
Description: Library contains the functions to handle database operations
*******************************************************************************
*/
package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.openqa.selenium.WebDriver;

import com.constant.pageElement.Top250PageUIDs;

public class SQLiteOperations {

	Connection conn = null;
	
	//method to connect to database
	public static Connection dbConnector(){
	
		try{
			
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:src\\main\\resources\\imdbmovies.db");
			System.out.println("Database Connection Successful");
			return conn;		

		}catch(Exception e){		
			e.printStackTrace();
			return null;
		}		
	}
	
	//method to populate database with the data from IMDB Top 250 chart page
	public static void populateDataBase(WebDriver driver){
		
		Top250PageUIDs objTop250Page; 
		
		Connection  connection = dbConnector();
		objTop250Page = new Top250PageUIDs(driver);
		String query = "";
		try{
			
			ArrayList<ArrayList<String>> array = objTop250Page.getMoviesDataInArrayList();
			PreparedStatement pst = null;
			
			
			for(int i=0 ; i< array.size() ; i++){

				String movieName = array.get(i).get(0);
				String movieYr = array.get(i).get(1);
				String movieRating = array.get(i).get(2);

				movieName = movieName.replace("'", "");
				
				query = "insert into top250movies (movieName, movieYear, movieRating) values (" + "\'" + movieName +"\'" + "," + "\'" + movieYr + "\'" + "," + "\'" + movieRating + "\'" + ")";
				pst = connection.prepareStatement(query);					
				pst.execute();

				}

			//close the connection
			pst.close();
			connection.close();
			
		}catch(Exception e){
			System.out.println(query);
			e.printStackTrace();
		}
		
	}
	
	//print all records from database to console
	public static void printDataFromDBToConsole(){
		
		Connection  connection = dbConnector();
		
		try{			
			String query = "Select * from top250movies";
			PreparedStatement pst = connection.prepareStatement(query);
			
			ResultSet rs = pst.executeQuery();
			int count = 0;
			while(rs.next()){				
				count = count + 1;
								
				String movieName = rs.getString("movieName");			
				String movieYr = rs.getString("movieYear").toString();				
				String movieRate = rs.getString("movieRating").toString();
				
				System.out.println(movieName + " | "+  movieYr + " | "+ movieRate);
			}
						
			//close the connection
			rs.close();
			pst.close();
			connection.close();
			
		}catch(Exception e){			
			e.printStackTrace();
		}		
	}

	//method delete all records from the database
	public static void deleteAllRecordsFromDataBase(){
		
		Connection  connection = dbConnector();
		String query = "";

		try{			
			PreparedStatement pst = null;
			
			query = "DELETE From top250movies";
			pst = connection.prepareStatement(query);					
			pst.execute();
			pst.close();
			connection.close();
			
			System.out.println("Database records truncated");
			
		}catch(Exception e){
			System.out.println(query);
			e.printStackTrace();
		}
		
	}
	
}


