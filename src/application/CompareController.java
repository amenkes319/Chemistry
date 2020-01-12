package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class CompareController
{
	Stage stgCompare;
	@FXML
	private Button btnBack;

	public CompareController()
	{
		stgCompare = new Stage();
		try
		{
			stgCompare.getIcons().add(new Image(this.getClass().getResourceAsStream("icon.png")));
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Periodic Table-Compare.fxml"));

			loader.setController(this);
			stgCompare.setScene(new Scene(loader.load()));
			stgCompare.setTitle("Periodic Table");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public void showStage()
	{
		stgCompare.show();
	}
	public void loadBack()
	{
		MainMenuController ctrlMenu = new MainMenuController();
        ctrlMenu.showStage();
        stgCompare.close();
	}
}
