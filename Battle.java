import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * The Battle class represents the graphical interface for a battle between the player and an enemy.
 * It extends the View class.
 * 
 * Inherits from the {@link View} class.
 * @author Marcus Apetreor, Vincent Vuelva
 */
public class Battle extends View {
    private JFrame frame;
    private JPanel mainPanel;
    private JLabel playerNameLabel;
    private JProgressBar playerHealthBar;
    private JLabel enemyNameLabel;
    private JProgressBar enemyHealthBar;
    private JTextArea systemMessagesArea;
    private JButton attackButton;
    private JButton dodgeButton;
    private Enemy enemy;
    private Player player;
    private double enemyTurn;

    /**
     * Constructs a new Battle instance with the specified enemy.
     *
     * @param enemy The enemy participating in the battle.
     */
    public Battle(Enemy enemy) {
        super("Battle");
        this.enemy = enemy;
        this.player = Controller.getPlayerInstance();

        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (!player.isDead() && !enemy.isDefeated()) {
                    JOptionPane.showMessageDialog(frame, "You cannot run away from the fight!");
                }
            }
        });
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setPreferredSize(new Dimension(300, 300));

        playerNameLabel = new JLabel(player.getPlayerName());
        playerHealthBar = new JProgressBar(0, (int)(((player.getStats().getVigor() + player.getEquippedWeapon().getStats().getVigor()) / 2.0) * 100.0));
        playerHealthBar.setValue((int) player.getMaxHealth());
        enemyNameLabel = new JLabel(enemy.getName());
        enemyHealthBar = new JProgressBar(0, (int) enemy.getHealth());
        enemyHealthBar.setValue((int) enemy.getHealth());
        systemMessagesArea = new JTextArea();
        systemMessagesArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(systemMessagesArea);

        attackButton = new JButton("Attack");
        attackButton.addActionListener(e -> {
            this.enemyTurn = enemy.getAttack();
            systemMessagesArea.append(enemy.getName() + " is about to deal " + enemyTurn + " damage!\n");
            Object[] options = {"Physical Attack", "Sorcery Attack", "Incantation Attack"};
            int option = JOptionPane.showOptionDialog(frame, "Choose an attack type:", "Attack",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
            handlePlayerTurn(1, option + 1);
        });

        dodgeButton = new JButton("Dodge");
        dodgeButton.addActionListener(e -> {
            this.enemyTurn = enemy.getAttack();
            systemMessagesArea.append(enemy.getName() + " is about to deal " + enemyTurn + " damage!\n");
            handlePlayerTurn(2, 0);
            if (!controller.playerTurn(enemy, 2, 0)) {
                systemMessagesArea.append("You have dodged the attack!\n");
            }
        });

        mainPanel.add(playerNameLabel);
        mainPanel.add(playerHealthBar);
        mainPanel.add(enemyNameLabel);
        mainPanel.add(enemyHealthBar);
        mainPanel.add(scrollPane);
        mainPanel.add(attackButton);
        mainPanel.add(dodgeButton);

        frame.add(mainPanel);

        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Shows a popup indicating the damage taken by the player.
     */
    private void showDamagePopup() {
        String message = enemy.getName() + " has hit you with " + enemyTurn + " damage!";
        JOptionPane.showMessageDialog(frame, message);
        playerHealthBar.setValue((int) player.getMaxHealth());
        enemyHealthBar.setValue((int) enemy.getHealth());
    }

    /**
     * Handles the prompts and messages after a player's turn.
     */
    private void messagePrompts() {
        if (enemy.isDefeated()) {
            frame.dispose();
            player.addRunes((int)enemy.dropRunes());
        } else if (player.isDead()) {
            frame.dispose();
            MapGUI.getCurrentInstance().dispose();
            View.titleScreen();
        }
    }

    /**
     * Handles the player's turn in the battle.
     *
     * @param attackType The type of attack chosen by the player.
     * @param actionType The type of action chosen by the player.
     */
    private void handlePlayerTurn(int attackType, int actionType) {
        if (controller.playerTurn(enemy, attackType, actionType)) {
            showDamagePopup();
        }
        repaint();
        messagePrompts();
    }
}
