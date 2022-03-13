package View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GUI extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Window1.fxml"));
            Scene scene = new Scene(root, 950, 600);
            primaryStage.setTitle("Select program to run");
            primaryStage.setScene(scene);
            primaryStage.show();
        }catch(Exception e){
        }
    }
}
