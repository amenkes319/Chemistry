package application;

import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;

import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
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
			//display();
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
	    String path = BondController.class.getProtectionDomain().getCodeSource().getLocation().getPath().substring(0, BondController.class.getProtectionDomain().getCodeSource().getLocation().getPath().length()-5);

	    IAtomContainer mol = smipar.parseSmiles(comp.getSmiles());
	    mol.setProperty(CDKConstants.TITLE, comp.getName());

	    DepictionGenerator dptgen = new DepictionGenerator();
	    dptgen.withSize(200, 250).withMolTitle().withTitleColor(Color.DARK_GRAY);
	    dptgen.depict(mol).writeTo("/structure.png", path + "/src/resources");
	}

	//public void display()
	{
		org.jsoup.nodes.Document doc = null;
		String CID;

		try
		{
			System.out.println(comp.getName().replaceAll(" ", "%20"));
			doc = org.jsoup.Jsoup.connect("https://pubchem.ncbi.nlm.nih.gov/compound/" + comp.getName().replaceAll(" ", "%20")).post();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

        org.jsoup.select.Elements elements = doc.getAllElements();

        if (elements.isEmpty())
        {
            CID = "No results";
        }
        else
        {
        	for(int i = 0; i<elements.size(); i++)
        	{
        		CID = elements.get(i).text();
        		System.out.println(CID);
        	}

        	try
    		{
    			//doc = org.jsoup.Jsoup.connect("https://pubchem.ncbi.nlm.nih.gov/image/imagefly.cgi?cid=" + CID + "&width=500&height=500").post();
    			Response resultImageResponse = Jsoup.connect("https://pubchem.ncbi.nlm.nih.gov/image/imagefly.cgi?cid=" + CID + "&width=500&height=500").ignoreContentType(true).execute();
    			String path = BondController.class.getProtectionDomain().getCodeSource().getLocation().getPath().substring(0, BondController.class.getProtectionDomain().getCodeSource().getLocation().getPath().length()-5) + "/src/resources";
    			FileOutputStream out = (new FileOutputStream(new java.io.File(path + "structure.png")));
    			out.write(resultImageResponse.bodyAsBytes());
    			out.close();
    		}
    		catch (IOException e)
    		{
    			e.printStackTrace();
    		}
        }
        //org.jsoup.select.Elements image = doc.select("img");

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
