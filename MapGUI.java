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

    public MapGUI(String[][][] matrix, int playerRoom, int playerRow, int playerCol) {
        super("Map Display");
        this.playerRoom = playerRoom;
        this.playerRow = playerRow;
        this.playerCol = playerCol;

        // Initialize tile icons
        initializeTileIcons();

        // Create panels for each room
        int numRooms = matrix.length;
        roomsPanel = new JPanel[numRooms][][];
        for (int room = 0; room < numRooms; room++) {
            int numRows = matrix[room].length;
            roomsPanel[room] = new JPanel[numRows][];
            for (int row = 0; row < numRows; row++) {
                int numCols = matrix[room][row].length;
                roomsPanel[room][row] = new JPanel[numCols];
                for (int col = 0; col < numCols; col++) {
                    JLabel label = new JLabel();
                    label.setIcon(tileIcons.get(matrix[room][row][col]));
                    roomsPanel[room][row][col] = new JPanel();
                    roomsPanel[room][row][col].add(label);
                }
            }
        }

        // Display player's initial position
        roomsPanel[playerRoom][playerRow][playerCol].setBackground(Color.RED);

        // Set layout for the main frame
        setLayout(new GridLayout(1, numRooms));

        // Add panels for each room to the main frame
        for (int room = 0; room < numRooms; room++) {
            JPanel roomPanel = new JPanel(new GridLayout(roomsPanel[room].length, roomsPanel[room][0].length));
            for (int row = 0; row < roomsPanel[room].length; row++) {
                for (int col = 0; col < roomsPanel[room][row].length; col++) {
                    roomPanel.add(roomsPanel[room][row][col]);
                }
            }
            add(roomPanel);
        }

        // Add key listener
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

        setVisible(true);
    }

    private void initializeTileIcons() {
        tileIcons = new HashMap<>();
        tileIcons.put("emptyTile", new ImageIcon("emptyTile.png"));
        tileIcons.put("fastTravelTile", new ImageIcon("fastTravelTile.png"));
        tileIcons.put("spawnTile", new ImageIcon("spawnTile.png"));
        tileIcons.put("doorTile", new ImageIcon("doorTile.png"));
        tileIcons.put("bossTile", new ImageIcon("bossTile.png"));
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_W:
                movePlayer(-1, 0); // Move Up
                break;
            case KeyEvent.VK_A:
                movePlayer(0, -1); // Move Left
                break;
            case KeyEvent.VK_S:
                movePlayer(1, 0); // Move Down
                break;
            case KeyEvent.VK_D:
                movePlayer(0, 1); // Move Right
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    private void movePlayer(int rowChange, int colChange) {
        int newRow = playerRow + rowChange;
        int newCol = playerCol + colChange;

        // Check if the new position is within bounds
        if (newRow >= 0 && newRow < roomsPanel[playerRoom].length && newCol >= 0 && newCol < roomsPanel[playerRoom][0].length) {
            // Remove player marker from current position
            roomsPanel[playerRoom][playerRow][playerCol].removeAll();
            roomsPanel[playerRoom][playerRow][playerCol].add(new JLabel(tileIcons.get("emptyTile"))); // Assuming the player was on an empty tile

            // Update player's position
            playerRow = newRow;
            playerCol = newCol;

            // Set player marker at new position
            roomsPanel[playerRoom][playerRow][playerCol].removeAll();
            roomsPanel[playerRoom][playerRow][playerCol].add(new JLabel(tileIcons.get("player")));

            // Repaint the frame to reflect changes
            validate();
            repaint();
        }
    }
    //     String[][][] matrix = {
    //             // Matrix initialization goes here
    //     };

    //     int playerRoom = 0;
    //     int playerRow = 2;
    //     int playerCol = 1;

    //     SwingUtilities.invokeLater(() -> new MapGUI(matrix, playerRoom, playerRow, playerCol));
    // }
}
