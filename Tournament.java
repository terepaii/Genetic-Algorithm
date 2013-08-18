import java.util.Random;
import java.io.PrintWriter;
public class Tournament {
	
	private int k;
	private int generations;
	private int probability;
	private double reproduction;
	private double crossover;
	private double mutation;
	private String name;
	private Individual[] population;
	private Individual[] matingPool;
	private PrintWriter printwriter;
	private Random rand;
	
	/**
	 * Tournament is the controller class for the algorithm 
	 * and takes a lot of parameters to manage the other classes
	 * The constructor sets up the conditions needed for the 
	 * tournament to run
	 */
	public Tournament(int k,
					  String name,
	 				  Individual[] population,
	 				  int generations,
	 				  double reproduction,
	 				  double crossover,
	 				  PrintWriter printwriter,
	 				  Random rand)
	{
		this.k = k;
		this.name = name;
		this.population = population;
		this.generations = generations;
		this.reproduction = reproduction * 100;
		this.crossover = crossover * 100;
		this.mutation = mutation * 100 ;
		this.printwriter = printwriter;
		this.rand = rand;
		matingPool = new Individual[population.length];
	}
	
	
	/**
	 * Prints out the statistics from the population and from 
	 * that population randomly selects k Individuals and chooses
	 * the best out of those three and adds it to the mating pool
	 */
	public void select()
	{
		printwriter.println(getAverage(population) + " " + getBest(population) 
			                + " " + getBestIndividual(population).getDecoded());
		
		if(getBest(population) == name.length())
		{	
		   printwriter.close();
		   System.exit(0);
		}
		
	    Individual[] selections  = new Individual[k];
	    Individual fittestIndividual ;
	    int fittest;
		for(int i = 0 ; i < matingPool.length ; i++)
		{

			for(int j  = 0 ; j < k ; j++)
			{
				selections[j] = population[rand.nextInt(population.length)];
			}
			
			fittestIndividual = selections[0];
            fittest = fittestIndividual.getFitness();
			
			for(int l = 0 ; l < selections.length ; l++)
			{
				if(selections[l].getFitness() >= fittest)
				{
					fittestIndividual = selections[l];
					fittest = selections[l].getFitness();
				}
			}
			matingPool[i] = fittestIndividual;
		}
	}
	
	
	
	/**
	 * Main method in the tournament class, Basically runs in a loop
	 * given the number of generations at the start , it selects given
	 * the probabilities at the command line whether to do mutation, 
	 * reproduction or crossover
	 */
	public void runTournament()
	{
		int popSize ;
		int crossoverPoint ;
		int mutationPoint ;
		char temp ;
		StringBuilder firstBitS ;
		StringBuilder secondBitS ;
		StringBuilder firstNewBitS ;
		StringBuilder secNewBitS ;
		Individual firstInd ;
		Individual secondInd ;
		
		for(int i = 0 ; i < generations; i++)
		{		
			printwriter.print(i + " ");
			select();
			
			popSize = 0;
		    
			while(popSize < population.length)
			{
				
				crossoverPoint = 0;
				mutationPoint = 0;
				firstBitS = new StringBuilder("");
				secondBitS = new StringBuilder("");
				firstNewBitS = new StringBuilder("");
			    secNewBitS = new StringBuilder("");
			    firstInd = null;
			    firstInd = null;
			    

				probability =  rand.nextInt(100) + 1;
				
				if(probability <= reproduction)
				{
					firstInd  = matingPool[rand.nextInt(matingPool.length)];
					population[popSize] = firstInd;
					popSize++;
				}
				
				else if (probability > reproduction && probability <= reproduction + crossover && popSize < population.length - 2)
				{
					firstInd = matingPool[rand.nextInt(matingPool.length)];
					secondInd = matingPool[rand.nextInt(matingPool.length)];
					
					crossoverPoint = rand.nextInt(firstInd.getBitString().length()-1);
					
					firstBitS = firstInd.getBitString();
					secondBitS = secondInd.getBitString();
					
					for(int k = 0 ; k < crossoverPoint ; k++)
					{
						firstNewBitS.append(firstBitS.charAt(k));
						secNewBitS.append(secondBitS.charAt(k));
					}
					
					for(int j = crossoverPoint ; j < firstBitS.length() ; j++)
					{
						temp = firstBitS.charAt(j);
						firstNewBitS.append(temp);
						
						temp = secondBitS.charAt(j);
						secNewBitS.append(temp);
					}
					
					firstInd = new Individual(firstNewBitS,name);
					secondInd = new Individual(secNewBitS,name);
					
					population[popSize] = firstInd;
					population[popSize + 1] = secondInd;
					
					popSize+=2;
					
				}
				
				else
				{
					firstInd = matingPool[rand.nextInt(matingPool.length)];
					
					firstBitS = firstInd.getBitString() ;
					
					mutationPoint = rand.nextInt(firstBitS.length());
					
					for(int j = 0 ; j < firstBitS.length() ; j++)
					{
						if(j == mutationPoint)
						{
							if(firstBitS.charAt(mutationPoint) == '1')
							{
								firstNewBitS.append('0');
							}
							else
							{
								firstNewBitS.append('1');
							}
						}
						else
						{
							firstNewBitS.append(firstBitS.charAt(j));
						}
					}
					
					firstInd = new Individual(firstNewBitS,name);
				
					population[popSize] = firstInd;
					
					popSize++;
					
				}
			}	
		}
		printwriter.close();
	}
	
	
	/**
	 * Gets the average fitness of the population 
	 * passed in 
	 */
	public float getAverage(Individual[] population)
	{
		float total = 0;
		for(int i = 0 ; i < population.length ; i++)
		{
			total += population[i].getFitness();
		}
		return total/population.length;
	}
	
	/**
	 * Gets the best fitness from the population
	 * passed in 
	 */
	public int getBest(Individual[] population)
	{
		int best = 0 ;
		best = population[0].getFitness();
		for(int i = 1 ; i < population.length ; i++)
		{
			if(population[i].getFitness() > best)
				best = population[i].getFitness();
		}
		
		return best ;
	}
	
	/**
	 * Gets the best Individual from the population
	 * passed in 
	 */
	public Individual getBestIndividual(Individual[] population)
	{	
		Individual best = population[0];
		for(int i = 1 ; i < population.length ; i++)
		{
			if(population[i].getFitness() > best.getFitness())
				best = population[i];
		}
		return best ;
	}
}
