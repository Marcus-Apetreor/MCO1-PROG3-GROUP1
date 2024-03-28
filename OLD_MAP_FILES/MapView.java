package OLD_MAP_FILES;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class MapView extends JFrame {
    private JPanel mapPanel;
    private String[][][] tileMap;
    private String mapName;

    public MapView(String[][][] tileMap, String mapName) {
        this.tileMap = tileMap;
        this.mapName = mapName;
    }

    public void displayPlayer(int row, int col) {
    }
    public void displayEmptyTile(int row, int col) {
    }
    public void displaySpawnTile(int row, int col) {
    }  
    public void displayBossTile(int row, int col) {
    }  
    public void displayDoorTile(int row, int col) {
    }  
    public void displayUnlockedFastTravelTile(int row, int col) {
    }
    public void displayLockedFastTravelTile(int row, int col) {
    }
}
