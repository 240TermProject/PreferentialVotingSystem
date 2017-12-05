import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.image.Image ;
import javafx.scene.image.ImageView;
import java.io.IOException;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws IOException {
		
		//The top window navigation section
		primaryStage.setTitle("Preferential Voting System");
		primaryStage.show();
		
		//Sets up the grid panels, center-aligned
		GridPane grid = new GridPane();
		grid.setHgap(20);
		grid.setVgap(6);
		grid.setPadding(new Insets(25,25,25,25));
		ColumnConstraints column1 = new ColumnConstraints();
	    column1.setPercentWidth(34);
	    ColumnConstraints column2 = new ColumnConstraints();
	    column2.setPercentWidth(33);
		ColumnConstraints column3 = new ColumnConstraints();
	    column1.setPercentWidth(33);
	    grid.getColumnConstraints().addAll(column1, column2, column3); // Makes sure the columns fill the grid

		
		//Defines the application(grid) size and external fonts/styles
		Scene scene = new Scene(grid, 1024, 768);
		scene.getStylesheets().add("http://fonts.googleapis.com/css?family=Oxygen:400,700");
		scene.getStylesheets().add("https://fonts.googleapis.com/css?family=Roboto+Slab:400,700");
		scene.getStylesheets().add("style.css");
		primaryStage.setScene(scene);
		
		Label about = new Label("About this Application");
		grid.add(about, 0, 1);
		Text about2 = new Text("Created by Group 3 for CPS240:\n\n "
				+ "Jacqueline Goolsby      Kenneth Hall \nCandace Johnson         Wynton Kirkpatrick \nJavarri Little                   DaRon Turner"
				+ "\n\n\nCommissioned by SGA for a voting system that depends on each "
				+ "voter indicating the order of preference for each candidate listed on a ballot");
		about2.setWrappingWidth(280);
		grid.add(about2, 0, 3, 1, 5);

		
		//Main directions for the user **Complete**
		Label title = new Label("Enter a URL for raw votes:");
		grid.add(title, 1, 1);
		
		//Text field for user to input voting collection URL  **Complete**
		TextField urlTextField = new TextField();
		grid.add(urlTextField, 1, 2);
		
		//Information for tie-breaking dropdown
		Label methodInfo = new Label("Select a tie-breaking method:");
		grid.add(methodInfo, 1, 3);
		// Dropdown menu for tie-breaking method selection **COMPLETE**
		ObservableList<String> drop = FXCollections.observableArrayList(	
				"Method 1",
				"Method 2",
				"Method 3"		
				);
		ComboBox<String> comboBox = new ComboBox<String>(drop);
		comboBox.setPromptText("Please choose method");
		grid.add(comboBox, 1, 4);
		
		Text spacer = new Text(" ");
		grid.add(spacer, 1, 5);
		Text spacer1 = new Text(" ");
		grid.add(spacer1, 1, 6);
		Text spacer2 = new Text(" ");
		grid.add(spacer2, 1, 7);
		//Vote button to start the election
		Button voteButton = new Button("Submit URL and Compute Results");
		grid.add(voteButton, 1, 10);
		
		//CMU Logo
		ImageView logo = new ImageView(new Image(getClass().getResourceAsStream("/CMU.png")));
		GridPane.setHalignment(logo, HPos.CENTER);
		logo.setFitWidth(240);
		logo.setPreserveRatio(true);
		// Put on cell (2,0), span 1 column, 4 rows vertically
		grid.add(logo, 2, 1, 1, 5);
		
		//Horizontal separator
		Separator separator1 = new Separator();
		grid.add(separator1, 0, 11, 3, 1);
		
		//Add method descriptions regarding tie-breaker options
		grid.add(new Label("Method 1"), 0, 13);
		Text methodOne = new Text("In the event of a tie for fewest first choice votes,"
				+ "last choice votes will be counted"
				+ "The candidate (out of the tied candidates) withn "
				+ "the most last choice votes will be removed from the round. "
				+ "The candidate (out of the tied candidates) with the most "
				+ "last choice votes will be removed from the round. "
				+ "If, in the event of a tie in this level of elimination,"
				+ " a tied candidate from this "
				+ " level will be chosen at random to be removed and "
				+ " removal rounds will continue with this logic.");
		methodOne.setWrappingWidth(280);
		grid.add(methodOne, 0, 14);
		
		grid.add(new Label("Method 2"), 1, 13);
		Text methodTwo = new Text("Any candidates who tie for lowest first choice votes will "
				+ "be chosen at random to be removed from the round.");
		methodTwo.setWrappingWidth(280);
		grid.add(methodTwo, 1, 14);
		
		grid.add(new Label("Method 3"), 2, 13);
		Text methodThree = new Text("ALL candidates who tie for fewest first "
				+ "choice votes will be eliminated.");
		methodThree.setWrappingWidth(280);
		grid.add(methodThree, 2, 14);	
		
		//Horizontal separator
		Separator separator2 = new Separator();
		grid.add(separator2, 0, 15, 3, 1);
		
		//Winner info spans bottom left/middle
		grid.add(new Label("Election Winner"), 0, 16, 2, 1);
		Text winner = new Text();
		grid.add(winner, 0, 17);
		
		//Round information displays in bottom right
		grid.add(new Label("Election Statistics"), 2, 16);
		Text rounds = new Text();
		grid.add(rounds, 2, 17);
		
		
		//Calls the election process to start and return the outcome to the GUI
		voteButton.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent arg0) {
			try {
				ElectionProcess e = ElectionProcess.getInstance(urlTextField.getText(), (String) comboBox.getValue());
			} catch (IOException e) {
				e.printStackTrace();
			}
			comboBox.getValue();
			winner.setText(Iterator.getWinner());
			rounds.setText(Iterator.sb.toString());
			
			}
			
		});
	//	http://www.acmaitp.org/test.txt

	}
	
	public static void main(String[] args) throws IOException {
		launch(args);
		

	}
}
