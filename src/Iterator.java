import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Iterator {
	
	//The round that we're on; Do we need one here and in the Round class if they're both protected?
	protected int roundNum = 1;
	//Candidate with the highest amount of votes
	protected String highCand;
	//Candidate with the lowest amount of votes; Do we need one here and in the Round class if they're both protected?
	protected String lowCand;
	//Added this to manage candidate names.

	
	public Iterator(ArrayList<ArrayList<String>> votes){
		tallyVotes(votes);
	}
	
	/*
	 * tallies how many votes each candidate has,
	 * We will need the ArrayList of votes from the Votes class
	 */
	private static void tallyVotes(ArrayList<ArrayList<String>> votes){
		int size = votes.size();
		HashMap<String, Integer> voteTallies = new HashMap<>();
		for (int i = 0; i < votes.size(); i++) {
			// This will need to be edited depending on what we pass into Iterator... whether it included voter ID or not
			for (int j = 1; j < votes.get(i).size(); j++) {
				voteTallies.put(votes.get(i).get(j), voteTallies.getOrDefault(votes.get(i).get(j), 0) + 1);
			}
		}
		checkMajority(voteTallies, size);
		
	}
	
	//checks to see if a candidate has more than 50% of the vote
	private static void checkMajority(HashMap<String, Integer> tallied, int numberOfVotes){
		Entry<String,Integer> maxEntry = null;

		for(Entry<String,Integer> entry : tallied.entrySet()) {
		    if (maxEntry == null || entry.getValue() > maxEntry.getValue()) {
		        maxEntry = entry;
		    }
		}
	
		// Declare winner if the maxEntry has >50% 
		if (maxEntry.getValue()/numberOfVotes > 0.5) {
			double percentage = maxEntry.getValue()/numberOfVotes;
			setWinner(maxEntry.getKey(),percentage);
		}
		Entry<String,Integer> minEntry = null;
		
		for(Entry<String,Integer> entry : tallied.entrySet()) {
		    if (minEntry == null || entry.getValue() < minEntry.getValue()) {
		        minEntry = entry;
		    }
		}
		
	}
	
	//Starts the round, or starts another round
	private void initiateRound(){
		
	}
	
	//Sends the winner to the ElectionProcess
	private static String setWinner(String name, double percentageOfVote){
		return (name + " | " + percentageOfVote);
	}
	public static String getWinner(String winner) {
		return winner;
	}
}
