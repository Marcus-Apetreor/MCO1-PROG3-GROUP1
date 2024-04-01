import javax.swing.event.*;
import javax.swing.*;
import java.awt.*;

public class View extends JFrame {
    protected static Controller controller;

    public View(String title) {
        super("Elden Rogue" + " - " + title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
    }
    
    public View(Controller c) {
        controller = c;
        TitleScreen titleScreen = new TitleScreen(controller);
    }

    public static Controller getController(){
        return controller;
    }
}