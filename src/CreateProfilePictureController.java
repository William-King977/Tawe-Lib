import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;

/**
 * Controller for the Create Profile Picture page.
 * Allows a user to create their own custom profile image by using
 * different drawing tools.
 * @author William King
 */
public class CreateProfilePictureController {
	/** Used to scale the size of the brush size. */
	private final int BRUSH_SCALE = 2;
	/** The file path for the profile pictures. */
	private final String PROFILE_PICTURE_PATH = "DataFiles/ProfilePictures/";
	/** The String name of the user's created profile picture. */
	private String newProfilePicture = "";
	
	/** Checks if the user has created a profile picture. */
	private boolean isProfilePictureCreated = false;
	
	/** The fxID for the colour picker. */
	@FXML private ColorPicker colourPicker;
	
	/** The fxID for the scrollbar determining brush size. */
	@FXML private Slider brushSize;
	
	/** The fxID for the canvas. */
	@FXML private Canvas canvas;
	
	/** The fxID for the back button. */
	@FXML private Button back;
	
	/** The fxID for the text field which takes in the filename wanting to be saved. */
	@FXML private TextField ImageFilename;
	
	/** The fxID for the stright line checkbox. */
	@FXML private CheckBox line;
	/** The fxID for the circle checkbox. */
	@FXML private CheckBox circle;
	/** The fxID for the paintbrush checkbox. */
	@FXML private CheckBox brush;
	/** The fxID for the fill canvas checkbox. */
	@FXML private CheckBox fill;
	/** The fxID for the arc checkbox. */
	@FXML private CheckBox arc;

	/** The variable used to manipulate all of the graphics context libraries. */
	private GraphicsContext gc;
	
	/**
	 * This method allows you to draw the shapes currently selected on the window.
	 * Determines what shape is selected, and then draws the shape applicable.
	 */
	public void draw() {
		gc = canvas.getGraphicsContext2D(); 
		// The stroke colour, fill colour, and width set initially.
		gc.setStroke(colourPicker.getValue()); 
		gc.setFill(colourPicker.getValue());
		gc.setLineWidth(brushSize.getValue() / BRUSH_SCALE);
		
		// Sets the initial x and y coordinate when pressed.
		canvas.setOnMousePressed(e -> { 
			double xStart = e.getX();
			double yStart = e.getY();
			// When released, the final x and y coordinates are recorded and used.
			canvas.setOnMouseReleased(a -> {
				// if line is selected, then a line is drawn.
				if (line.isSelected()) { 
					gc.strokeLine(xStart, yStart, a.getX(), a.getY());
					// Closes the path so following lines don't link up from the previous line.
					gc.closePath(); 
				}
			});
		});
		
		// Sets the initial x and y values when the mouse is selected, and the shape size.
		canvas.setOnMouseClicked(e -> { 
			double shapeSize = brushSize.getValue() / BRUSH_SCALE;
			double initX = e.getX() - shapeSize;
			double initY = e.getY() - shapeSize;
			
			if (circle.isSelected()) { // Draws a circle if the shape is selected.
				gc.setFill(colourPicker.getValue());
				gc.fillOval(initX, initY, shapeSize, shapeSize);
				gc.strokeOval(initX, initY, shapeSize, shapeSize);
			} else if (fill.isSelected()) { // Fills the canvas if selected.
				gc.setFill(colourPicker.getValue());
				gc.fillRect(0, 0, 300, 300);
			} else if (arc.isSelected()) { // Draws an arc if the shape is selected.
				gc.setFill(colourPicker.getValue());
				gc.fillArc(initX, initY, shapeSize, shapeSize, 380, 310, ArcType.ROUND);
			}
		});
		
		// Sets the current x and y coordinate every time the mouse is dragged.
		canvas.setOnMouseDragged(e -> { 
			double thickness = brushSize.getValue() / BRUSH_SCALE;
			double x = e.getX() - thickness;
			double y = e.getY() - thickness;
			
			// When a brush is selected, a stream of circles are displayed every drag.
			if (brush.isSelected()) { 
				gc.setFill(colourPicker.getValue());
				gc.fillOval(x, y, thickness, thickness);
			}
		});
	}
	
