import java.util.ArrayList;

import java.util.Random;

public class Wizard {
    // basic attributes
    private String name;
    private String alias;
    private int actualHP;
    private int maxHP;
    private int actualMana;
    private int maxMana;
    private int magicalStrength;
    private int defence;
    private int speed;
    private int imageIndex;
    private ArrayList<Spell> spellBook;

    // game flow attributes
    private boolean isBot;
    private int timesHit = 0;
    
    public Wizard(String name, String alias, int index, boolean isBot, ArrayList<Spell> spellBook) {
        this.name = name;
        this.alias = alias;
        Random rand = new Random();

        this.maxHP = rand.nextInt(21) + 40;
        this.actualHP = maxHP;

        this.maxMana = rand.nextInt(21) + 20;
        this.actualMana = this.maxMana;

        this.magicalStrength = rand.nextInt(6) + 5;

        this.defence = rand.nextInt(6) + 3;

        this.speed = rand.nextInt(10) + 1;

        this.imageIndex = index;

        this.isBot = isBot;

        this.spellBook = spellBook;
    }

    boolean isAlive(){
        return this.actualHP > 0;
    }

    void takeDamage(int damage){
        this.actualHP -= damage;
        if (this.actualHP < 0){
            this.actualHP = 0;
        }
    }

    /**
     * heals the caster
     * @param amount (int)
     */
    void heal(int amount){
        this.actualHP += amount;
        if(this.actualHP > this.maxHP){
            this.actualHP = this.maxHP;
        }
    }

    boolean canCast(Spell s){
        return this.actualMana >= s.getManaCost();
    }

    /**
     * casts a spell against an enemy wizard if the spell is type attack, else it heals the caster
     * @param s (Spell)
     * @param target (Wizard)
     */
    void cast(Spell s, Wizard target){
        if(isAlive() && canCast(s)){
            this.actualMana -= s.getManaCost();
            if(s.getType().toLowerCase().equals("heal")){
                heal(s.getBaseValue());
            }
            else{
                target.takeDamage(getFinalDamage(s, target));
            }
        }
    }

    /**
     * gives plus 5 mana to the caster
     */
    void rest(){
        this.actualMana += 5;
        if(this.actualMana > this.maxMana){
            this.actualMana = this.maxMana;
        }
    }

    /**
     * gets the effective damage calculated by summing the magical strength and the spell's base damage, and removing the target's defence
     * @param spell (Spell) 
     * @param target (Wizard)
     * @return int damage
     */
    int getFinalDamage(Spell spell, Wizard target){
        return Math.max(this.magicalStrength + spell.getBaseValue() - target.defence, 0);       // if the damage is negative it returns 0
    }

    /**
     * adds a spell to the wizard's spellbook, ignores duplicates
     * @param spell (Spell)
     */
    void addSpellToSpellBook(Spell spell){
        if(!this.spellBook.contains(spell)){
            spellBook.add(spell);
        }
    }

    public int getSpeed() {
        return speed;
    }

    public String getName() {
        return name;
    }

    public String getAlias() {
        return alias;
    }

    public int getActualHP() {
        return actualHP;
    }
    
    public int getActualMana() {
        return actualMana;
    }

    public int getDefence() {
        return defence;
    }

    public int getMagicalStrength() {
        return magicalStrength;
    }
    
    public int getMaxHP() {
        return maxHP;
    }

    public int getMaxMana() {
        return maxMana;
    }

    public int getImageIndex() {
        return imageIndex;
    }

    public boolean isBot() {
        return isBot;
    }

    public int getTimesHit() {
        return timesHit;
    }

    public void incrementTimesHit() {
        this.timesHit++;
    }

    public void resetTimesHit() {
        this.timesHit = 0;
    }

    public ArrayList<Spell> getSpellBook() {
        return spellBook;
    }
}
