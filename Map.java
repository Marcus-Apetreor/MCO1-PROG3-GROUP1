
import java.util.*;
import javax.swing.*;

/**
 * Class to facilitate map traversal, map loading, and manage map rules.
 * It allows players to move around the game world, encounter enemies, find items, and interact with various tiles.
 * 
 * @author Marcus Apetreor, Vincent Vuelva
 */
public class Map {
    protected static final Random random = new Random();
    private final String[][][] tileMap;
    private int playerRoom;
    private int playerRow;
    private int playerCol;
    private static int bossRoom;
    private static int bossRow;
    private static int bossCol;
    private String mapName;
    private String prefix;
    private String usedDoorName;
    private int areaIndex;
    private int[] usedDoorCoordinates;
    private static int unlockedAreas = 1;
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
    public Map(String[][][] tileMap, int playerRoom, int playerRow, int playerCol, int bossRoom, int bossRow, int bossCol, int index, String prefix, String mapName) {
        unlockedFastTravelTiles.add(tileMap[playerRoom][playerRow][playerCol]);
        this.tileMap = tileMap;
        this.playerRoom = playerRoom; // Starting room for the player
        this.playerRow = playerRow; // Starting row for the player
        this.playerCol = playerCol; // Starting column for the player
        this.bossRoom = bossRoom;
        this.bossRow = bossRow;
        this.bossCol = bossCol;
        this.prefix = prefix;
        this.mapName = mapName;
        areaIndex = index;
        if(bossTile(areaIndex).isDefeated(areaIndex)){
            tileMap[bossRoom][bossRow][bossCol] = "emptyTile";
            for (int i = 0; i < tileMap.length; i++) {
                for (int j = 0; j < tileMap[i].length; j++) {
                    for (int k = 0; k < tileMap[i][j].length; k++) {
                        if (tileMap[i][j][k].endsWith("FastTravelTile1") && tileMap[i][j][k].startsWith(prefix)) {
                            if (!unlockedFastTravelTiles.contains(tileMap[i][j][k])) {
                                unlockedFastTravelTiles.add(tileMap[i][j][k]);
                            }
                        }
                    }
                }
            }
            unlockedAreas++;
        }
    }
    
    public static int getUnlockedAreas(){
        return unlockedAreas;
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
            int gainedRunes = (50 + random.nextInt(101))*areaIndex;
            Model.getPlayer().addRunes(gainedRunes);
        }
    }

    public Boss bossTile(int areaIndex){
        Boss boss = Boss.spawnBoss(areaIndex);
        return boss;
    }

    //dx and dy represent the direction the player wants to move:
    //to move up dx = -1, dy = 0
    //to move down dx = 1, dy = 0
    //to move left dx = 0, dy = -1
    //to move right dx = 0, dy = 1
    public void doorTile(int dx, int dy){
        int newPlayerRow = playerRow + dx;
        int newPlayerCol = playerCol + dy;
    
        // Check if the new position is within the map boundaries
        if (newPlayerRow >= 0 && newPlayerRow < tileMap[playerRoom].length && 
            newPlayerCol >= 0 && newPlayerCol < tileMap[playerRoom][newPlayerRow].length) {
    
            String possibleDoorTile = tileMap[playerRoom][newPlayerRow][newPlayerCol];
    
            // Check if the tile in the direction the player wants to move starts with "doorTile"
            if (possibleDoorTile.startsWith("doorTile")) {
                int currentRoom = playerRoom;
                for (int i = 0; i < tileMap.length; i++) {
                    if (i == currentRoom) {
                        continue;
                    }
                    for (int j = 0; j < tileMap[i].length; j++) {
                        for (int k = 0; k < tileMap[i][j].length; k++) {
                            if (possibleDoorTile.equals(tileMap[i][j][k])) {
                                playerRoom = i;
                                playerRow = j;
                                playerCol = k;
                                usedDoorName = possibleDoorTile;
                                int[] coordinates = {i, j, k};
                                this.usedDoorCoordinates = coordinates;
                                tileMap[i][j][k] = "";
                            }
                        }
                    }
                }
            }
        }
    }

    public void fastTravelTile(int dx, int dy, Controller controller){
        int newPlayerRow = playerRow + dx;
        int newPlayerCol = playerCol + dy;
    
        // Check if the new position is within the map boundaries
        if (newPlayerRow >= 0 && newPlayerRow < tileMap[playerRoom].length && 
            newPlayerCol >= 0 && newPlayerCol < tileMap[playerRoom][newPlayerRow].length) {
    
            String possibleFastTravelTile = tileMap[playerRoom][newPlayerRow][newPlayerCol];
    
            // Check if the tile in the direction the player wants to move is a fast travel tile
            if (unlockedFastTravelTiles.contains(possibleFastTravelTile)) {
                // Prompt the user to go back to the menu
                int response = JOptionPane.showConfirmDialog(null, "You've touched a fast travel tile. Do you want to go back to the menu?", "Fast Travel", JOptionPane.YES_NO_OPTION);
    
                if (response == JOptionPane.YES_OPTION) {
                    controller.gameLobby();
                }
            }
        }
    }

    public String getMapName(){
        return mapName;
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

    public static ArrayList<String> getUnlockedFastTravelTiles(){
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

    public static int getBossRoom(){
        return bossRoom;
    }

    public static int getBossRow(){
        return bossRow;
    }

    public static int getBossCol(){
        return bossCol;
    }
}
