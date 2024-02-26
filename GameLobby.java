import java.util.*;

public class GameLobby {

    private static String[] gameSelectorOptions = {"Fast Travel", "Level Up", "Inventory", "Shop", "Exit Game"};
    private static ArrayList<String> unlockedMapOptions = new ArrayList<String>();
    private static String[] mapOptions = {"Stormveil Castle", "Raya Lucaria Academy", "The Elden Throne"};

    public static void GameSelector(){
        ConsoleMethods.refreshScreen();
        unlockedMapOptions.add(mapOptions[0]);
    
        Scanner sc = new Scanner(System.in);
    
        while (true) {
            System.out.print("Roundtable Hold\n");
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
                    stormveilCastle.play();
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
        sc.close();
    }
}
