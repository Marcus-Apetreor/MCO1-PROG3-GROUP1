import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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

    public void setUsername(String username) {
        usernameField.setText(username);
    }

    public String getUsername() {
        return usernameField.getText();
    }

    public int getSelectedJobIndex() {
        return currentIndex;
    }
}