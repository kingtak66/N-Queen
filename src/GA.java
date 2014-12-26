import java.util.Random;


public class GA {
	private final int n=9;
	private final int p=200;
	int[][] currentPopulation=new int[p][n];
	int[][] newGeneration=new int[p][n];

	//Create population in p size and Initial chromosome  from 0 to n and call shuffle method to shuffle gens
	public void initialPopulation() {
		Random r = new Random();
		for (int i = 0; i < p; i++) {
			for (int j = 0; j < n-1; j++) {
				currentPopulation[i][j]=j;
			}
		}

		shuffle(currentPopulation);
	}

	//New Generation is half of the population that their fitness is good 
	public void newGeneration() {
		sort(currentPopulation);
		for (int i = 0; i < p/2; i++) {
			for (int j = 0; j <n ; j++) {
				newGeneration[i][j]=currentPopulation[i][j];
			}
		}
		sort(currentPopulation);
	}

	//Fitness Calculation
	public void FitnessCalc(int[][] population)
	{
		int fitness=0;
		for (int k = 0; k < p; k++) {
			fitness=0;
			for (int i = 0; i < n-1; i++)
			{
				for (int j = i+1; j < n-1; j++)
				{
					if(i!=j)
					{
						if( population[k][i]==population[k][j] || Math.abs(i-j)==Math.abs(population[k][i]-population[k][j]))
							fitness++;	
					}
				}
			}
			population[k][n-1]=fitness;
		}
	}


	//Return the fitness of chromosome with certain index
	public int getFitness(int[][] population,int index) {
		int fitness=0;
		fitness=population[index][8];	
		return fitness;	
	}

	//Cross over chromosoms by an random crossOver point
	public void crossover() {
		Random rand=new Random();
		int crossoverPoint=rand.nextInt(7);

		for (int i = 0; i < (p/2); i+=2) {
			for (int j = 0; j <n ; j++) {
				if(j<crossoverPoint)
				{
					newGeneration[i+(p/2)][j]=newGeneration[i][j];
					newGeneration[i+(p/2)+1][j]=newGeneration[i+1][j];
				}
				else {
					newGeneration[i+(p/2)][j]=newGeneration[i][j];
					newGeneration[i+(p/2)+1][j]=newGeneration[i][j];
				}
			}
		}	
	}
	//Mutation Code
	public void mutation() {
		Random rand=new Random();
		for (int i = 0; i < newGeneration.length-1; i++) {
			newGeneration[i][rand.nextInt(n-2)]=rand.nextInt(n-1);
			newGeneration[i+1][rand.nextInt(n-1)]=rand.nextInt(n-1);
		}
	}
	//give an population and check if that ha a Solution
	public boolean isSolution(int[][] population) {
		for (int i = 0; i < population.length; i++) {
			for (int j = 0; j < n; j++) {
				if(population[i][n-1]==0)
					return true;
			}
		}
		return false;

	}


	//Sort population By each chromosome Fitness
	public void sort(int[][] populatin) 
	{

		for (int i = 0; i < p; i++) 
		{
			for (int j = i+1; j < p; j++)
			{
				if(getFitness(populatin,i)>getFitness(populatin,j))
					swap(populatin,i, j);
			}
		}	
	}
	//Swap two chromosome in population
	public void swap(int[][] population,int a,int b) {
		int temp;
		for (int j = 0; j < n; j++) {
			temp=population[a][j];
			population[a][j]=population[b][j];	
			population[b][j]=temp;	
		}
	}
	//Print a chromosome that fitness is 0
	public void printSolution() {
		for (int i = 0; i < n; i++) {
			System.err.print(newGeneration[0][i]+" ");
		}	
	}
	//print all chromosome of population
	public void print(int[][] population) {
		for (int i = 0; i < p; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(population[i][j]+" ");
			}
			System.out.println();
		}

	}


	public	void shuffle(int[][] a) {
		Random random = new Random();

		for (int i = p-1; i > 0; i--) {
			for (int j = n-2; j > 0; j--) {
				int n = random.nextInt(j );

				int temp = a[i][j];
				a[i][j] = a[i][n];
				a[i][n] = temp;
			}
		}
	}

}
