import java.util.Random;

import javax.swing.JOptionPane;

/**
 * The Boss class represents boss enemies players encounter in specific game areas.
 * Each boss has unique characteristics and stats.
 * It inherits from the Enemy class.
 * 
 * @author Marcus Apetreor
 */
public class Boss extends Enemy {

    // Arrays to store boss-specific attributes
    private int attackRanges[][] = { { 150, 300 }, { 200, 300 }, { 250, 500 } };
    private int healthValues[] = { 200, 400, 800 };
    private String bossNames[] = { "Godrick the Grafted", "Rennala, Queen of the Full Moon", "The Elden Beast" };
    private double defenseValues[][] = { { 0.35, 0.2, 0.15 }, { 0.15, 0.35, 0.25 }, { 0.25, 0.5, 0.4 } };
    private int areaIndex;

    /**
     * Constructs a Boss object with specified type and area index.
     * Initializes boss attributes based on the area index.
     * 
     * @param type The type of the boss.
     * @param areaIndex The index of the area where the boss is spawned.
     */
    public Boss(EnemyType type, int areaIndex) {
        super(type, areaIndex);
        this.areaIndex = areaIndex;
        initializeStats(areaIndex); // Initialize boss stats based on type and area
    }

    /**
     * Initializes boss stats based on the area index.
     * 
     * @param areaIndex The index of the area where the boss is spawned.
     */
    private void initializeStats(int areaIndex) {
        int bossAreaIndex = areaIndex - 1;
        name = bossNames[bossAreaIndex];
        health = healthValues[bossAreaIndex];
        physicalDefense = defenseValues[bossAreaIndex][0];
        sorceryDefense = defenseValues[bossAreaIndex][1];
        incantationDefense = defenseValues[bossAreaIndex][2];
    }

    /**
     * Generates a random value within the specified range.
     * 
     * @param range An array representing the range of values.
     * @return A random integer within the specified range.
     */
    private int randomValue(int[] range) {
        Random random = new Random();
        return random.nextInt(range[1] - range[0] + 1) + range[0];
    }
    
    
    /** 
     * Checks if the boss is defeated.
     * 
     * @return true if the boss is defeated, false otherwise.
     */
    public boolean isDefeated(){
        if(health <= 0){
            JOptionPane.showMessageDialog(null, "GREAT ENEMY FELLED");
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns the calculated ammount of runes dropped by the boss.
     * 
     * @return The ammount of runes dropped by boss.
     */
    public double dropRunes(){
        return healthValues[areaIndex-1] * 5;
    }

    /**
     * Gets the attack value of the boss.
     * 
     * @return The attack value of the boss.
     */
    public int getAttack() {
        return randomValue(attackRanges[areaIndex-1]);
    }

    /**
     * Spawns a boss specific to the area index.
     * 
     * @param areaIndex The index of the area where the boss is spawned.
     * @return A Boss object representing the spawned boss.
     */
    public static Boss spawnBoss(int areaIndex) {
        Enemy.EnemyType type;
        switch (areaIndex - 1) {
            case 1:
                type = EnemyType.TYPE1;
                break;
            case 2:
                type = EnemyType.TYPE2;
                break;
            case 3:
                type = EnemyType.TYPE3;
                break;
            default:
                type = EnemyType.TYPE1; // Default type if area index is invalid
                break;
        }
        return new Boss(type, areaIndex);
    }
}
