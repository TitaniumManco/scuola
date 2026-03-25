import java.util.ArrayList;
import java.util.Random;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GamePlayController {

    private ArrayList<Wizard> wizards;
    private Wizard player;
    private Wizard selectedTarget;

    /**
     * sets the wizard list with the parameter, finds the player inside it, sets the turn count to 1 and 
     * @param wizards
     */
    public void initializeGame(ArrayList<Wizard> wizards) {
        this.wizards = wizards;
    
        for (Wizard wizard : wizards) {
            if (!wizard.isBot()){
                this.player = wizard;
            }
        }

        lblTurnCounter.setText("1");
        updatePlayerStats();
        updateTargetSelector();
    }

    /**
     * updates the player's stats after the player and the bots take action
     */
    private void updatePlayerStats() {
        lblHP.setText(player.getActualHP() + "/" + player.getMaxHP());
        lblMana.setText(player.getActualMana() + "/" + player.getMaxMana());
    }

    private void updateBotStats() {
        if (selectedTarget != null) {
            lblBotHP.setText(selectedTarget.getActualHP() + "/" + selectedTarget.getMaxHP());
            lblBotDef.setText(String.valueOf(selectedTarget.getDefence()));
        }
    }

    /**
     * generates buttons that the player can click to choose a target 
     */
    private void updateTargetSelector() {
        targetSelector.getChildren().clear();

        for (Wizard w : wizards) {
            if (w.isBot() && w.isAlive()) {
                Button btn = new Button(w.getName());
                btn.setPrefWidth(120);
                btn.setOnAction(e -> selectTarget(w));
                targetSelector.getChildren().add(btn);
            }
        }

        if (selectedTarget != null && !selectedTarget.isAlive()) {      // if the wizard we were targeting is dead
            selectedTarget = null;
            imgBotMage.setImage(null);                                  // it clears everything
            lblBotHP.setText("");                                       // associated with him
            lblBotDef.setText("");
            lblBotInfoHP.setText("");
            lblBotInfoDef.setText("");
        }
    }

    /**
     * shows info regarding hp and defence, and also sets the selectedTarget variable
     * @param target
     */
    private void selectTarget(Wizard target) {
        this.selectedTarget = target;
        lblBotInfoHP.setText("HP:");
        lblBotInfoDef.setText("Def:");
        updateBotStats();
        imgBotMage.setImage(new Image(getClass().getResourceAsStream("images/player" + target.getImageIndex() + ".png")));
    }


    @FXML
    private Button btnAction;

    @FXML
    private Button btnRest;

    @FXML
    private Label lblBotInfoDef;

    @FXML
    private Label lblBotInfoHP;

    @FXML
    private ImageView imgBotMage;

    @FXML
    private ImageView imgPlayerMage;

    @FXML
    private Label lblHP;

    @FXML
    private Label lblMana;

    @FXML
    private Label lblBotDef;

    @FXML
    private Label lblBotHP;

    @FXML
    private Label lblTurnCounter;

    @FXML
    private Label msgLose;

    @FXML
    private Label msgWin;

    @FXML
    private VBox spellSelector;

    @FXML
    private VBox targetSelector;

    @FXML
    private VBox turnResume;

    @FXML
    void btnRestController(ActionEvent event) {
        clear();
        
        player.rest();
        updatePlayerStats();
        botsTurn();
    }

    @FXML
    void btnActionController(ActionEvent event) {
        clear();

        for (Spell s : player.getSpellBook()) {             // creates the ui to choose the spell and shows info such as dmg and mana cost
            Button btn = new Button(s.getSpellName());
            btn.setPrefWidth(120);
            btn.setOnAction(e -> playerCast(s));

            Label info;
            if (s.getType().equals("Heal")) {
                info = new Label("+" + s.getBaseValue() + " health");
            } else {
                info = new Label((s.getBaseValue() + player.getMagicalStrength()) + " dmg");
            }

            Label mana = new Label(s.getManaCost() + " mp");

            HBox row = new HBox(10, btn, info, mana);
            spellSelector.getChildren().add(row);
        }

        Label disclaimer = new Label("* Damage is not final, defence is not calculated");
        spellSelector.getChildren().add(disclaimer);
    }

    /**
     * allows the player to cast the spell against the target
     * @param spell (Spell)
     */
    private void playerCast(Spell spell) {
        
        if (spell.getType().equals("Attack") && selectedTarget == null) {
            logAction("Select a target first!");
            return;
        }

        if (!player.canCast(spell)) {
            logAction("Not enough mana!");
            return;
        }

        if(spell.getType().equals("Heal")) {
            player.cast(spell, selectedTarget);
            logAction(player.getName() + " heals using " + spell.getSpellName());
        }
        else{
            castAndLog(player, spell, selectedTarget);
        }

        spellSelector.getChildren().clear();    // hides the spell selector after casting
        updatePlayerStats();
        botsTurn();
    }

    /**
     * lets the bot heal, rest or attack, attacked target and spell are all randomized
     */
    private void botsTurn(){
        Random rand = new Random();

        for (Wizard bot : wizards) {
            if (!player.isAlive()) {
                break;
            }

            if(bot.isBot() && bot.isAlive()){

                boolean isCritical = bot.getActualHP() < bot.getMaxHP() / 3; 
                Spell healSpell = bot.getSpellBook().get(0);

                if(isCritical && bot.canCast(healSpell)){
                    bot.cast(healSpell, bot);   // here the bot heals himself when his health is less than a third of his maximum and gets the heal spell which is ALWAYS the first in the spellbook
                    logAction(bot.getName() + " heals using " + healSpell.getSpellName());
                }
                else{

                    ArrayList<Wizard> targettable = new ArrayList<>();  
                    for (Wizard w : wizards) {                                   // here we generate a list of possible targets so we can randomize the final target
                        if(w.isAlive() && w != bot && w.getTimesHit() < 1){      // to be added a wizard must be alive not the caster and more importantly he must not have gotten hit already during the turn
                            targettable.add(w);
                        }
                    }

                    ArrayList<Spell> castable = new ArrayList<>();
                    for (Spell s : bot.getSpellBook()) {
                        if (bot.canCast(s) && s.getType().equals("Attack")) {       // here we generate a list of castable spells so we can randomize the casted spell
                            castable.add(s);
                        }
                    }

                    if(!castable.isEmpty()){
                        Spell finalSpell = castable.get(rand.nextInt(castable.size()));             // getting random spell... 
                        Wizard finalBotTarget = targettable.get(rand.nextInt(targettable.size()));  // getting random target...

                        castAndLog(bot, finalSpell, finalBotTarget);
                    }
                    else{
                        bot.rest();
                        logAction(bot.getName() + " rested");
                    }
                }
            }
            
            updatePlayerStats();
        }


        updateTargetSelector();
        updateBotStats();
        checkGameOver();
        lblTurnCounter.setText(String.valueOf(Integer.parseInt(lblTurnCounter.getText()) + 1));
    }

    /**
     * decides if the game continues or ends by checking the alive status of the human player or if all the bots are dead
     */
    private void checkGameOver() {
        if (!player.isAlive()) {
            msgLose.setVisible(true);
            btnAction.setDisable(true);
            btnRest.setDisable(true);
            spellSelector.getChildren().clear();
            return;
        }

        boolean allDead = true;
        for (Wizard w : wizards) {
            if (w.isBot() && w.isAlive()) {
                allDead = false;
            }
        }

        if (allDead) {
            msgWin.setVisible(true);
            btnAction.setDisable(true);
            btnRest.setDisable(true);
            spellSelector.getChildren().clear();
        }
    }

    /**
     * adds to the turn log the string passed as parameter
     * @param message (String)
     */
    private void logAction(String message) {
        Label log = new Label(message);
        turnResume.getChildren().add(log);
    }

    /**
     * first casts the spell, increases hit count, and lastly logs it
     * @param caster (Wizard)
     * @param spell (Spell)
     * @param target (Wizard)
     */
    private void castAndLog(Wizard caster, Spell spell, Wizard target){
        caster.cast(spell, target);
        target.incrementTimesHit();
        logAction(caster.getName() + " cast " + spell.getSpellName() + " on " + target.getName());
        deathMessage(target);
    }

    /**
     * prints on the log a death message
     * @param dead
     */
    private void deathMessage(Wizard dead) {
        if(!dead.isAlive()){
            logAction("--- Rejoice! " + dead.getName() + " has been defeated!");
        }
    }

    /**
     * clears gui elements and resets the hitcount
     */
    private void clear() {
        for (Wizard wizard : wizards) {
            wizard.resetTimesHit();             // resets the hit counter for the turn
        }
        turnResume.getChildren().clear();       // clears for display
        spellSelector.getChildren().clear();    // clears for display
    }

}
