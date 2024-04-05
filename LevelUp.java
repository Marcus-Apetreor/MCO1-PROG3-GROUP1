import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * The LevelUp class represents the level up menu where players can allocate attribute points
 * upon leveling up their character.
 * 
 * <p>It provides buttons for allocating points to different attributes such as vigor, endurance,
 * dexterity, strength, intelligence, and faith.</p>
 * 
 * <p>This class extends the View class and implements the ActionListener interface to handle
 * button click events.</p>
 * 
 * Inherits from the {@link View} class.
 * @author Marcus Apetreor, Vincent Vuelva
 */
public class LevelUp extends View implements ActionListener {
    private JButton vigorBtn, enduranceBtn, dexterityBtn, strengthBtn, intelligenceBtn, faithBtn;
    private Player playerInstance;
    private Controller controller;

    /**
     * Constructs a LevelUp object with the specified player instance and controller.
     * 
     * @param playerInstance The player instance for which the level up menu is displayed.
     * @param controller     The controller instance to handle game logic.
     */
    public LevelUp(Player playerInstance, Controller controller) {
        super("Level Up");
        this.controller = controller;
        this.playerInstance = playerInstance;

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1, 0, 10));

        vigorBtn = new JButton("Vigor");
        enduranceBtn = new JButton("Endurance");
        dexterityBtn = new JButton("Dexterity");
        strengthBtn = new JButton("Strength");
        intelligenceBtn = new JButton("Intelligence");
        faithBtn = new JButton("Faith");

        vigorBtn.addActionListener(this);
        enduranceBtn.addActionListener(this);
        dexterityBtn.addActionListener(this);
        strengthBtn.addActionListener(this);
        intelligenceBtn.addActionListener(this);
        faithBtn.addActionListener(this);

        panel.add(vigorBtn);
        panel.add(enduranceBtn);
        panel.add(dexterityBtn);
        panel.add(strengthBtn);
        panel.add(intelligenceBtn);
        panel.add(faithBtn);

        add(panel);
        setVisible(true);
    }

    /**
     * Handles button click events for allocating attribute points upon leveling up.
     * 
     * @param e The ActionEvent object representing the button click event.
     */
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton)e.getSource();
        int playerLevel = playerInstance.getPlayerLevel();
        int runeCount = playerInstance.getRuneCount();
        int levelUpCost = (playerLevel * 100) / 2;
        
        if (runeCount - levelUpCost < 0) {
            JOptionPane.showMessageDialog(this, "You do not have enough runes!", "Insufficient Runes", JOptionPane.ERROR_MESSAGE);
        } else if (source == vigorBtn) {
            controller.levelUp(1); // Level up vigor
        } else if (source == enduranceBtn) {
            controller.levelUp(2); // Level up endurance
        } else if (source == dexterityBtn) {
            controller.levelUp(3); // Level up dexterity
        } else if (source == strengthBtn) {
            controller.levelUp(4); // Level up strength
        } else if (source == intelligenceBtn) {
            controller.levelUp(5); // Level up intelligence
        } else if (source == faithBtn) {
            controller.levelUp(6); // Level up faith
        }
        controller.gameLobby();
        dispose(); // Close the level up window
    }
}
