package application;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application
{
    public static void main(String[] args)
    {
    	Compound test = new Compound(new Element(6),1,new Element(8),2);
    	System.out.println(test.getSmiles());

    	launch(args);
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