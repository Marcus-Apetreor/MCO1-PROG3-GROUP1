import java.util.*;

public class Model {

    //player 
    private static Player playerInstance = new Player();
    private int runeCount = playerInstance.getRuneCount();
    private int playerLevel = playerInstance.getPlayerLevel();
    private ArrayList<Weapons> playerInventory = playerInstance.getPlayerInventory();
    private double playerHealth = 0;

    //no weapon
    private Weapons fists = new Weapons("No Weapon", "Fists", 0, 0, 0, 0, 0, 0, 0);

    //swords
    private Swords shortSword = new Swords("Short Sword", 1000, 0, 15, 13, 15, 15, 15);
    private Swords rogiersRapier = new Swords("Rogier's Rapier", 2000, 10, 25, 18, 35, 35, 35);
    private Swords codedSword = new Swords("Coded Sword", 4000, 20, 35, 21, 40, 40, 40);
    private Swords nightFlame = new Swords("Sword of Night and Flame", 8000, 30, 45, 25, 55, 55, 55);

    //katanas
    private Katanas uchigatana = new Katanas("Uchigatana", 1875, 20, 35, 15, 30, 0, 0);
    private Katanas moonveil = new Katanas("Moonveil", 3750, 30, 40, 20, 45, 0, 0);
    private Katanas riversOfBlood = new Katanas("Rivers of Blood", 7500, 40, 45, 25, 60, 0, 0);
    private Katanas handOfMalenia = new Katanas("Hand of Malenia", 1875, 50, 50, 30, 75, 0, 0);

    //whips
    private Whips whip = new Whips("Whip", 1500, 15, 60, 20, 20, 0, 0);
    private Whips urumi = new Whips("Urumi", 3000, 20, 70, 25, 40, 10, 0);
    private Whips thornedWhip = new Whips("Thorned Whip", 5000, 30, 80, 30, 50, 0, 40);
    private Whips hoslowsWhip = new Whips("Hoslow's Petal Whip", 10000, 35, 90, 35, 55, 20, 20);

    //greatswords
    private Greatswords claymore = new Greatswords("Claymore", 3000, 15, 10, 9, 20, 0, 0);
    private Greatswords starscourge = new Greatswords("Starscourge Greatsword", 6000, 20, 15, 14, 40, 0, 20);
    private Greatswords inseperable = new Greatswords("Inseperable Sword", 12000, 25, 20, 19, 70, 60, 60);
    private Greatswords maliketh = new Greatswords("Maliketh's Black Blade", 24000, 30, 25, 24, 80, 40, 60);

    //staves
    private Staves astrologers = new Staves("Astrologer's Staff", 2000, 5, 20, 12, 5, 25, 15);
    private Staves albinauric = new Staves("Albinauric Staff", 4000, 10, 30, 14, 10, 45, 35);
    private Staves staffGuilty = new Staves("Staff of the Guilty", 8000, 15, 40, 16, 15, 65, 60);
    private Staves carianScepter = new Staves("Carian Regal Scepter", 16000, 25, 50, 18, 20, 85, 75);

    //seals
    private Seals fingerSeal = new Seals("Finger Seal", 2500, 10, 45, 10, 0, 15, 20);
    private Seals godslayerSeal = new Seals("Godslayer's Seal", 5000, 15, 50, 12, 0, 35, 40);
    private Seals goldenSeal = new Seals ("Golden Order Seal", 10000, 20, 55, 14, 0, 65, 65);
    private Seals dragonSeal = new Seals ("Dragon Communion Seal", 15000, 25, 60, 18, 0, 75, 80);

    //jobclasses
    private JobClass vagabond = new JobClass(9,"Vagabond",15,11,13,14,9,9);
    private JobClass samurai = new JobClass(9,"Samurai",12,13,15,12,9,8);
    private JobClass warrior = new JobClass(8,"Warrior", 11, 11, 16, 10, 10, 8);
    private JobClass hero = new JobClass(7,"Hero", 14, 12, 9, 16, 7, 8);
    private JobClass astrologer = new JobClass(6,"Astrologer", 9, 9, 12, 8, 16, 7);
    private JobClass prophet = new JobClass(7,"Prophet", 10,8, 10, 11, 7, 16);
    private JobClass[] jobClasses = {vagabond, samurai, warrior, hero, astrologer, prophet};

