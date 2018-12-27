package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class BondController
{
	Stage stgBond;
	@FXML
	private Button btnBack;

	public BondController()
	{
		stgBond = new Stage();
		try
		{
			stgBond.getIcons().add(new Image(this.getClass().getResourceAsStream("icon.png")));
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Bond Table.fxml"));

			loader.setController(this);
			stgBond.setScene(new Scene(loader.load()));
			stgBond.setTitle("Periodic Table");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public void showStage()
	{
		stgBond.show();
	}
	public void loadBack()
	{
		MainMenuController ctrlMenu = new MainMenuController();
        ctrlMenu.showStage();
        stgBond.close();
	}
}
