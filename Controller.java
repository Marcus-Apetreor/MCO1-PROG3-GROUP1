import OLD_MAP_FILES.MapController;

public class Controller {
    private Model model;
    private MapController mapController;
    private Player playerInstance = Model.getPlayer();

    public Controller(Model model){
        this.model = model;
    }

    //title screen methods
    public void exit(){
        System.exit(0);
    }

    //create character methods
    public void createCharacter(){
        CharacterCreation characterCreation = new CharacterCreation(model.getJobClasses(), View.getController());
    }

    public void inputUsername(String username){
        model.setPlayerName(username);
    }

    public void chooseJobClass(int i){
        model.chooseJobClass(i);
    }

    //lobby methods
    public void gameLobby(){
        
    }

    public void fastTravel(){

    }

    public void levelUpMenu(){
        LevelUp levelUpMenu = new LevelUp(playerInstance, View.getController());
    }

    public void levelUp(int i){
        switch(i){
            case 1:
                model.levelUp(1);
                break;
            case 2:
                model.levelUp(2);
                break;
            case 3:
                model.levelUp(3);
                break;
            case 4:
                model.levelUp(4);
                break;
            case 5:
                model.levelUp(5);
                break;
            default:
                System.out.println("Invalid input");
                break;
        }
    }

    public void inventory(){
        Inventory inventory = new Inventory(playerInstance);
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
