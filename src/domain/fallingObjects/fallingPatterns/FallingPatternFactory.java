package domain.fallingObjects.fallingPatterns;

public class FallingPatternFactory {

	private static FallingPatternFactory instance;
	private FallingPatternFactory() {}

	public static FallingPatternFactory getInstance() {
		if (instance == null) {
			instance = new FallingPatternFactory();
		}
		return instance;
	}
	
	//Assigning corresponding falling patterns to atom types 
	public IFallingPattern getFallingPattern(String type) {
		if(type.contains("alpha")) {
			return new ZigZagPattern();
		}else if(type.contains("beta")) {
			return new HalfZigZagPattern();
		}else if(type.contains("gamma")) {
			return new QuarterZigZagPattern();
		}else {
			return new StraightPattern();
		}
	}
}

