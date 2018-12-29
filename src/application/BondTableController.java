package application;

import java.io.File;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class BondTableController
{
	Stage stgBondTable;
	int elementCounter;
	@FXML
	private Button btnBack;

	public BondTableController()
	{
		elementCounter = 0;
		stgBondTable = new Stage();
		try
		{
			stgBondTable.getIcons().add(new Image(this.getClass().getResourceAsStream("icon.png")));
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Bond Table.fxml"));

			loader.setController(this);
			stgBondTable.setScene(new Scene(loader.load()));
			stgBondTable.setTitle("Periodic Table");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public void showStage()
	{
		stgBondTable.show();
	}
	public void loadBack()
	{
		MainMenuController ctrlMenu = new MainMenuController();
        ctrlMenu.showStage();
        stgBondTable.close();
	}

	public void isPressed(ActionEvent event)
	{
		String symbol = (((Button) event.getSource()).getText());
		int atomicNum1 = 1;
		int atomicNum2 = 1;
		try
		{
			Scanner scanSymbol = new Scanner(new File("src\\application\\symbol.txt"));
			if(elementCounter == 0)
			{
				while(!scanSymbol.nextLine().equals(symbol))
				{
					atomicNum1++;
				}
			}
			else if(elementCounter == 1)
			{
				while(!scanSymbol.nextLine().equals(symbol))
				{
					atomicNum2++;
				}
			}
			scanSymbol.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		elementCounter++;
		if(elementCounter == 2)
			loadBond(atomicNum1, atomicNum2);
	}

	private void loadBond(int a1, int a2)
	{
		BondController ctrlBond = new BondController(a1, a2);
		ctrlBond.showStage();
		stgBondTable.close();
	}
}
