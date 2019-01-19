package application;

import java.io.IOException;
import java.util.Arrays;

import org.jsoup.select.Elements;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application
{
    public static void main(String[] args)
    {
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