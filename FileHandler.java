package project4;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Class that handles creating file objects and writing data to a csv file.
 */
public class FileHandler 
{
	private String surveyFile;
	private FileWriter fileOutput;
	private PrintWriter printWriter;
	
	/**
	 * creates a file handler object, checks if the file object has been created.
	 * If the object does not exist, it creates a new file object and it prints the header to the csv file, else 
	 * it does not print the header to prevent having multiple headers.
	 */
	public FileHandler()
	{
		surveyFile = "survey_results.csv";
		File file = new File(surveyFile);
		try 
		{
			
			fileOutput = new FileWriter(file);
			printWriter = new PrintWriter(fileOutput);
			printWriter.println("DateTime,FirstName,LastName,PhoneNumber,Email,Sex,Water,Meals,Wheat,Sugar,Dairy,Miles,Weight");
			
			
			printWriter.close();
			
		} 
		catch (IOException e) 
		{

			e.printStackTrace();
		}
		
		
	}

	/**
	 * prints the survey data collected by the GUI to a csv file
	 * @param surveyData String passed in from the method InnerActionListener.actionPerformed(ActionEvent e)
	 */
	public void writeResults(String surveyData)
	{
		surveyFile = "survey_results.csv";
		File file = new File(surveyFile);
		try
		{
			fileOutput = new FileWriter(file, true);
			printWriter = new PrintWriter(fileOutput);
			
			printWriter.println(surveyData);
			
			printWriter.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
	}

}
