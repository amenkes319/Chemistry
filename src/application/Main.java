package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application
{
    public static void main(String[] args)
    {
//    	org.jsoup.nodes.Document doc = null;
//    	try
//		{
//			doc = org.jsoup.Jsoup.connect("https://pubchem.ncbi.nlm.nih.gov/compound/Methane").post();
//		}
//		catch (IOException e)
//		{
//			e.printStackTrace();
//		}
//
//        String black = doc.getAllElements().text();
//        System.out.println(black);
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