import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameLobby extends View implements ActionListener {
    private JButton fastTravelBtn, levelUpBtn, inventoryBtn, shopBtn, exitBtn;
    private Player playerInstance;

    public GameLobby(Controller controller) {
        super("Roundtable Hold");
        this.playerInstance = controller.getPlayerInstance();

        JPanel panel = new JPanel(new BorderLayout()); // Use BorderLayout
        JLabel playerStats = new JLabel();
        playerStats.setText(playerInstance.printPlayer()+playerInstance.getStats().printStats());
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
