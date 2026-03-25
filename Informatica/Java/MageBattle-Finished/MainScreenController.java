import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MainScreenController {

    @FXML
    private Button btnStart;

    @FXML
    private TextField playerCount;

    @FXML
    void btnStartController(ActionEvent event) throws IOException{
        int numBots;

        String input = playerCount.getText();   // gets the number of opponents

        if(input == null || input.isBlank()){   // if input is blank uses a base of 1 opponent
            numBots = 1;
        }
        else{
            try {
                numBots = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                numBots = 1;    // if input is not a number, defaults to 1
            }
        }

        if (numBots < 1) {       // minimum 1 opponent
            numBots = 1;
        }
        if (numBots > 7) {       // maximum 7 opponents
            numBots = 7;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("nameInputs.fxml"));
        Parent root = loader.load();

        NameInputsController controller = loader.getController();
        controller.setNumBots(numBots);   // passes the bot count to the next screen

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }
}
