import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

// THE PROGRAM STARTS HERE AND GOES, mainScreen > nameInputs > mageViewer > gamePlay


public class MageBattle extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("mainScreen.fxml"));
        stage.setTitle("Main Menu");
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
    }
    
    public static void main(String[] args) {
        launch();
    }
}