
public class Evaluator {
	
	private String name;
	
	private int fitnessLevel;
	
	
	
	/**
	 * Constructor, sets up the initial 
	 * parameters needed to evaluate an 
	 * Individual 
	 */
	public Evaluator(String name)
	{
		this.name = name;
	}
	
	
	/**
	 * Evaluates a given string based on the name
	 * passed to the constructor and for every 
	 * correct character in the correct place, 
	 * the fitness level rises
	 */
	public void evaluate(StringBuilder decodedString)
	{
		fitnessLevel = 0;
		for(int i = 0; i < decodedString.length(); i++)
		{
			if(decodedString.charAt(i) == name.charAt(i))
			{
				fitnessLevel++;
			}
		}
	}

	/**
	 * Get the fitness level of the current 
	 * string of characters being evaluated
	 */
	public int getFitnessLevel()
	{
		return fitnessLevel;
	}
}
