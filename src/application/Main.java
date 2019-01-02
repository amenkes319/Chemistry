package application;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application
{
    public static void main(String[] args)
    {
    	Compound nigga = new Compound("H2O");
    	System.out.println(nigga.getFormula());
    	System.out.println(nigga.getName());
    	System.out.println(nigga.getElement1());
    	System.out.println(nigga.getElement2());
    	System.out.println(nigga.getQuantity1());
    	System.out.println(nigga.getQuantity2());
    	//launch(args);
    }

    @Override

    public void start(Stage primaryStage)
    {
    	primaryStage.getIcons().add(new Image(this.getClass().getResourceAsStream("icon.png")));
        MainMenuController ctrlMainMenu = new MainMenuController();
        ctrlMainMenu.showStage();
        AlertBox.displayMainAlert();
    }
}