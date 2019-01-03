package application;

import java.awt.Color;

import org.openscience.cdk.CDKConstants;
import org.openscience.cdk.depict.DepictionGenerator;
import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.interfaces.IChemObjectBuilder;
import org.openscience.cdk.silent.SilentChemObjectBuilder;
import org.openscience.cdk.smiles.SmilesParser;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class BondController
{
	Stage stgBond;
	Compound comp;

	@FXML
	private Label lblName;
	@FXML
	private Label lblType;
	@FXML
	private Label lblPolarity;
	@FXML
	private Label lblMoleculeShape;

	public BondController(Compound c)
	{
		stgBond = new Stage();
		comp = c;
		try
		{
			stgBond.getIcons().add(new Image(this.getClass().getResourceAsStream("icon.png")));
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Bond Info.fxml"));

			loader.setController(this);
			stgBond.setScene(new Scene(loader.load()));
			stgBond.setTitle("Bond");
			displayImage();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public void initialize()
	{
		lblName.setText(comp.getName());
		lblType.setText(comp.getBondType());
		lblPolarity.setText(comp.getBondPolarity());
		lblMoleculeShape.setText(comp.getMoleculeShape());
	}

	public void displayImage() throws Exception
	{
		IChemObjectBuilder bldr = SilentChemObjectBuilder.getInstance();
	    SmilesParser smipar = new SmilesParser(bldr);

	    IAtomContainer mol = smipar.parseSmiles(comp.getSmiles());
	    mol.setProperty(CDKConstants.TITLE, comp.getName());

	    DepictionGenerator dptgen = new DepictionGenerator();

	    dptgen.withSize(200, 250)
	          .withMolTitle()
	          .withTitleColor(Color.DARK_GRAY);
	    dptgen.depict(mol)
	          .writeTo("~/caffeine.png");
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

}
