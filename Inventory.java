import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Inventory extends View {
    private Player playerInstance;
    private JPanel weaponsPanel;
    private JPanel weaponDetailsPanel;
    private JButton exitButton;
    private JButton equipButton;
    private ArrayList<JButton> weaponButtons;

    public Inventory(Player playerInstance) {
        super("Inventory"); // Add constructor call to superclass View
        this.playerInstance = playerInstance;

        // Initialize panels
        weaponsPanel = new JPanel(new GridLayout(5, 5));
        weaponDetailsPanel = new JPanel(new BorderLayout());

        // Initialize weapon buttons
        weaponButtons = new ArrayList<>();
        ArrayList<Weapons> inventory = playerInstance.getPlayerInventory();
        for (Weapons weapon : inventory) {
            JButton button = new JButton(weapon.getName());
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    displayWeaponDetails(weapon);
                }
            });
            weaponButtons.add(button);
            weaponsPanel.add(button);
        }

        // Initialize exit button
        exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.gameLobby();
                dispose();
            }
        });

        // Add components to panels
        weaponDetailsPanel.add(exitButton, BorderLayout.SOUTH);

        // Add panels to frame
        add(new JScrollPane(weaponsPanel), BorderLayout.CENTER);
        add(weaponDetailsPanel, BorderLayout.EAST);

        setVisible(true);
    }

    private void displayWeaponDetails(Weapons weapon) {
        weaponDetailsPanel.removeAll();
        weaponDetailsPanel.add(new JLabel("Selected Weapon: " + weapon.getName()), BorderLayout.NORTH);

        JLabel statsLabel = new JLabel(weapon.getStats().printStats());
        weaponDetailsPanel.add(statsLabel, BorderLayout.NORTH);
    
        // Check if weapon can be equipped
        if (weapon.getStats().getDexterity() <= playerInstance.getStats().getDexterity()) {
            equipButton = new JButton(playerInstance.getEquippedWeapon() == weapon ? "Equipped" : "Equip");
            equipButton.setEnabled(playerInstance.getEquippedWeapon() != weapon);
            equipButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (playerInstance.getEquippedWeapon() != null) {
                        weaponButtons.get(playerInstance.getPlayerInventory().indexOf(playerInstance.getEquippedWeapon())).setText("Equip");
                    }
                    playerInstance.setEquippedWeapon(playerInstance.getPlayerInventory().indexOf(weapon));
                    equipButton.setText("Equipped");
                    equipButton.setEnabled(false);
                }
            });
            weaponDetailsPanel.add(equipButton, BorderLayout.CENTER);
        } else {
            weaponDetailsPanel.add(new JLabel("Requires higher dexterity to equip"), BorderLayout.CENTER);
        }
    
        weaponDetailsPanel.add(exitButton, BorderLayout.SOUTH);
    
        weaponDetailsPanel.revalidate();
        weaponDetailsPanel.repaint();
    }
}