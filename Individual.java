
public class Individual {

	private StringBuilder decodedString;
	private StringBuilder bitString;
	private Decoder decoder;
	private Evaluator evaluator;
	private int fitnessLevel;
	
	/**
	 * Sets up the Individual, also set the
	 * Individual to be evaluated and decoded
	 */
	public Individual(StringBuilder bitString, String name)
	{
		decoder = new Decoder();
		decodedString = decoder.decode(bitString);
		evaluator = new Evaluator(name);
		evaluator.evaluate(decodedString);
		fitnessLevel = evaluator.getFitnessLevel();
		this.bitString = bitString;
	}
	
	/**
	 * Gets the fitness level of 
	 * this Individual
	 */
	public int getFitness()
	{
		return fitnessLevel;
	}
	
	/**
	 * Gets the decoded character version of 
	 * this Individual from  the bit-string
	 * in the constructor
	 */
	public StringBuilder getDecoded()
	{
		return decodedString;
	}
	
	/**
	 * Gets the bit-string of this Individual 
	 */
	public StringBuilder getBitString()
	{
		return bitString;
	}
}
