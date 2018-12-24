package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class PeriodicTableController
{
	Stage stgPeriodicTable;
	@FXML
	private Button btnBack;

	public PeriodicTableController()
	{
		stgPeriodicTable = new Stage();
		try
		{
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Periodic Table.fxml"));

			loader.setController(this);
			stgPeriodicTable.setScene(new Scene(loader.load()));
			stgPeriodicTable.setTitle("Periodic Table");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public void showStage()
	{
		stgPeriodicTable.show();
	}

	public void loadBack()
	{
		MainMenuController ctrlMenu = new MainMenuController();
        ctrlMenu.showStage();
        stgPeriodicTable.close();
	}
}
