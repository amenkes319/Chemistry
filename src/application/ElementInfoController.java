package application;

import java.text.DecimalFormat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ElementInfoController
{
	Stage stgElementInfo;
	Stage stgBack;
	int atomicNum;

	@FXML
	private Button btnMenu;

	@FXML
	private Label lblName = new Label();
	@FXML
	private Label lblSymbol = new Label();
	@FXML
	private Label lblAtomicNum = new Label();
	@FXML
	private Label lblAtomicMass = new Label();
	@FXML
	private Label lblNeutrons = new Label();
	@FXML
	private Label lblGroup = new Label();
	@FXML
	private Label lblPeriod = new Label();
	@FXML
	private Label lblDiatomic = new Label();
	@FXML
	private Label lblIE = new Label();
	@FXML
	private Label lblEN = new Label();
	@FXML
	private Label lblMeltingPt = new Label();
	@FXML
	private Label lblBoilingPt = new Label();
	@FXML
	private Label lblDensity = new Label();
	@FXML
	private Label lblRadius = new Label();
	@FXML
	private Label lblEConfig = new Label();
	@FXML
	private Label lblPhase = new Label();
	@FXML
	private Label lblType = new Label();
	@FXML
	private Label lblOxidState = new Label();

	public ElementInfoController(int a, Stage s)
	{
		atomicNum = a;
		stgBack = s;
		stgElementInfo = new Stage();
		try
		{
			stgElementInfo.getIcons().add(new Image(this.getClass().getResourceAsStream("icon.png")));
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Element Info.fxml"));

			loader.setController(this);

			stgElementInfo.setScene(new Scene(loader.load()));
			stgElementInfo.setTitle("Element Info");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public void initialize()
	{
		Element element = new Element(atomicNum);
		DecimalFormat df = new DecimalFormat("#.######");

		lblName.setText(element.getName());
		lblSymbol.setText("Element Symbol: " + element.getSymbol());
		lblAtomicNum.setText("Atomic Number: " + atomicNum);
		lblAtomicMass.setText("Atomic Mass: " + element.getAtomicMass());
		lblNeutrons.setText("Number of Neutrons: " + element.getNumOfNeutrons());
		lblGroup.setText("Group: " + element.getGroup());
		lblPeriod.setText("Period: " + element.getPeriod());
		lblDiatomic.setText(element.getDiatomic());
		lblIE.setText("Ionization Energy: " + element.getIonizationEnergy());

		if(element.getElectronegativity() == 0)
			lblEN.setText("Electronegativity: N/A");
		else
			lblEN.setText("Electronegativity: " + element.getElectronegativity());

		if(element.getMeltingPoint() == 0)
			lblMeltingPt.setText("Melting Point: N/A");
		else
			lblMeltingPt.setText("Melting Point: " + element.getMeltingPoint() + "K");

		if(element.getBoilingPoint() == 0)
			lblBoilingPt.setText("Boiling Point: N/A");
		else
			lblBoilingPt.setText("Boiling Point: " + element.getBoilingPoint() + "K");

		if(element.getDensity() == 0)
			lblDensity.setText("Density: N/A");
		else
			lblDensity.setText("Density: " + df.format(element.getDensity()) + "g/cm3");

		lblRadius.setText("Atomic Radius: " + element.getAtomicRadius() + "pm");
		lblEConfig.setText("Electron Configuration: " + element.getElectronConfiguration());
		lblPhase.setText("Phase at STP: " + element.getPhase());
		lblType.setText("Type: " + element.getType());

		if(element.getOxidationState() > 0)
			lblOxidState.setText("Oxidation State: +" + element.getOxidationState());
		else
			lblOxidState.setText("Oxidation State: " + element.getOxidationState());
	}

	public void showStage()
	{
		stgElementInfo.show();
	}

	public void loadBack()
	{
		stgElementInfo.close();
		stgBack.show();
	}

	public void loadMenu()
	{
		MainMenuController ctrlMenu = new MainMenuController();
		ctrlMenu.showStage();
		stgElementInfo.close();
	}
}
