package cf.choosefood;

import cf.data.datasave.FoodsToChoose;

/**
 * This class is designed to compute one kind 
 * of food that is different from any kind of 
 * food in "last five meals", according to the 
 * probability of every level of foods.
 * 
 * @author Shuolin Yang
 *
 */
public class GetFood {
	
	public GetFood(FoodsToChoose foodsInput) {
		foods = foodsInput;
	}
	
	public String GetAKindOfFood(String[] lastFiveMeals) {
		
		// Get a random number as probability.
		double random = Math.random();
		
		// According to probability, find foods of a level.
		double point1 = foods.GetProLevel1();
		double point2 = foods.GetProLevel1()+ foods.GetProLevel2();
		String[] target;
		if (random > 0.0 && random < point1) {
			target = foods.GetFoodsLevel1();
		} else if (random > point1 && random < point2) {
			target = foods.GetFoodsLevel2();
		} else {
			target = foods.GetFoodsLevel3();
		}
		
		// Pick a kind of food which is not the same as any kind
		// of food in "last five meals".
		int monitor = 0;
		String tempFood = null;
		do {
			
			// To avoid looping forever.
			if (monitor == 2 * target.length) {
				return new String("OUT OF BOUND");
			}
			
			// Get a random kind of food.
			int index = (int)(Math.random() * (double)target.length );
			tempFood = target[index];
			
			monitor++;
		} while (IfStringArrayHas(tempFood, lastFiveMeals));
		
		// If has a kind of food which is not in "last five meals".
		return tempFood;
	}
	
	private boolean IfStringArrayHas(String target, String[] array) {
		int count;
		for (count = 0; count < array.length; count++) {
			if (array[count].equals(target)) {
				break;
			}
		}
		if (count == array.length) {
			return false;
		} else {
			return true;
		}
	}
	
	private FoodsToChoose foods;
}
