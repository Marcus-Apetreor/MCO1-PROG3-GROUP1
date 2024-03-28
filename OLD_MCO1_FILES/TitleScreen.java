package OLD_MCO1_FILES;
import java.util.*;

import ConsoleMethods;

/**
 * Class representing the title screen of the game.
 * Allows players to start the game or exit.
 * 
 * Authors: Marcus Apetreor, Vincent Vuelva
 */
public class TitleScreen {

    // Options available on the title screen
    private static String[] titleOptions = {"Start", "Exit"};

    /**
     * Main method representing the title screen.
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        ConsoleMethods.refreshScreen();
        Scanner sc = new Scanner(System.in);

        // Display title screen until the player chooses to start or exit
        while (true) {
            printTitle(); // Print the title in ASCII art
            ConsoleMethods.printOptions(titleOptions); // Print title options

            String userInput = sc.nextLine();

            ConsoleMethods.arrowSelector(userInput, 2); // Select option based on user input

            // Start the game if the player chooses to start
            if (ConsoleMethods.optionCondition(0,userInput)) {
                System.out.println("Starting the game...");
                CharacterCreationScreen.characterCreation(sc); // Proceed to character creation
                break;
            } 
            // Exit the game if the player chooses to exit
            else if (ConsoleMethods.optionCondition(1,userInput)) {
                System.out.println("Exiting the game...");
                break;
            }
        }
        sc.close(); // Close the scanner
    }

    /**
     * Helper method to print the title in ASCII art.
     */
    private final static void printTitle() {
        ConsoleMethods.clearConsole();
        System.out.println("'||''''| '||`     ||`                    '||'''|,                        '||''''| ");
        System.out.println(" ||   .   ||      ||                      ||   ||                         ||   .  ");
        System.out.println(" ||'''|   ||  .|''||  .|''|, `||''|,      ||...|' .|''|, .|''|, '||  ||`  ||'''|  ");
        System.out.println(" ||       ||  ||  ||  ||..||  ||  ||      || \\\\   ||  || ||  ||  ||  ||   ||      ");
        System.out.println(".||....| .||. `|..||. `|...  .||  ||.    .||  \\\\. `|..|' `|..||  `|..'|. .||....| ");
        System.out.println("                                                             ||                   ");
        System.out.println("                                                          `..|'                   ");
        System.out.println();
        System.out.println("<>-<> <>-<> <>-<> ");
        System.out.println();
    }

    public void show() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'show'");
    }
}
