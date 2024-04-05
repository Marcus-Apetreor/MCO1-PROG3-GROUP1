import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

public class MapGUI extends View implements KeyListener {
    private JPanel[][][] roomsPanel;
    private Map<String, ImageIcon> tileIcons;
    private int playerRoom, playerRow, playerCol;
    private String[][][] matrix;
    private String prefix;
    private TileMap tileMap;

    private static MapGUI currentInstance = null;

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

    private void movePlayer(int rowChange, int colChange) {
        int newRow = playerRow + rowChange;
        int newCol = playerCol + colChange;
        if (newRow >= 0 && newRow < matrix[playerRoom].length && newCol >= 0 && newCol < matrix[playerRoom][newRow].length) {
            updatePlayerPosition(newRow, newCol);
            displayCurrentRoom();
        }
    }

    private void updatePlayerPosition(int newRow, int newCol) {
        playerRow = newRow;
        playerCol = newCol;
        doorTile(newRow, newCol);
        fastTravelTile(newRow, newCol);
        spawnTile(newRow, newCol);
        bossTile(newRow, newCol);
        creditTile(newRow, newCol);
    }

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

    public void spawnTile(int newRow, int newCol){
        if (newRow >= 0 && newRow < matrix[playerRoom].length && newCol >= 0 && newCol < matrix[playerRoom][newRow].length) {
            if(matrix[playerRoom][newRow][newCol].equals("spawnTile")){
                tileMap.spawnTile();
                matrix[playerRoom][newRow][newCol] = "emptyTile";
            }
        }
    }

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

    public void creditTile(int newRow, int newCol){
        if (newRow >= 0 && newRow < matrix[playerRoom].length && newCol >= 0 && newCol < matrix[playerRoom][newRow].length) {
            if(matrix[playerRoom][newRow][newCol].equals("creditsTile")){
                new CreditScreen();
            }
        }
    }
}
