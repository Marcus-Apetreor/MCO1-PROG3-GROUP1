/**
 * Child class of Maps to facilitate Stormveil Castle's information.
 * Represents the Stormveil Castle area in the game.
 * Extends the Maps class to define the specific layout and mechanics of Stormveil Castle.
 * 
 * @author Marcus Apetreor, Vincent Vuelva
 */
public final class StormveilCastle extends Maps {
    /**
     * Constructor to initialize the Stormveil Castle map.
     * Constructs the Stormveil Castle map with its specific layout and features.
     */
    public StormveilCastle(){
        super(new String[][][] 
        {
            {
                {"emptyTile", "emptyTile", "fastTravelTileS1", "emptyTile", "emptyTile"}, 
                 {"emptyTile", "emptyTile", "emptyTile", "emptyTile", "emptyTile"},
                 {"emptyTile", "emptyTile", "emptyTile", "emptyTile", "emptyTile"},
                 {"emptyTile", "emptyTile", "bossTile", "emptyTile", "emptyTile"},
                 {"emptyTile", "emptyTile", "emptyTile", "emptyTile", "emptyTile"},
                 {"emptyTile", "emptyTile", "emptyTile", "emptyTile", "emptyTile"},
                 {"emptyTile", "emptyTile", "doorTile1", "emptyTile", "emptyTile"}
            },
            {
                {"emptyTile", "emptyTile", "emptyTile", "doorTile1", "emptyTile", "emptyTile", "emptyTile"}, 
                 {"emptyTile", "emptyTile", "emptyTile", "spawnTile", "emptyTile", "emptyTile", "emptyTile"},
                 {"emptyTile", "emptyTile", "emptyTile", "emptyTile", "emptyTile", "emptyTile", "emptyTile"},
                 {"spawnTile", "emptyTile", "spawnTile", "spawnTile", "spawnTile", "emptyTile", "spawnTile"},
                 {"emptyTile", "emptyTile", "emptyTile", "emptyTile", "emptyTile", "emptyTile", "emptyTile"},
                 {"emptyTile", "emptyTile", "spawnTile", "emptyTile", "spawnTile", "emptyTile", "emptyTile"},
                 {"emptyTile", "emptyTile", "emptyTile", "doorTile0", "emptyTile", "emptyTile", "emptyTile"}
            },
            {
                {"emptyTile", "doorTile0", "emptyTile"},
                 {"spawnTile", "emptyTile", "spawnTile"},
                 {"emptyTile", "emptyTile", "emptyTile"},
                 {"emptyTile", "emptyTile", "emptyTile"},
                 {"emptyTile", "emptyTile", "emptyTile"},
                 {"emptyTile", "emptyTile", "emptyTile"},
                 {"emptyTile", "fastTravelTileS0", "emptyTile"}
            }
        }, 2, 6, 1, 1);
    }
}
