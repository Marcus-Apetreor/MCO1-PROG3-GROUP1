import javax.swing.*;

/**
 * Represents a generic view in the application.
 * This class extends JFrame and provides methods to create and manage graphical user interfaces.
 * 
 * @author Marcus Apetreor, Vincent Vuelva
 */
public class View extends JFrame {
    protected static Controller controller;

    /**
     * Constructs a new View object with the specified title.
     *
     * @param title The title of the view.
     */
    public View(String title) {
        super("Elden Rogue" + " - " + title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
    }
    
    /**
     * Constructs a new View object with the specified controller.
     * This constructor sets the controller and displays the title screen.
     *
     * @param c The controller to associate with the view.
     */
    public View(Controller c) {
        controller = c;
        titleScreen();
    }

    /**
     * Displays the title screen.
     */
    public static void titleScreen(){
        new TitleScreen(controller);
    }
}