    //no jobclass
    private JobClass jobless = new JobClass(0, "jobless", 0, 0, 0, 0, 0, 0);

    //maps and map related variables
    private Map stormveilCastle = new StormveilCastle();
    private Map RayaLucariaAcademy = new RayaLucariaAcademy();
    private Map TheEldenThrone = new TheEldenThrone();

    //shop
    private static ArrayList<Weapons> shopInventory = new ArrayList<Weapons>();

    //lobby
    private static String[] gameSelectorOptions = {"Fast Travel", "Level Up", "Inventory", "Shop", "Exit Game"};
    private static String[] mapOptions = {"Stormveil Castle", "Raya Lucaria Academy", "The Elden Throne"};

    //title screen
    private static String[] titleOptions = {"Start", "Exit"};

    //battle
    private boolean turn = true;
    
    //constructor
    public Model(){
        initializeShop();
        initializePlayer();
    }

    //initialization methods
    public void initializePlayer(){
        playerInstance.addWeapon(fists);
        playerInstance.setEquippedWeapon(0);
        playerInstance.setJobClass(jobless);
    }

    public double getMaxHealth(){
        return ((playerInstance.getStats().getVigor() + playerInstance.getEquippedWeapon().getStats().getVigor())/2.0)*100.0;
    }
    public void setMaxHealth(double playerHealth){
        playerHealth = getMaxHealth();
    }
    
    public double dodgeRate(){
        return (20+((playerInstance.getStats().getEndurance() + playerInstance.getEquippedWeapon().getStats().getEndurance())/2.0))/100.0;
    }

    public void initializeShop(){
        //swords [0-3]
        shopInventory.add(shortSword); //0
        shopInventory.add(rogiersRapier); //1
        shopInventory.add(codedSword); //2
        shopInventory.add(nightFlame); //3

        //katanas [4-7]
        shopInventory.add(uchigatana); //4
        shopInventory.add(moonveil); //5
        shopInventory.add(riversOfBlood); //6
        shopInventory.add(handOfMalenia); //7
        
        //whips [8-11]
        shopInventory.add(whip); //8
        shopInventory.add(urumi); //9
        shopInventory.add(thornedWhip); //10
        shopInventory.add(hoslowsWhip); //11
        
        //greatswords [12-15]
        shopInventory.add(claymore); //12
        shopInventory.add(starscourge); //13
        shopInventory.add(inseperable); //14
        shopInventory.add(maliketh); //15

        //staves [16-19]
        shopInventory.add(astrologers); //16
        shopInventory.add(albinauric); //17
        shopInventory.add(staffGuilty); //18
        shopInventory.add(carianScepter); //19
        
        //seals [20-23]
        shopInventory.add(fingerSeal); //20
        shopInventory.add(godslayerSeal); //21
        shopInventory.add(goldenSeal); //22
        shopInventory.add(dragonSeal); //23
    }

    //character creation methods
    public JobClass[] getJobClasses(){
        return jobClasses;
    }

    public void setPlayerName(String name){
        playerInstance.setPlayerName(name.substring(0, Math.min(name.length(), 25)));
    }

    public void chooseJobClass(int jobIndex) {
        JobClass[] jobClasses = {vagabond, samurai, warrior, hero, astrologer, prophet};
        playerInstance.setJobClass(jobClasses[jobIndex]);
    }

    //battle methods
    public void physicalDamage(Enemy enemy){
        double damage=(playerInstance.getStats().getStrength() + playerInstance.getEquippedWeapon().getStats().getStrength()) * (1-enemy.getPhysicalDefense());
        enemy.setHealth(enemy.getHealth()-(int)damage);
    }
    
