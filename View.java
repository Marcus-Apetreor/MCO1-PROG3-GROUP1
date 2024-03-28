import javax.swing.event.*;
import javax.swing.*;
import java.awt.*;

public class View extends JFrame {
    private JTextArea textArea;
    private JList<String> list;
    private int selectedIndex = -1; // Add this line
    private Controller controller;
    // private String[] mainMenuOptions = {"Start", "Exit"};
    // private String[] jobClassOptions = {"Vagabond", "Samurai", "Warrior", "Hero", "Astrologer", "Prophet"};
    // private String[] gameLobbyOptions = {"Fast Travel", "Level Up", "Inventory", "Shop", "Exit Game"};

    public void view(){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                TitleScreen titleScreen = new TitleScreen();
                titleScreen.show();
            }
        });
    }
}