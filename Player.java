import java.util.*;

public class Player {

    private String playerName;
    private String jobClass = "";
    private int playerLevel = 1;
    private int runeCount;
    private Stats playerStats = new Stats();
        
    //getters and setters for level
    public int getPlayerLevel(){
        return playerLevel;
    }

    public void setPlayerLevel(int playerLevel){
        this.playerLevel = playerLevel;
    }

    public void levelUp(){
        int levelUpCost = (playerLevel*100)/2;
        String[] statChoices = {"Vigor", "Endurance", "Dexterity", "Strength", "Intelligence", "Faith"};
        Scanner sc = new Scanner(System.in);
        while(true){
            if(this.runeCount<levelUpCost){
                System.out.println("You do not have enough runes.");
            } else {
                ConsoleMethods.printOptions(statChoices);
                String userInput = sc.nextLine();
                ConsoleMethods.arrowSelector(userInput, 6);
                if (ConsoleMethods.optionCondition(0, userInput)){
                    this.playerStats.addStats(1,0,0,0,0,0);
                    break;
                } else if (ConsoleMethods.optionCondition(1, userInput)){
                    this.playerStats.addStats(0,1,0,0,0,0);
                    break;
                } else if (ConsoleMethods.optionCondition(2, userInput)){
                    this.playerStats.addStats(0,0,1,0,0,0);
                    break;
                } else if (ConsoleMethods.optionCondition(3, userInput)){
                    this.playerStats.addStats(0,0,0,1,0,0);
                    break;
                } else if (ConsoleMethods.optionCondition(4, userInput)){
                    this.playerStats.addStats(0,0,0,0,1,0);
                    break;
                } else if (ConsoleMethods.optionCondition(5, userInput)){
                    this.playerStats.addStats(0,0,0,0,0,1);
                    break;
                }
            }
        }
        sc.close();
        playerCard();
    }

    //methods for playerName
    public String getPlayerName(){
        return playerName;
    }
    public void setPlayerName(String playerName){
        this.playerName = playerName;
    }

    //methods for jobClass
    public String getJobClass(){
        return jobClass;
    }

    public void setJobClass(String jobClass){
        this.jobClass = jobClass; 
    }

    //methods for soulCount
    public int getSoulCount(){
        return runeCount;
    }

    public void setSoulCount(int runeCount){
        this.runeCount = runeCount;
    }

    //methods for stats

    public void setStats(int Vigor,int Endurance,int Dexterity,int Strength,int Intelligence,int Faith){
        this.playerStats.setStats(Vigor, Endurance, Dexterity, Strength, Intelligence, Faith);
    }

    //methods for soul count calculation (leveling up or buying from shop or selling to shop)

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
