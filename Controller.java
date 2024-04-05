import java.util.ArrayList;

public class Controller {
    private Model model;
    private static Player playerInstance = Model.getPlayer();

    public Controller(Model model){
        this.model = model;
    }

    public static Player getPlayerInstance(){
        return playerInstance;
    }

    //title screen methods
    public void exit(){
        System.exit(0);
    }

    //create character methods
    public void createCharacter(){
        new CharacterCreation(model.getJobClasses(), View.getController());
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
        new GameLobby(this);
    }

    public void fastTravel(){
        new FastTravel(this);
    }

    public int getUnlockedAreas(){
        return model.getUnlockedAreas();
    }

    public ArrayList<String> getUnlockedFastTravelTiles(){
        return model.getUnlockedFastTravelTiles();
    }

    public void levelUpMenu(){
        new LevelUp(playerInstance, this);
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
        }
    }

    public void inventory(){
        new Inventory(playerInstance);
    }

    public void shop(){
        new Shop(playerInstance, model.getShopInventory(), this);
    }

    public void buyWeapon(int i){
        Weapons weapon = model.getShopInventory().get(i);
        model.addWeapon(weapon);
        Model.getPlayer().subtractRuneCount(weapon.getPrice());
    }

    //battle methods

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

    public void enemyTurn(Enemy enemy){
        playerInstance.subtractMaxHealth(enemy.getAttack());
    }
}
