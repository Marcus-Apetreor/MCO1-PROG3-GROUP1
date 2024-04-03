import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class FastTravel extends View implements ActionListener {
    private JList<String> areaList;
    private DefaultListModel<String> listModel;
    private JButton travelButton;
    private JButton exitButton;
    private Map<String, Boolean> areaUnlockStatus;
    private Controller controller;
    private int unlockedAreas;
    private ArrayList<String> unlockedFastTravelTiles;

    public FastTravel(Controller controller) { // Modify this line
        super("Fast Travel Menu");
        this.controller = controller;
        this.unlockedAreas = controller.getUnlockedAreas();
        this.unlockedFastTravelTiles = controller.getUnlockedFastTravelTiles();

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

    @Override
    public void actionPerformed(ActionEvent e) {
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
            if (option == 0) {
                JOptionPane.showMessageDialog(this, "Traveling to the Spawn Point in " + selectedArea.replace(" [LOCKED]", ""));
                // logic to travel to spawn
            } else if (option == 1 && options[1].equals("Boss Room")) {
                JOptionPane.showMessageDialog(this, "Traveling to the Boss Room in " + selectedArea.replace(" [LOCKED]", ""));
                // logic to travel to boss room
            }
        } else if (e.getSource() == exitButton) {
            controller.gameLobby();
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "This area is locked or not selected.");
        }
    }

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

    public boolean unlockFastTravelTiles(String prefix, ArrayList<String> unlockedFastTravelTiles, int playerInput){
        int i;
        boolean unlocked = false;
        for(i=0; i<unlockedFastTravelTiles.size(); i++){
            if (unlockedFastTravelTiles.get(i).startsWith(prefix)){
                if(unlockedFastTravelTiles.get(i).endsWith("1")){
                    unlocked = true;
                }
            }
        }

        if (playerInput == 1 && unlocked){
            return true;
        } else {
            return false;
        }
    } 
}
