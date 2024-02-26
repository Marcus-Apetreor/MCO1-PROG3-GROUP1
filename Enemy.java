import java.util.Random;

public class Enemy {
    public enum CharacterType {
        TYPE1, TYPE2, TYPE3,
        GODRICK, RENNALA, ELDEN_BEAST, PATCHES
    }

    protected CharacterType type;
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

    private static final Random random = new Random();

    public Enemy(CharacterType type, int areaIndex) {
        this.type = type;
        initializeStats(areaIndex);
        this.isDefeated = false;
    }

    private void initializeStats(int areaIndex) {
        int typeIndex = type.ordinal();
        name = (enemyNames[typeIndex][areaIndex-1]);
        health = randomValue(healthRanges[typeIndex]) * areaIndex;
        attack = randomValue(attackRanges[typeIndex]) * areaIndex;
        physicalDefense = defenseRanges[0][typeIndex];
        sorceryDefense = defenseRanges[1][typeIndex];
        incantationDefense = defenseRanges[2][typeIndex];
    }

    private int randomValue(int[] range) {
        return random.nextInt(range[1] - range[0] + 1) + range[0];
    }

    public boolean isDefeated() {
        return isDefeated;
    }

    public void defeat() {
        isDefeated = true;
    }

    public CharacterType getType() {
        return type;
    }

    public void setType(CharacterType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        this.attack = attack;
    }

    public double getPhysicalDefense() {
        return physicalDefense;
    }

    public void setPhysicalDefense(double physicalDefense){
        this.physicalDefense = physicalDefense;
    }

    public double getSorceryDefense() {
        return sorceryDefense;
    }

    public void setSorceryDefense(double sorceryDefense){
        this.sorceryDefense = sorceryDefense;
    }

    public double getIncantationDefense() {
        return incantationDefense;
    }

    public void setIncantationDefense(double incantationDefense){
        this.incantationDefense = incantationDefense;
    }

    public static Enemy spawnEnemy(int areaIndex) {
        int randomType = random.nextInt(3) + 1;
        CharacterType type;

        switch (randomType) {
            case 1:
                type = CharacterType.TYPE1;
                break;
            case 2:
                type = CharacterType.TYPE2;
                break;
            case 3:
                type = CharacterType.TYPE3;
                break;
            default :
                type = CharacterType.TYPE1;
                break;
        }

        return new Enemy(type, areaIndex);
    }
}
