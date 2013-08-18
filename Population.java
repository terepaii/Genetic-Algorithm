import java.util.Random;

public class Population {
	
	private Individual[] population;
	private int popSize;
	private String name;
	private Random rand;
	
	
	/**
	 * Sets up the conditions necessary 
	 * for the population allocation of space
	 * and creation
	 */
	public Population(int popSize, Random rand, String name)
	{
		population = new Individual[popSize];
		this.popSize = popSize;
        this.rand = rand ;
		this.name = name;
	}
	
	
	/**
	 * Creates a random population, given a
	 * particular seed from the constructor it
	 * adds either a 1 or a 0 
	 */
	public void createPopulation()
	{		
		int bitSize = name.length() * 7;
		
		for(int i = 0; i < popSize ; i++)
		{
			StringBuilder bitString = new StringBuilder("");
			for(int j = 0 ; j< bitSize ; j++)
			{
				bitString.append(rand.nextInt(2));
			}
			population[i] = new Individual(bitString,name);
		}	
	}
	
	
	/**
	 * Returns the current population of
	 * Individuals
	 */
	public Individual[] getPop()
	{
		return population;
	}
}

