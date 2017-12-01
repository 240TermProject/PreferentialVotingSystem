import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class Votes{
	//Contains all of the voter's globalIDs 
	private ArrayList <String> voterIDs = new ArrayList <String>();
	//Contains all of a voter's individual votes
	private ArrayList <ArrayList<String>> indVotes = new ArrayList <ArrayList<String>>();
	
	public Votes(){
		
	}
	
	//check to see if we used this globalID before
	public boolean checkID(String voterID){
		if (!voterIDs.contains(voterID)) {
			voterIDs.add(voterID);
			return true;
		} else {
			return false;
		}
	}
	
	/*
	 * creates a Voter class with the voter's GlobalID, then loads the voter's votes,
	 * OR stores a voter's votes into indVotes.
	 */
	public void storeVote(int[] indVote) throws IOException{
		URL rawResults = new URL("http://www.acmaitp.org/test.txt");
		Scanner dataScanner = new Scanner(rawResults.openStream()).useDelimiter("[,|\n|,\r]+");
		while (dataScanner.hasNextLine()) {
			String vote = dataScanner.nextLine();
			String[] data = vote.split("[,]");
			
			if (!checkID(data[0])) {
				continue;
			} else {
				ArrayList<String> votes = new ArrayList<String>();
				for (int i = 1; i < data.length; i++) {
					votes.add(data[i]);
				}
				indVotes.add(votes);
			}
			
			
		}
		
	}
	
}
