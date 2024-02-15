public class Player {

    private String playerName;
    private String jobClass;
    private int playerLevel = 1;
    private int soulCount;

    public Player(){
    }

    //getters and setters for playerName
    public String getPlayerName(){
        return playerName;
    }
    public void setPlayerName(String playerName){
        this.playerName = playerName;
    }

    //getters and setters for playerLevel
    public int getPlayerLevel(){
        return playerLevel;
    }

    public void setPlayerLevel(int playerLevel){
        this.playerLevel = playerLevel;
    }

    //getters and setters for jobClass
    public String getJobClass(){
        return jobClass;
    }

    public void setJobClass(String jobClass){
        this.jobClass = jobClass; 
    }

    //getters and setters for soulCount
    public int getSoulCount(){
        return soulCount;
    }

    public void setSoulCount(int soulCount){
        this.soulCount = soulCount;
    }

    //methods for stats

    //methods for soul count calculation (leveling up or buying from shop or selling to shop)

    public void playerCard(){
        System.out.println("Name: " + playerName);
        System.out.println("-----------");
        System.out.println("Level: " + playerLevel);
        System.out.println("Job Class: " + jobClass);
        System.out.println("Souls: " + soulCount);
        System.out.println("Stats: " /*+ Stats.statCard*/);
        System.out.println(/*statcard*/);
    }
}
