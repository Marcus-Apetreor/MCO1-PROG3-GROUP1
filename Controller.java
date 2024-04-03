import java.util.ArrayList;

public class Controller {
    private Model model;
    private Player playerInstance = Model.getPlayer();

    public Controller(Model model){
        this.model = model;
    }

    public Player getPlayerInstance(){
        return playerInstance;
    }

    //title screen methods
    public void exit(){
        System.exit(0);
    }

    //create character methods
    public void createCharacter(){
        CharacterCreation characterCreation = new CharacterCreation(model.getJobClasses(), View.getController());
    }

    public void setImagePath(String imagePath){
        playerInstance.setImagePath(imagePath);
    }

    public void inputUsername(String username){
        model.setPlayerName(username);
    }

    public void chooseJobClass(int i){
        model.chooseJobClass(i);
    }

    //lobby methods
    public void gameLobby(){
        GameLobby gameLobby = new GameLobby(this);
    }

    public void fastTravel(){
        FastTravel fastTravel = new FastTravel(this);
    }

    public int[] getBossFastTravel(){
        int[] coordinates = {model.getBossRoom(),model.getBossRow(),model.getBossCol()};
        return coordinates;
    }

    public int getUnlockedAreas(){
        return model.getUnlockedAreas();
    }

    public ArrayList<String> getUnlockedFastTravelTiles(){
        return model.getUnlockedFastTravelTiles();
    }

    public void levelUpMenu(){
        LevelUp levelUpMenu = new LevelUp(playerInstance, this);
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
        Shop shop = new Shop(playerInstance, model.getShopInventory(), this);
    }

    public void buyWeapon(int i){
        Weapons weapon = model.getShopInventory().get(i);
        model.addWeapon(weapon);
        Model.getPlayer().subtractRuneCount(weapon.getPrice());
    }

    //play game methods
    public void loadMap(){

    }
    
    public void loadLevels(){
        
    }

    public void chooseSpawnLocation(){

    }

        //print current room

    public void movePlayer(){

    }

    //battle methods

        //tile logic
}
