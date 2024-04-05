import java.util.ArrayList;

/**
 * The Controller class manages the flow of the game by coordinating interactions between the model and the views.
 * 
 * @author Marcus Apetreor, Vincent Vuelva
 */
public class Controller {
    private Model model;
    private static Player playerInstance = Model.getPlayer();

    /**
     * Constructs a Controller object with the specified model.
     * 
     * @param model The model representing the game's data and logic.
     */
    public Controller(Model model){
        this.model = model;
    }

    /**
     * Retrieves the player instance.
     * 
     * @return The player instance.
     */
    public static Player getPlayerInstance(){
        return playerInstance;
    }

    //title screen methods
    /**
     * Exits the game.
     */
    public void exit(){
        System.exit(0);
    }

    //create character methods
    /**
     * Initiates character creation.
     */
    public void createCharacter(){
        new CharacterCreation(model.getJobClasses(), this);
    }

    /**
     * Sets the image path for the player instance.
     * 
     * @param imagePath The path of the image to set.
     */
    public void setImagePath(String imagePath){
        playerInstance.setImagePath(imagePath);
    }

    /**
     * Sets the username for the player.
     * 
     * @param username The username to set.
     */
    public void inputUsername(String username){
        model.setPlayerName(username);
    }

    /**
     * Chooses the job class for the player.
     * 
     * @param i The index of the job class to choose.
     */
    public void chooseJobClass(int i){
        model.chooseJobClass(i);
    }

    //lobby methods
    /**
     * Enters the game lobby.
     */
    public void gameLobby(){
        new GameLobby(this);
    }

    /**
     * Initiates fast travel.
     */
    public void fastTravel(){
        new FastTravel(this);
    }

    /**
     * Retrieves the number of unlocked areas.
     * 
     * @return The number of unlocked areas.
     */
    public int getUnlockedAreas(){
        return model.getUnlockedAreas();
    }

    /**
     * Retrieves a list of unlocked fast travel tiles.
     * 
     * @return An ArrayList of unlocked fast travel tiles.
     */
    public ArrayList<String> getUnlockedFastTravelTiles(){
        return model.getUnlockedFastTravelTiles();
    }

    /**
     * Enters the level up menu.
     */
    public void levelUpMenu(){
        new LevelUp(playerInstance, this);
    }

    /**
     * Levels up the player.
     * 
     * @param i The level to increase to.
     */
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
        }
    }

    /**
     * Enters the inventory.
     */
    public void inventory(){
        new Inventory(playerInstance);
    }

    /**
     * Enters the shop.
     */
    public void shop(){
        new Shop(playerInstance, model.getShopInventory(), this);
    }

    /**
     * Buys a weapon from the shop.
     * 
     * @param i The index of the weapon to buy.
     */
    public void buyWeapon(int i){
        Weapons weapon = model.getShopInventory().get(i);
        model.addWeapon(weapon);
        Model.getPlayer().subtractRuneCount(weapon.getPrice());
    }

    //battle methods

    /**
     * Handles the player's turn during battle.
     * 
     * @param enemy The enemy the player is battling.
     * @param userInput The type of action chosen by the player.
     * @param attackInput The type of attack chosen by the player.
     * @return true if the player's turn is completed, false otherwise.
     */
    public boolean playerTurn(Enemy enemy, int userInput, int attackInput){
        //print incoming damage    

        //player turn
        //attack
        if (userInput == 1){
            //attack options
            if(attackInput == 1){
                model.physicalDamage(enemy);
            } else if (attackInput == 2){
                model.sorceryDamage(enemy);
            } else if (attackInput == 3){
                model.incantationDamage(enemy);
            }
        enemyTurn(enemy);
        return true;

        //dodge
        } else if (userInput == 2){
            if(model.calculateDodge(playerInstance.getDodgeRate())){
                return false;
            } else {
                enemyTurn(enemy);
                return true;
            }
        }
        return true;
    }

    /**
     * Handles the enemy's turn during battle.
     * 
     * @param enemy The enemy taking its turn.
     */
    public void enemyTurn(Enemy enemy){
        playerInstance.subtractMaxHealth(enemy.getAttack());
    }
}
