import java.util.Scanner;

public class Maps {
    private String[][][] tileMap;
    private int playerRoom;
    private int playerRow;
    private int playerCol;

    public Maps(String[][][] tileMap,int playerRoom,int playerRow,int playerCol) {
        this.tileMap = tileMap;
        this.playerRoom = playerRoom; // Starting room for the player
        this.playerRow = playerRow; // Starting row for the player
        this.playerCol = playerCol; // Starting column for the player
    }

    public void printCurrentRoom() {
        for (int i = 0; i < tileMap[playerRoom].length; i++) {
            for (int j = 0; j < tileMap[playerRoom][i].length; j++) {
                if (i == playerRow && j == playerCol) {
                    System.out.print("----P---- "); // Print 'P' for player's location
                } else {
                    System.out.print(tileMap[playerRoom][i][j] + " ");
                }
            }
            System.out.println();
        }
    }    

    public void movePlayer(char direction) {
        int newRow = playerRow;
        int newCol = playerCol;
    
        switch (direction) {
            case 'w':
                newRow--;
                break;
            case 'a':
                newCol--;
                break;
            case 's':
                newRow++;
                break;
            case 'd':
                newCol++;
                break;
            default:
                System.out.println("Invalid direction!");
                return;
        }
    
        if (isValidMove(newRow, newCol)) {
            if (tileMap[playerRoom][newRow][newCol].startsWith("doorTile")) {
                String doorName = tileMap[playerRoom][newRow][newCol];
                boolean found = false;
                for (int i = 0; i < tileMap.length; i++) {
                    if (i != playerRoom && tileMap[i][newRow][newCol].equals(doorName)) {
                        playerRoom = i;
                        for (int j = 0; j < tileMap[playerRoom].length; j++) {
                            for (int k = 0; k < tileMap[playerRoom][j].length; k++) {
                                if (tileMap[playerRoom][j][k].equals(doorName)) {
                                    playerRow = j;
                                    playerCol = k;
                                    found = true;
                                    break;
                                }
                            }
                            if (found) {
                                break;
                            }
                        }
                        break;
                    }
                }
            } else {
                playerRow = newRow;
                playerCol = newCol;
            }
            System.out.println("Player moved to: (" + playerRoom + ", " + playerRow + ", " + playerCol + ")");
        } else {
            System.out.println("Invalid move! Out of bounds.");
        }
    }    

    private boolean isValidMove(int row, int col) {
        return row >= 0 && row < tileMap[playerRoom].length && col >= 0 && col < tileMap[playerRoom][row].length;
    }

    public void play() {
        Scanner sc = new Scanner(System.in);
        char direction;
    
        while (true) {
            printCurrentRoom();
            System.out.println("Player location: (" + playerRoom + ", " + playerRow + ", " + playerCol + ")");
            System.out.print("Enter direction (w/a/s/d): ");
            direction = sc.next().charAt(0);
            movePlayer(direction);
            ConsoleMethods.clearConsole();
        }
    }    
}
