import java.util.Random;
public class Boss extends Enemy {
    public enum BossType {
        GODRICK,
        RENNALA,
        ELDEN_BEAST,
        PATCHES //PATCHES is to just deal with initialization
    }

    private int attackRanges[][]={{150,300},{200,300},{250,500}};
    private int healthValues[]={200,400,800};
    private String enemyNames[]={"Godrick the Grafted", "Rennala, Queen of the Full Moon", "The Elden Beast"};
    private double defenseValues[][]={{0.35,0.2,0.15},{0.15,0.35,0.25},{0.25,0.5,0.4}};

    // Constructor
    public Boss(BossType bossType, int areaIndex) {
        super(null, null, areaIndex);
        initializeStats(areaIndex); // Initialize stats based on bossType
    }

    // Initialize boss stats based on boss type and area index
    private void initializeStats(int areaIndex) {
        name = (enemyNames[areaIndex-1]);
        health = healthValues[areaIndex-1];
        attack = randomValue(attackRanges[areaIndex]);
        physicalDefense = defenseValues[areaIndex][0];
        sorceryDefense = defenseValues[areaIndex][1];
        incantationDefense = defenseValues[areaIndex][2];
    }

    // Helper method to get a random value within a given range
    private int randomValue(int[] range) {
        Random random = new Random();
        return random.nextInt(range[1] - range[0] + 1) + range[0];
    }

    public static Boss spawnBoss(int areaIndex) {
        BossType bossType;
        switch (areaIndex) {
            case 1:
                bossType = BossType.GODRICK;
                break;
            case 2:
                bossType = BossType.RENNALA;
                break;
            case 3:
                bossType = BossType.ELDEN_BEAST;
                break;
            default :
                bossType = BossType.PATCHES;
                break;
        }
        return new Boss(bossType, areaIndex);
    }
}
