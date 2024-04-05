import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * The GameLobby class represents the game lobby interface where players can access various game functionalities.
 * It displays player stats, character sprite, and buttons for fast travel, leveling up, accessing inventory, visiting shop, and exiting to title screen.
 * Extends View and implements ActionListener.
 * 
 * Inherits from the {@link View} class.
 * @author Marcus Apetreor
 */
public class GameLobby extends View implements ActionListener {
    private JButton fastTravelBtn, levelUpBtn, inventoryBtn, shopBtn, exitBtn;
    private Player playerInstance;

    /**
     * Constructs a GameLobby object.
     * 
     * @param controller The game's controller instance.
     */
    public GameLobby(Controller controller) {
        super("Roundtable Hold");
        this.playerInstance = Controller.getPlayerInstance();
        playerInstance.initializeMaxHealth();
        playerInstance.initializeDodgeRate();
        playerInstance.initializeCombinedStats();

        JPanel panel = new JPanel(new BorderLayout()); // Use BorderLayout
        JPanel statsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Use FlowLayout

        JLabel playerStats = new JLabel();
        playerStats.setText(playerInstance.printPlayer() + playerInstance.getStats().printStats());

        JLabel combinedStats = new JLabel();
        combinedStats.setText("<html>Stats with Weapons: <br>" + playerInstance.getCombinedStats().printStats());

        statsPanel.add(playerStats);
        statsPanel.add(combinedStats);

        panel.add(statsPanel, BorderLayout.NORTH); // Add statsPanel to the top of the main panel
        JLabel characterSpriteLabel = new JLabel();
        characterSpriteLabel.setIcon(new ImageIcon(playerInstance.getImagePath()));

        fastTravelBtn = new JButton("Fast Travel");
        levelUpBtn = new JButton("Level Up");
        inventoryBtn = new JButton("Inventory");
        shopBtn = new JButton("Shop");
        exitBtn = new JButton("Exit to Title Screen");

        fastTravelBtn.addActionListener(this);
        levelUpBtn.addActionListener(this);
        inventoryBtn.addActionListener(this);
        shopBtn.addActionListener(this);
        exitBtn.addActionListener(this);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.add(Box.createVerticalGlue());
        buttonPanel.add(fastTravelBtn);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(levelUpBtn);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(inventoryBtn);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(shopBtn);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(exitBtn);
        buttonPanel.add(Box.createVerticalGlue());

        panel.add(buttonPanel, BorderLayout.WEST); 
        panel.add(characterSpriteLabel, BorderLayout.EAST); 
        panel.add(playerStats, BorderLayout.CENTER); 

        add(panel);
        setVisible(true);
    }

    /**
     * ActionListener implementation for handling button clicks.
     * 
     * @param e The ActionEvent instance.
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == fastTravelBtn) {
            controller.fastTravel();
            dispose();
        } else if (e.getSource() == levelUpBtn) {
            controller.levelUpMenu();
            dispose();
        } else if (e.getSource() == inventoryBtn) {
            controller.inventory();
            dispose();
        } else if (e.getSource() == shopBtn) {
            controller.shop();
            dispose();
        } else if (e.getSource() == exitBtn) {
            View.titleScreen();
            dispose();
        }
    }
}
