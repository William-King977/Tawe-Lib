import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The main class starts the program and executes the 
 * login page for use.
 * @author William King
 */
public class Main extends Application {
	
	/**
	 * Performs any pre-launch tasks and then launches the system.
	 * @param args Takes a string of arguments.
	 */
	public static void main(String[] args) {
		// Run the program
		launch(args);
	}
	
	/**
	 * The primary stage hosts the application and provides a UI for the user.
	 * @param primaryStage The stage.
	 */
	@Override 
	public void start(Stage primaryStage) {
		try {
			// Launch login page here.
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
