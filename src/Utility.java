import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * The Utility class holds commonly used methods / functions that are used 
 * within different classes such as validation etc.
 * @author William King
 */
public class Utility {
	
	public static void userNotExist() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error: User does not exist.");
		alert.setHeaderText(null);
		alert.setContentText("The username entered does not exist in the "
				+ "system. Please try again.");	
		alert.showAndWait();
	}

}
