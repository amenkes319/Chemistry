package application;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Logger;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class BondController
{
	Stage stgBond;
	Compound comp;

	@FXML
	private ImageView imgStructure;
	@FXML
	private Label lblFormula;
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
		imgStructure = new ImageView();
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
		try
		{
			display(findCID());
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		lblFormula.setText(Compound.subscript(comp.getFormula()));
		lblName.setText(comp.getName()[0]);

		if(lblName.getText().equals("Hydrogen Fluoride"))
			lblName.setText("Hydrofluoric Acid");

		lblType.setText(comp.getBondType());
		lblPolarity.setText(comp.getBondPolarity());
		lblMoleculeShape.setText(comp.getMoleculeShape());

		File file = new File("bin/resources/structure.png");
		//Image image = new Image(file.toURI().toString());

		Platform.runLater( () -> { Image image = new Image(file.toURI().toString()); imgStructure.setImage(image); } );

		//imgStructure.setImage(image);
	}

	public String findCID() throws IOException
	{
		org.jsoup.nodes.Document doc = null;
    	try
		{
			doc = org.jsoup.Jsoup.connect("https://pubchem.ncbi.nlm.nih.gov/rest/pug/compound/name/" + comp.getName()[0].replace(" ","%20").trim() + "/record/SDF/?record_type=2d&response_type=display").get();
		}
		catch (Exception e)
		{
	    	org.jsoup.nodes.Document doc1 = null;
	    	try
			{
				doc1 = org.jsoup.Jsoup.connect("https://pubchem.ncbi.nlm.nih.gov/rest/pug/compound/formula/" + "BH3" + "/txt").get();
				String listkey = doc1.getAllElements().text().split("Your request is running ListKey: ")[1].trim();
				try
				{
					doc1 = org.jsoup.Jsoup.connect("https://pubchem.ncbi.nlm.nih.gov/rest/pug/compound/listkey/" + listkey + "/cids/txt").get();
						return doc1.getAllElements().text().split(" ")[0];
				}
				catch(Exception e2)
				{
					e.printStackTrace();
				}
			}
			catch(Exception e1)
			{
				e1.printStackTrace();
			}
		}

        if (doc == null)
        {
            return "No results";
        }
        else
        {
        	return doc.getAllElements().text().split(" ")[0];
        }
	}

	public void display(String CID)
	{
		InputStream inputStream = null;
		OutputStream outputStream = null;

		try
		{
			URL url = new URL("https://pubchem.ncbi.nlm.nih.gov/image/imagefly.cgi?cid=" + CID + "&width=500&height=500");
			String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36";
			URLConnection con = url.openConnection();
			con.setRequestProperty("User-Agent", USER_AGENT);
			inputStream = con.getInputStream();
			String path = BondController.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "/resources";
			new File(path + "/structure.png").delete();
			outputStream = new FileOutputStream(new java.io.File(path + "/structure.png"), false);

			byte[] buffer = new byte[2048];

			int length;
			while ((length = inputStream.read(buffer)) != -1)
			{
				outputStream.write(buffer, 0, length);
			}
			outputStream.close();
			inputStream.close();
		}
		catch (Exception ex)
		{
			Logger.getLogger("Compound Image Not Available");
		}
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
