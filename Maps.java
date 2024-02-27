import java.util.*;

/**
 * Class to facilitate map traversal, map loading, and manage map rules.
 * It allows players to move around the game world, encounter enemies, find items, and interact with various tiles.
 * 
 * @author Marcus Apetreor, Vincent Vuelva
 */
public class Maps {
    private static final Random random = new Random();
    private final String[][][] tileMap;
    private int playerRoom;
    private int playerRow;
    private int playerCol;
    private int bossRoom;
    private int bossRow;
    private int bossCol;
    private String prefix;
    private String usedDoorName;
    private int[] usedDoorCoordinates;
    private int areaIndex;
    private static ArrayList<String> unlockedFastTravelTiles = new ArrayList<>();
    private boolean getOut = false;


    public static int getUnlockedFastTravelTilesCount() {
        int count = 0;
        for (String tile : unlockedFastTravelTiles) {
            if (tile.endsWith("1")) {
                count++;
            }
        }
        return count;
    }
    
    /**
     * Constructor for creating map instances.
     * 
     * @param tileMap The 3D array representing the map layout.
     * @param playerRoom The initial room of the player.
     * @param playerRow The initial row of the player.
     * @param playerCol The initial column of the player.
     * @param bossRoom The room of the boss room fast travel tile.
     * @param bossRow The row of the boss room fast travel tile.
     * @param bossCol The column of the boss room fast travel tile.
     * @param areaIndex The index of the current area.
     * @param prefix The prefix of the current map.
     */
    public Maps(String[][][] tileMap, int playerRoom, int playerRow, int playerCol, int bossRoom, int bossRow, int bossCol, int areaIndex, String prefix) {
        unlockedFastTravelTiles.add(tileMap[playerRoom][playerRow][playerCol]);
        this.tileMap = tileMap;
        this.playerRoom = playerRoom; // Starting room for the player
        this.playerRow = playerRow; // Starting row for the player
        this.playerCol = playerCol; // Starting column for the player
        this.bossRoom = bossRoom;
        this.bossRow = bossRow;
        this.bossCol = bossCol;
        this.prefix = prefix;
        this.areaIndex = areaIndex;
    }

    
    /**
     * Initiates the game loop for map traversal and interaction.
     * 
     * @param sc The scanner object to accept user input.
     */
    public void chooseSpawnLocation(Scanner sc) {
        boolean bossRoomUnlocked = false;
        String[] spawnOptions = {"Start", "Boss"};
        for (String tile : unlockedFastTravelTiles) {
            if (tile.startsWith(prefix) && tile.endsWith("1")) {
                bossRoomUnlocked = true;
                break;
            }
        }
        if (bossRoomUnlocked) {
            while(true){
                System.err.println("Where would you like to spawn?");
                ConsoleMethods.printOptions(spawnOptions);
                String userInput = sc.nextLine();
                ConsoleMethods.arrowSelector(userInput, 5);
                if (ConsoleMethods.optionCondition(0,userInput)){
                    break;
                } else if (ConsoleMethods.optionCondition(1, userInput)){
                    playerRoom = bossRoom;
                    playerRow = bossRow;
                    playerCol = bossCol;
                    break;
                }
            }
        }
        play(sc);
    }    
    
    /** 
     * @return ArrayList String Returns the arraylist of unlocked fast travel tiles so that the compiler can detect when a fast travel tile is accessible.
     */
    public ArrayList<String> getUnlockedFastTravelTiles(){
        return unlockedFastTravelTiles;
    }

    /**
     * Prints the current room layout with the player's position.
     */
    public void printCurrentRoom() {
        for (int i = 0; i < tileMap[playerRoom].length; i++) {
            for (int j = 0; j < tileMap[playerRoom][i].length; j++) {
                if (i == playerRow && j == playerCol) {
                    System.out.print("(-_-) ");
                } else if (tileMap[playerRoom][i][j].equals("emptyTile")) {
                    System.out.print(".   . ");
                } else if (tileMap[playerRoom][i][j].equals("spawnTile")) {
                    System.out.print(". ? . ");
                } else if (tileMap[playerRoom][i][j].equals("bossTile")) {
                    System.out.print(".xXx. ");
                } else if (tileMap[playerRoom][i][j].startsWith("doorTile")) {
                    System.out.print("|  °| ");
                } else if (unlockedFastTravelTiles.contains(tileMap[playerRoom][i][j])) {
                    System.out.print("o<O>o ");
                } else if (!unlockedFastTravelTiles.contains(tileMap[playerRoom][i][j])) {
                    System.out.print("o<X>o ");
                }
            }
            System.out.println();
        }
    }    
    
