import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Battle extends View{
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

    public Battle(Enemy enemy) {
        super("Battle");
        this.enemy = enemy;
        this.player = Controller.getPlayerInstance();

        // main panel that holds all the components
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if(!player.isDead()&&!enemy.isDefeated()){
                    JOptionPane.showMessageDialog(frame, "You cannot run away from the fight!");
                }
            }
        });
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setPreferredSize(new Dimension(300, 300));

        // player name
        playerNameLabel = new JLabel(player.getPlayerName());
        
        // player health bar
        playerHealthBar = new JProgressBar(0, (int)(((player.getStats().getVigor() + player.getEquippedWeapon().getStats().getVigor())/2.0)*100.0));
        playerHealthBar.setValue((int)player.getMaxHealth());
        
        // enemy name
        enemyNameLabel = new JLabel(enemy.getName());
        
        // enemy health bar
        enemyHealthBar = new JProgressBar(0, (int)enemy.getHealth());
        enemyHealthBar.setValue((int)enemy.getHealth());
        
        // system messages
        systemMessagesArea = new JTextArea();
        systemMessagesArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(systemMessagesArea);
        
        // attack button
        attackButton = new JButton("Attack");
        attackButton.addActionListener(e -> {
            this.enemyTurn = enemy.getAttack();
            systemMessagesArea.append(enemy.getName() + " is about to deal " + enemyTurn + " damage!\n");
            // Create options
            Object[] options = {"Physical Attack", "Sorcery Attack", "Incantation Attack"};
            // Show option dialog
            int option = JOptionPane.showOptionDialog(frame, "Choose an attack type:", "Attack",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
            // Handle selected option
            handlePlayerTurn(1, option+1);
        });

        // dodge button
        dodgeButton = new JButton("Dodge");
        dodgeButton.addActionListener(e -> {
            this.enemyTurn = enemy.getAttack();
            systemMessagesArea.append(enemy.getName() + " is about to deal " + enemyTurn + " damage!\n");
            handlePlayerTurn(2, 0);
            if(!controller.playerTurn(enemy, 2, 0)){
                systemMessagesArea.append("You have dodged the attack!\n");
            }
        });

        // adding components to the main panel
        mainPanel.add(playerNameLabel);
        mainPanel.add(playerHealthBar);
        mainPanel.add(enemyNameLabel);
        mainPanel.add(enemyHealthBar);
        mainPanel.add(scrollPane);
        mainPanel.add(attackButton);
        mainPanel.add(dodgeButton);
        
        // adds the main panel to frame
        frame.add(mainPanel);

        // display the window
        frame.pack();
        frame.setVisible(true);
    }

    private void showDamagePopup(){
        String message = enemy.getName() + " has hit you with " + enemyTurn + " damage!";
        JOptionPane.showMessageDialog(frame, message);
        playerHealthBar.setValue((int)player.getMaxHealth());
        enemyHealthBar.setValue((int)enemy.getHealth());
    }

    private void messagePrompts(){
        if(enemy.isDefeated()){
            frame.dispose();
        } else if(player.isDead()){
            frame.dispose();
            MapGUI.getCurrentInstance().dispose();
            View.titleScreen();
        }
    }

    private void handlePlayerTurn(int attackType, int actionType) {
        if(controller.playerTurn(enemy, attackType, actionType)){
            showDamagePopup();
        }
        repaint();
        messagePrompts();
    }
}
