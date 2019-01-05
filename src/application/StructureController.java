package application;

import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class StructureController
{
	Stage stgStructure;

	public StructureController()
	{
		stgStructure = new Stage();
		try
		{
			stgStructure.getIcons().add(new Image(this.getClass().getResourceAsStream("icon.png")));
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Structure Image.fxml"));

			loader.setController(this);
			stgStructure.setScene(new Scene(loader.load()));
			stgStructure.setTitle("Structure");
			stgStructure.showAndWait();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
