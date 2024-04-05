/**
 * 
 * Main method to instantiate main components.
 */
public class Main {
    public static void main(String[] args){
        Model model = new Model();
        Controller controller = new Controller(model);
        new View(controller);
    }
}