    public void sorceryDamage(Enemy enemy){
        double damage=(playerInstance.getStats().getIntelligence() + playerInstance.getEquippedWeapon().getStats().getIntelligence()) * (1-enemy.getSorceryDefense());
        enemy.setHealth(enemy.getHealth()-(int)damage);
    }

    public void incantationDamage(Enemy enemy){
        double damage=(playerInstance.getStats().getFaith() + playerInstance.getEquippedWeapon().getStats().getFaith()) * (1-enemy.getIncantationDefense());
        enemy.setHealth(enemy.getHealth()-(int)damage);
    }

    public boolean calculateDodge(double chance) {
        double randomValue = new Random().nextDouble() * 100;

        if (randomValue < chance) {
            return true; // Dodge successful
        } else {
            return false; // Dodge unsuccessful
        }
    }

    public void battle(Enemy enemy, int userInput, int attackInput){
        //print incoming damage    

        //player turn
        //attack
        if (userInput == 1 && turn){
            //attack options
            if(attackInput == 1){
                physicalDamage(enemy);
            } else if (attackInput == 2){
                sorceryDamage(enemy);
            } else if (attackInput == 3){
                incantationDamage(enemy);
            }
            turn = false;
        //dodge
        } else if (userInput == 2 && turn){
            if(calculateDodge(dodgeRate())){
                //success
            } else {
                setMaxHealth(enemy.getAttack());
                //fail
            }
            turn = true;

        //enemy turn
        } else {
            setMaxHealth(enemy.getAttack());
            turn = true;
        }
    }

    //player methods

    public static Player getPlayer(){
        return playerInstance;
    }


    public void levelUp(int userInput){
        int levelUpCost = (playerLevel*100)/2;
        if(playerInstance.getRuneCount()-levelUpCost<0){
        } else {
            if (userInput == 1){
                playerInstance.addStats(1,0,0,0,0,0);
                playerInstance.addPlayerLevel();
                playerInstance.subtractRuneCount(levelUpCost);
            } else if (userInput == 2){
                playerInstance.addStats(0,1,0,0,0,0);
                playerInstance.addPlayerLevel();
                playerInstance.subtractRuneCount(levelUpCost);
            } else if (userInput == 3){
                playerInstance.addStats(0,0,1,0,0,0);
                playerInstance.addPlayerLevel();
                playerInstance.subtractRuneCount(levelUpCost);
            } else if (userInput == 4){
                playerInstance.addStats(0,0,0,1,0,0);
                playerInstance.addPlayerLevel();
                playerInstance.subtractRuneCount(levelUpCost);
            } else if (userInput == 5){
                playerInstance.addStats(0,0,0,0,1,0);
                playerInstance.addPlayerLevel();
                playerInstance.subtractRuneCount(levelUpCost);
            } else if (userInput == 6){
                playerInstance.addStats(0,0,0,0,0,1);
                playerInstance.addPlayerLevel();
                playerInstance.subtractRuneCount(levelUpCost);
            }
        }
    }

    public void addWeapon(Weapons weapon){
        playerInstance.addWeapon(weapon);
    }

    public void equipWeapon(int i){
        playerInstance.setEquippedWeapon(i);
    }

    //map methods

    public Map getMap(int i){
        switch(i){
            case 1:
                return stormveilCastle;
            case 2:
                return RayaLucariaAcademy;
            case 3:
                return TheEldenThrone;
            default:
                return null;
        }
    }

    public int getBossRoom(){
        return Map.getBossRoom();
    }

    public int getBossRow(){
        return Map.getBossRow();
    }

    public int getBossCol(){
        return Map.getBossCol();
    }

    public int getUnlockedAreas(){
        return Map.getUnlockedAreas();
    }

    public ArrayList<String> getUnlockedFastTravelTiles(){
        return Map.getUnlockedFastTravelTiles();
    }

    //shop methods

    public ArrayList<Weapons> getShopInventory(){
        return shopInventory;
    }
}
