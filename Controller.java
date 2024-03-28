import OLD_MAP_FILES.MapController;

public class Controller {
    private Model model;
    private View view;
    private MapController mapController;

    public Controller(Model model, View view){
        this.model = model;
        this.view = view;
    }

    //title screen methods
    public void mainMenu(){
        view.mainMenu();
        switch(view.getSelectedIndex()){
            case 0:
                createCharacter();
                break;
            case 1:
                exit();
                break;
            default:
                view.error();
                mainMenu();
        }
    }

    public void exit(){
        System.exit(0);
    }

    //create character methods
    public void createCharacter(){
        view.characterCreation();
        switch(view.getSelectedIndex()){
            case 0:
                inputUsername();
                break;
            case 1:
                chooseJobClass();
                break;
            default:
                view.error();
                createCharacter();
        }
    }

    public void inputUsername(){
        model.setPlayerName(view.inputUsername());
    }

    public void chooseJobClass(){
        model.chooseJobClass(view.getSelectedIndex());
    }

    //lobby methods
    public void gameLobby(){
        view.gameLobby();
        switch(view.getSelectedIndex()){
            case 0:
                fastTravel();
                break;
            case 1:
                levelUp();
                break;
            case 2:
                inventory();
                break;
            case 3:
                shop();
                break;
            case 4:
                exitToTitle();
                break;
            default:
                view.error();
                gameLobby();
        }
    }

    public void fastTravel(){

    }

    public void levelUp(){

    }

    public void inventory(){

    }

    public void shop(){

    }

    public void exitToTitle(){

    }

    //play game methods
    public void loadMap(){

    }
    
    public void loadLevels(){
        
    }

    public void chooseSpawnLocation(){
        mapController.chooseSpawnLocation();
    }

        //print current room

    public void movePlayer(){

    }

    //battle methods

        //tile logic
}
