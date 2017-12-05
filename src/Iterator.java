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
	// Making max and min class variables
	protected static Entry<String, Integer> minEntry = null;
	protected static Entry<String, Integer> maxEntry = null;
	private static String winner = null;

	public Iterator(ArrayList<ArrayList<String>> votes) {
		tallyVotes(votes);
	}

	/*
	 * tallies how many votes each candidate has, We will need the ArrayList of
	 * votes from the Votes class
	 */
	private static void tallyVotes(ArrayList<ArrayList<String>> votes) {
		System.out.println("tallying votes");
		int size = votes.size();
		HashMap<String, Integer> voteTallies = new HashMap<>();
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

		if (!checkMajority(voteTallies, size)) {
			initiateRound(votes, voteTallies);
		}

	}

	// checks to see if a candidate has more than 50% of the vote
	private static boolean checkMajority(HashMap<String, Integer> tallied, int numberOfVotes) {
		for (Entry<String, Integer> entry : tallied.entrySet()) {
			if (maxEntry == null || entry.getValue() > maxEntry.getValue()) {
				maxEntry = entry;
			}
		}
		// Declare winner if the maxEntry has >50%
		if (maxEntry.getValue() / numberOfVotes > 0.5) {
			double percentage = maxEntry.getValue() / numberOfVotes;
			setWinner(maxEntry.getKey(), percentage);
			return true;
		}

		for (Entry<String, Integer> entry : tallied.entrySet()) {
			if (minEntry == null || entry.getValue() < minEntry.getValue()) {
				minEntry = entry;
			}
		}
		return false;

	}

	// Starts the round, or starts another round
	private static void initiateRound(ArrayList<ArrayList<String>> votes, HashMap<String, Integer> voteTallies) {
		System.out.println("Round initiated");
		int removalMethod = ElectionProcess.removalChoice();
		switch (ElectionProcess.methodNum) {
		case -1:
			System.out.println("A fatal error has occured, an invalid removal choice has been passed to Iterator");		
		case 1:
			List<String> losers = Round.checkTie(voteTallies, minEntry);
			if (losers.size() == 1) {
				votes = Round.removeLow(votes, minEntry.getKey());
			} else {
				String loser = Round.tieBreakerOne(votes, losers);
				votes = Round.removeLow(votes, loser);
			}
			break;
		case 2:
			List<String> losersOne = Round.checkTie(voteTallies, minEntry);
			if (losersOne.size() == 1) {
				votes = Round.removeLow(votes, minEntry.getKey());
			} else {
				String nameToRemove = Round.tieBreakerTwo(losersOne);
				Round.removeLow(votes, nameToRemove);
			}
			break;
		case 3:
			List<String> losersTwo = Round.checkTie(voteTallies, minEntry);
			votes = Round.removeAll(votes, losersTwo);
			break;
		default:
			List<String> losersThree = Round.checkTie(voteTallies, minEntry);
			if (losersThree.size() == 1) {
				votes = Round.removeLow(votes, minEntry.getKey());
			} else {
				String loser = Round.tieBreakerOne(votes, losersThree);
				votes = Round.removeLow(votes, loser);
			}
			break;
		}

		List<String> losers = Round.checkTie(voteTallies, minEntry);
		if (losers.size() == 1) {
			votes = Round.removeLow(votes, minEntry.getKey());
		} else {
			String loser = Round.tieBreakerOne(votes, losers);
			votes = Round.removeLow(votes, loser);
		}
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
