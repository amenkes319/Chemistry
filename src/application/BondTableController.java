package application;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class BondTableController
{
	Stage stgBondTable;
	int elementCounter;
	Element element1;
	Element element2;
	String style1;
	String style2;
	String first;
	String second;

	@FXML
	private Button btnBack;
	@FXML
	private Label lblSelect;
	@FXML
	private ChoiceBox<String> choice;

	public BondTableController()
	{
		elementCounter = 0;
		element1 = new Element(1);
		element2 = new Element(1);

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
		String symbol = (((ToggleButton)event.getSource()).getText());

		try
		{
			Scanner scanSymbol = new Scanner(new File("src\\application\\symbol.txt"));

			if(((ToggleButton)event.getSource()).isSelected() && ((ToggleButton)event.getSource()).getText()==first && elementCounter>0)
			{
				((ToggleButton)event.getSource()).setStyle(style1);
				((ToggleButton)event.getSource()).setSelected(false);
				style1 = "";
				scanSymbol.close();
				elementCounter--;

				return;
			}
			else if(((ToggleButton)event.getSource()).isSelected() && ((ToggleButton)event.getSource()).getText()==second && elementCounter>0)
			{
				((ToggleButton)event.getSource()).setStyle(style2);
				((ToggleButton)event.getSource()).setSelected(false);
				style2 = "";
				elementCounter--;
				scanSymbol.close();

				return;
			}

			if(elementCounter == 0)
			{
				first = ((ToggleButton)event.getSource()).getText();
				style1 = ((ToggleButton)event.getSource()).getStyle();
				while(!scanSymbol.nextLine().equals(symbol))
				{
					element1 = new Element(element1.getAtomicNum()+1);
				}
			}
			else if(elementCounter == 1)
			{
				second = ((ToggleButton)event.getSource()).getText();
				style2 = ((ToggleButton)event.getSource()).getStyle();
				while(!scanSymbol.nextLine().equals(symbol))
				{
					element2 = new Element(element2.getAtomicNum()+1);
				}
			}

			((ToggleButton)event.getSource()).setStyle("-fx-border-color: black");
			((ToggleButton)event.getSource()).setStyle("-fx-background-color: white");
			scanSymbol.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		elementCounter++;
		if(elementCounter == 2)
			displayCompounds(Compound.searchCompounds(element1, element2));
	}

	private void loadBond(Compound comp)
	{
		BondController ctrlBond = new BondController(comp);
		ctrlBond.showStage();
		stgBondTable.close();
	}

	public void displayCompounds(ArrayList<String> compounds)
	{
		lblSelect.setText("Select a Compound");
		choice.setVisible(true);
		for(int i = 0; i < compounds.size(); i++)
		{
			choice.getItems().add(compounds.get(i));
		}
		choice.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>()
		{
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1)
            {
                 System.out.println(choice.getSelectionModel().getSelectedItem());
            }
        });
	}
}
