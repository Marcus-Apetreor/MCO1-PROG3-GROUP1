import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class Shop extends View implements ActionListener {
    private Player player;
    private JTabbedPane tabbedPane;
    private HashMap<String, JButton> purchaseButtons;
    private ArrayList<Weapons> shopInventory;
    private Controller controller;

    public Shop(Player player, ArrayList<Weapons> shopInventory, Controller controller) {
        super("Shop");
        this.controller = controller;
        this.player = player;
        this.shopInventory = shopInventory;
        this.purchaseButtons = new HashMap<>();
        setLocationRelativeTo(null);
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        tabbedPane = new JTabbedPane();
    
        shopInventory.stream()
            .map(Weapons::getType)
            .distinct()
            .sorted()
            .forEach(category -> tabbedPane.addTab(category, createWeaponPanel(category)));
    
        add(tabbedPane);
    }

    private JPanel createWeaponPanel(String category) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 4, 10, 10)); // 4 items per row, as per the images

        shopInventory.stream()
                .filter(weapon -> weapon.getType().equals(category))
                .forEach(weapon -> panel.add(createWeaponCard(weapon)));

        return panel;
    }

    private Component createWeaponCard(Weapons weapon) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.PAGE_AXIS));
        card.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    
        JLabel nameLabel = new JLabel(weapon.getName());
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    
        JLabel imageLabel = new JLabel(new ImageIcon(weapon.getImagePath()));
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    
        // Create a JLabel for each stat
        JLabel statsLabel = new JLabel(weapon.getStats().printStats());
        statsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    
        JButton buyButton = new JButton("Buy");
        buyButton.setActionCommand(weapon.getName());
        buyButton.addActionListener(this);
        purchaseButtons.put(weapon.getName(), buyButton);
    
        card.add(imageLabel);
        card.add(nameLabel);
        card.add(statsLabel); // Add the stats label to the card
        card.add(buyButton);
    
        buyButton.setEnabled(player.getRuneCount() >= weapon.getPrice());
    
        return card;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String weaponName = e.getActionCommand();
        int weaponIndex = getWeaponIndex(weaponName);
        if (weaponIndex != -1) {
            controller.buyWeapon(weaponIndex);
            controller.gameLobby();
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Weapon " + weaponName + " not found.");
        }
    }

    private int getWeaponIndex(String weaponName) {
        for (int i = 0; i < shopInventory.size(); i++) {
            if (shopInventory.get(i).getName().equals(weaponName)) {
                return i;
            }
        }
        return -1;
    }
}
