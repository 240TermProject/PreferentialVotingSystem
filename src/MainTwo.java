package application;
	
import javafx.application.Application;
import javafx.geometry.Insets;
import java.awt.*;
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
		
		Scene scene = new Scene(grid, 400, 400);
		primaryStage.setScene(scene);
			
		Text title = new Text("Please enter a URL");
		title.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 20));
		grid.add(title, 0, 0);
		
		TextField urlTextField = new TextField();
		grid.add(urlTextField, 1, 0);
		
		Button voteButton = new Button("Submit URL");
		HBox hButton = new HBox(5);
		hButton.setAlignment(Pos.BOTTOM_LEFT);
		hButton.getChildren().addAll(voteButton);
		grid.add(hButton, 0, 4);
		
		
		// When Submit URL is press  information on page disappears
		// Compute Results will come up
		
		Button computeButton = new Button("Compute Results");
		HBox hButton2= new HBox(5);
		hButton2.setAlignment(Pos.BOTTOM_RIGHT);
		hButton2.getChildren().addAll(computeButton);
		grid.add(hButton2, 1, 4);
		
		
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
	
	public static void main(String[] args) {
		launch(args);
	}
}
