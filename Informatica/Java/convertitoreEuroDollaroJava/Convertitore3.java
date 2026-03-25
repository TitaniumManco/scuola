import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/* Tutorial JavaFX – BorderPane
BorderPane è un layout che divide lo spazio in 5 aree:
    TOP
    BOTTOM
    LEFT
    RIGHT
    CENTER

Ogni regione può contenere un solo nodo
Il CENTER prende tutto lo spazio disponibile rimanente
*/
public class Convertitore3 extends Application {
    @Override
    public void start(Stage stage) {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));
        Button btnc = new Button("Chiudi");

        btnc.setOnAction(e -> {stage.close();});
        root.setTop(btnc);
        

        GridPane grid = new GridPane();

        grid.setHgap(10);
        grid.setVgap(10);

        Label lbl1 = new Label("Inserisci il valore:");
        TextField txt = new TextField();
        Label lbl2 = new Label();
        Button btne = new Button("Ad euro");
        Button btnd = new Button("A dollaro");

        btne.setOnAction(e -> lbl2.setText("Il valore in euro è di: " + String.valueOf(Integer.parseInt(txt.getText()) * 0.86)));
        btnd.setOnAction(e -> lbl2.setText("Il valore in dollari è di: " + String.valueOf(Integer.parseInt(txt.getText()) * 1.16)));

        grid.addColumn(0, lbl1, txt, lbl2, btne);
        grid.add(btnd, 1, 3);

        root.setCenter(grid);
        stage.setScene(new Scene(root, 500, 300));
        stage.show();
    }
    public static void main(String[] args) { launch(); }
}
