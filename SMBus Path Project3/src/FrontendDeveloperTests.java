import static org.junit.jupiter.api.Assertions.*;

import java.util.Scanner;

import org.junit.jupiter.api.Test;

class FrontendDeveloperTests {

	/**
	 * Test running command loop
	 */
	@Test
	void testOne() 
	{
		boolean runCommandLoopTest = false;
		boolean loadDataCommandTest = false;
		
		TextUITester tester = new TextUITester("L\nBusRoute.DOT\nQ\n");
		
		try(Scanner in = new Scanner(System.in))
		{
			SMBPFrontendFD frontend = new SMBPFrontendFD(in, new SMBPBackendFD());
			
			frontend.runCommandLoop();
			
			String result = tester.checkOutput();
			
			if(result.contains("Welcome to the Shortest Madison Bus Path Calculator"))
				runCommandLoopTest = true;
			
			if(result.contains("Enter the name of the file: ") && 
					result.contains("Data Loaded Successfully."))
				loadDataCommandTest = true;
		}
		
		assertEquals(true, runCommandLoopTest);
		assertEquals(true, loadDataCommandTest);
	}

	/**
	 * Tests the correctness of adding a new stop
	 */
	@Test
	void testTwo()
	{
		boolean addStudent = false;
		
		TextUITester tester = new TextUITester("L\nBusRoute.DOT\nN\nTestStop\nQ\n");
		
		try(Scanner in = new Scanner(System.in))
		{
			SMBPFrontendFD frontend = new SMBPFrontendFD(in, new SMBPBackendFD());
			
			frontend.runCommandLoop();
			
			String result = tester.checkOutput();
			
			if(result.contains("Enter a new stop: ") && 
					result.contains("Stop \"TestStop\" successfuly added"))
				addStudent = true;
		}
		
		assertEquals(addStudent, true);
	}
	
	/**
	 * Checks the correctness of deleting a stop
	 */
	@Test
	void testThree()
	{
		boolean deleteStudent = false;
		
		TextUITester tester = new TextUITester("L\nBusRoute.DOT\nD\nTestStop\nQ\n");
		
		try(Scanner in = new Scanner(System.in))
		{
			SMBPFrontendFD frontend = new SMBPFrontendFD(in, new SMBPBackendFD());
			
			frontend.runCommandLoop();
			
			String result = tester.checkOutput();
			
			if(result.contains("Enter a stop to delete: ") && 
					result.contains("Stop \"TestStop\" successfuly removed"))
				deleteStudent = true;
		}
		
		assertEquals(deleteStudent, true);
	}
	
	/**
	 * Tests correctness of getting number of stops
	 */
	@Test
	void testFour()
	{
		boolean numStops = false;
		
		TextUITester tester = new TextUITester("L\nBusRoute.DOT\nU\nQ\n");
		
		try(Scanner in = new Scanner(System.in))
		{
			SMBPFrontendFD frontend = new SMBPFrontendFD(in, new SMBPBackendFD());
			
			frontend.runCommandLoop();
			
			String result = tester.checkOutput();
			
			if(result.contains("4"))
					numStops = true;
		}
		
		assertEquals(numStops, true);
	}
	
	/**
	 * Test correctness of getting number of streets
	 */
	@Test
	void testFive()
	{
		boolean numStreets = false;
		
		TextUITester tester = new TextUITester("L\nBusRoute.DOT\nM\nQ\n");
		
		try(Scanner in = new Scanner(System.in))
		{
			SMBPFrontendFD frontend = new SMBPFrontendFD(in, new SMBPBackendFD());
			
			frontend.runCommandLoop();
			
			String result = tester.checkOutput();
			
			if(result.contains("8"))
					numStreets = true;
		}
		
		assertEquals(numStreets, true);
	}
}