    /**
     * Moves the player on the map according to the given direction.
     * 
     * @param direction The direction in which the player should move.
     */
    public void movePlayer(String direction) {
        int newRow = playerRow;
        int newCol = playerCol;

        if (direction.equalsIgnoreCase("w")){
            newRow--;
        } else if (direction.equalsIgnoreCase("a")) {
            newCol--;
        } else if (direction.equalsIgnoreCase("s")) {
            newRow++;
        } else if (direction.equalsIgnoreCase("d")) {
            newCol++;
        } else if (direction.equalsIgnoreCase("e")) {
        } else {
            ConsoleMethods.clearConsole();
            System.out.println("Invalid direction!");
            return;
        }
        ConsoleMethods.clearConsole();
    
        if (isValidMove(newRow, newCol)){
            if (tileMap[playerRoom][newRow][newCol].startsWith("doorTile")){
                String doorTile = tileMap[playerRoom][newRow][newCol];
                int currentRoom = playerRoom;
                for (int i=0; i<tileMap.length; i++){
                    if (i==currentRoom){
                        continue;
                    }
                    for (int j=0; j<tileMap[i].length; j++){
                        for (int k=0; k<tileMap[i][j].length; k++){
                            if (doorTile.equals(tileMap[i][j][k])){
                                playerRoom = i;
                                playerRow = j;
                                playerCol = k;
                                usedDoorName=doorTile;
                                int[] coordinates={i, j, k};
                                this.usedDoorCoordinates = coordinates;
                                tileMap[i][j][k] = "";
                            }
                        }
                    }

                }
            } else {
                int[] coordinates = usedDoorCoordinates;
                if (coordinates != null) {
                    int doorRoom = coordinates[0];
                    int doorRow = coordinates[1];
                    int doorCol = coordinates[2];
                    tileMap[doorRoom][doorRow][doorCol] = usedDoorName;
                }
                if (unlockedFastTravelTiles.contains(tileMap[playerRoom][newRow][newCol])) {
                    ConsoleMethods.clearConsole();
                    System.out.println("You have found a site of grace. Would you like to teleport back?");
                    System.out.println("Input [e] to teleport.");
                    if(direction.equalsIgnoreCase("e")){
                        ConsoleMethods.clearConsole();
                        getOut = true;
                    }
                }
                if (tileMap[playerRoom][newRow][newCol].equals("spawnTile")) {
                    ConsoleMethods.clearConsole();
                    spawnTile(areaIndex);
                    tileMap[playerRoom][newRow][newCol] = "emptyTile";
                }
                if (tileMap[playerRoom][newRow][newCol].equals("bossTile")) {
                    ConsoleMethods.clearConsole();
                    bossTile(areaIndex);
                    tileMap[playerRoom][playerRow][playerCol] = "emptyTile";
                    for (int i=0; i<tileMap.length; i++){
                        for (int j=0; j<tileMap[i].length; j++){
                            for (int k=0; k<tileMap[i][j].length; k++){
                                if(tileMap[i][j][k].endsWith("1")){
                                    String fastTravelTile = tileMap[i][j][k];
                                    unlockedFastTravelTiles.add(fastTravelTile);
                                }
                            }
                        }
                    }
                    tileMap[playerRoom][newRow][newCol] = "emptyTile";
                }
                playerRow = newRow;
                playerCol = newCol;
            }
        } else {
            System.out.println("Invalid move! Out of bounds.");
        }
    }    

    
    /**
     * Checks if a move to the specified row and column is within bounds.
     * 
     * @param row The row index.
     * @param col The column index.
     * @return boolean True if the move is valid, false otherwise.
     */
    private boolean isValidMove(int row, int col) {
        return row >= 0 && row < tileMap[playerRoom].length && col >= 0 && col < tileMap[playerRoom][row].length;
    }

    
    /**
     * Initiates the game loop for map traversal and interaction.
     * 
     * @param sc The scanner object to accept user input.
     */
    public void play(Scanner sc) {
        ConsoleMethods.refreshScreen();
        String direction;
        
        while (!getOut) {
            printCurrentRoom();
            System.out.print("Enter direction (w/a/s/d): ");
            direction = sc.nextLine();
            movePlayer(direction);
        }
    }    
    
    
    /**
     * Handles the mechanics when a player steps onto a spawn tile.
     * 
     * @param areaIndex The index of the current area.
     */
    public void spawnTile(int areaIndex){
        boolean isEnemy;
        if (random.nextDouble()<=0.25){
            isEnemy=false;
        } else {
            isEnemy=true;
        }
        if (isEnemy){
            Enemy enemy = Enemy.spawnEnemy(areaIndex);
            System.out.println("A " + enemy.getName() + " has appeared!");
            System.out.println("Health: " + enemy.getHealth());
            System.out.println("Attack: " + enemy.getAttack());
            System.out.println("Physical Defense: " + enemy.getPhysicalDefense());
            System.out.println("Sorcery Defense: " + enemy.getSorceryDefense());
            System.out.println("Incantation Defense: " + enemy.getIncantationDefense());
        } else {
            int gainedRunes = 50 + random.nextInt(101);
            CharacterCreationScreen.getPlayerInstance().addRunes(gainedRunes);
            System.out.println("You have found " + gainedRunes + " runes!");
        }
    }

    
    /**
     * Handles the mechanics when a player steps onto a boss tile.
     * 
     * @param areaIndex The index of the current area.
     * @return Boss The instantiated boss.
     */
    public Boss bossTile(int areaIndex){
        Boss boss = Boss.spawnBoss(areaIndex);
        System.out.println(boss.getName() + " has appeared!");
        System.out.println("Health: " + boss.getHealth());
        System.out.println("Attack: " + boss.getAttack());
        System.out.println("Physical Defense: " + boss.getPhysicalDefense());
        System.out.println("Sorcery Defense: " + boss.getSorceryDefense());
        System.out.println("Incantation Defense: " + boss.getIncantationDefense());
        return boss;
    }
}
