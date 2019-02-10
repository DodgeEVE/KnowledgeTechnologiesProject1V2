/*
 * COMP90049 Knowledge Technologies Semester 2 2018 Project1- Wikipedia 
 * Mohammed Atiq Shaikh
 */

package com.comp90049.project1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * This class is the starting point of the project.
 * It read all the three text files misspell, correct and dictionary and store it into
 * arraylist and ask for user input to test the correction method.
 * 
 * */

public class WikipediaDemo {

	private final static String MISSPELLFILE = "src/com/comp90049/project1/wiki_misspell.txt";
	private final static String CORRECTFILE = "src/com/comp90049/project1/wiki_correct.txt";
	private final static String DICTIONARYFILE = "src/com/comp90049/project1/dict.txt";
	private static ArrayList<String> misspellList = null;
	private static ArrayList<String> correctList = null;
	private static ArrayList<String> dictionaryList = null;
	
	public static WikipediaDemo wDemo = new
			WikipediaDemo();
	
	
	public static void main(String[] args){
		
		Scanner src = null;
		
		
		//get file name and path
		File misspellFile = new File(MISSPELLFILE);
		File correctFile = new File(CORRECTFILE);
		File dictionaryFile = new File(DICTIONARYFILE);
		
		FileReader misspellFileReader = null, correctFileReader = null, dictionaryFileReader = null;
		Scanner scanMisspell = null, scanCorrect = null, scanDictionary = null;
			
		misspellList = wDemo.readMisspellFile(misspellFile, misspellFileReader, scanMisspell, misspellList);
		correctList = wDemo.readCorrectFile(correctFile, correctFileReader, scanCorrect, correctList);
		dictionaryList = wDemo.readDictionary(dictionaryFile, dictionaryFileReader, scanDictionary, dictionaryList);
		wDemo.chooseOperation(src, misspellList, correctList, dictionaryList);
		
	}
	
	//This method read the words in the dictionary text file
	private ArrayList<String> readDictionary(File dictionaryFile, FileReader dictionaryFileReader,
			Scanner scanDictionary, ArrayList<String> dictionaryList) {
		// TODO Auto-generated method stub

		try {
			
			//Check for the file existence
			if (!dictionaryFile.exists()) {
				System.out.println("File not found");
			}			
			
			dictionaryFileReader = new FileReader(dictionaryFile);
			scanDictionary = new Scanner(dictionaryFileReader);
			
				//Read and store into arraylist 
				try{
					dictionaryList = new ArrayList<String>();
					while (scanDictionary.hasNextLine()){
						dictionaryList.add(scanDictionary.nextLine().trim());
					}
					
				} catch (Exception e) {
				// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			scanDictionary.close();
			dictionaryFileReader.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dictionaryList;
	}

	//This method read the words in the correct text file
	private ArrayList<String> readCorrectFile(File correctFile, FileReader correctFileReader, 
			Scanner scanCorrect, ArrayList<String> correctList) {
		// TODO Auto-generated method stub

		try {
			
			//Check for the file existence
			if (!correctFile.exists()) {
				System.out.println("File not found");
			}			
			
			correctFileReader = new FileReader(correctFile);
			scanCorrect = new Scanner(correctFileReader);
			
				//Read and store into arraylist 
				try{
					correctList = new ArrayList<String>();
					while (scanCorrect.hasNextLine()){
						correctList.add(scanCorrect.nextLine().trim());
					}
					
				} catch (Exception e) {
				// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			scanCorrect.close();
			correctFileReader.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return correctList;
	}

	//This method read the words in the misspell text file
	private ArrayList<String> readMisspellFile(File misspellFile,
			FileReader misspellFileReader, Scanner scanMisspell, ArrayList<String> misspellList) {
		// TODO Auto-generated method stub
		 
		
		try {
			
			//Check for the file existence
			if (!misspellFile.exists()) {
				System.out.println("File not found");
			}			
			
			misspellFileReader = new FileReader(misspellFile);
			scanMisspell = new Scanner(misspellFileReader);
			
				//Read and store into arraylist 
				try{
					misspellList = new ArrayList<String>();
					while (scanMisspell.hasNextLine()){
						misspellList.add(scanMisspell.nextLine().trim());
					}
					
					
					
				} catch (Exception e) {
				// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			scanMisspell.close();
			misspellFileReader.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return misspellList;
	}

	//This method enable user to choose operation of his/her choice
	private void chooseOperation(Scanner src, ArrayList<String> misspellList,
			ArrayList<String> correctList, ArrayList<String> dictionaryList){
		src = new Scanner(System.in);
		System.out.println("Please select the approximate string matching algorithm you wish to test.");
		System.out.println("For Wagner Fischer Algorithm press 1\n"
				+ "For Global Edit Distance Algorithm press 2\n"
				+ "For Levenshtein Distance Algorithm press 3");
		
		int input = src.nextInt();
		switch(input){
		case 1:
			System.out.println("Wagner Fischer Algorithm");
			WFMatchMethod wfMatchMethod = new WFMatchMethod();
			wfMatchMethod.performOperation(misspellList, correctList, dictionaryList);
			break;
		case 2:
			System.out.println("Global Edit Distance Algorithm");
			GEDMatchMethod gedMatchMethod = new GEDMatchMethod();
			gedMatchMethod.performOperation(misspellList, correctList, dictionaryList);
			break;
		case 3:
			System.out.println("Levenshtein Distance Algorithm");
			LDMatchMethod ledMatchMethod = new LDMatchMethod();
			ledMatchMethod.performOperation(misspellList, correctList, dictionaryList);
			break;
		default:
			System.out.println("For Wagner Fischer matching method press 1,"
					+ " For Global Edit Distance matching method press 2"
					+ " For Levenshtein Distance matching method press 3");
		}
		
		src.close();
	}
	
}
