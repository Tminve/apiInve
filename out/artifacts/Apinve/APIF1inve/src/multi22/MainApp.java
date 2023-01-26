package APIF1inve.src.multi22;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import APIF1inve.src.multi22.*;

import java.io.IOException;

public class MainApp extends Application
{
    private Stage primaryStage;
    private MainController mainController;

    @Override
    public void start(Stage primaryStage) throws Exception 
    {
        
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Multi22");

        intialize();
        
    }
    
     public static void main(String[] args) 
    {
        launch(args);
    }

    private void intialize()
    {
        
        try 
        {



            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("mainGui.fxml"));
            AnchorPane rootLayout = loader.load();

            //Model initialization


            mainController=loader.getController();
            mainController.setMainApp(this);
            
            
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();

        }
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        
    }

}
