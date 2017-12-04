import java.io.File;

public class ElectionProcess {
	/*
	 * gets the file that we write after all voters vote through GUI; File
	 * should contain:
	 * 
	 * Every Voter's vote
	 */
	File candidates = new File("candidates.txt");
    //Singleton design pattern
	private static ElectionProcess oneElection = null;
	private static boolean electionAvailable = true;

	private ElectionProcess(String URL, String Method) {
		this.beginElection();
		
	}
	
	protected static int removalChoice() {
		//  This is where I need an integer from 1 to 3 to be returned for removal choice
		int removalChoice = -1;
		return removalChoice;
	}

	public static ElectionProcess getInstance(String URL, String Method) {
		//if there hasn't been an election
		if (electionAvailable) {
			//double check to see if there's no instantiation
			if (oneElection == null) {
				//create an election
				oneElection = new ElectionProcess(URL, Method);
			}
			//an election is available, so set to false
			electionAvailable = false;
			//returns the election
			return oneElection;
		} else {
			//if there has been an election, return nothing
			return null;
		}
	}

	// starts the election
	public void beginElection() {

	}

	// ends the election
	public void endElection() {

	}

	// takes each voter's votes from the textfile
	public void indVote() {

	}

	// displays the winning candidate
	public void displayWinner() {

	}

	// assigns candidate their primary key
	private void assignCandNum() {

	}

}
