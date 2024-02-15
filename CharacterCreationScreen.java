import java.util.*;

public class CharacterCreationScreen {

    private static String[] characterCreationOptions = {"Name", "Select Job Class", "Confirm", "Back"};
    private static String[] jobClasses = {"Vagabond", "Samurai", "Warrior", "Hero", "Astrologer", "Prophet"};
    public static void characterCreation(){
        ConsoleMethods.refreshScreen();
    
        Scanner sc = new Scanner(System.in);
    
        Player playerInstance = new Player();
    
        while (true) {
            System.out.println("Character Creation Menu\n");
            ConsoleMethods.printOptions(characterCreationOptions);
    
            String userInput = sc.nextLine();
    
            ConsoleMethods.arrowSelector(userInput, 4);
    
            if (ConsoleMethods.optionCondition(0, userInput)) {
                System.out.println("Input username [max of 25 characters] :");
                String playerInstanceName = sc.nextLine();
                if (playerInstanceName.length() > 25) {
                    playerInstanceName = playerInstanceName.substring(0, 25);
                }
                playerInstance.setPlayerName(playerInstanceName);
    
            } else if (ConsoleMethods.optionCondition(1, userInput)) {
                ConsoleMethods.refreshScreen();
                while (true){
                System.out.println("Job Classes");
                ConsoleMethods.printOptions(jobClasses);
                
                String jobClassInput = sc.nextLine();
                
                ConsoleMethods.arrowSelector(jobClassInput, 6);
                
                if(ConsoleMethods.optionCondition(0, jobClassInput)) {
                    playerInstance.setJobClass(jobClasses[0]);
                    break;
                }
                else if(ConsoleMethods.optionCondition(1, jobClassInput)) {
                    playerInstance.setJobClass(jobClasses[1]);
                    break;
                }
                else if(ConsoleMethods.optionCondition(2, jobClassInput)) {
                    playerInstance.setJobClass(jobClasses[2]);
                    break;
                }
                else if(ConsoleMethods.optionCondition(3, jobClassInput)) {
                    playerInstance.setJobClass(jobClasses[3]);
                    break;
                }
                else if(ConsoleMethods.optionCondition(4, jobClassInput)) {
                    playerInstance.setJobClass(jobClasses[4]);
                    break;
                }
                else if(ConsoleMethods.optionCondition(5, jobClassInput)) {
                    playerInstance.setJobClass(jobClasses[5]);
                    break;
                } ConsoleMethods.clearConsole();
                }
                ConsoleMethods.resetSelectedOption();
    
            } else if (ConsoleMethods.optionCondition(2, userInput)) {
                playerInstance.playerCard();
                //print jobclass and stats and etc..
                System.out.println("Travelling to Stormveil Castle...");
                //put logic for fasttravel to stormveil castle
                break;
    
            } else if (ConsoleMethods.optionCondition(3, userInput)) {
                System.out.println("Going back to main menu...");
                playerInstance = null;
                TitleScreen.main(characterCreationOptions);
                break;
            }
        ConsoleMethods.clearConsole();
        }
        sc.close();
    }
    
}