
import java.util.*;
import javax.swing.*;

/**
 * Class to facilitate map traversal, map loading, and manage map rules.
 * It allows players to move around the game world, encounter enemies, find items, and interact with various tiles.
 * 
 * @author Marcus Apetreor, Vincent Vuelva
 */
public class TileMap {
    protected static final Random random = new Random();
    private final String[][][] matrix;
    private int playerRoom;
    private int playerRow;
    private int playerCol;
    private int bossRoom;
    private int bossRow;
    private int bossCol;
    private String mapName;
    private String prefix;
    private String usedDoorName;
    protected static int areaIndex;
    private int[] usedDoorCoordinates;
    private static int unlockedAreas = 1;
    private static ArrayList<String> unlockedFastTravelTiles = new ArrayList<>();
    private Boss boss;
    
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
    public TileMap(String[][][] matrix, int playerRoom, int playerRow, int playerCol, int bossRoom, int bossRow, int bossCol, int index, String prefix, String mapName) {
        unlockedFastTravelTiles.add(matrix[playerRoom][playerRow][playerCol]);
        this.matrix = matrix;
        this.playerRoom = playerRoom; // Starting room for the player
        this.playerRow = playerRow; // Starting row for the player
        this.playerCol = playerCol; // Starting column for the player
        this.bossRoom = bossRoom;
        this.bossRow = bossRow;
        this.bossCol = bossCol;
        this.prefix = prefix;
        this.mapName = mapName;
        areaIndex = index;
    }

    
    /** 
     * @return ArrayList<String>
     */
    public static ArrayList<String> getUnlockedFastTravelTiles(){
        return unlockedFastTravelTiles;
    }

    public static void addUnlockedFastTravelTile(String fastTravelTile){
        unlockedFastTravelTiles.add(fastTravelTile);
    }
    
    public static int getUnlockedAreas(){
        return unlockedAreas;
    }

    public static void addUnlockedAreas(){
        unlockedAreas++;
    }
    
    public static void clearUnlockedFastTravelTiles() {
        unlockedFastTravelTiles.clear();
    }
    
    public void spawnTile(){
        boolean isEnemy;
        if (random.nextDouble()<=0.25){
            isEnemy=false;
        } else {
            isEnemy=true;
        }
        if (isEnemy){
            Enemy enemy = Enemy.spawnEnemy(areaIndex);
            new Battle(enemy);
        } else {
            int gainedRunes = (50 + random.nextInt(101))*areaIndex;
            Model.getPlayer().addRunes(gainedRunes);
            JOptionPane.showMessageDialog(null, "You have gained " + gainedRunes + " runes!");
        }
    }

    public Boss bossTile(){
        boss = Boss.spawnBoss(areaIndex);
        return boss;
    }

    public void fightBoss(){
        new Battle(bossTile());
        if(bossTile().isDefeated()){
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    for (int k = 0; k < matrix[i][j].length; k++) {
                        if (matrix[i][j][k].endsWith("FastTravelTile1") && matrix[i][j][k].startsWith(prefix)) {
                            if (!unlockedFastTravelTiles.contains(matrix[i][j][k])) {
                                unlockedFastTravelTiles.add(matrix[i][j][k]);
                            }
                        }
                    }
                }
            }
            unlockedAreas++;
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

    public String[][][] getMatrix(){
        return matrix;
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

    public void displayGUI(int userInput) {
        SwingUtilities.invokeLater(() -> {
            TileMap tileMap = this;
            if(userInput == 1){
                new MapGUI(tileMap, playerRoom, playerRow, playerCol);
            } else {
                new MapGUI(tileMap, bossRoom, bossRow, bossCol);
            }
        });
    }
}
