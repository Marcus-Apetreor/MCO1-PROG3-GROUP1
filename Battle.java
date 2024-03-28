import java.util.Random;

public class Battle {
    public static double maxHealth(int playerVig, int weaponVig){
        return 100*((playerVig*weaponVig)/2);
    }
    //maxHealth(playerInstance.getStats().getVigor(), playerInstance.getWeapon().getStats().getVigor());
    
    public static double dodgeRate(int playerEnd, int weaponEnd){
        return (20+((playerEnd+weaponEnd)/2))/100;
    }
    //dodgeRate(playerInstance.getStats().getEndurance(), playerInstance.getWeapon().getStats().getEndurance());
    
    public static void physicalDamage(double playerPhysicalDamage, Enemy enemy){
        double damage=(playerPhysicalDamage) * (1-enemy.getPhysicalDefense());
        enemy.setHealth(enemy.getHealth()-(int)damage);
    }
    //physicalDamage(playerInstance.getStats().getStrength(), playerInstance.getWeapon().getStats().getStrength(), enemy);
    
    public static void sorceryDamage(double playerSorceryDamage, Enemy enemy){
        double damage=(playerSorceryDamage) * (1-enemy.getSorceryDefense());
        enemy.setHealth(enemy.getHealth()-(int)damage);
    }
    //sorceryDamage(playerInstance.getStats().getIntelligence(), playerInstance.getWeapon().getStats().getIntelligence(), enemy);

    public static void incantationDamage(double playerIncantationDamage, Enemy enemy){
        double damage=(playerIncantationDamage) * (1-enemy.getIncantationDefense());
        enemy.setHealth(enemy.getHealth()-(int)damage);
    }
    //incantationDamage(playerInstance.getStats().getFaith(), playerInstance.getWeapon().getStats().getFaith(), enemy);

    public static boolean calculateDodge(double chance) {
        double randomValue = new Random().nextDouble() * 100;

        if (randomValue < chance) {
            return true; // Dodge successful
        } else {
            return false; // Dodge unsuccessful
        }
    }
}
