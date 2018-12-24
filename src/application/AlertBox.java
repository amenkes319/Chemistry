package application;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AlertBox
{
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
		alert.setHeaderText("ERROR... Element not found!");
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
}