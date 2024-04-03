package OLD_MAP_FILES;

import java.util.ArrayList;
import javax.swing.*;

import ConsoleMethods;
import MapModel;
import OLD_MCO1_FILES.GameLobby;

import java.awt.event.*;

public class MapController extends JFrame implements KeyListener{
    private static MapModel model;
    private MapView view;
    private int playerRoom = model.getPlayerRoom();
    private int playerCol = model.getPlayerCol();
    private int playerRow = model.getPlayerRow();
    private int bossRoom = model.getBossRoom();
    private int bossCol = model.getBossCol();
    private int bossRow = model.getBossRow();
    private String[][][] tileMap = model.getTileMap();
    private String usedDoorName = model.getUsedDoorName();
    private int[] usedDoorCoordinates = model.getUsedDoorCoordinates();
    private int areaIndex = model.getAreaIndex();
    private static ArrayList<String> unlockedFastTravelTiles = model.getUnlockedFastTravelTiles();
    private boolean getOut=false;
    private char lastPressedKey = '\0';
    
    
    public MapController(MapModel model, MapView view) {
        MapController.model = model;
        this.view = view;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        char keyChar = e.getKeyChar();
        if (keyChar == 'w' || keyChar == 'a' || keyChar == 's' || keyChar == 'd') {
            lastPressedKey = keyChar; // Store the pressed key
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not used in this example
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Not used in this example
    }

    public char getLastPressedKey() {
        return lastPressedKey; // Getter method to retrieve the last pressed key
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
            if (tile.startsWith(prefix) && tile.endsWith("FastTravelTile1")) {
                bossRoomUnlocked = true;
                break;
            }
        }
        if (bossRoomUnlocked) {
            while(true){
                System.err.println("Where would you like to spawn?");
                ConsoleMethods.printOptions(spawnOptions);
                String userInput = sc.nextLine();
                ConsoleMethods.arrowSelector(userInput, 2);
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
        // play(sc);
    }    

    /**
     * Moves the player on the map according to the given direction.
     * 
     * @param direction The direction in which the player should move.
     */
    public void movePlayer(String direction) {
        int newRow = playerRow;
        int newCol = playerCol;
        int count = 0;
    
        if (direction.equalsIgnoreCase("w")) {
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
    
        if (isValidMove(newRow, newCol)) {
            if (tileMap[playerRoom][newRow][newCol].startsWith("doorTile")) {
                String doorTile = tileMap[playerRoom][newRow][newCol];
                int currentRoom = playerRoom;
                for (int i = 0; i < tileMap.length; i++) {
                    if (i == currentRoom) {
                        continue;
                    }
                    for (int j = 0; j < tileMap[i].length; j++) {
                        for (int k = 0; k < tileMap[i][j].length; k++) {
                            if (doorTile.equals(tileMap[i][j][k])) {
                                playerRoom = i;
                                playerRow = j;
                                playerCol = k;
                                usedDoorName = doorTile;
                                int[] coordinates = {i, j, k};
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
                    if (direction.equalsIgnoreCase("e")) {
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
                    for (int i = 0; i < tileMap.length; i++) {
                        for (int j = 0; j < tileMap[i].length; j++) {
                            for (int k = 0; k < tileMap[i][j].length; k++) {
                                if (tileMap[i][j][k].endsWith("FastTravelTile1") && tileMap[i][j][k].startsWith(prefix)) {
                                    if (!unlockedFastTravelTiles.contains(tileMap[i][j][k])) {
                                        unlockedFastTravelTiles.add(tileMap[i][j][k]);
                                        count++;
                                        GameLobby.setLockedOptions(count);
                                    }
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
            direction = sc.nextLine();
            movePlayer(direction);
        }
    }    

    public void chooseSpawnLocation(){
        boolean bossRoomUnlocked = false;
        String[] spawnOptions = {"Start", "Boss"};
        for (String tile : model.getUnlockedFastTravelTiles()) {
            if (tile.startsWith(model.getPrefix()) && tile.endsWith("FastTravelTile1")) {
                bossRoomUnlocked = true;
                break;
            }
        }
        if (bossRoomUnlocked) {
            // Display spawn options in your GUI and handle user selection
            // For example, you can use a JOptionPane to show the options
            Object selectedOption = JOptionPane.showInputDialog(null, "Where would you like to spawn?", "Spawn Location", JOptionPane.QUESTION_MESSAGE, null, spawnOptions, spawnOptions[0]);
            if ("Start".equals(selectedOption)) {
                // Do nothing, player stays at start
            } else if ("Boss".equals(selectedOption)) {
                model.setPlayerRoom(bossRoom);
                model.setPlayerRow(bossRow);
                model.setPlayerCol(bossCol);
            }
        }
        playMap();
    }

    private boolean isValidMove(int row, int col) {
        return row >= 0 && row < tileMap[playerRoom].length && col >= 0 && col < tileMap[playerRoom][row].length;
    }

    public void movePlayer(String direction){
        int newRow = model.getPlayerRow();
        int newCol = model.getPlayerCol();
        int count = 0;
    
        if (direction.equalsIgnoreCase("w")) {
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
            //invalid direction message
            return;
        }
        ConsoleMethods.clearConsole();
    
        if (isValidMove(newRow, newCol)) {
            if (tileMap[playerRoom][newRow][newCol].startsWith("doorTile")) {
                String doorTile = tileMap[playerRoom][newRow][newCol];
                int currentRoom = playerRoom;
                for (int i = 0; i < tileMap.length; i++) {
                    if (i == currentRoom) {
                        continue;
                    }
                    for (int j = 0; j < tileMap[i].length; j++) {
                        for (int k = 0; k < tileMap[i][j].length; k++) {
                            if (doorTile.equals(tileMap[i][j][k])) {
                                playerRoom = i;
                                playerRow = j;
                                playerCol = k;
                                usedDoorName = doorTile;
                                int[] coordinates = {i, j, k};
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
                    // System.out.println("You have found a site of grace. Would you like to teleport back?");
                    // System.out.println("Input [e] to teleport.");
                    if (direction.equalsIgnoreCase("e")) {
                        ConsoleMethods.clearConsole();
                        getOut = true;
                    }
                }
                if (tileMap[playerRoom][newRow][newCol].equals("spawnTile")) {
                    ConsoleMethods.clearConsole();
                    model.spawnTile(areaIndex);
                    tileMap[playerRoom][newRow][newCol] = "emptyTile";
                }
                if (tileMap[playerRoom][newRow][newCol].equals("bossTile")) {
                    ConsoleMethods.clearConsole();
                    model.bossTile(areaIndex);
                    tileMap[playerRoom][playerRow][playerCol] = "emptyTile";
                    for (int i = 0; i < tileMap.length; i++) {
                        for (int j = 0; j < tileMap[i].length; j++) {
                            for (int k = 0; k < tileMap[i][j].length; k++) {
                                if (tileMap[i][j][k].endsWith("FastTravelTile1") && tileMap[i][j][k].startsWith(model.getPrefix())) {
                                    if (!unlockedFastTravelTiles.contains(tileMap[i][j][k])) {
                                        unlockedFastTravelTiles.add(tileMap[i][j][k]);
                                        count++;
                                        GameLobby.setLockedOptions(count);
                                    }
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
            //out of bounds message
        }
    }

    public void playMap(){
        view.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                String direction = String.valueOf(e.getKeyChar());
                movePlayer(direction);
            }
        });
        view.setFocusable(true);
    }

    public void printCurrentRoom() {
        for (int i = 0; i < tileMap[playerRoom].length; i++) {
            for (int j = 0; j < tileMap[playerRoom][i].length; j++) {
                if (i == playerRow && j == playerCol) {
                    view.displayPlayer(i, j);
                } else if (tileMap[playerRoom][i][j].equals("emptyTile")) {
                    view.displayEmptyTile(i, j);
                } else if (tileMap[playerRoom][i][j].equals("spawnTile")) {
                    view.displaySpawnTile(i, j);
                } else if (tileMap[playerRoom][i][j].equals("bossTile")) {
                    view.displayBossTile(i, j);
                } else if (tileMap[playerRoom][i][j].startsWith("doorTile")) {
                    view.displayDoorTile(i, j);
                } else if (unlockedFastTravelTiles.contains(tileMap[playerRoom][i][j])) {
                    view.displayUnlockedFastTravelTile(i, j);
                } else if (!unlockedFastTravelTiles.contains(tileMap[playerRoom][i][j])) {
                    view.displayLockedFastTravelTile(i, j);
                }
            }
        }
    }    
}
