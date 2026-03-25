import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import java.io.IOException;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NameInputsController {

    private int numBots;
    private ArrayList<Wizard> wizards = new ArrayList<>();
    
    /**
     * sets the number of bots
     * @param n (int)
     */
    public void setNumBots(int n) {
        this.numBots = n;
    }

    @FXML
    private Button btnNext;

    @FXML
    private TextField userName;

    @FXML
    private TextField userNickname;

    @FXML
    void btnNextController(ActionEvent event) throws IOException {
        wizards.add(new Wizard(userName.getText(), userNickname.getText(), 0, false, GameData.casualSpellBook()));    // creates the player

        // generates the bots
        for (int i = 0; i < numBots; i++) {
            wizards.add(GameData.createBot(i + 1));     // index starts at 1 since 0 is the human
        }

        GameData.sortBySpeed(wizards);
    
        FXMLLoader loader = new FXMLLoader(getClass().getResource("mageViewer.fxml"));
        Parent root = loader.load();

        MageViewerController controller = loader.getController();
        controller.setWizards(wizards);     // passes the wizard list to the viewer screen

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }


}
