package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class AlertBox
{
	@FXML
	ImageView imgStructure;
	public static void displayMainAlert()
	{
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("");
		alert.setHeaderText("Elements 58-71 and 90 and above have been omitted.");
		alert.setContentText("Please do not attempt to locate them");

		alert.showAndWait();
	}

	public static void displayNumberFormatException()
	{
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("ERROR");
		alert.setHeaderText("ERROR... Number Not Entered!");
		alert.setContentText("Please enter the atomic number");

		alert.showAndWait();
	}

	public static void displayElementNotFound()
	{
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("ERROR");
		alert.setHeaderText("ERROR... Element not found!");
		alert.setContentText("Please try entering a valid element");

		alert.showAndWait();
	}

	public void displayStructure()
	{
		try
		{
			Image image = new Image(new FileInputStream("src/resources/structure.png"));
			imgStructure = new ImageView(image);

			StructureController ctrlStructure = new StructureController();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}
}