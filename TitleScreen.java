import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TitleScreen extends View {
    public TitleScreen(Controller controller) {
        super("Title Screen");

        JPanel panel = new JPanel(new BorderLayout());

        // Get screen size
        Dimension windowSize = getSize();
        double width = windowSize.getWidth();
        double height = windowSize.getHeight();

        // Create a label for the title image
        ImageIcon titleImage = new ImageIcon("title.png");

        // Scale the image to fit the screen
        Image scaledImage = titleImage.getImage().getScaledInstance((int) width, (int) height, Image.SCALE_DEFAULT);
        titleImage = new ImageIcon(scaledImage);

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
                dispose();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.exit();
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
