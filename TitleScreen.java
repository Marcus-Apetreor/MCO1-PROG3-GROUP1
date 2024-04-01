import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TitleScreen extends View {
    private Controller controller;
    public TitleScreen(Controller controller) {
        super("Title Screen");
        this.controller = controller;

        JPanel panel = new JPanel(new BorderLayout());

        // Create a label for the title image
        ImageIcon titleImage = new ImageIcon("title.png");
        JLabel titleLabel = new JLabel(titleImage);
        panel.add(titleLabel, BorderLayout.CENTER);

        // Create a panel for the buttons and align them in the bottom center
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton startButton = new JButton("Start");
        JButton exitButton = new JButton("Exit");

        // Add action listeners to the buttons
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.createCharacter();
                JOptionPane.showMessageDialog(null, "Starting the game...");
                dispose();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.exit();
                int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Confirm Exit", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    dispose();
                    System.exit(0);
                }
            }
        });

        // Add buttons to the button panel
        buttonPanel.add(startButton);
        buttonPanel.add(exitButton);

        // Add the button panel to the bottom of the main panel
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Add the main panel to the frame
        add(panel);
        setVisible(true);
    }
}
