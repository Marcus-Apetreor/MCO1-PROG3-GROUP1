import java.util.*;

public class GameLobby {

    private static String[] gameSelectorOptions = {"Fast Travel", "Level Up", "Inventory", "Shop", "Exit Game"};
    private static ArrayList<String> unlockedMapOptions = new ArrayList<String>();
    private static String[] mapOptions = {"Stormveil Castle", "Raya Lucaria Academy", "The Elden Throne"};

    public static void GameSelector(){
        unlockedMapOptions.add(mapOptions[0]);
        ConsoleMethods.refreshScreen();
    
        Scanner sc = new Scanner(System.in);
    
        while (true) {
            System.out.println("Roundtable Hold\n");
            String userInput = sc.nextLine();
            ConsoleMethods.printOptions(gameSelectorOptions);
            ConsoleMethods.arrowSelector(userInput, 5);
    
            if (ConsoleMethods.optionCondition(0, userInput)) {
                ConsoleMethods.refreshScreen();
                while (true){
                System.out.println("Fast travel areas unlocked:");
                ConsoleMethods.printOptions(mapOptions);
                
                String jobClassInput = sc.nextLine();
                
                ConsoleMethods.arrowSelector(jobClassInput, 6);
                
                if(ConsoleMethods.optionCondition(0, jobClassInput)) {
                    System.err.println("Fast Travelling to Stormveil Castle");
                    ConsoleMethods.loadingScreen();
                    Maps stormveilCastle = new StormveilCastle();
                    stormveilCastle.play();
                    break;
                }
                else if(ConsoleMethods.optionCondition(1, jobClassInput)) {
                    if (unlockedMapOptions.contains(mapOptions[1])){
                    } else {
                        System.out.println("LOCKED, please complete " + gameSelectorOptions[0] + " first!");
                        break;
                    }
                    break;
                } else if(ConsoleMethods.optionCondition(2, jobClassInput)) {
                    if (unlockedMapOptions.contains(mapOptions[2])){
                    } else {
                        System.out.println("LOCKED, please complete " + gameSelectorOptions[1] + " first!");
                        break;
                    }
                    break;
                } ConsoleMethods.clearConsole();
                }
                ConsoleMethods.resetSelectedOption();
                ConsoleMethods.clearConsole();
            } else if (ConsoleMethods.optionCondition(1, userInput)) {
                CharacterCreationScreen.getPlayerInstance().levelUp();
                break;
            } else if (ConsoleMethods.optionCondition(2, userInput)) {
                System.out.println("Feature not yet added.");
                break;
            } else if (ConsoleMethods.optionCondition(3, userInput)) {
                System.out.println("Feature not yet added.");
                break;
            } else if (ConsoleMethods.optionCondition(4, userInput)) {
                ConsoleMethods.clearConsole();
                System.out.println("Going back to main menu...");
                TitleScreen.main(gameSelectorOptions);
                break;
            }
        }
        sc.close();
    }
}
