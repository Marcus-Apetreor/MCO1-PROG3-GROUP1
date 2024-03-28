
import java.util.*;

/**
 * Class to facilitate map traversal, map loading, and manage map rules.
 * It allows players to move around the game world, encounter enemies, find items, and interact with various tiles.
 * 
 * @author Marcus Apetreor, Vincent Vuelva
 */
public class Map {
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
    public Map(String[][][] tileMap, int playerRoom, int playerRow, int playerCol, int bossRoom, int bossRow, int bossCol, int areaIndex, String prefix) {
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
    
    public static void clearUnlockedFastTravelTiles() {
        unlockedFastTravelTiles.clear();
    }
    
    public void spawnTile(int areaIndex){
        boolean isEnemy;
        if (random.nextDouble()<=0.25){
            isEnemy=false;
        } else {
            isEnemy=true;
        }
        if (isEnemy){
            Enemy enemy = Enemy.spawnEnemy(areaIndex);
        } else {
            int gainedRunes = 50 + random.nextInt(101);
            Model.getPlayer().addRunes(gainedRunes);
        }
    }

    public String getUsedDoorName(){
        return usedDoorName;
    }

    public int[] getUsedDoorCoordinates(){
        return usedDoorCoordinates;
    }

    public int getAreaIndex(){
        return areaIndex;
    }

    public Boss bossTile(int areaIndex){
        Boss boss = Boss.spawnBoss(areaIndex);
        return boss;
    }

    public ArrayList<String> getUnlockedFastTravelTiles(){
        return unlockedFastTravelTiles;
    }

    public String[][][] getTileMap(){
        return tileMap;
    }

    public String getPrefix(){
        return prefix;
    }

    public int getPlayerRoom(){
        return playerRoom;
    }

    public int getPlayerRow(){
        return playerRow;
    }

    public int getPlayerCol(){
        return playerCol;
    }

    public void setPlayerRoom(int playerRoom) {
        this.playerRoom = playerRoom;
    }

    public void setPlayerRow(int playerRow) {
        this.playerRow = playerRow;
    }

    public void setPlayerCol(int playerCol) {
        this.playerCol = playerCol;
    }

    public int getBossRoom(){
        return bossRoom;
    }

    public int getBossRow(){
        return bossRow;
    }

    public int getBossCol(){
        return bossCol;
    }
}
