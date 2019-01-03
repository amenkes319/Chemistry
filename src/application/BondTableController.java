package application;

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
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class BondTableController
{
	Stage stgBondTable;
	int elementCounter;
	ToggleGroup one;
	ToggleGroup two;
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

	public void loadClear()
	{
		BondTableController ctrlBondTable = new BondTableController();
		ctrlBondTable.showStage();
		stgBondTable.close();

	}

	public void isPressed(ActionEvent event)
	{
		try
		{
			InputStream in = BondTableController.class.getResourceAsStream("/resources/symbol.txt");
			Scanner scanSymbol = new Scanner(in);
			ToggleButton button = (ToggleButton)event.getSource();
			String symbol = button.getText();

			if(!button.isSelected() && symbol.equals(first))
			{
				button.setSelected(false);
				button.setToggleGroup(null);
				button.setStyle(style1);

				element1 = element2;
				style1 = style2;
				first = second;

				element2 = null;
				style2 = null;
				second = null;


				scanSymbol.close();
				elementCounter--;

				lblSelect.setText("Select 2 Elements to Bond Together");
				combo.setVisible(false);
				combo.getItems().clear();

				return;
			}
			else if(!button.isSelected() && symbol.equals(second))
			{
				button.setSelected(false);
				button.setToggleGroup(null);
				button.setStyle(style2);

				element2 = null;
				style2 = null;
				second = null;

				scanSymbol.close();
				elementCounter--;

				lblSelect.setText("Select 2 Elements to Bond Together");
				combo.setVisible(false);
				combo.getItems().clear();

				return;
			}

			if(elementCounter == 0)
			{
				button.setToggleGroup(one);
				first = button.getText();
				style1 = button.getStyle();
				button.setSelected(true);
				element1 = new Element(1);

				while(!scanSymbol.nextLine().equals(symbol))
				{
					element1 = new Element(element1.getAtomicNum()+1);
				}
				System.out.println("1 = " + element1.getSymbol());

				elementCounter++;
				button.setStyle("-fx-border-color: black; -fx-border-width: 1.5px; -fx-background-color: white");
				scanSymbol.close();
			}
			else if(elementCounter == 1)
			{
				button.setToggleGroup(two);
				second = button.getText();
				style2 = button.getStyle();
				button.setSelected(true);
				element2 = new Element(1);

				while(!scanSymbol.nextLine().equals(symbol))
				{
					element2 = new Element(element2.getAtomicNum()+1);
				}
				System.out.println("2 = " + element2.getSymbol());

				elementCounter++;
				button.setStyle("-fx-border-color: black; -fx-border-width: 1.5px; -fx-background-color: white");
				scanSymbol.close();
			}

			if(elementCounter == 2 && combo.getItems().isEmpty())
			{
				displayCompounds(Compound.searchCompounds(element1, element2));
				scanSymbol.close();
			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
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
			if(!compound.getText().equals(""))
			{
				combo.getItems().add(compound);
			}
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
