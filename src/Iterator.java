import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Iterator {

	// The round that we're on; Do we need one here and in the Round class if
	// they're both protected?

	// Candidate with the highest amount of votes
	protected String highCand;
	// Candidate with the lowest amount of votes; Do we need one here and in the
	// Round class if they're both protected?
	// We can actually delete these variables as they are not needed, high and low
	// cand is now an "Entry"
	protected String lowCand;
	protected static double percentage;
	// Making max and min class variables
	//protected static Entry<String, Integer> minEntry = null;
	//protected static Entry<String, Integer> maxEntry = null;
	private static String winner = null;

	public Iterator(ArrayList<ArrayList<String>> votes) {
		tallyVotes(votes);
	}

	/*
	 * tallies how many votes each candidate has, We will need the ArrayList of
	 * votes from the Votes class
	 */
	protected static void tallyVotes(ArrayList<ArrayList<String>> votes) {
		int size = votes.size();
		HashMap<String, Integer> voteTallies = new HashMap<String, Integer>();
		for (int i = 0; i < votes.size(); i++) {
			// This will need to be edited depending on what we pass into Iterator...
			// whether it included voter ID or not
			// I believe this will cause a logic error... for (int j = 1; j <
			// votes.get(i).size(); j++) {

			// Uncomment the following line and delete the proceeding statement if voter ID
			// is still included in ArrayList
			// voteTallies.put(votes.get(i).get(1),
			// voteTallies.getOrDefault(votes.get(i).get(1), 0) + 1);
			// This is the code if we remove voter ID in the original ArrayList
			voteTallies.put(votes.get(i).get(0), voteTallies.getOrDefault(votes.get(i).get(0), 0) + 1);
		}
		System.out.println(voteTallies);
		Entry<String, Integer> minEntry = null;
		Entry<String, Integer> maxEntry = null;
		for (Entry<String, Integer> entry : voteTallies.entrySet()) {
			if (maxEntry == null || entry.getValue() > maxEntry.getValue()) {
				maxEntry = entry;
			}
		}
		for (Entry<String, Integer> entry : voteTallies.entrySet()) {
			if (minEntry == null || entry.getValue() < minEntry.getValue()) {
				//System.out.println("This should set a new min");
				minEntry = entry;
			}
		}
		if (checkMajority(voteTallies, size, maxEntry)) {
			setWinner(maxEntry.getKey(), percentage);
		} else {	
			initiateRound(votes, voteTallies);
		}

	}

	// checks to see if a candidate has more than 50% of the vote
	protected static boolean checkMajority(HashMap<String, Integer> tallied, int numberOfVotes, Entry<String, Integer> maxEntry2) {
		

		// Declare winner if the maxEntry has >50%
		System.out.println("Percentage: " + (maxEntry2.getValue() / numberOfVotes));
		if ((double)maxEntry2.getValue() / numberOfVotes > 0.5) {
			System.out.println("Winner is set");
			percentage = (double)maxEntry2.getValue() / numberOfVotes;
			//setWinner(maxEntry2.getKey(), percentage);
			return true;
		}

		
		return false;

	}

	// Starts the round, or starts another round
	protected static void initiateRound(ArrayList<ArrayList<String>> votes, HashMap<String, Integer> voteTallies) {
		Entry<String, Integer> minEntry = null;
		Entry<String, Integer> maxEntry = null;
		for (Entry<String, Integer> entry : voteTallies.entrySet()) {
			if (maxEntry == null || entry.getValue() > maxEntry.getValue()) {
				maxEntry = entry;
			}
		}
		for (Entry<String, Integer> entry : voteTallies.entrySet()) {
			if (minEntry == null || entry.getValue() < minEntry.getValue()) {
				//System.out.println("This should set a new min");
				minEntry = entry;
			}
		}
		
		System.out.println("at the start of initiateRound");
		switch (ElectionProcess.methodNum) {
		case -1:
			System.out.println("A fatal error has occured, an invalid removal choice has been passed to Iterator");		
		case 1:
			ArrayList<ArrayList<String>>newvotes = new ArrayList<ArrayList<String>>();
			ArrayList<String> losers = Round.checkTie(voteTallies, minEntry);
			if (losers.size() == 1) {
				System.out.println("iterator.initiateRound - Passing to Round.removeLow: " + minEntry.getKey());
				newvotes = Round.removeLow(votes, minEntry.getKey());
			} else {
				String loser = Round.tieBreakerOne(votes, losers);
				System.out.println("iterator.initiateRound - Passing to Round.removeLow: " + minEntry.getKey());
				newvotes = Round.removeLow(votes, loser);
			}
			tallyVotes(newvotes);
			// May need to create a new hashmap at this point to pass back into checkMajority
			// But, end game, we need to call checkMajority to set winner on all cases.
			break;
		case 2:
			ArrayList<ArrayList<String>>newvotesTwo = new ArrayList<ArrayList<String>>();
			ArrayList<String> losersOne = Round.checkTie(voteTallies, minEntry);
			if (losersOne.size() == 1) {
				System.out.println("iterator.initiateRound - Passing to Round.removeLow: " + minEntry.getKey());

				newvotesTwo = Round.removeLow(votes, minEntry.getKey());
			} else {
				String nameToRemove = Round.tieBreakerTwo(losersOne);
				System.out.println("iterator.initiateRound - Passing to Round.removeLow: " + minEntry.getKey());

				newvotesTwo = Round.removeLow(votes, nameToRemove);
			}
			tallyVotes(newvotesTwo);

			break;
		case 3:
			ArrayList<ArrayList<String>>newvotesThree = new ArrayList<ArrayList<String>>();
			ArrayList<String> losersTwo = Round.checkTie(voteTallies, minEntry);
			newvotesThree  = Round.removeAll(votes, losersTwo);
			tallyVotes(newvotesThree);

			break;
		default:
			ArrayList<String> losersThree = Round.checkTie(voteTallies, minEntry);
			if (losersThree.size() == 1) {
				//votes = Round.removeLow(votes, minEntry.getKey());
			} else {
				String loser = Round.tieBreakerOne(votes, losersThree);
				//votes = Round.removeLow(votes, loser);
			}
			tallyVotes(votes);

			break;
		}

//		List<String> losers = Round.checkTie(voteTallies, minEntry);
//		if (losers.size() == 1) {
//			votes = Round.removeLow(votes, minEntry.getKey());
//		} else {
//			String loser = Round.tieBreakerOne(votes, losers);
//			votes = Round.removeLow(votes, loser);
//		}
	}

	// Sends the winner to the ElectionProcess
	private static void setWinner(String name, double percentageOfVote) {
		winner = (name + " | " + percentageOfVote);
		System.out.println(winner);
	}

	protected static String getWinner() {

		return winner;
	}
}
