import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

/**
 * The MapGUI class represents the graphical user interface for displaying the game map
 * and navigating through it.
 * 
 * <p>This class extends the View class and implements the KeyListener interface to handle
 * keyboard input for player movement.</p>
 * 
 * <p>It provides methods for initializing the map, updating the player's position, handling
 * different types of tiles, and responding to keyboard events for player movement.</p>
 * 
 * <p>Instances of this class allow players to move around the game map, interact with tiles,
 * and trigger various game events.</p>
 * 
 * <p>Note: This class assumes that the map is represented as a three-dimensional matrix of strings,
 * where each string represents a type of tile in the game.</p>
 * 
 * <p>Note: The MapGUI class is a singleton, meaning that only one instance of it can exist at a time.</p>
 * 
 * Inherits from the {@link View} class.
 * @author Marcus Apetreor, Vincent Vuelva
 */
public class MapGUI extends View implements KeyListener {
    private JPanel[][][] roomsPanel;
    private Map<String, ImageIcon> tileIcons;
    private int playerRoom, playerRow, playerCol;
    private String[][][] matrix;
    private String prefix;
    private TileMap tileMap;

    private static MapGUI currentInstance = null;

    /**
     * Constructs a MapGUI object with the specified TileMap, player room, row, and column.
     * 
     * @param tileMap     The TileMap representing the game map.
     * @param playerRoom  The initial room index where the player is located.
     * @param playerRow   The initial row index where the player is located.
     * @param playerCol   The initial column index where the player is located.
     */
    public MapGUI(TileMap tileMap, int playerRoom, int playerRow, int playerCol) {
        super("Map Display");
        this.tileMap = tileMap;
        this.matrix = tileMap.getMatrix();
        this.prefix = tileMap.getPrefix();
        this.playerRoom = playerRoom;
        this.playerRow = playerRow;
        this.playerCol = playerCol;
        currentInstance = this;
        Player player = Controller.getPlayerInstance();
        if (player.isDead()) {
            dispose();
            View.titleScreen();
        }

        initializeTileIcons();
        createRoomPanels();
        displayCurrentRoom();

        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }

    /**
     * Returns the current instance of the MapGUI class.
     * 
     * @return The current instance of MapGUI.
     */
    public static MapGUI getCurrentInstance() {
        return currentInstance;
    }

    private void initializeTileIcons() {
        tileIcons = new HashMap<>();
        int width = 200;
        int height = 200;
        String[] tileNames = {"doorTile", "fastTravelTile", "emptyTile", "spawnTile", "bossTile", "creditsTile", "playerIcon"};

        for (String tileName : tileNames) {
            ImageIcon tileIcon = new ImageIcon(tileName + ".png");
            Image scaledImage = tileIcon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
            tileIcons.put(tileName, new ImageIcon(scaledImage));
        }
    }

    /**
     * Handles the creation of the room panels in order to display them properly.
     * 
     */
    private void createRoomPanels() {
        int numRooms = matrix.length;
        roomsPanel = new JPanel[numRooms][][];
    
        for (int room = 0; room < numRooms; room++) {
            int numRows = matrix[room].length;
            int numCols = matrix[room][0].length;
            roomsPanel[room] = new JPanel[numRows][numCols];
    
            for (int row = 0; row < numRows; row++) {
                for (int col = 0; col < numCols; col++) {
                    roomsPanel[room][row][col] = new JPanel();
                    JLabel label = new JLabel();
                    updateTileIcon(label, room, row, col);
                    roomsPanel[room][row][col].add(label);
                }
            }
        }
    }

    /**
     * Handles the updates of any tile icons when player moves around the map.
     * @param label The label of the tile.
     * @param room The room index of the tile.
     * @param row The row index of the tile.
     * @param col The column index of the tile.
     */
    private void updateTileIcon(JLabel label, int room, int row, int col) {
        if (room == playerRoom && row == playerRow && col == playerCol) {
            label.setIcon(tileIcons.get("playerIcon"));
        } else if (matrix[room][row][col].startsWith("doorTile")) {
            label.setIcon(tileIcons.get("doorTile"));
        } else if (matrix[room][row][col].startsWith(prefix)) {
            label.setIcon(tileIcons.get("fastTravelTile"));
        } else {
            label.setIcon(tileIcons.get(matrix[room][row][col]));
        }
    }

