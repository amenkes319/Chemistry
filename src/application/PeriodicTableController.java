package application;

import java.io.File;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class PeriodicTableController
{
	int atomicNum;
	Stage stgPeriodicTable;

	public PeriodicTableController()
	{
		stgPeriodicTable = new Stage();
		try
		{
			stgPeriodicTable.getIcons().add(new Image(this.getClass().getResourceAsStream("icon.png")));
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

	public void elementSelected(ActionEvent event)
	{
		String symbol = (((Button) event.getSource()).getText());
		atomicNum = 1;
		try
		{
			Scanner scanSymbol = new Scanner(new File("src\\application\\symbol.txt"));
			while(!scanSymbol.nextLine().equals(symbol))
			{
				atomicNum++;
			}

			scanSymbol.close();
			stgPeriodicTable.close();
			ElementInfoController ctrlElementInfo = new ElementInfoController(atomicNum, stgPeriodicTable);
			ctrlElementInfo.showStage();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
