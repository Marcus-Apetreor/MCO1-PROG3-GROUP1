public class StormveilCastle extends Maps {

    public StormveilCastle(){
        super(new String[][][][] {
            {
                {{"emptyTile", "emptyTile", "fastTravelTile0", "emptyTile", "emptyTile"}, 
                 {"emptyTile", "emptyTile", "emptyTile", "emptyTile", "emptyTile"},
                 {"emptyTile", "emptyTile", "emptyTile", "emptyTile", "emptyTile"},
                 {"emptyTile", "emptyTile", "bossTile", "emptyTile", "emptyTile"},
                 {"emptyTile", "emptyTile", "emptyTile", "emptyTile", "emptyTile"},
                 {"emptyTile", "emptyTile", "emptyTile", "emptyTile", "emptyTile"},
                 {"emptyTile", "emptyTile", "doorTile1", "emptyTile", "emptyTile"}
                },
            },
            {
                {{"emptyTile", "emptyTile", "emptyTile", "doorTile1", "emptyTile", "emptyTile", "emptyTile"}, 
                 {"emptyTile", "emptyTile", "emptyTile", "spawnTile", "emptyTile", "emptyTile", "emptyTile"},
                 {"emptyTile", "emptyTile", "emptyTile", "emptyTile", "emptyTile", "emptyTile", "emptyTile"},
                 {"spawnTile", "emptyTile", "spawnTile", "spawnTile", "spawnTile", "emptyTile", "spawnTile"},
                 {"emptyTile", "emptyTile", "emptyTile", "emptyTile", "emptyTile", "emptyTile", "emptyTile"},
                 {"emptyTile", "emptyTile", "spawnTile", "emptyTile", "spawnTile", "emptyTile", "emptyTile"},
                 {"emptyTile", "emptyTile", "emptyTile", "doorTile2", "emptyTile", "emptyTile", "emptyTile"},
                },
            },
            {
                {{"emptyTile", "doorTile2", "emptyTile"},
                 {"spawnTile", "emptyTile", "spawnTile"},
                 {"emptyTile", "emptyTile", "emptyTile"},
                 {"emptyTile", "emptyTile", "emptyTile"},
                 {"emptyTile", "emptyTile", "emptyTile"},
                 {"emptyTile", "emptyTile", "emptyTile"},
                 {"emptyTile", "fastTravelTile1", "emptyTile"},
                },
            }
        });
    }
}
