package cf.data;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Scanner;

import cf.data.datasave.FoodsToChoose;
import cf.data.datasave.UpdateFoodsResult;

public class Data {
	
	public Data() {
		tempFilePath = "Temp.txt";
		logFilePath = "Log.txt";
		foodsSaveFilePath = "Foods.txt";
	}
	
	public void NoteFiveMeals(String[] food) throws IOException {
		
		// Define writer of five-meals temp file.
		PrintWriter tempFileWriter = null;
		tempFileWriter = new PrintWriter(tempFilePath, "utf-8");
		
		// Write the five-meals array into temp file.
		for (String currentFood : food) {
			tempFileWriter.println(currentFood);
		}
		tempFileWriter.close();
	}
	
	public String[] LastFiveMeals() throws IOException {
		
		// Define the scanner of five-meals temp file.
		Scanner tempFileScanner = null;
		tempFileScanner = new Scanner(Paths.get(tempFilePath), "utf-8");
		
		// Define the returning string array.
		String[] foods = new String[5];
		
		// Get the five-meals content.
		for (int count = 0; count < foods.length; count++) {
			foods[count] = tempFileScanner.nextLine();
		}
		tempFileScanner.close();
		return foods;
	}
	
	public void NoteLogFile(String food) throws IOException {
		
		// Define the scanner of log file.
		Scanner logFileScanner = null;
		logFileScanner = new Scanner(Paths.get(logFilePath), "utf-8");
		
		// Define the log copy-temp file.
		String copyTempFile = "temp_copy.txt";
		
		// Define the writer of log copy-temp file.
		PrintWriter copyWriter = null;
		copyWriter = new PrintWriter(copyTempFile, "utf-8");
		
		// Copy the content of log file to copy-temp file and add a latest meal.
		String copyTemp = null;
		while (logFileScanner.hasNextLine()) {
			copyTemp = logFileScanner.nextLine();
			copyWriter.println(copyTemp);
		}
		copyWriter.println(food);
		logFileScanner.close();
		copyWriter.close();
		
		// Define the writer of log file.
		PrintWriter logFileWriter = null;
		logFileWriter = new PrintWriter(logFilePath, "utf-8");
		
		// Define the scanner of log copy-temp file.
		Scanner copyScanner = null;
		copyScanner = new Scanner(Paths.get(copyTempFile), "utf-8");
		
		// Copy the content of copy-temp file to log file.
		while (copyScanner.hasNextLine()) {
			copyTemp = copyScanner.nextLine();
			logFileWriter.println(copyTemp);
		}
		copyScanner.close();
		logFileWriter.close();
	}
	
	public UpdateFoodsResult UpdateFiveMeals(String newFood, String [] foods) {
		
		// Define the old food.
		String oldFood = foods[0];
		
		// Update the foods.
		int count;
		for (count = 1; count < foods.length; count++) {
			foods[count - 1] = foods[count];
		}
		foods[count - 1] = newFood;
		
		// Return result.
		return new UpdateFoodsResult(oldFood, foods);
	}
	
	/**
	 * Format of file is:<br>
	 * The first line saves summary number of all foods.<br>
	 * The second line saves summary number of level1 foods.<br>
	 * The third line saves summary number of level2 foods.<br>
	 * The fourth line saves summary number of level3 foods.<br>
	 * The rest lines save food lines.
	 * 
	 * @return Data of foods to choose.
	 * @throws IOException
	 */
	public FoodsToChoose GetFoodsToChoose() throws IOException {
		
		// Define a scanner.
		Scanner scanner = null;
		scanner = new Scanner(Paths.get(foodsSaveFilePath), "utf-8");
		
		// Get the summary number of foods.
		int summary = scanner.nextInt();
		String[] allFoods = new String[summary];
		
		// Get every level of foods' summary number.
		int[] levelsNum = new int[3];
		for (int count = 0; count < 3; count++) {
			levelsNum[count] = (int)scanner.nextInt();
		}
		
		// Get all foods.
		for (int count = 0; count < summary; count++) {
			allFoods[count] = scanner.nextLine();
		}
		
		// Close the scanner.
		scanner.close();
		
		// Divide foods into three arrays.
		String[][] levelsFoods = new String[3][];
		levelsFoods[0] = new String[levelsNum[0]];
		levelsFoods[1] = new String[levelsNum[1]];
		levelsFoods[2] = new String[levelsNum[2]];
		int summaryCount = 0;
		for (int levelCount = 0; levelCount < 3; levelCount++) {
			for (int foodCount = 0; foodCount < levelsNum[levelCount]; foodCount++) {
				levelsFoods[levelCount][foodCount] = allFoods[summaryCount++];
			}
		}
		
		// Return the food to choose object.
		return new FoodsToChoose(levelsFoods[0], levelsFoods[1], levelsFoods[2]);
	}
	
	private String foodsSaveFilePath;
	private String tempFilePath;
	private String logFilePath;
}
