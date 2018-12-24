package application;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ElementInfoController
{
	Stage stgElementInfo;
	int atomicNum;
	@FXML
	private Button btnBack;
	@FXML
	private Button btnMenu;
	@FXML
	private Label lblInfo[] = new Label[18];

	public ElementInfoController(int a)
	{
		atomicNum = a;
		stgElementInfo = new Stage();
		try
		{
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Element Info.fxml"));
			loader.setController(this);
			stgElementInfo.setScene(new Scene(loader.load()));
			stgElementInfo.setTitle("Element Info");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		initialize();
	}

	private void initialize()
	{
		for(int i=0; i<lblInfo.length; i++)
		{

		}
	}

	public void showStage()
	{
		stgElementInfo.show();
	}

	public void loadBack()
	{
		SearchController ctrlSearch = new SearchController();
		ctrlSearch.showStage();
		stgElementInfo.close();
	}

	public void loadMenu()
	{
		MainMenuController ctrlMenu = new MainMenuController();
		ctrlMenu.showStage();
		stgElementInfo.close();
	}
}
