package cf.data.datasave;

public class FoodsToChoose {
	
	/**
	 * The foods are divided into three levels(1, 2, 3).
	 */
	public FoodsToChoose(String[] level1, String[] level2, String[] level3) {
		probabilityLevel1 = 0.6;
		probabilityLevel2 = 0.3;
		probabilityLevel3 = 0.1;
		foodsLevel1 = level1;
		foodsLevel2 = level2;
		foodsLevel3 = level3;
	}
	
	public double GetProLevel1() {
		return probabilityLevel1;
	}
	
	public double GetProLevel2() {
		return probabilityLevel2;
	}
	
	public double GetProLevel3() {
		return probabilityLevel3;
	}
	
	public String[] GetFoodsLevel1() {
		return foodsLevel1;
	}
	
	public String[] GetFoodsLevel2() {
		return foodsLevel2;
	}
	
	public String[] GetFoodsLevel3() {
		return foodsLevel3;
	}
	
	private double probabilityLevel1;
	private double probabilityLevel2;
	private double probabilityLevel3;
	private String[] foodsLevel1;
	private String[] foodsLevel2;
	private String[] foodsLevel3;
}
