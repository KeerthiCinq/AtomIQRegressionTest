package javaPackage;

import java.util.Date;
import java.util.Random;

public class DataStructureAlogorithm {

	public static void main(String[] args) {
		ArrayOptimization();

	}

	
	public static void ArrayOptimization() {
		int[] employee100 = new int[100];
		int[] employee5000 = new int[5000];
		Random random = new Random();
Date startTime = new Date();
long startTimeInMS = startTime.getTime();
System.out.println("Start Time : "+startTimeInMS);
/*
 * for(int i=1; i<employee5000.length; i++) { employee5000[i] =
 * random.nextInt(); // System.out.println("iteration count : " +i+
 * " ; iteration value : "+employee1000[i]); }
 */
		
		
		for(int empArr :employee100 ) {
			employee100[empArr] = random.nextInt(10);
			System.out.println("iteration value is " +employee100[empArr]);
		}
		
		Date endTime = new Date();
		long endTimeInMS = endTime.getTime();
		System.out.println("End Time : "+endTimeInMS);
		
		long timetaken = endTimeInMS-startTimeInMS ;
		System.out.println("Time taken : "+ timetaken);
          System.out.println("Test another commit");

		
		
		
		
	}
}
