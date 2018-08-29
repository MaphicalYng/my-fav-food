package cf.data.datasave;

public class UpdateFoodsResult {
	
	public UpdateFoodsResult(String oldFood , String[] foods) {
		foodToLog = oldFood;
		updatedFoods = foods;
	}
	
	public String GetFoodToLog() {
		return foodToLog;
	}
	
	public String[] GetUpdatedFoods() {
		return updatedFoods;
	}
	
	private String[] updatedFoods;
	private String foodToLog;
}
