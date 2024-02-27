import java.util.*;

/**
 * Class to manage the Game Lobby menu and its functionalities.
 * The Game Lobby allows players to access various in-game features such as fast travel, leveling up, inventory, and more.
 * 
 * @author Marcus Apetreor, Vincent Vuelva
 */
public class GameLobby {

    private static String[] gameSelectorOptions = {"Fast Travel", "Level Up", "Inventory", "Shop", "Exit Game"};
    private static ArrayList<String> unlockedMapOptions = new ArrayList<String>();
    private static String[] mapOptions = {"Stormveil Castle", "Raya Lucaria Academy", "The Elden Throne"};
    
    /**
     * Facilitates the Game Lobby menu and its functionalities.
     * 
     * @param sc The scanner object to accept user input.
     */
    public static void GameSelector(Scanner sc){
        ConsoleMethods.refreshScreen();
        unlockedMapOptions.add(mapOptions[0]);
        while (true) {
            System.out.print("Roundtable Hold\n\n");
            System.out.println("You have " + CharacterCreationScreen.getPlayerInstance().getRuneCount() + " Runes.");
            ConsoleMethods.printOptions(gameSelectorOptions);
            String userInput = sc.nextLine();
            ConsoleMethods.arrowSelector(userInput, 5);
    
            if (ConsoleMethods.optionCondition(0, userInput)) {
                ConsoleMethods.refreshScreen();
                while (true){
                    System.out.println("Fast travel areas unlocked:");
                    ConsoleMethods.printOptions(mapOptions);
                    
                    String locationInput = sc.nextLine();
                    
                    ConsoleMethods.arrowSelector(locationInput, 6);
                    
                    if(ConsoleMethods.optionCondition(0, locationInput)) {
                        System.err.println("Fast Travelling to Stormveil Castle");
                        ConsoleMethods.loadingScreen();
                        Maps stormveilCastle = new StormveilCastle();
                        stormveilCastle.play(sc);
                        break;
                    }
                    else if(ConsoleMethods.optionCondition(1, locationInput)) {
                        if (unlockedMapOptions.contains(mapOptions[1])){
                        } else {
                            System.out.println("LOCKED, please complete " + mapOptions[0] + " first!");
                            break;
                        }
                        break;
                    } else if(ConsoleMethods.optionCondition(2, locationInput)) {
                        if (unlockedMapOptions.contains(mapOptions[2])){
                        } else {
                            System.out.println("LOCKED, please complete " + mapOptions[1] + " first!");
                            break;
                        }
                        break;
                    }
                }
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
