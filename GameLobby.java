import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameLobby extends View  implements ActionListener{
    private JButton fastTravelBtn, levelUpBtn, inventoryBtn, shopBtn, exitBtn;

    public GameLobby(){

        super("Roundtable Hold");

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

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

        panel.add(Box.createVerticalGlue());
        panel.add(fastTravelBtn);
        panel.add(Box.createVerticalStrut(10));
        panel.add(levelUpBtn);
        panel.add(Box.createVerticalStrut(10));
        panel.add(inventoryBtn);
        panel.add(Box.createVerticalStrut(10));
        panel.add(shopBtn);
        panel.add(Box.createVerticalStrut(10));
        panel.add(exitBtn);
        panel.add(Box.createVerticalGlue());

        add(panel);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == fastTravelBtn) {
            System.out.println("Fast Travel button clicked.");
            controller.fastTravel();
            dispose();
        } else if (e.getSource() == levelUpBtn) {
            System.out.println("Level Up button clicked.");
            controller.levelUpMenu();
            // Add your level up functionality here
        } else if (e.getSource() == inventoryBtn) {
            System.out.println("Inventory button clicked.");
            controller.inventory();
            // Add your inventory functionality here
        } else if (e.getSource() == shopBtn) {
            System.out.println("Shop button clicked.");
            controller.shop();
            // Add your shop functionality here
        } else if (e.getSource() == exitBtn) {
            System.out.println("Exit to Title Screen button clicked.");
            controller.exitToTitle();
            dispose();
            // Add your exit functionality here
        }
    }
}