    /**
     * Handles the display of the room that the player is currently in.
     * 
     */
    private void displayCurrentRoom() {
        getContentPane().removeAll();
        setLayout(new GridLayout(roomsPanel[playerRoom].length, roomsPanel[playerRoom][0].length));
        for (int row = 0; row < roomsPanel[playerRoom].length; row++) {
            for (int col = 0; col < roomsPanel[playerRoom][row].length; col++) {
                JLabel label = (JLabel) roomsPanel[playerRoom][row][col].getComponent(0);
                updateTileIcon(label, playerRoom, row, col);
                add(roomsPanel[playerRoom][row][col]);
            }
        }
        validate();
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    /**
     * Handles the movement inputs for player movement.
     *  @param e The KeyEvent object representing the key press event.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_W:
                movePlayer(-1, 0);
                break;
            case KeyEvent.VK_A:
                movePlayer(0, -1);
                break;
            case KeyEvent.VK_S:
                movePlayer(1, 0);
                break;
            case KeyEvent.VK_D:
                movePlayer(0, 1);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    /**
     * Handles the validation of movement and updating the player's position.
     * 
     * @param newRow The row index of the player's new position.
     * @param newCol The column index of the player's new position.
     */
    private void movePlayer(int rowChange, int colChange) {
        int newRow = playerRow + rowChange;
        int newCol = playerCol + colChange;
        if (newRow >= 0 && newRow < matrix[playerRoom].length && newCol >= 0 && newCol < matrix[playerRoom][newRow].length) {
            updatePlayerPosition(newRow, newCol);
            displayCurrentRoom();
        }
    }
    /**
     * Handles the updating of the players position using helper functions for special tile interactions.
     * 
     * @param newRow The row index of the player's new position.
     * @param newCol The column index of the player's new position.
     */
    private void updatePlayerPosition(int newRow, int newCol) {
        playerRow = newRow;
        playerCol = newCol;
        doorTile(newRow, newCol);
        fastTravelTile(newRow, newCol);
        spawnTile(newRow, newCol);
        bossTile(newRow, newCol);
        creditTile(newRow, newCol);
    }

    /**
     * Handles the movement of the player through door tiles to other rooms.
     * 
     * @param newRow The row index of the player's new position.
     * @param newCol The column index of the player's new position.
     */
    public void doorTile(int newRow, int newCol){
        if (matrix[playerRoom][newRow][newCol].startsWith("doorTile")){
            String currentDoor = matrix[playerRoom][newRow][newCol];
            for(int i=0; i<matrix.length; i++){
                for(int j=0; j<matrix[i].length; j++){
                    for(int k=0; k<matrix[i][j].length; k++){
                        if(matrix[i][j][k].equals(currentDoor) && i != playerRoom){
                            playerRoom = i;
                            // The player should appear at the door in the new room
                            playerRow = j;
                            playerCol = k;
                            return;
                        }
                    }
                }
            }
        }
    }

    /**
     * Handles player interaction with fast travel tiles.
     * 
     * @param newRow The row index of the player's new position.
     * @param newCol The column index of the player's new position.
     */
    public void fastTravelTile(int newRow, int newCol){
        if (newRow >= 0 && newRow < matrix[playerRoom].length && newCol >= 0 && newCol < matrix[playerRoom][newRow].length) {
            if (matrix[playerRoom][newRow][newCol].startsWith(prefix)&&TileMap.getUnlockedFastTravelTiles().contains(matrix[playerRoom][newRow][newCol])){
                int response = JOptionPane.showConfirmDialog(null, "You've touched a fast travel tile. Do you want to go back to the menu?", "Fast Travel", JOptionPane.YES_NO_OPTION);
    
                if (response == JOptionPane.YES_OPTION) {
                    controller.gameLobby();
                    dispose();
                }
            }
        }
    }

    /**
     * Handles player interaction with spawn tiles.
     * 
     * @param newRow The row index of the player's new position.
     * @param newCol The column index of the player's new position.
     */
    public void spawnTile(int newRow, int newCol){
        if (newRow >= 0 && newRow < matrix[playerRoom].length && newCol >= 0 && newCol < matrix[playerRoom][newRow].length) {
            if(matrix[playerRoom][newRow][newCol].equals("spawnTile")){
                tileMap.spawnTile();
                matrix[playerRoom][newRow][newCol] = "emptyTile";
            }
        }
    }

    /**
     * Handles player interaction with boss tiles.
     * 
     * @param newRow The row index of the player's new position.
     * @param newCol The column index of the player's new position.
     */
    public void bossTile(int newRow, int newCol){
        if (newRow >= 0 && newRow < matrix[playerRoom].length && newCol >= 0 && newCol < matrix[playerRoom][newRow].length) {
            if(matrix[playerRoom][newRow][newCol].equals("bossTile")){
                tileMap.fightBoss();
                matrix[playerRoom][newRow][newCol] = "emptyTile";
                for(int i=0; i<matrix.length; i++){
                    for(int j=0; j<matrix[i].length; j++){
                        for(int k=0; k<matrix[i][j].length; k++){
                            if(matrix[i][j][k].startsWith(prefix) && matrix[i][j][k].endsWith("1")){
                                if(!TileMap.getUnlockedFastTravelTiles().contains(matrix[i][j][k])){
                                    TileMap.getUnlockedFastTravelTiles().add(matrix[i][j][k]);
                                    TileMap.addUnlockedAreas();
                                    return;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Handles player interaction with credit tiles.
     * 
     * @param newRow The row index of the player's new position.
     * @param newCol The column index of the player's new position.
     */
    public void creditTile(int newRow, int newCol){
        if (newRow >= 0 && newRow < matrix[playerRoom].length && newCol >= 0 && newCol < matrix[playerRoom][newRow].length) {
            if(matrix[playerRoom][newRow][newCol].equals("creditsTile")){
                new CreditScreen();
            }
        }
    }
}

