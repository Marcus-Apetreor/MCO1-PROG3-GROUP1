import java.util.*;

public class Shop {
    public boolean isPurchaseable(Weapons weapon, int runeCount){
        int price = weapon.getPrice();
        if(price<=runeCount){
            return true;
        } else {
            return false;
        }
    }
    public void purchaseWeapon(int i, ArrayList<Weapons> shopInventory, Player playerInstance){
        Weapons weapon = shopInventory.get(i);
        if(isPurchaseable(weapon, playerInstance.getRuneCount())){
            playerInstance.addWeapon(weapon);
            playerInstance.setRuneCount(playerInstance.getRuneCount()-weapon.getPrice());
        }
    }
}
