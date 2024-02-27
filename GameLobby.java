import java.util.*;

/**
 * Class to manage the Game Lobby menu and its functionalities.
 * The Game Lobby allows players to access various in-game features such as fast travel, leveling up, inventory, and more.
 * 
 * @author Marcus Apetreor, Vincent Vuelva
 */
public class GameLobby {

    private static String[] gameSelectorOptions = {"Fast Travel", "Level Up", "Inventory", "Shop", "Exit Game"};
    private static String[] mapOptions = {"Stormveil Castle", "Raya Lucaria Academy", "The Elden Throne"};
    private static int lockedOptions = 2;

    /**
     * Facilitates the Game Lobby menu and its functionalities.
     * 
     * @param sc The scanner object to accept user input.
     */
    public static void GameSelector(Scanner sc){
        ConsoleMethods.refreshScreen();
        while (true) {
            CharacterCreationScreen.getPlayerInstance().playerCard();
            System.out.println("Roundtable Hold\n\n");
            ConsoleMethods.printOptions(gameSelectorOptions);
            String userInput = sc.nextLine();
            ConsoleMethods.arrowSelector(userInput, 5);
    
            if (ConsoleMethods.optionCondition(0, userInput)) {
                ConsoleMethods.refreshScreen();
                while (true){
                    System.out.println("Floors:");
                    ConsoleMethods.printOptionsLocked(mapOptions, lockedOptions);
                    
                    String locationInput = sc.nextLine();
                    
                    ConsoleMethods.arrowSelector(locationInput, 3-lockedOptions);
                    
                    if(ConsoleMethods.optionCondition(0, locationInput)) {
                        System.err.println("Fast Travelling to Stormveil Castle");
                        ConsoleMethods.loadingScreen();
                        Maps stormveilCastle = new StormveilCastle();
                        stormveilCastle.chooseSpawnLocation(sc);
                        break;
                    }
                    else if(ConsoleMethods.optionCondition(1, locationInput)) {
                        System.out.println("Come back another time.");
                        break;
                    } else if(ConsoleMethods.optionCondition(2, locationInput)) {
                        System.out.println("Come back another time.");
                        break;
                    }
                }
                ConsoleMethods.refreshScreen();
            } else if (ConsoleMethods.optionCondition(1, userInput)) {
                CharacterCreationScreen.getPlayerInstance().levelUp(sc);
            } else if (ConsoleMethods.optionCondition(2, userInput)) {
                System.out.println("Feature not yet added.");
            } else if (ConsoleMethods.optionCondition(3, userInput)) {
                System.out.println("Feature not yet added.");
            } else if (ConsoleMethods.optionCondition(4, userInput)) {
                System.out.println("Going back to main menu...");
                TitleScreen.main(gameSelectorOptions);
                break;
            }
        }
    }
}
