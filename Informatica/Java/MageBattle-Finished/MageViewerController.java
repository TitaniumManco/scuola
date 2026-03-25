import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MageViewerController {

    private ArrayList<Wizard> wizards;

    @FXML
    private FlowPane playersContainer;

    @FXML
    private Button btnBattleStart;

    /**
     * sets the wizards arraylist of the controller and generates the players
     * @param wizards (ArrayList<Wizard>)
     */
    public void setWizards(ArrayList<Wizard> wizards) {

        this.wizards = wizards;

        playersContainer.getChildren().clear();
        for (int i = 0; i < wizards.size(); i++) {
            playersContainer.getChildren().add(showPlayers(wizards.get(i)));     // adds the image and stats of a player to the screen
        }
    }

    /**
     * returns an HBox used to show the player's image and stats
     * @param wizard (Wizard)
     */
    private HBox showPlayers(Wizard wizard) {
        ImageView photo = new ImageView(new Image(getClass().getResourceAsStream("images/player" + wizard.getImageIndex() + ".png")));
        photo.setFitWidth(250);
        photo.setFitHeight(300);

        Label hp = new Label("HP: " + wizard.getMaxHP());
        Label mana = new Label("Mana: " + wizard.getMaxMana());
        Label speed = new Label("Speed: " + wizard.getSpeed());
        Label name = new Label("Name: " + wizard.getName());
        Label nick = new Label("Nickname: " + wizard.getAlias());
        Label def = new Label("Defence: " + wizard.getDefence());
        Label st = new Label("Magical Strength: " + wizard.getMagicalStrength());


        VBox stats = new VBox(5, name, nick, speed, hp, mana, def, st);
        return new HBox(10, photo, stats);
    }

    @FXML
    void btnBattleStartController(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("gamePlay.fxml"));
        Parent root = loader.load();

        GamePlayController controller = loader.getController();
        controller.initializeGame(wizards);     // passes the wizard list to the next screen

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

}
