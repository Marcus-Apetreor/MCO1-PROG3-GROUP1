import java.util.*;
import javax.swing.JOptionPane;
/**
 * Class for representing a player character in the game.
 * It contains player attributes and methods related to leveling up and managing stats.
 * 
 * @author Marcus Apetreor
 */
public class Player {

    private String playerName = "";
    private JobClass jobClass;
    private int playerLevel = 1;
    private int runeCount = 0;
    private Stats playerStats;
    private ArrayList<Weapons> playerInventory = new ArrayList<Weapons>();
    private Weapons equippedWeapon;
    private String imagePath;
    private double maxHealth = 0;
    private double dodgeRate = 0;
    private Stats combinedStats;

    public Player(){
        this.playerStats = new Stats();
        this.combinedStats = new Stats();
    }

    public void initializeMaxHealth(){
        this.maxHealth=((getStats().getVigor() + getEquippedWeapon().getStats().getVigor())/2.0)*100.0;
    }

    
    /** 
     * @return double
     */
    public double getMaxHealth(){
        return maxHealth;
    }

    public void setMaxHealth(double maxHealth){
        this.maxHealth = maxHealth;
    }

    public void subtractMaxHealth(double maxHealth){
        this.maxHealth-=maxHealth;
    }

    public boolean isDead(){
        if(maxHealth<=0){
            JOptionPane.showMessageDialog(null, "YOU DIED");
            return true;
        } else {
            return false;
        }
    }

    public void initializeDodgeRate(){
        this.dodgeRate=(20+((getStats().getEndurance() + getEquippedWeapon().getStats().getEndurance())/2.0))/100.0;
    }

    public double getDodgeRate(){
        return dodgeRate;
    }

    public void addWeapon(Weapons weapon){
        playerInventory.add(weapon);
    }

    public void setEquippedWeapon(int i){
        this.equippedWeapon = playerInventory.get(i);
    }

    public Weapons getEquippedWeapon(){
        return equippedWeapon;
    }

    public ArrayList<Weapons> getPlayerInventory(){
        return playerInventory;
    }

    public void setImagePath(String imagePath){
        this.imagePath = imagePath;
    }

    public String getImagePath(){
        return imagePath;
    }
    
    /** 
     * Getter for the player's level.
     * 
     * @return int The player's level.
     */
    public int getPlayerLevel(){
        return playerLevel;
    }
    
    /** 
     * Setter for the player's level.
     * 
     * @param playerLevel The level to set for the player.
     */
    public void addPlayerLevel(){
        this.playerLevel ++;
    }

    public void setJobClass(JobClass jobClass){
        this.jobClass=jobClass;
        this.playerLevel=jobClass.getJobLevel();
        this.playerStats.setStats(jobClass.getJobStats());
    }

    public JobClass getJobClass(){
        return this.jobClass;
    }
    
    /** 
     * Getter for the player's name.
     * 
     * @return String The player's name.
     */
    //methods for playerName
    public String getPlayerName(){
        return playerName;
    }
    
    /** 
     * Setter for the player's name.
     * 
     * @param playerName The name to set for the player.
     */
    public void setPlayerName(String playerName){
        this.playerName = playerName;
    }
    
    /** 
     * Getter for the player's rune count.
     * 
     * @return int The player's rune count.
     */
    //methods for soulCount
    public int getRuneCount(){
        return runeCount;
    }

    public void subtractRuneCount(int runeCount){
        this.runeCount-=runeCount;
    }
    
    /** 
     * Setter for adding runes to the player's total rune count.
     * 
     * @param runeCount The number of runes to add.
     */
    public void addRunes(int runeCount){
        this.runeCount += runeCount;
    }

    /** 
     * Setter for the player's stats.
     * 
     * @param Vigor The vigor stat attribute for the player.
     * @param Endurance The endurance stat attribute for the player.
     * @param Dexterity The dexterity stat attribute for the player.
     * @param Strength The strength stat attribute for the player.
     * @param Intelligence The intelligence stat attribute for the player.
     * @param Faith The faith stat attribute for the player.
     */
    public void setStats(int Vigor,int Endurance,int Dexterity,int Strength,int Intelligence,int Faith){
        this.playerStats.setStats(Vigor, Endurance, Dexterity, Strength, Intelligence, Faith);
    }

    public Stats getStats(){
        return playerStats;
    }

    public void addStats(int Vigor,int Endurance,int Dexterity,int Strength,int Intelligence,int Faith){
        this.playerStats.addStats(Vigor, Endurance, Dexterity, Strength, Intelligence, Faith);
    }

    public String printPlayer(){
        return "<html><b>Name: </b> " + playerName + "<br/>" + "<b>Level: </b>" + playerLevel + "<br/>" + "<b>Rune Count: </b>" + runeCount + "<br/>" + "<b>Stats: </b>" + playerStats.printStats() + "</html>";
    }

    public void initializeCombinedStats() {
        combinedStats.setStats(playerStats);
        combinedStats.addStats(equippedWeapon.getStats());
    }

    public Stats getCombinedStats() {
        return combinedStats;
    }
}
