package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ElementInfoController
{
	Stage stgElementInfo;
	int atomicNum;
	@FXML
	private Button btnBack;
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

	public ElementInfoController(int a)
	{
		atomicNum = a;
		stgElementInfo = new Stage();
		try
		{
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

		lblName.setText(element.getName());
		lblSymbol.setText("Element Symbol: " + element.getSymbol());
		lblAtomicNum.setText("Atomic Number: " + atomicNum);
		lblAtomicMass.setText("Atomic Mass: " + element.getAtomicMass());
		lblNeutrons.setText("Number of Neutrons: " + element.getNumOfNeutrons());
		lblGroup.setText("Group: " + element.getGroup());
		lblPeriod.setText("Period: " + element.getPeriod());
		lblDiatomic.setText(element.getDiatomic());
		lblIE.setText("Ionization Energy: " + element.getIonizationEnergy());
		lblEN.setText("Electronegativity: " + element.getElectronegativity());
		lblMeltingPt.setText("Melting Point: " + element.getMeltingPoint() + "K");
		lblBoilingPt.setText("Boiling Point: " + element.getBoilingPoint() + "K");
		lblDensity.setText("Density: " + element.getDensity() + "g/cm3");
		lblRadius.setText("Atomic Radius: " + element.getAtomicRadius() + "pm");
		lblEConfig.setText("Electron Configuration: " + element.getElectronConfiguration());
		lblPhase.setText("Phase at STP: " + element.getPhase());
		lblType.setText("Type: " + element.getType());
		if(element.getOxidationState() < 0)
			lblOxidState.setText("Oxidation State: " + element.getOxidationState());
		else
			lblOxidState.setText("Oxidation State: +" + element.getOxidationState());
	}

	public void showStage()
	{
		stgElementInfo.show();
	}

	public void loadBack()
	{
		SearchController ctrlSearch = new SearchController();
		ctrlSearch.showStage();
		stgElementInfo.close();
	}

	public void loadMenu()
	{
		MainMenuController ctrlMenu = new MainMenuController();
		ctrlMenu.showStage();
		stgElementInfo.close();
	}
}
