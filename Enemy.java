import java.util.Random;

/**
 * Enemy class to create enemy instances with specific attributes.
 * Each enemy has a type, name, health, attack, and defense values.
 * It also handles enemy spawning and initialization of enemy stats.
 * 
 * @author Marcus Apetreor, Vincent Vuelva
 */
public class Enemy {
    public enum EnemyType {
        TYPE1, TYPE2, TYPE3
    }
    
    protected EnemyType type;
    protected String name;
    protected double health;
    protected int attack;
    protected double physicalDefense;
    protected double sorceryDefense;
    protected double incantationDefense;
    protected boolean isDefeated;

    private static final String[][] enemyNames = {{"Godrick Soldier", "Living Jar", ""}, 
                                                  {"Godrick Archer", "Glintstone Sorcerer", ""}, 
                                                  {"Godrick Knight", "Battlemage", ""}};
    private static final int[][] healthRanges = {{20, 30}, {25, 35}, {70, 80}};
    private static final int[][] attackRanges = {{70, 80}, {110, 120}, {120, 130}};
    private static final double[][] defenseRanges = {
            {0.20, 0.50, 0.25}, // Physical Defense
            {0.15, 0.15, 0.25}, // Sorcery Defense
            {0.10, 0.20, 0.20}  // Incantation Defense
    };

    private static final Random random = new Random();

    /**
     * Constructs an enemy instance with a specified type and area index.
     * 
     * @param type      The type of the enemy.
     * @param areaIndex The index of the area where the enemy is spawned.
     */
    public Enemy(EnemyType type, int areaIndex) {
        this.type = type;
        initializeStats(areaIndex);
        this.isDefeated = false;
    }

    /**
     * Initializes the stats of the enemy based on the area index.
     * 
     * @param areaIndex The index of the area where the enemy is spawned.
     */
    private void initializeStats(int areaIndex) {
        int typeIndex = type.ordinal();
        name = enemyNames[typeIndex][areaIndex - 1];
        health = randomValue(healthRanges[typeIndex]) * areaIndex;
        attack = randomValue(attackRanges[typeIndex]) * areaIndex;
        physicalDefense = defenseRanges[0][typeIndex];
        sorceryDefense = defenseRanges[1][typeIndex];
        incantationDefense = defenseRanges[2][typeIndex];
    }

    /**
     * Generates a random value within the specified range.
     * 
     * @param range An array representing the range of values.
     * @return      A random integer within the specified range.
     */
    private int randomValue(int[] range) {
        return random.nextInt(range[1] - range[0] + 1) + range[0];
    }

    /**
     * Checks if the enemy is defeated.
     * 
     * @return True if the enemy is defeated, false otherwise.
     */
    public boolean isDefeated() {
        return isDefeated;
    }

    /**
     * Marks the enemy as defeated.
     */
    public void defeat() {
        isDefeated = true;
    }

    /**
     * Get the type of the enemy.
     * 
     * @return The type of the enemy.
     */
    public EnemyType getType() {
        return type;
    }

    /**
     * Set the type of the enemy.
     * 
     * @param type The type of the enemy.
     */
    public void setType(EnemyType type) {
        this.type = type;
    }

    /**
     * Get the name of the enemy.
     * 
     * @return The name of the enemy.
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the enemy.
     * 
     * @param name The name of the enemy.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the health of the enemy.
     * 
     * @return The health of the enemy.
     */
    public double getHealth() {
        return health;
    }

    /**
     * Set the health of the enemy.
     * 
     * @param d The health of the enemy.
     */
    public void setHealth(double d) {
        this.health = d;
        if (this.health <= 0) {
            defeat();
        }
    }

    /**
     * Get the attack power of the enemy.
     * 
     * @return The attack power of the enemy.
     */
    public int getAttack() {
        return attack;
    }

    /**
     * Set the attack power of the enemy.
     * 
     * @param attack The attack power of the enemy.
     */
    public void setAttack(int attack){
        this.attack = attack;
    }

    /**
     * Get the physical defense of the enemy.
     * 
     * @return The physical defense of the enemy.
     */
    public double getPhysicalDefense() {
        return physicalDefense;
    }

    /**
     * Set the physical defense of the enemy.
     * 
     * @param physicalDefense The physical defense of the enemy.
     */
    public void setPhysicalDefense(double physicalDefense){
        this.physicalDefense = physicalDefense;
    }

    /**
     * Get the sorcery defense of the enemy.
     * 
     * @return The sorcery defense of the enemy.
     */
    public double getSorceryDefense() {
        return sorceryDefense;
    }

    /**
     * Set the sorcery defense of the enemy.
     * 
     * @param sorceryDefense The sorcery defense of the enemy.
     */
    public void setSorceryDefense(double sorceryDefense){
        this.sorceryDefense = sorceryDefense;
    }

    /**
     * Get the incantation defense of the enemy.
     * 
     * @return The incantation defense of the enemy.
     */
    public double getIncantationDefense() {
        return incantationDefense;
    }

    /**
     * Set the incantation defense of the enemy.
     * 
     * @param incantationDefense The incantation defense of the enemy.
     */
    public void setIncantationDefense(double incantationDefense){
        this.incantationDefense = incantationDefense;
    }

    /**
     * Spawns a enemy specific to the area index.
     * 
     * @param areaIndex The index of the area where the boss is spawned.
     * @return An Enemy object representing the spawned enemy.
     */
    public static Enemy spawnEnemy(int areaIndex) {
        int randomType = random.nextInt(3) + 1;
        EnemyType type;

        switch (randomType) {
            case 1:
                type = EnemyType.TYPE1;
                break;
            case 2:
                type = EnemyType.TYPE2;
                break;
            case 3:
                type = EnemyType.TYPE3;
                break;
            default :
                type = EnemyType.TYPE1;
                break;
        }

        return new Enemy(type, areaIndex);
    }
}
