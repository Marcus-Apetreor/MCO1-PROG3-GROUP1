import java.util.*;
/**
 * Class for representing a player character in the game.
 * It contains player attributes and methods related to leveling up and managing stats.
 * 
 * @author Marcus Apetreor, Vincent Vuelva
 */
public class Player {

    private String playerName = "";
    private String jobName = "";
    private int playerLevel = 1;
    private int runeCount;
    private Stats playerStats;
    private Stats weaponStats;
    private ArrayList<Weapons> playerInventory = new ArrayList<Weapons>();
    private Weapons equippedWeapon;

    public void addWeapon(Weapons weapon){
        playerInventory.add(weapon);
    }

    public void setEquippedWeapon(int i){
        this.equippedWeapon = playerInventory.get(i);
        this.weaponStats = equippedWeapon.getStats();
    }

    public Weapons getEquippedWeapon(){
        return equippedWeapon;
    }

    public ArrayList<Weapons> getPlayerInventory(){
        return playerInventory;
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
    public void setPlayerLevel(int playerLevel){
        this.playerLevel = playerLevel;
    }

    public void setJobClass(JobClass jobClass){
        this.jobName=jobClass.getJobName();
        this.playerLevel=jobClass.getJobLevel();
        this.playerStats.setStats(jobClass.getJobStats());
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

    public void setRuneCount(int runeCount){
        this.runeCount=runeCount;
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
}
