import java.util.ArrayList;

public class Round{
	//Candidate with the lowest votes; Do we need one here and in the Iterator class if they're both protected?
	// We actually dont need this as a class variable anywhere.
	protected int lowCand;
	//The round that we're on; Do we need one here and in the Iterator class if they're both protected?
	protected static int roundNum = 0;
	
	public Round(){
		
	}
	
	//Removes the lowest candidate
	protected static ArrayList<ArrayList<String>> removeLow(ArrayList<ArrayList<String>> votes, String lowCand){
		roundNum++;
		for (int i = 0; i < votes.size(); i++) {
			if (votes.get(i).get(0).equals(lowCand)) {
				votes.get(i).remove(0);
			}
		}
		return votes;
	}
	
	//Check to see if there is a tie; Possibly send up to Election class so it can call pickTieBreaker?
	
	// We shouldnt need a method to check
	protected void checkTie() {
		
	}
	
	//Logic for handling one of the two tiebreaker methods
	private void tieBreakerOne() {
		
	}
	
}