	/**
	 * Clears the canvas.
	 */
	public void clearImage() {
		gc.clearRect(0, 0, 300, 300);	
	}
	
	/**
	 * Saves the image created on the canvas to the folder specified.
	 * Providing the filename doesn't already exist, saving the image as
	 * a .png file.
	 */
	public void saveImage() {
		// Creates the snapshot object, setting the background of the image transparent. 
		SnapshotParameters snapParameter = new SnapshotParameters();
		snapParameter.setFill(Color.TRANSPARENT);
		
		// Creating a writable image object which is of 300x300. 
		WritableImage customImage = new WritableImage(300,300);
		String fileName = ImageFilename.getText().trim() + ".png"; 
		
		if (fileName.equals(".png")) { 
			Utility.fileNameEmpty();
		} else if (FileHandling.checkImageExists(fileName)) {
			Utility.fileNameExists();
		// Save the image if the filename doesn't already exist.
		} else if (!FileHandling.checkImageExists(fileName)) { 
			// Creates the file by concatenating the complete path to the filename. 
			File file = new File(PROFILE_PICTURE_PATH + fileName);
			
			// Creating the image by taking a snapshot of the canvas. 
			Image snap = canvas.snapshot(snapParameter, customImage); 
			
			try { 
				ImageIO.write(SwingFXUtils.fromFXImage(snap, null), "png", file);
				isProfilePictureCreated = true;
				
				// Alert message for confirmation.
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Profile Picture Created.");
				alert.setHeaderText(null);
				alert.setContentText("The profile picture has been created "
						+ "successfully. Do you want to set it as your profile picture?");
				alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
				// Handles the button press event (Yes, No).
				alert.showAndWait().ifPresent(e -> {
					// If they want it as their profile picture.
					if (e == ButtonType.YES) {
						newProfilePicture = fileName;
					}
				});
			} catch (IOException e) {
				// Catches an exception if the image can't be saved.
				System.out.println("Cannot save " + fileName);
				System.exit(-1);
			}
			
		} 
	}
	
	/**
	 * When the canvas check box is selected, this method will run.
	 * Setting canvas fill selected to true, and all others to false.
	 */
	public void selectFillCanvas() {
		arc.setSelected(false);
		fill.setSelected(true);
		line.setSelected(false);
		circle.setSelected(false);
		brush.setSelected(false);
	}
	
	/**
	 * When the paintbrush check box is selected, this method will run.
	 * Setting paintbrush selected to true, and all others to false.
	 */
	public void selectArc() {
		arc.setSelected(true);
		fill.setSelected(false);
		line.setSelected(false);
		circle.setSelected(false);
		brush.setSelected(false);
	}
	
	/**
	 * When the paintbrush check box is selected, this method will run.
	 * Setting paintbrush selected to true, and all others to false.
	 */
	public void selectPaintbrush() {
		arc.setSelected(false);
		fill.setSelected(false);
		line.setSelected(false);
		circle.setSelected(false);
		brush.setSelected(true);
	}
	
	/**
	 * When the circle check box is selected, this method will run.
	 * Setting circle selected to true, and all others to false.
	 */
	public void selectCircle() {
		arc.setSelected(false);
		fill.setSelected(false);
		line.setSelected(false);
		circle.setSelected(true);
		brush.setSelected(false);
	}
	
	/**
	 * When the line check box is selected, this method will run.
	 * Setting line selected to true, and all others to false.
	 */
	public void selectLine() {
		arc.setSelected(false);
		fill.setSelected(false);
		line.setSelected(true);
		circle.setSelected(false);
		brush.setSelected(false);
	}
	
	/**
	 * Gets the user's newly created profile picture.
	 * @return The user's new profile picture as a String file name.
	 */
	public String getNewProfilePicture() {
		return newProfilePicture; 
	}
	
	/**
	 * Gets if the user has created a profile picture or not.
	 * @return If the user has created a profile picture.
	 */
	public boolean isProfilePictureCreated() {
		return isProfilePictureCreated;
	}
	
	/**
	 * Closes the current page.
	 */
	public void handleBackButtonAction() {
		Stage curStage = (Stage) back.getScene().getWindow(); 
		curStage.close(); // Closes that stage.
	}
}
