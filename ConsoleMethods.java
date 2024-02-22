public class ConsoleMethods {

    private static int selectedOption=0;
    
    public static void printOptions(String[] options){
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

    public static void helpfulPrompt(){
        System.out.println("\n<Input [w] to scroll Up, [s] to scroll Down> \n<Input [Space] To Select> \n");
    }

    public static void clearConsole() {
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }

    public static void arrowSelector(String userInput, int maxChoices) {
        maxChoices=maxChoices-1;
        if (userInput.equalsIgnoreCase("w")){
            selectedOption = (selectedOption - 1);
            if (selectedOption < 0){
                selectedOption = maxChoices;
            }
        } else if (userInput.equalsIgnoreCase("s")) {
            selectedOption = (selectedOption + 1);
            if (selectedOption > maxChoices){
                selectedOption = 0;
            }
        }
        ConsoleMethods.clearConsole();
    }

    public static boolean optionCondition(int choiceNumber, String userInput) {
        if(selectedOption == choiceNumber && userInput.equalsIgnoreCase(" ")){
            return true;
        } else {
            return false;
        }
    }

    public static boolean confirmation(String userInput) {
        if (userInput.equalsIgnoreCase(" ")){
            return true;
        } else {
            return false;
        }
    }

    public static void resetSelectedOption() {
        selectedOption = 0;
    }

    public static void refreshScreen() {
        resetSelectedOption();
        clearConsole();
    }

    public static int getSelectedOption() {
        return selectedOption;
    }
}
