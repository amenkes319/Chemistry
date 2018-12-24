package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;

public class MainMenuController
{
	Stage stgMainMenu;
	@FXML
	private Button btnOpenSearch;
	@FXML
	private Button btnOpenPT;
	@FXML
	private Button btnOpenCompare;
	@FXML
	private Button btnOpenBond;

	public MainMenuController()
	{
		stgMainMenu = new Stage();
		try
		{
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Main Menu.fxml"));

			loader.setController(this);
			stgMainMenu.setScene(new Scene(loader.load()));
			stgMainMenu.setTitle("Main Menu");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public void showStage()
	{
		stgMainMenu.show();
	}

	public void loadSearch()
	{
		SearchController ctrlSearch = new SearchController();
		ctrlSearch.showStage();
		stgMainMenu.close();
	}

	public void loadPeriodicTable()
	{
		PeriodicTableController ctrlPeriodicTable = new PeriodicTableController();
		ctrlPeriodicTable.showStage();
		stgMainMenu.close();
	}

	public void loadCompare()
	{
		CompareController ctrlCompare = new CompareController();
		ctrlCompare.showStage();
		stgMainMenu.close();
	}

	public void loadBond()
	{
		BondController ctrlBond = new BondController();
		ctrlBond.showStage();
		stgMainMenu.close();
	}
}
