import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * The FastTravel class represents a menu for fast traveling to unlocked areas in the game.
 * It allows the player to select an area and choose between spawn point or boss room.
 * It also handles the unlocking of fast travel tiles based on player progress.
 * Extends View and implements ActionListener.
 * 
 * Inherits from the {@link View} class.
 * @author Marcus Apetreor, Vincent Vuelva
 */
public class FastTravel extends View implements ActionListener {
    private JList<String> areaList;
    private DefaultListModel<String> listModel;
    private JButton travelButton;
    private JButton exitButton;
    private Map<String, Boolean> areaUnlockStatus;
    private Controller controller;
    private int unlockedAreas;
    private ArrayList<String> unlockedFastTravelTiles;

    /**
     * Constructs a FastTravel object.
     * 
     * @param controller The game's controller instance.
     */
    public FastTravel(Controller controller) {
        super("Fast Travel Menu");
        this.controller = controller;
        this.unlockedAreas = TileMap.getUnlockedAreas();
        this.unlockedFastTravelTiles = TileMap.getUnlockedFastTravelTiles();

        setLocationRelativeTo(null);
        areaUnlockStatus = new LinkedHashMap<>();
        areaUnlockStatus.put("Stormveil Castle" + (unlockedAreas >= 1 ? "" : " [LOCKED]"), unlockedAreas >= 1);
        areaUnlockStatus.put("Raya Lucaria Academy" + (unlockedAreas >= 2 ? "" : " [LOCKED]"), unlockedAreas >= 2);
        areaUnlockStatus.put("The Elden Throne" + (unlockedAreas >= 3 ? "" : " [LOCKED]"), unlockedAreas >= 3);
        listModel = new DefaultListModel<>();
        areaUnlockStatus.forEach((area, isUnlocked) -> listModel.addElement(area));

        areaList = new JList<>(listModel);
        areaList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        areaList.setCellRenderer(new AreaListCellRenderer());

        JScrollPane scrollPane = new JScrollPane(areaList);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 1)); // 2 rows, 1 column

        travelButton = new JButton("Travel");
        travelButton.addActionListener(this);
        buttonPanel.add(travelButton);

        exitButton = new JButton("Exit");
        exitButton.addActionListener(this);
        buttonPanel.add(exitButton);

        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    /**
     * ActionListener implementation for handling button clicks.
     * 
     * @param e The ActionEvent instance.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // Check if the exit button was clicked
        if (e.getSource() == exitButton) {
            controller.gameLobby();
            dispose();
            return; // Exit the method immediately
        }

        // Handle selected map
        String selectedArea = areaList.getSelectedValue();
        if (selectedArea != null && areaUnlockStatus.get(selectedArea.replace(" [LOCKED]", ""))) {
            // Create options
            String[] options = {"Spawn Point", "Boss Room"};
            // Disable "Boss Room" option if the boss room is not unlocked
            if (!unlockFastTravelTiles(selectedArea, unlockedFastTravelTiles, 1)) {
                options[1] = "<html><font color=gray>Boss Room [LOCKED]</font></html>";
            }
            // Show option dialog
            int option = JOptionPane.showOptionDialog(this, "Choose a destination:", "Travel to " + selectedArea.replace(" [LOCKED]", ""),
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
            // Handle selected option
            if (option == 0 || (option == 1 && options[1].equals("Boss Room"))) {
                Object areaInstance = null;
                if (selectedArea.startsWith("S")) {
                    areaInstance = new StormveilCastle();
                } else if (selectedArea.startsWith("R")) {
                    areaInstance = new RayaLucariaAcademy();
                } else if (selectedArea.startsWith("T")) {
                    areaInstance = new TheEldenThrone();
                }
                if (areaInstance != null) {
                    try {
                        Method displayGUIMethod = areaInstance.getClass().getMethod("displayGUI", int.class);
                        displayGUIMethod.invoke(areaInstance, option == 0 ? 1 : 2);
                        dispose();
                    } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "This area is locked or not selected.");
        }
    }

    /**
     * Custom cell renderer for the area list to display locked areas in gray.
     */
    private static class AreaListCellRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            String stringValue = (String) value;
            if (stringValue.endsWith("[LOCKED]")) {
                c.setForeground(Color.GRAY);
            } else {
                c.setForeground(Color.BLACK);
            }
            return c;
        }
    }

    /**
     * Method to unlock fast travel tiles based on player progress.
     * 
     * @param prefix                 The prefix of the area.
     * @param unlockedFastTravelTiles The list of unlocked fast travel tiles.
     * @param playerInput            The player input option.
     * @return                       True if the fast travel tiles are unlocked, false otherwise.
     */
    public boolean unlockFastTravelTiles(String prefix, ArrayList<String> unlockedFastTravelTiles, int playerInput) {
        boolean unlocked = false;
        for(String tile : unlockedFastTravelTiles) {
            if (tile.startsWith(prefix.substring(0, 1)) && tile.endsWith("1")) {
                unlocked = true;
                break;
            }
        }

        if (playerInput == 1 && unlocked) {
            return true;
        } else {
            return false;
        }
    }
}
