/**
 * Helper class that provides support for option selectors and other console-specific methods for different menus.
 * It includes methods for printing options, handling option conditions, and managing the console screen.
 * 
 * @author Marcus Apetreor, Vincent Vuelva
 */
public class ConsoleMethods {

    // Selector and option methods
    private static int selectedOption = 0;

    /**
     * Prints an array of options with a selector on the currently selected option.
     * 
     * @param options An array of options to be printed.
     */
    public static void printOptions(String[] options) {
        for (int i = 0; i < options.length; i++) {
            if (i == selectedOption) {
                System.out.print(">");
            } else {
                System.out.print(" ");
            }

            // Print the option
            System.out.println(" " + options[i]);
        }
        helpfulPrompt();
    }

    /**
     * Prints an array of options with a selector on the currently selected option. There are locked options which may be unlocked if the passed "locked" value is lessened.
     * 
     * @param options An array of options to be printed.
     * @param locked The ammount of locked options.
     */
    public static void printOptionsLocked(String[] options, int locked) {
        for (int i = 0; i < options.length; i++) {
            if (i == selectedOption) {
                System.out.print(">");
            } else {
                System.out.print(" ");
            }
    
            // Print the option
            if (i >= options.length - locked) {
                System.out.println(" " + options[i] + " [LOCKED]");
            } else {
                System.out.println(" " + options[i]);
            }
        }
        helpfulPrompt();
    }    

    /**
     * Checks if a specific option has been selected by the user.
     * 
     * @param choiceNumber The number of the choice that the user is currently on.
     * @param userInput    User input to check if the player has inputted "space".
     * @return             True if the specific option has been selected by the user, false otherwise.
     */
    public static boolean optionCondition(int choiceNumber, String userInput) {
        return selectedOption == choiceNumber && userInput.equalsIgnoreCase(" ");
    }

    /**
     * Handles user input to scroll the selector up or down.
     * 
     * @param userInput   User input ("w" or "s") to scroll the selector.
     * @param maxChoices The maximum number of choices to determine the last option.
     */
    public static void arrowSelector(String userInput, int maxChoices) {
        maxChoices = maxChoices - 1;
        if (userInput.equalsIgnoreCase("w")) {
            selectedOption = (selectedOption - 1);
            if (selectedOption < 0) {
                selectedOption = maxChoices;
            }
        } else if (userInput.equalsIgnoreCase("s")) {
            selectedOption = (selectedOption + 1);
            if (selectedOption > maxChoices) {
                selectedOption = 0;
            }
        }
        clearConsole();
    }

    /**
     * Resets the selected option to 0.
     * Mostly used so that when another menu is opened, the selector starts from the topmost option.
     */
    public static void resetSelectedOption() {
        selectedOption = 0;
    }

    /**
     * Clears the console and resets the selected option.
     */
    public static void refreshScreen() {
        resetSelectedOption();
        clearConsole();
    }

    /**
     * Clears the console screen.
     */
    public static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Displays a loading screen with three dots.
     */
    public static void loadingScreen() {
        for (int i = 1; i <= 3; i++) {
            System.out.print(".");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        clearConsole();
    }

    /**
     * Displays instructions for navigating the menu.
     */
    public static void helpfulPrompt() {
        System.out.println("\n<Input [w] to scroll Up, [s] to scroll Down> \n<Input [Space] To Select> \n");
    }
}
