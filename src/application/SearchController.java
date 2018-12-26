package application;

import java.util.Scanner;
import java.io.*;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class SearchController
{
	int     atomicNum;
	Stage   stgSearch;
	@FXML
	private Button btnBack;
	@FXML
	private Button btnSearchNum;
	@FXML
	private Button btnSearchName;
	@FXML
	private Button btnSearchSymbol;
	@FXML
	private TextField txtFldSearchNum;
	@FXML
	private TextField txtFldSearchName;
	@FXML
	private TextField txtFldSearchSymbol;

	public SearchController()
	{
		stgSearch = new Stage();
		stgSearch.getIcons().add(new Image(this.getClass().getResourceAsStream("icon.png")));
		try
		{
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Search.fxml"));

			loader.setController(this);
			stgSearch.setScene(new Scene(loader.load()));
			stgSearch.setTitle("Search");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public void showStage()

	{
		stgSearch.show();
	}

	public void loadBack()
	{
		MainMenuController ctrlMenu = new MainMenuController();
        ctrlMenu.showStage();
        stgSearch.close();
	}

	public void loadElementInfo()
	{
		ElementInfoController ctrlElementInfo = new ElementInfoController(atomicNum, stgSearch);
		ctrlElementInfo.showStage();
		stgSearch.close();
	}

	public void getAtomicNum()
	{
		try
		{
			atomicNum = Integer.parseInt(txtFldSearchNum.getText());

			if(atomicNum < 1 || atomicNum > 57 && atomicNum < 72 || atomicNum > 89)
				AlertBox.displayElementNotFound();
			else
			{
				loadElementInfo();
				stgSearch.close();
			}

		}
		catch(NumberFormatException e)
		{
			AlertBox.displayNumberFormatException();
		}
	}

	private String getAtomName()
	{
		return txtFldSearchName.getText();
	}

	private String getAtomicSymbol()
	{
		return txtFldSearchSymbol.getText();
	}

	public void nameToAtomicNum() throws Exception
	{
		Scanner scanName = new Scanner(new File("src\\application\\name.txt"));
		for(int i=1; scanName.hasNextLine(); i++)
		{
			if(getAtomName().equalsIgnoreCase(scanName.next()))
			{
				scanName.close();
				atomicNum =  i;
				if(atomicNum < 1 || atomicNum > 57 && atomicNum < 72 || atomicNum > 89)
					AlertBox.displayElementNotFound();
				else
				{
					loadElementInfo();
					return;
				}
			}
		}
		scanName.close();
		AlertBox.displayElementNotFound();
	}

	public void symbolToAtomicNum() throws Exception
	{
		Scanner scanSymbol= new Scanner(new File("src\\application\\symbol.txt"));
		for(int i=1; scanSymbol.hasNextLine(); i++)
		{
			if(getAtomicSymbol().equals(scanSymbol.next()))
			{
				scanSymbol.close();
				atomicNum =  i;

				if(atomicNum < 1 || atomicNum > 57 && atomicNum < 72 || atomicNum > 89)
					AlertBox.displayElementNotFound();
				else
				{
					loadElementInfo();
					return;
				}
			}
		}
		scanSymbol.close();
		AlertBox.displayElementNotFound();
	}
}
