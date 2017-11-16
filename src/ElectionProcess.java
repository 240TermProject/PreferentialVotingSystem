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
	
	//default constructor
	public ElectionProcess(){
		
	}
	
	//starts the election
	public void beginElection() throws FileNotFoundException{
		Scanner s = new Scanner(candidates);
		String nextline = "";
		String votes = "";
		String cand = "";
		while(s.hasNextLine()) {
			nextline = s.nextLine();
			System.out.println(nextline);
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
