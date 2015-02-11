package com.common;

import org.testng.Assert;

public class Assertions {

	
	public static void verifyEqual(String Actual, String Expected){
		
		if(Actual.equals(Expected)){
			System.out.println("Actual : " + Actual + " equals " + " Expected : " + Expected );
		}else{
			System.out.println("Actual : " + Actual + " Not equals " + " Expected : " + Expected );
		}
		
		Assert.assertEquals(Expected, Actual);

	}
	
	
	
}
