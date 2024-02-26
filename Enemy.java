import java.util.Random;

public class Enemy {
    // enum for enemy types
    public enum Type {
        TYPE1, TYPE2, TYPE3
    }

    // enemy stats
    protected Type type;
    protected String name;
    protected int health;
    protected int attack;
    protected double physicalDefense;
    protected double sorceryDefense;
    protected double incantationDefense;
    protected boolean isDefeated;

    private static final String[][] enemyNames = {{"Godrick Soldier", "Living Jar"},{"Godrick Archer", "Glintstone Sorcerer"},{"Godrick Knight", "Battlemage"}};
    private static final int[][] healthRanges = {{20, 30}, {25, 35}, {70, 80}};
    private static final int[][] attackRanges = {{70, 80}, {110, 120}, {120, 130}};
    private static final double[][] defenseRanges = {
            {0.20, 0.50, 0.25}, // Physical Defense
            {0.15, 0.15, 0.25}, // Sorcery Defense
            {0.10, 0.20, 0.20}  // Incantation Defense
    };

    // random number generator
    private static final Random random = new Random();

    // Constructor
    public Enemy(Type type, String name, int areaIndex) {
        this.type = type;
        this.name = name;
        initializeStats(areaIndex);
        this.isDefeated = false;
    }

    // initialize enemy stats based on type and area index
    private void initializeStats(int areaIndex) {
        int typeIndex = type.ordinal();
        name = (enemyNames[typeIndex][areaIndex-1]);
        health = randomValue(healthRanges[typeIndex]) * areaIndex;
        attack = randomValue(attackRanges[typeIndex]) * areaIndex;
        physicalDefense = defenseRanges[0][typeIndex];
        sorceryDefense = defenseRanges[1][typeIndex];
        incantationDefense = defenseRanges[2][typeIndex];
    }

    // helper method to get a random value within a given range
    private int randomValue(int[] range) {
        return random.nextInt(range[1] - range[0] + 1) + range[0];
    }

    // methods to interact with the enemy
    public boolean isDefeated() {
        return isDefeated;
    }

    public void defeat() {
        isDefeated = true;
    }

    // type setter
    public Type getType() {
        return type;
    }
    public void setType(Type type) {
        this.type=type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name=name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
        if (this.health <= 0) {
            defeat();
        }
    }


    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack){
        this.attack=attack;
    }

    public double getPhysicalDefense() {
        return physicalDefense;
    }
    
    public void setPhysicalDefense(double physicalDefense){
        this.physicalDefense=physicalDefense;
    }

    public double getSorceryDefense() {
        return sorceryDefense;
    }

    public void setSorceryDefense(double sorceryDefense){
        this.sorceryDefense=sorceryDefense;
    }

    public double getIncantationDefense() {
        return incantationDefense;
    }

    public void setIncantationDefense(double incantationDefense){
        this.incantationDefense=incantationDefense;
    }

    // this method would be used when an enemy spawns
    public static Enemy spawnEnemy(int areaIndex) {
        int randomType = random.nextInt(3) + 1;
        Type type;

        switch (randomType) {
            case 1:
                type = Type.TYPE1;
                break;
            case 2:
                type = Type.TYPE2;
                break;
            case 3:
                type = Type.TYPE3;
                break;
            default :
                type = Type.TYPE1;
                break;
        }

        String name = "Enemy";

        return new Enemy(type, name, areaIndex);
    }
}
