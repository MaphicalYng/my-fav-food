package cf.choosefood;

import java.io.IOException;
import java.util.Scanner;

import cf.data.Data;
import cf.data.datasave.FoodsToChoose;
import cf.data.datasave.UpdateFoodsResult;

public class ChooseFood {

	public static void main(String[] args) throws IOException {
		
		// Get data object.
		Data data = new Data();
		
		// Get foods to choose.
		FoodsToChoose foodsToChoose = data.GetFoodsToChoose();
		
		// Get last five meals.
		String[] lastFiveMeals = data.LastFiveMeals();
		
		// Get a target food.
		GetFood getFood = new GetFood(foodsToChoose);
		Scanner reader = new Scanner(System.in);
		String answer = null;
		String todayFood = null;
		do {
			
			todayFood = getFood.GetAKindOfFood(lastFiveMeals);
		
			// Give the new kind of food.
			System.out.println("This meal is " + todayFood + ".");
			
			// Have the answer from user.

			if (!todayFood.equals("OUT OF BOUND") && !todayFood.equals("")) {
				System.out.println("Will you have it?(y/n)");
				answer = reader.nextLine();
			} else {
				answer = "n";
			}
			
			// If user won't have today's food, don't log food.
			if (answer.equals("y")) {
				
				// Update "last five meals".
				UpdateFoodsResult lastFiveMealsUpdatedResult = null;
				lastFiveMealsUpdatedResult = data.UpdateFiveMeals(todayFood, lastFiveMeals);
				
				// Update "last five meals" in file.
				data.NoteFiveMeals(lastFiveMealsUpdatedResult.GetUpdatedFoods());
				
				// Update log in file.
				data.NoteLogFile(lastFiveMealsUpdatedResult.GetFoodToLog());
			}
		} while (todayFood.equals("OUT OF BOUND") || todayFood.equals(""));
		
		reader.close();
		
		// End the program.
		return;
	}

}
