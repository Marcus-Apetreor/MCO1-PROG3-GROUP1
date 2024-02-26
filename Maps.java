import java.util.*;

public class Maps {
    private static final Random random = new Random();
    private final String[][][] tileMap;
    private int playerRoom;
    private int playerRow;
    private int playerCol;
    private String usedDoorName;
    private int[] usedDoorCoordinates;
    private int areaIndex=1;
    private ArrayList<String> unlockedFastTravelTiles = new ArrayList<>();

    public Maps(String[][][] tileMap,int playerRoom,int playerRow,int playerCol) {
        unlockedFastTravelTiles.add(tileMap[playerRoom][playerRow][playerCol]);
        this.tileMap = tileMap;
        this.playerRoom = playerRoom; // Starting room for the player
        this.playerRow = playerRow; // Starting row for the player
        this.playerCol = playerCol; // Starting column for the player
    }

    public ArrayList<String> getUnlockedFastTravelTiles(){
        return unlockedFastTravelTiles;
    }

    public void printCurrentRoom() {
        for (int i = 0; i < tileMap[playerRoom].length; i++) {
            for (int j = 0; j < tileMap[playerRoom][i].length; j++) {
                if (i == playerRow && j == playerCol) {
                    System.out.print("(-_-) ");
                } else if (tileMap[playerRoom][i][j].equals("emptyTile")) {
                    System.out.print(".   . ");
                } else if (tileMap[playerRoom][i][j].equals("spawnTile")) {
                    System.out.print(". ? . ");
                } else if (tileMap[playerRoom][i][j].equals("bossTile")) {
                    System.out.print(".xXx. ");
                } else if (tileMap[playerRoom][i][j].startsWith("doorTile")) {
                    System.out.print("|  °| ");
                } else if (tileMap[playerRoom][i][j].startsWith("lockedTravelTile")) {
                    System.out.print("o---o ");
                }
            }
            System.out.println();
        }
    }    

    public void movePlayer(String direction) {
        int newRow = playerRow;
        int newCol = playerCol;
        
        if (direction.equalsIgnoreCase("w")){
            newRow--;
        } else if (direction.equalsIgnoreCase("a")) {
            newCol--;
        } else if (direction.equalsIgnoreCase("s")) {
            newRow++;
        } else if (direction.equalsIgnoreCase("d")) {
            newCol++;
        } else {
            ConsoleMethods.clearConsole();
            System.out.println("Invalid direction!");
            return;
        }
        ConsoleMethods.clearConsole();
    
        if (isValidMove(newRow, newCol)||
        tileMap[playerRoom][playerRow][playerCol].startsWith("doorTile")||
        tileMap[playerRoom][playerRow][playerCol].equals("spawnTile")||
        tileMap[playerRoom][playerRow][playerCol].equals("bossTile")) {
            if (tileMap[playerRoom][playerRow][playerCol].startsWith("doorTile")){
                String doorTile = tileMap[playerRoom][playerRow][playerCol];
                int currentRoom = playerRoom;
                for (int i=0; i<tileMap.length; i++){
                    if (i==currentRoom){
                        continue;
                    }
                    for (int j=0; j<tileMap[i].length; j++){
                        for (int k=0; k<tileMap[i][j].length; k++){
                            if (doorTile.equals(tileMap[i][j][k])){
                                playerRoom = i;
                                playerRow = j;
                                playerCol = k;
                                usedDoorName=doorTile;
                                int[] coordinates={i, j, k};
                                this.usedDoorCoordinates = coordinates;
                                tileMap[i][j][k] = "";
                            }
                        }
                    }

                }
            } else {
                int[] coordinates = usedDoorCoordinates;
                if (coordinates != null) {
                    int doorRoom = coordinates[0];
                    int doorRow = coordinates[1];
                    int doorCol = coordinates[2];
                    tileMap[doorRoom][doorRow][doorCol] = usedDoorName;
                }
                if (tileMap[playerRoom][playerRow][playerCol].equals("spawnTile")) {
                    ConsoleMethods.clearConsole();
                    enemyTile(areaIndex);
                    tileMap[playerRoom][playerRow][playerCol] = "emptyTile";
                    return;
                }
                if (tileMap[playerRoom][playerRow][playerCol].equals("bossTile")) {
                    ConsoleMethods.clearConsole();
                    bossTile(areaIndex);
                    tileMap[playerRoom][playerRow][playerCol] = "emptyTile";
                    for (int i=0; i<tileMap.length; i++){
                        for (int j=0; j<tileMap[i].length; j++){
                            for (int k=0; k<tileMap[i][j].length; k++){
                                if(tileMap[i][j][k].startsWith("fastTravelTile")){
                                    String fastTravelTile = tileMap[i][j][k];
                                    unlockedFastTravelTiles.add(fastTravelTile);
                                }
                            }
                        }
                    }
                    return;
                }
                playerRow = newRow;
                playerCol = newCol;
            }
        } else {
            System.out.println("Invalid move! Out of bounds.");
        }
    }    

    private boolean isValidMove(int row, int col) {
        return row >= 0 && row < tileMap[playerRoom].length && col >= 0 && col < tileMap[playerRoom][row].length;
    }

    public void play() {
        Scanner sc = new Scanner(System.in);
        String direction;
    
        while (true) {
            printCurrentRoom();
            System.out.println("Player location: (" + playerRoom + ", " + playerRow + ", " + playerCol + ")");
            System.out.print("Enter direction (w/a/s/d): ");
            direction = sc.nextLine();
            movePlayer(direction);
        }
    }    

    public void enemyTile(int areaIndex){
        boolean isEnemy;
        if (random.nextDouble()<=0.25){
            isEnemy=false;
        } else {
            isEnemy=true;
        }
        if (isEnemy){
            String noun = "An";
            Enemy enemy = Enemy.spawnEnemy(areaIndex);
            char[] vowels = {'A', 'E', 'I', 'O', 'U'};
            for (char vowel : vowels) {
                if (enemy.getName().startsWith(String.valueOf(vowel))) {
                    noun = "A";
                    break;
                }
            }
            System.out.println(noun + " " + enemy.getName() + " has appeared!");
            System.out.println("Type: " + enemy.getType());
            System.out.println("Health: " + enemy.getHealth());
            System.out.println("Attack: " + enemy.getAttack());
            System.out.println("Physical Defense: " + enemy.getPhysicalDefense());
            System.out.println("Sorcery Defense: " + enemy.getSorceryDefense());
            System.out.println("Incantation Defense: " + enemy.getIncantationDefense());
        } else {
            int gainedRunes = 50 + random.nextInt(101);
            CharacterCreationScreen.getPlayerInstance().addRunes(gainedRunes);
            System.out.println("You have found " + gainedRunes + " runes!");
        }
    }

    public Enemy bossTile(int areaIndex){
        Enemy boss = Boss.spawnBoss(areaIndex);
        System.out.println(boss.getName() + " has appeared!");
        System.out.println("Health: " + boss.getHealth());
        System.out.println("Attack: " + boss.getAttack());
        System.out.println("Physical Defense: " + boss.getPhysicalDefense());
        System.out.println("Sorcery Defense: " + boss.getSorceryDefense());
        System.out.println("Incantation Defense: " + boss.getIncantationDefense());
        return boss;
    }
}
