import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class Votes extends main{
	// Contains all of the voter's globalIDs
	private static ArrayList<String> voterIDs = new ArrayList<String>();
	// Contains all of a voter's individual votes
	private static ArrayList<ArrayList<String>> indVotes = new ArrayList<ArrayList<String>>();

	public static void Votes() throws IOException {
		// Set URL of raw vote data
		URL rawResults = new URL("http://www.acmaitp.org/test.txt");
		// Establish Scanner for the URL file
		Scanner dataScanner = new Scanner(rawResults.openStream()).useDelimiter("[,|\n|,\r]+");
		// Start processing data
		while (dataScanner.hasNextLine()) {
			String vote = dataScanner.nextLine();
			String[] data = vote.split("[,]");

			if (!checkID(data[0])) {
				continue;
			} else {
				storeVote(data);
			}

		}
		// Print statements for testing
		printArray();
		printIDs();
	}

	// check to see if we used this globalID before
	public static boolean checkID(String voterID) {
		if (!voterIDs.contains(voterID)) {
			voterIDs.add(voterID);
			return true;
		} else {
			return false;
		}
	}

	/*
	 * creates a Voter class with the voter's GlobalID, then loads the voter's
	 * votes, OR stores a voter's votes into indVotes.
	 */
	public static void storeVote(String[] data) throws IOException {
		ArrayList<String> votes = new ArrayList<String>();
		for (int i = 1; i < data.length; i++) {
			votes.add(data[i]);
		}
		indVotes.add(votes);

	}
	
	// Method for testing population of votes array
	public static void printArray() {
		for (int i = 0; i < indVotes.size(); i++) {
			int size = indVotes.get(i).size();
			for (int j = 0; j < size; j++) {
				System.out.print(indVotes.get(i).get(j) + ", ");
			}
			System.out.println();
		}
	}
	
	// Method for testing population of voterIDs
	public static void printIDs() {
		for (int i = 0; i < voterIDs.size(); i++) {
			System.out.println(voterIDs.get(i));
		}
	}

}
