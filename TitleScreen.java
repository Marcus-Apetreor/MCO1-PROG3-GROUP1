import java.util.*;

public class TitleScreen {

    private static String[] titleOptions = {"Start", "Exit"};

    public static void main(String[] args) {
        ConsoleMethods.refreshScreen();
        Scanner sc = new Scanner(System.in);

        while (true) {
            printTitle();
            ConsoleMethods.printOptions(titleOptions);

            String userInput = sc.nextLine();

            ConsoleMethods.arrowSelector(userInput, 2);

            if (ConsoleMethods.optionCondition(0,userInput)) {
                System.out.println("Starting the game...");
                CharacterCreationScreen.characterCreation();
                break;
            } else if (ConsoleMethods.optionCondition(1,userInput)) {
                System.out.println("Exiting the game...");
                break;
            }

            ConsoleMethods.clearConsole();
        }
        sc.close();
    }

    public static void printTitle() {
        System.out.println("'||''''| '||`     ||`                    '||'''|,                        '||''''| ");
        System.out.println(" ||   .   ||      ||                      ||   ||                         ||   .  ");
        System.out.println(" ||'''|   ||  .|''||  .|''|, `||''|,      ||...|' .|''|, .|''|, '||  ||`  ||'''|  ");
        System.out.println(" ||       ||  ||  ||  ||..||  ||  ||      || \\\\   ||  || ||  ||  ||  ||   ||      ");
        System.out.println(".||....| .||. `|..||. `|...  .||  ||.    .||  \\\\. `|..|' `|..||  `|..'|. .||....| ");
        System.out.println("                                                             ||                   ");
        System.out.println("                                                          `..|'                   ");
        System.out.println();
        System.out.println();
        System.out.println("<>-<> <>-<> <>-<> ");
        System.out.println();
    }
}
