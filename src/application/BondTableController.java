package application;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
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
	private ComboBox<Text> combo;

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
			InputStream in = BondTableController.class.getResourceAsStream("/resources/symbol.txt");
			Scanner scanSymbol = new Scanner(in);

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
			
			((ToggleButton)event.getSource()).setStyle("-fx-border-color: black; -fx-border-width: 1.5px; -fx-background-color: white");
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

	private void loadBond(String formula)
	{
		BondController ctrlBond = new BondController(new Compound(formula));
		ctrlBond.showStage();
		stgBondTable.close();
	}

	public void displayCompounds(ArrayList<String> compounds)
	{
		lblSelect.setText("Select a Compound");
		combo.setVisible(true);

		for(int i = 0; i < compounds.size(); i++)
		{
			Text compound = new Text(compounds.get(i));
			compound.setFont(new Font(25));
			combo.getItems().add(compound);
		}

		combo.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Text>()
		{
            @Override
            public void changed(ObservableValue<? extends Text> ov, Text t, Text t1)
            {
                loadBond(Compound.removeSubscript(combo.getSelectionModel().getSelectedItem().getText()));
            }
        });

	}
}
