import java.util.ArrayList;
import java.util.Random;

public class GameData {

    private static final ArrayList<Spell> baseSpellBook = new ArrayList<>();
    // initializes the spell pool
    static {
        baseSpellBook.add(new Spell("Small Heal", 5, 10, "Heal"));
        baseSpellBook.add(new Spell("Large Heal", 10, 20, "Heal"));
        baseSpellBook.add(new Spell("Flamethrower", 7, 10, "Attack"));  // Pokémon
        baseSpellBook.add(new Spell("Aura Sphere", 8, 12, "Attack"));
        baseSpellBook.add(new Spell("Blizzard", 5, 7, "Attack"));
        baseSpellBook.add(new Spell("Ice Beam", 8, 12, "Attack"));
        baseSpellBook.add(new Spell("Draco Meteor", 10, 15, "Attack"));
        baseSpellBook.add(new Spell("Razor Leaf", 4, 5, "Attack"));
        baseSpellBook.add(new Spell("Petal Dance", 9, 13, "Attack"));
        baseSpellBook.add(new Spell("Moonblast", 8, 12, "Attack"));
        baseSpellBook.add(new Spell("Bubble Beam", 6, 8, "Attack"));
        baseSpellBook.add(new Spell("Liquidation", 7, 10, "Attack"));
        baseSpellBook.add(new Spell("Azure Comet", 9, 13, "Attack"));   // Elden Ring
        baseSpellBook.add(new Spell("Rotten Breath", 6, 8, "Attack"));
        baseSpellBook.add(new Spell("Poison Mist", 5, 7, "Attack"));
        baseSpellBook.add(new Spell("Scarlet Aeonia", 7, 10, "Attack"));
        baseSpellBook.add(new Spell("Rock Sling", 6, 8, "Attack"));
        baseSpellBook.add(new Spell("Placidusax's Ruin", 10, 15, "Attack"));
        baseSpellBook.add(new Spell("Gravity Well", 7, 10, "Attack"));
        baseSpellBook.add(new Spell("Stars of Ruin", 9, 13, "Attack"));
        baseSpellBook.add(new Spell("Burn, O Flame!", 6, 8, "Attack"));
        baseSpellBook.add(new Spell("Frenzied Burst", 8, 12, "Attack"));  // Dragon Ball
        baseSpellBook.add(new Spell("Kamehameha", 10, 15, "Attack"));
        baseSpellBook.add(new Spell("Final Flash", 8, 12, "Attack"));
        baseSpellBook.add(new Spell("Gyarikku Ho", 4, 5, "Attack"));
        baseSpellBook.add(new Spell("Genki-dama", 10, 15, "Attack"));
        baseSpellBook.add(new Spell("Kienzan", 6, 8, "Attack"));
        baseSpellBook.add(new Spell("Makankosappo", 9, 13, "Attack"));
        baseSpellBook.add(new Spell("Masenko", 6, 8, "Attack"));
        baseSpellBook.add(new Spell("Shin Kikoho", 9, 13, "Attack"));
        baseSpellBook.add(new Spell("Soul Punisher", 9, 13, "Attack"));
        baseSpellBook.add(new Spell("Lunar Flare", 9, 13, "Attack"));    // Terraria
        baseSpellBook.add(new Spell("Crystal Storm", 7, 10, "Attack"));
        baseSpellBook.add(new Spell("Razorblade Typhoon", 9, 13, "Attack"));
        baseSpellBook.add(new Spell("Blood Thorn", 6, 8, "Attack"));
        baseSpellBook.add(new Spell("Stellar Tune", 10, 15, "Attack"));
        baseSpellBook.add(new Spell("Nightglow", 7, 10, "Attack"));
        baseSpellBook.add(new Spell("Magic Dagger", 4, 5, "Attack"));
        baseSpellBook.add(new Spell("Last Prism", 8, 12, "Attack"));
        baseSpellBook.add(new Spell("Cursed Flames", 6, 8, "Attack"));
    }

    private static final String[] botNames = {
        "Ranni", "Magikoopa", "Shang Tsung", "Sir Gideon Ofnir",
        "Minä", "Nebula Mage", "Ainz Ooal Gown"
    };

    private static final String[] botAliases = {
        "The Witch", "Kamek", "Soul Stealer",
        "The All-Knowing", "Knower to be", "Star Weaver", "Overlord"
    };


    /**
     * creates a bot wizard with a predefined name and alias
     * @param index (int)
     * @return bot (Wizard)
     */
    public static Wizard createBot(int index) {
        return new Wizard(botNames[index - 1], botAliases[index - 1], index, true, casualSpellBook());
    }

    /**
     * generates a randomized spellbook
     * @return spellBook (ArrayList<Spell>)
     */
    public static ArrayList<Spell> casualSpellBook() {
        Random rand = new Random();
        ArrayList<Spell> personalSpellBook = new ArrayList<>();

        personalSpellBook.add(baseSpellBook.get(rand.nextInt(2))); // to guarantee at least one heal

        while (personalSpellBook.size() < 4) {
            int randomIndex = rand.nextInt(baseSpellBook.size());
            Spell spell = baseSpellBook.get(randomIndex);
            
            if (!personalSpellBook.contains(spell) && !spell.getType().equals("Heal")) {
                personalSpellBook.add(spell);
            }
        }

        return personalSpellBook;
    }

    /**
     * sorts in place the passed ArrayList containing the wizards by speed
     * @param wizards (ArrayList<Wizard>)
     */
    public static void sortBySpeed(ArrayList<Wizard> wizards){
        for(int i = 0; i < wizards.size() - 1; i++){
            for(int j = 0; j < wizards.size() - 1 - i; j++){
                if(wizards.get(j).getSpeed() < wizards.get(j + 1).getSpeed()){
                    Wizard temp = wizards.get(j);
                    wizards.set(j, wizards.get(j + 1));
                    wizards.set(j + 1, temp);
                }
            }
        }
    }

    /**
     * returns the base spell book
     * @return baseSpellBook (ArrayList<Spell>)
     */
    public static ArrayList<Spell> getBaseSpellBook() {
        return baseSpellBook;
    }
}