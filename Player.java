import java.util.*;
/**
 * Class for representing a player character in the game.
 * It contains player attributes and methods related to leveling up and managing stats.
 * 
 * @author Marcus Apetreor, Vincent Vuelva
 */
public class Player {

    private String playerName = "";
    private String jobClass = "";
    private int playerLevel = 1;
    private int runeCount;
    private Stats playerStats = new Stats();
        
    
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

    /** 
     * Method to level up the player's stats.
     * 
     * @param sc The scanner object to accept user input.
     */
    public void levelUp(Scanner sc){
        int levelUpCost = (playerLevel*100)/2;
        String[] statChoices = {"Vigor", "Endurance", "Dexterity", "Strength", "Intelligence", "Faith"};
        while(true){
            System.out.println("You have " + CharacterCreationScreen.getPlayerInstance().getRuneCount() + " Runes.");
            System.out.println("You need " + levelUpCost + " Runes to level up.");
            if(this.runeCount<levelUpCost){
                System.out.println("You do not have enough runes.");
                break;
            } else {
                ConsoleMethods.refreshScreen();
                System.out.println("Choose an attribute to level up.");
                playerCard();
                ConsoleMethods.printOptions(statChoices);
                String userInput = sc.nextLine();
                ConsoleMethods.arrowSelector(userInput, 6);
                if (ConsoleMethods.optionCondition(0, userInput)){
                    this.playerStats.addStats(1,0,0,0,0,0);
                    runeCount=-levelUpCost;
                    break;
                } else if (ConsoleMethods.optionCondition(1, userInput)){
                    this.playerStats.addStats(0,1,0,0,0,0);
                    runeCount=-levelUpCost;
                    break;
                } else if (ConsoleMethods.optionCondition(2, userInput)){
                    this.playerStats.addStats(0,0,1,0,0,0);
                    runeCount=-levelUpCost;
                    break;
                } else if (ConsoleMethods.optionCondition(3, userInput)){
                    this.playerStats.addStats(0,0,0,1,0,0);
                    runeCount=-levelUpCost;
                    break;
                } else if (ConsoleMethods.optionCondition(4, userInput)){
                    this.playerStats.addStats(0,0,0,0,1,0);
                    runeCount=-levelUpCost;
                    break;
                } else if (ConsoleMethods.optionCondition(5, userInput)){
                    this.playerStats.addStats(0,0,0,0,0,1);
                    runeCount=-levelUpCost;
                    break;
                }
            }
        }
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
     * Getter for the player's job class.
     * 
     * @return String The player's job class.
     */
    public String getJobClass(){
        return jobClass;
    }

    
    /** 
     * Setter for the player's job class.
     * 
     * @param jobClass The job class to set for the player.
     */
    public void setJobClass(String jobClass){
        this.jobClass = jobClass; 
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

    
    /** 
     * Setter for adding runes to the player's total rune count.
     * 
     * @param runeCount The number of runes to add.
     */
    public void addRunes(int runeCount){
        this.runeCount = runeCount;
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

    /**
     * Method to print the player's card with all the player info.
     */
    public void playerCard(){
        System.out.println("Name: " + playerName);
        System.out.println("-----------");
        System.out.println("Level: " + playerLevel);
        System.out.println("Job Class: " + jobClass);
        System.out.println("Rune: " + runeCount);
        System.out.println("\nStats: ");
        this.playerStats.statCard();
        System.out.println();
    }
}
