package application;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application
{
    @Override
    public void start(Stage primaryStage)
    {
    	primaryStage.getIcons().add(new Image(this.getClass().getResourceAsStream("icon.png")));
        MainMenuController ctrlMainMenu = new MainMenuController();
        ctrlMainMenu.showStage();
        AlertBox.displayMainAlert();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}