public class TheEldenThrone extends Map{
    public TheEldenThrone(){
        super(new String[][][] 
        {
            {
                {"emptyTile","creditsTile","emptyTile"},
                {"spawnTile","emptyTile","spawnTile"},
                {"emptyTile","emptyTile","emptyTile"},
                {"spawnTile","emptyTile","spawnTile"},
                {"emptyTile","emptyTile","emptyTile"},
                {"spawnTile","emptyTile","spawnTile"},
                {"emptyTile","emptyTile","emptyTile"},
                {"spawnTile","emptyTile","spawnTile"},
                {"emptyTile","doorTile1","emptyTile"}
            },
            {
                {"emptyTile","emptyTile","doorTile1","emptyTile","emptyTile"},
                {"emptyTile","emptyTile","emptyTile","emptyTile","emptyTile","emptyTile","emptyTile"},
                {"emptyTile","emptyTile","emptyTile","emptyTile","emptyTile","emptyTile","emptyTile"},
                {"emptyTile","emptyTile","emptyTile","bossTile","emptyTile","emptyTile","emptyTile"},
                {"emptyTile","emptyTile","emptyTile","emptyTile","emptyTile","emptyTile","emptyTile"},
                {"emptyTile","emptyTile","emptyTile","emptyTile","emptyTile","emptyTile","emptyTile"},
                {"emptyTile","emptyTile","doorTile0","emptyTile","emptyTile"}
            },
            {
                {"emptyTile", "doorTile0", "emptyTile"},
                {"emptyTile", "emptyTile", "emptyTile"},
                {"emptyTile", "emptyTile", "emptyTile"},
                {"emptyTile", "emptyTile", "emptyTile"},
                {"emptyTile", "spawnTile", "emptyTile"},
                {"emptyTile", "emptyTile", "emptyTile"},
                {"emptyTile", "emptyTile", "emptyTile"},
                {"emptyTile", "emptyTile", "emptyTile"},
                {"emptyTile", "SFastTravelTile0", "emptyTile"}
            },
        }, 2, 8, 1, 2, 3, 3, 3, "T");
    }
}
