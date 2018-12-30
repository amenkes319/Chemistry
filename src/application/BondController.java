package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class BondController
{
	Stage stgBond;
	Element element1;
	Element element2;
	@FXML
	private Label lblName;
	@FXML
	private Label lblType;
	@FXML
	private Label lblPolarity;
	@FXML
	private Label lblMoleculeShape;

	public BondController(int e1, int e2)
	{
		stgBond = new Stage();
		element1 = new Element(e1);
		element2 = new Element(e2);
		try
		{
			stgBond.getIcons().add(new Image(this.getClass().getResourceAsStream("icon.png")));
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Bond Info.fxml"));

			loader.setController(this);
			stgBond.setScene(new Scene(loader.load()));
			stgBond.setTitle("Bond");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public void initialize()
	{
		lblName.setText(getBondName());
		lblType.setText(getBondType());
		lblPolarity.setText(getBondPolarity());
		lblMoleculeShape.setText(getMoleculeShape());
	}

	public void showStage()
	{
		stgBond.show();
	}

	public void loadBack()
	{
		BondTableController ctrlBondTable = new BondTableController();
		ctrlBondTable.showStage();
		stgBond.close();
	}

	public void loadMenu()
	{
		MainMenuController ctrlMainMenu = new MainMenuController();
		ctrlMainMenu.showStage();
		stgBond.close();
	}

	private String getBondName()
	{
		String name = "Name";


		return name;
	}

	private String getBondType()
	{
		String bondType = null;

		if(element1.isMetallic() && !element2.isMetallic() || !element1.isMetallic() && element2.isMetallic())
			bondType = "Ionic";
		else if(!(element1.isMetallic() || element2.isMetallic()))
			bondType = "Covalent";
		else if(element1.isMetallic() && element2.isMetallic())
			bondType = "Metallic";

		return bondType;
	}

	private String getBondPolarity()
	{
		String polarity = null;
		if(!getBondType().equals("Covalent"))
			polarity = null;
		else if(element1.getElectronegativity() == element2.getElectronegativity())
			polarity = "Nonpolar";
		else if(element1.getElectronegativity() != element2.getElectronegativity())
			polarity = "Polar";

		return polarity;
	}

	private String getMoleculeShape()
	{
		String moleculeShape = "Shape";


		return moleculeShape;
	}
}
