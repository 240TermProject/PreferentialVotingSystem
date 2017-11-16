import java.io.FileNotFoundException;

public class Election {

	public static void main(String[] args) throws FileNotFoundException {
		//do stuff
		ElectionProcess election = new ElectionProcess();
		election.beginElection();
	}
	
	/*
	 * There will be two ways to handle ties. 
	 * Depending on what's returned, we will handle the ties differently.
	 */
	public static void pickTieBreaker(){
		
	}

}
