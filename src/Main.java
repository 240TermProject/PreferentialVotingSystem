
	
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import java.awt.*;
import java.io.IOException;

import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
	
		primaryStage.setTitle("DirtyBits Preferrential Voting System");
		primaryStage.show();
		
		
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(20);
		grid.setVgap(20);
		grid.setPadding(new Insets(25,25,25,25));
		
		Scene scene = new Scene(grid, 1000, 700);
		primaryStage.setScene(scene);
			
		Text title = new Text("Please enter a URL and select method choice");
		title.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 20));
		grid.add(title, 0, 0);
		
		TextField urlTextField = new TextField();
		grid.add(urlTextField, 0, 1);
		
		Button voteButton = new Button("Submit URL and Compute Results");
		HBox hButton = new HBox(5);
		hButton.setAlignment(Pos.BOTTOM_LEFT);
		hButton.getChildren().addAll(voteButton);
		grid.add(hButton, 0 , 5);
		
		
		// When Submit URL is press  information on page disappears
		// Compute Results will come up
		
//		Button computeButton = new Button("Compute Results");
//		HBox hButton2= new HBox(5);
//		hButton2.setAlignment(Pos.BOTTOM_RIGHT);
//		hButton2.getChildren().addAll(computeButton);
//		grid.add(hButton2, 1, 4);
//		
		
		
ObservableList<String> drop = FXCollections.observableArrayList(	
				"Method 1",
				"Method 2",
				"Method 3"
				
				);
		
		ComboBox comboBox = new ComboBox(drop);
		comboBox.setPromptText("Please choose method");
		grid.add(comboBox, 0 ,3);
		
		
		
		Text test = new Text();
		grid.add(test, 1, 7);
		
		voteButton.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent arg0) {
			try {
				ElectionProcess e = ElectionProcess.getInstance(urlTextField.getText(), (String) comboBox.getValue());
			} catch (IOException e) {
				e.printStackTrace();
			}
			comboBox.getValue();
			}
		});
		
		grid.add(new Label("Method 1:"), 0, 9);
		grid.add(new Label("In the event of a tie for fewest first choice votes,"
				+ "\n last choice votes will be counted"
				+ "The candidate (out of the tied candidates) with\n"
				+ "the most last choice votes will be removed from the round.\n "
				+ "The candidate (out of the tied candidates) with the most "
				+ "\n last choice votes will be removed from the round.\n  "
				+ "If, in the event of a tie in this level of elimination,"
				+ "\n a tied candidate from this "
				+ "\n level will be chosen at random to be removed and "
				+ "\n removal rounds will continue with this logic."), 0, 10);
		grid.add(new Label("Method 2:"), 1, 9);
		grid.add(new Label("Any candidates who tie for lowest first choice votes will\n"
				+ "be chosen at random to be removed from the round."), 1,10);
		
		grid.add(new Label("Method 3:"), 3, 9);
		grid.add(new Label("ALL candidates who tie for fewest first\n"
				+ "choice votes will be eliminated."), 3, 10);	
		
		//When Compue Results is pressed AccessPreferrential Voting System Main class
		
		
//		try {
//			BorderPane root = new
//		BorderPane();
//			Scene scene = new Scene(root,400,400);
//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//			primaryStage.setScene(scene);
//			primaryStage.show();
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
	}
	
	public static void main(String[] args) throws IOException {
		//launch(args);
		ElectionProcess.getInstance("http://www.acmaitp.org/test.txt", "Method 2");
	}
}
