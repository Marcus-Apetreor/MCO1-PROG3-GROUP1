public class Maps {
    private String[][][][] tileMap;

    public Maps(String[][][][] tileMap) {
        this.tileMap = tileMap;
    }

    public void displayMap() {
        for (String[][][] level : tileMap) {
            for (String[][] row : level) {
                for (String[] column : row) {
                    for (String element : column) {
                        if ("emptyTile".equals(element)) {
                            System.out.print("_");
                        } else if ("bossTile".equals(element)){
                            System.out.print("B");
                        } else if ("spawnTile".equals(element)){
                            System.out.print("S");
                        } else if (element.startsWith("doorTile")) {
                            System.out.print("D");
                        } else if (element.startsWith("fastTravelTile")){
                            System.out.print("F");
                        } else {
                            System.out.print(element + " ");
                        }
                    }
                    System.out.println();
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    public static void mapMenu(){
        StormveilCastle stormveilCastle = new StormveilCastle();
        stormveilCastle.displayMap();
    }
}