import java.util.Random;

public class Boss extends Enemy {

    private int attackRanges[][]={{150,300},{200,300},{250,500}};
    private int healthValues[]={200,400,800};
    private String enemyNames[]={"Godrick the Grafted", "Rennala, Queen of the Full Moon", "The Elden Beast"};
    private double defenseValues[][]={{0.35,0.2,0.15},{0.15,0.35,0.25},{0.25,0.5,0.4}};

    public Boss(CharacterType type, int areaIndex) {
        super(type, areaIndex);
        initializeStats(areaIndex); // Initialize stats based on type
        this.isDefeated = false;
    }

    private void initializeStats(int areaIndex) {
        name = (enemyNames[areaIndex-1]);
        health = healthValues[areaIndex-1];
        attack = randomValue(attackRanges[areaIndex]);
        physicalDefense = defenseValues[areaIndex][0];
        sorceryDefense = defenseValues[areaIndex][1];
        incantationDefense = defenseValues[areaIndex][2];
    }

    private int randomValue(int[] range) {
        Random random = new Random();
        return random.nextInt(range[1] - range[0] + 1) + range[0];
    }

    public static Boss spawnBoss(int areaIndex) {
        Enemy.CharacterType type;
        switch (areaIndex) {
            case 1:
                type = CharacterType.GODRICK;
                break;
            case 2:
                type = CharacterType.RENNALA;
                break;
            case 3:
                type = CharacterType.ELDEN_BEAST;
                break;
            default :
                type = CharacterType.PATCHES;
                break;
        }
        return new Boss(type, areaIndex);
    }
}