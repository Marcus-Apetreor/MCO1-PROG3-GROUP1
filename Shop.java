import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Shop class represents the graphical user interface for the in-game shop where players can purchase weapons.
 * Extends View class and implements ActionListener interface.
 * 
 * Inherits from the {@link View} class.
 * @author Marcus Apetreor, Vincent Vuelva
 */
public class Shop extends View implements ActionListener {
    private Player player;
    private JTabbedPane tabbedPane;
    private HashMap<String, JButton> purchaseButtons;
    private ArrayList<Weapons> shopInventory;
    private Controller controller;

    /**
     * Constructs a new Shop object.
     *
     * @param player        The player object representing the player in the game.
     * @param shopInventory The list of weapons available in the shop.
     * @param controller    The controller object used to control the flow of the game.
     */
    public Shop(Player player, ArrayList<Weapons> shopInventory, Controller controller) {
        super("Shop");
        this.controller = controller;
        this.player = player;
        this.shopInventory = shopInventory;
        this.purchaseButtons = new HashMap<>();
        setLocationRelativeTo(null);
        initComponents();
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> {
            controller.gameLobby();
            dispose();
        });
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(exitButton);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    /**
     * Initializes the components of the shop interface.
     */
    private void initComponents() {
        tabbedPane = new JTabbedPane();

        shopInventory.stream()
                .map(Weapons::getType)
                .distinct()
                .sorted()
                .forEach(category -> tabbedPane.addTab(category, createWeaponPanel(category)));

        add(tabbedPane);
    }

    /**
     * Creates a panel containing the weapons of a specific category.
     *
     * @param category The category of weapons.
     * @return JPanel representing the weapon panel.
     */
    private JPanel createWeaponPanel(String category) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 4, 10, 10)); // 4 items per row, as per the images

        shopInventory.stream()
                .filter(weapon -> weapon.getType().equals(category))
                .forEach(weapon -> panel.add(createWeaponCard(weapon)));

        return panel;
    }

    /**
     * Creates a card for a specific weapon.
     *
     * @param weapon The weapon for which the card is created.
     * @return Component representing the weapon card.
     */
    private Component createWeaponCard(Weapons weapon) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.PAGE_AXIS));
        card.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel nameLabel = new JLabel(weapon.getName());
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel imageLabel = new JLabel(new ImageIcon(weapon.getImagePath()));
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel statsLabel = new JLabel(weapon.getStats().printStats());
        statsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton buyButton = new JButton("Buy");
        buyButton.setActionCommand(weapon.getName());
        buyButton.addActionListener(this);
        purchaseButtons.put(weapon.getName(), buyButton);

        card.add(imageLabel);
        card.add(nameLabel);
        card.add(statsLabel);
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

    /**
     * Retrieves the index of a weapon in the shop inventory based on its name.
     *
     * @param weaponName The name of the weapon.
     * @return The index of the weapon in the shop inventory, or -1 if not found.
     */
    private int getWeaponIndex(String weaponName) {
        for (int i = 0; i < shopInventory.size(); i++) {
            if (shopInventory.get(i).getName().equals(weaponName)) {
                return i;
            }
        }
        return -1;
    }
}
