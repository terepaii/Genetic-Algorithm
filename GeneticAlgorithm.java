import java.util.Random;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class GeneticAlgorithm {

	private static String name = "String to decode";
	
	public static void main(String[] args) throws Exception {

	    int arg1 = 0;
		int arg2 = 0;
		double arg3 = 0;
		double arg4 = 0;
	    double arg5 = 0;
		int seed = 10123164;
		PrintWriter printwriter = null;
		Random rand = new Random(seed);
		
		
		if(args.length < 6 )
		{
			System.err.print("Incorrect number of arguments");
			System.exit(0);
		}
		
		
		try
		{
		    arg1 = Integer.parseInt(args[0]);
		    arg2 = Integer.parseInt(args[1]);
		    arg3 = Double.parseDouble(args[2]);
		    arg4 = Double.parseDouble(args[3]);
		    arg5 = Double.parseDouble(args[4]);
		}
		catch(NumberFormatException e)
		{
			System.err.print("Invalid arguments passed");
                        System.exit(0);
		}
		
		if(arg1 < 0 || arg2 <0 || arg3 < 0 || arg4 <0 || arg5 <0)
		{
			System.err.print("Invalid arguments passed - all numbers must be greater than zero");
			System.exit(-1);
		}
		else if (arg1 > 100000 || arg2 > 100000)
		{
			System.err.print("Invalid arguments passed - first two arguments must be less than 100,000");
			System.exit(-1);
		}
		else if (arg3 > 1.0 || arg4 > 1.0 || arg5 > 1.0)
		{
			System.err.print("Invalid arguments passed - last three arguments must be less than or equal to 1.0");
			System.exit(-1);
		}
		
		
		double probs = arg3 + arg4 + arg5;  
		
		if(probs != 1.0)
		{
			System.err.print("Arguments three to five combined must equal 1.0");
			System.exit(-1);
		}
		
		File file = new File(args[5]);
		
		try
		{
			printwriter = new PrintWriter(file);
		}
		catch(FileNotFoundException e)
		{
			System.err.print("File not found");
                        System.exit(0);
		}
				
		printwriter.println(arg1 + " "+arg2 + " " + arg3 + " " + arg4 + " " + arg5 +" " + seed);
        
		    Population population = new Population(arg1,rand,name);
		    population.createPopulation();
		
		    
		    Tournament tournament = new Tournament(5,name,population.getPop(),arg2,arg3,arg4,printwriter,rand);
		    tournament.runTournament();
		
	}
}
