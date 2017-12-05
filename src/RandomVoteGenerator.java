import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

public class RandomVoteGenerator {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
File fi = new File("votes.txt");
PrintWriter pw = new PrintWriter(fi);
pw.print("once upon a time");
pw.close();

	}

}
