import java.awt.FlowLayout;
import java.awt.*;
import javax.swing.*;

public class TitleScreen extends JFrame {

    public TitleScreen() {
        JFrame frame = new JFrame();
        frame.setTitle("Elden Rogue");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
        panel.setBackground(Color.BLACK);

        JButton startButton = new JButton();
        startButton.setText("Start");
        panel.add(startButton);

        JButton endButton = new JButton();
        endButton.setText("End");
        panel.add(endButton);

        frame.add(panel,BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}