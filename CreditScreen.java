import javax.swing.*;
import java.awt.*;

/**
 * The CreditScreen class displays the credits for the game.
 * It inherits from the View class.
 * 
 * Inherits from the {@link View} class.
 * @author Marcus Apetreor
 */
public class CreditScreen extends View {
    /**
     * Constructs a CreditScreen object.
     */
    public CreditScreen() {
        super("Credits");

        JPanel panel = new JPanel(new BorderLayout());

        // Get screen size
        Dimension windowSize = getSize();
        double width = windowSize.getWidth();
        double height = windowSize.getHeight();

        // Create a label for the credit image
        ImageIcon creditImage = new ImageIcon("credits.png");

        // Scale the image to fit the screen
        Image scaledImage = creditImage.getImage().getScaledInstance((int) width, (int) height, Image.SCALE_DEFAULT);
        creditImage = new ImageIcon(scaledImage);

        JLabel creditLabel = new JLabel(creditImage);
        panel.add(creditLabel, BorderLayout.CENTER);

        // Add the main panel to the frame
        add(panel);
        setVisible(true);
    }
}
