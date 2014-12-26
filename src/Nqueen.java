
public class Nqueen {

	public static void main(String[] args) {
		GA g=new GA();
		g.initialPopulation();
		g.FitnessCalc(g.currentPopulation);
		
		int iteration=1;
		while (true) {
			g.newGeneration();
			g.crossover();
			g.mutation();
			g.FitnessCalc(g.newGeneration);
			g.sort(g.newGeneration);
			g.currentPopulation=g.newGeneration;
			if(g.newGeneration[0][8]==0)
			{				
				System.err.print("Iteration: "+iteration+"\t");
				g.printSolution();
				break;
			}
			else {
				iteration++;
				continue;
			}

		}
	}
}
