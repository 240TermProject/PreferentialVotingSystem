import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Iterator {
	
	//The round that we're on; Do we need one here and in the Round class if they're both protected?
	// The answer to that question is no
	protected int roundNum = 1;
	//Candidate with the highest amount of votes
	protected String highCand;
	//Candidate with the lowest amount of votes; Do we need one here and in the Round class if they're both protected?
	// We can actually delete these variables as they are not needed, high and low cand is now an "Entry"
	protected String lowCand;
	// Making max and min class variables 
	protected static Entry<String,Integer> minEntry = null;
	protected static Entry<String,Integer> maxEntry = null;
	private static String winner = null;

	
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
		if (!checkMajority(voteTallies, size)) {
			initiateRound(votes, voteTallies);
		}
		
	}
	
	//checks to see if a candidate has more than 50% of the vote
	private static boolean checkMajority(HashMap<String, Integer> tallied, int numberOfVotes){
		

		for(Entry<String,Integer> entry : tallied.entrySet()) {
		    if (maxEntry == null || entry.getValue() > maxEntry.getValue()) {
		        maxEntry = entry;
		    }
		}
	
		// Declare winner if the maxEntry has >50% 
		if (maxEntry.getValue()/numberOfVotes > 0.5) {
			double percentage = maxEntry.getValue()/numberOfVotes;
			setWinner(maxEntry.getKey(),percentage);
			return true;
		}
		
		
		for(Entry<String,Integer> entry : tallied.entrySet()) {
		    if (minEntry == null || entry.getValue() < minEntry.getValue()) {
		        minEntry = entry;
		    }
		}
		return false;
		
		
	}
	
	//Starts the round, or starts another round
	private static void initiateRound(ArrayList<ArrayList<String>> votes, HashMap<String, Integer> voteTallies){
		List<String> losers = Round.checkTie(voteTallies, minEntry);
		if (losers.size() == 1) {
		votes = Round.removeLow(votes, minEntry.getKey());
		} else {
			String loser = Round.tieBreakerOne(votes, losers);
		}
	}
	
	//Sends the winner to the ElectionProcess
	private static void setWinner(String name, double percentageOfVote){
		winner = (name + " | " + percentageOfVote);
	}
	protected static String getWinner() {
		
		return winner;
	}
}
