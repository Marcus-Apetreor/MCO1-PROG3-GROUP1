import java.util.Random;

public class Boss extends Enemy {

    private int attackRanges[][]={{150,300},{200,300},{250,500}};
    private int healthValues[]={200,400,800};
    private String bossNames[]={"Godrick the Grafted", "Rennala, Queen of the Full Moon", "The Elden Beast"};
    private double defenseValues[][]={{0.35,0.2,0.15},{0.15,0.35,0.25},{0.25,0.5,0.4}};

    public Boss(EnemyType type, int areaIndex) {
        super(type, areaIndex);
        this.type=type;
        initializeStats(areaIndex); // Initialize stats based on type
        this.isDefeated = false;
    }

    private void initializeStats(int areaIndex) {
        int bossAreaIndex = areaIndex-1;
        name = bossNames[bossAreaIndex];
        health = healthValues[bossAreaIndex];
        attack = randomValue(attackRanges[bossAreaIndex]);
        physicalDefense = defenseValues[bossAreaIndex][0];
        sorceryDefense = defenseValues[bossAreaIndex][1];
        incantationDefense = defenseValues[bossAreaIndex][2];
    }

    private int randomValue(int[] range) {
        Random random = new Random();
        return random.nextInt(range[1] - range[0] + 1) + range[0];
    }

    public static Boss spawnBoss(int areaIndex) {
        Enemy.EnemyType type;
        switch (areaIndex-1) {
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
        return new Boss(type, areaIndex);
    }
}