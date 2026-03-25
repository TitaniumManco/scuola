public class Spell {
    private String spellName;
    private int manaCost;
    private int baseValue;
    private String type;

    public Spell(String spellName, int manaCost, int baseValue, String type) {
        this.spellName = spellName;
        this.manaCost = manaCost;
        this.baseValue = baseValue;
        this.type = type;
    }

    public int getManaCost() {
        return manaCost;
    }
    
    public String getType() {
        return type;
    }

    public int getBaseValue() {
        return baseValue;
    }

    public String getSpellName() {
        return spellName;
    }

}
