import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class Round {
	// Candidate with the lowest votes; Do we need one here and in the Iterator
	// class if they're both protected?
	// We actually dont need this as a class variable anywhere.
	protected int lowCand;
	// The round that we're on; Do we need one here and in the Iterator class if
	// they're both protected?
	protected static int roundNum = 0;
	protected static List<String> tieNames;

	public Round() {

	}

	// Removes the lowest candidate
	protected static ArrayList<ArrayList<String>> removeLow(ArrayList<ArrayList<String>> votes, String lowCand) {
		roundNum++;
		for (int i = 0; i < votes.size(); i++) {
			if (votes.get(i).get(0).equals(lowCand)) {
				votes.get(i).remove(0);
			}
		}
		return votes;
	}

	// Check to see if there is a tie; Possibly send up to Election class so it can
	// call pickTieBreaker?

	protected static List<String> checkTie(HashMap<String, Integer> tallied, Entry<String, Integer> minEntry) {
		List<String> tieNames = null;
		tieNames.add(minEntry.getKey());
		for(Entry<String,Integer> entry : tallied.entrySet()) {
		    if (entry.getValue() == minEntry.getValue() && (!tieNames.contains(entry.getKey()))) {
		       tieNames.add(entry.getKey());
		    }
		}
		return tieNames;
		
		//  NEED TO ADD CODE HERE TO CHECK WITH MAIN REGARDING A TIE BREAKING METHOD.
		//  AT THIS POINT IN THE PROGRAM WE HAVE IDENTIFIED THAT THERE IS A TIE AND WE HAVE
		//  A LIST OF THE TIE NAMES CALLES tieNames
		
		
	}

	// Logic for handling one of the two tiebreaker methods
	// Tie breaker one looks for the candidate with the most second to last choice votes if a tie is 
	// found at that level, tie breaker two is called to randomly pick someone to remove.
	protected static String tieBreakerOne(ArrayList<ArrayList<String>> votes, List<String> losers) {
		String loser = null;
		List <String> tieLosers = null;
	/*
	 * These comments should be removed... This is an interim commit so I can get help writing logic.
	 * My approach is this...
	 * tieBreakerOne will take in a list of the tie losers.  A new hashmap will be created in this method
	 * to tally the number of second to last choice votes.  The candidate with the larger amount of second
	 * to last choice votes will be returned in the form of a string so that it can be passed to removeLow
	 * to eliminate that candidate.  If in the event of a tie in the second round of tie eliminations, we can
	 * pass the List of losers created here to tieBreakerTwo to eliminate a random person.  Randomization 
	 * should be relatively easy.  My eyes are crossing with the amount of code ive been looking at.  I'm 
	 * going to shut my eyes for a bit and return to it with fresh eyes. Please let me know if you have done
	 * a push to this code so I can look at it and understand it before I start at it again.
	 */
		return loser;

	}

	private static void tieBreakerTwo() {

	}

}
