import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ElectionProcess {
	/*
	 * gets the file that we write after all voters vote through GUI;
	 * File should contain:
	 * 	
	 * 	Every Voter's vote
	 */
	File candidates = new File("candidates.txt");
	File votes = new File("voters.txt");
	
	//default constructor
	public ElectionProcess(){
		
	}
	
	//starts the election
	public void beginElection() throws FileNotFoundException{
		Scanner scanCands = new Scanner(candidates);
		int candNum = 0;
		while(scanCands.hasNextLine()) {
			//System.out.println(scanCands.nextLine());
			Candidate newCand = new Candidate(candNum, scanCands.nextLine());
			//Need to add the candidate to a list in iterator
			candNum++;
		}
		Scanner scanVoters = new Scanner(votes);
		while(scanVoters.hasNextLine()) {
			System.out.println(scanVoters.nextLine());
			Voter newVoter = new Voter(scanVoters.nextLine());
			int[] ranks = new int[candNum];
			//add try catch for null ranks/votes inn the index i
			for (int i = 0; i < candNum; i++){
				ranks[i] = scanVoters.nextInt();
			}
			newVoter.loadVote(ranks);
			//Need to add the voter to a list in iterator
			
		}
	}
	
	//ends the election
	public void endElection(){
		
	}
	
	//takes each voter's votes from the textfile
	public void indVote(){
		
	}
	
	//displays the winning candidate
	public void displayWinner(){
		
	}
	
	//assigns candidate their primary key
	private void assignCandNum(){
		
	}
	
}
