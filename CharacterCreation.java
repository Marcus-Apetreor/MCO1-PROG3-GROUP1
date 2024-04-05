import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * The CharacterCreation class represents the graphical interface for character creation.
 * Players can choose their character's name, class, and view their character's attributes.
 * It inherits from the View class.
 * 
 * Inherits from the {@link View} class.
 * @author Marcus Apetreor, Vincent Vuelva
 */
public class CharacterCreation extends View {
    private JLabel characterNameLabel;
    private JLabel statsLabel;
    private JTextField usernameField;
    private JButton leftButton;
    private JButton rightButton;
    private JButton confirmButton;
    private JLabel characterSpriteLabel;
    private ImageIcon characterSprite[] = {
        new ImageIcon("vagabond.png"),
        new ImageIcon("samurai.png"),
        new ImageIcon("warrior.png"),
        new ImageIcon("hero.png"),
        new ImageIcon("astrologer.png"),
        new ImageIcon("prophet.png")
    };
    private Player playerInstance;

    private JobClass[] jobClasses;
    private int currentIndex;

    /**
     * Constructs a CharacterCreation object with specified job classes and a controller.
     * 
     * @param jobClasses An array of JobClass representing available character classes.
     * @param controller The controller managing game logic.
     */
    public CharacterCreation(JobClass[] jobClasses, Controller controller) {
        super("Character Creation Menu");
        this.jobClasses = jobClasses;
        this.playerInstance = Controller.getPlayerInstance();
        this.currentIndex = 0;

        JPanel panel = new JPanel(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout());
        characterNameLabel = new JLabel();
        topPanel.add(characterNameLabel);

        JPanel centerPanel = new JPanel(new FlowLayout());
        leftButton = new JButton("<");
        rightButton = new JButton(">");
        characterSpriteLabel = new JLabel();
        centerPanel.add(leftButton);
        centerPanel.add(characterSpriteLabel);
        centerPanel.add(rightButton);

        JPanel bottomPanel = new JPanel(new FlowLayout());
        confirmButton = new JButton("Confirm");
        usernameField = new JTextField(20);
        bottomPanel.add(new JLabel("Username:"));
        bottomPanel.add(usernameField);
        bottomPanel.add(confirmButton);

        statsLabel = new JLabel();
        updateCharacter();

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(centerPanel, BorderLayout.CENTER);
        panel.add(statsLabel, BorderLayout.EAST);
        panel.add(bottomPanel, BorderLayout.SOUTH);

        add(panel);
        setVisible(true);

        leftButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                currentIndex = (currentIndex - 1 + jobClasses.length) % jobClasses.length;
                updateCharacter();
            }
        });

        rightButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                currentIndex = (currentIndex + 1) % jobClasses.length;
                updateCharacter();
            }
        });

        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(usernameField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter a username.");
                    return;
                }
                controller.inputUsername(usernameField.getText());
                controller.chooseJobClass(currentIndex);
                controller.setImagePath(characterSprite[currentIndex].toString());
                JOptionPane.showMessageDialog(null, playerInstance.getPlayerName() + ", " + "the " + playerInstance.getJobClass().getJobName() + " has been created!");
                controller.gameLobby();
                dispose();        
            }
        });
    }

    /**
     * Updates the character's information based on the selected job class.
     */
    private void updateCharacter() {
        JobClass currentJob = jobClasses[currentIndex];
        characterNameLabel.setText(currentJob.getJobName() + " (Level " + currentJob.getJobLevel() + ")");
        characterSpriteLabel.setIcon(characterSprite[currentIndex]);
        statsLabel.setText("<html><b>Level:</b> " + currentJob.getJobLevel() + "<br/>" +
                "<b>Vigor:</b> " + currentJob.getJobStats().getVigor() + "<br/>" +
                "<b>Endurance:</b> " + currentJob.getJobStats().getEndurance() + "<br/>" +
                "<b>Dexterity:</b> " + currentJob.getJobStats().getDexterity() + "<br/>" +
                "<b>Strength:</b> " + currentJob.getJobStats().getStrength() + "<br/>" +
                "<b>Intelligence:</b> " + currentJob.getJobStats().getIntelligence() + "<br/>" +
                "<b>Faith:</b> " + currentJob.getJobStats().getFaith() + "</html>");
    }

    /**
     * Sets the username in the username field.
     * 
     * @param username The username to set.
     */
    public void setUsername(String username) {
        usernameField.setText(username);
    }

    /**
     * Retrieves the username from the username field.
     * 
     * @return The username entered by the user.
     */
    public String getUsername() {
        return usernameField.getText();
    }

    /**
     * Retrieves the index of the selected job class.
     * 
     * @return The index of the selected job class.
     */
    public int getSelectedJobIndex() {
        return currentIndex;
    }
}
