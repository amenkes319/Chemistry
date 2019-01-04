package application;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

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
			display();
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

	public void display()
	{
		org.jsoup.nodes.Document doc = null;
    	try
		{
			doc = org.jsoup.Jsoup.connect("https://pubchem.ncbi.nlm.nih.gov/rest/pug/compound/name/ " + comp.getName().replaceAll(" ","%20") + "/record/SDF/?record_type=2d&response_type=display").get();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

        String CID;

        if (false)
        {
            CID = "No results";
        }
        else
        {
        	CID = doc.getAllElements().text().split(" ")[0];
                System.out.println(CID.split(" ")[0]);
        	try
    		{
    			doc = org.jsoup.Jsoup.connect("https://pubchem.ncbi.nlm.nih.gov/image/imagefly.cgi?cid=" + CID + "&width=500&height=500").get();
                        org.jsoup.select.Elements img = doc.getElementsByTag("img");
    			String path = BondController.class.getProtectionDomain().getCodeSource().getLocation().getPath().substring(0, BondController.class.getProtectionDomain().getCodeSource().getLocation().getPath().length()-5) + "/src/resources";
    			FileOutputStream out = (new FileOutputStream(new java.io.File(path + "structure.png")));
                String src = null;        
    			
                        for(org.jsoup.nodes.Element el : img)
                        {
                        	src = el.absUrl("src");
                        }
                        URL url = new URL(src);
                        InputStream in = url.openStream();
                        
                        for (int b; (b = in.read()) != -1;) 
                        {
                            out.write(b);
                        }

                        in.close();
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