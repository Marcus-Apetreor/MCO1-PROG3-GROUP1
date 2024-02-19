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
                    playerInstance.setStats(15,11,13,14,9,9);
                    playerInstance.setPlayerLevel(9);
                    break;
                }
                else if(ConsoleMethods.optionCondition(1, jobClassInput)) {
                    playerInstance.setJobClass(jobClasses[1]);
                    playerInstance.setStats(12,13,15,12,9,8);
                    playerInstance.setPlayerLevel(9);
                    break;
                }
                else if(ConsoleMethods.optionCondition(2, jobClassInput)) {
                    playerInstance.setJobClass(jobClasses[2]);
                    playerInstance.setStats(11,11,16,10,10,8);
                    playerInstance.setPlayerLevel(8);
                    break;
                }
                else if(ConsoleMethods.optionCondition(3, jobClassInput)) {
                    playerInstance.setJobClass(jobClasses[3]);
                    playerInstance.setStats(14,12,9,16,7,8);
                    playerInstance.setPlayerLevel(7);
                    break;
                }
                else if(ConsoleMethods.optionCondition(4, jobClassInput)) {
                    playerInstance.setJobClass(jobClasses[4]);
                    playerInstance.setStats(9,9,12,8,16,7);
                    playerInstance.setPlayerLevel(6);
                    break;
                }
                else if(ConsoleMethods.optionCondition(5, jobClassInput)) {
                    playerInstance.setJobClass(jobClasses[5]);
                    playerInstance.setStats(10,8,10,11,7,16);
                    playerInstance.setPlayerLevel(7);
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