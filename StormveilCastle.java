/**
 * Data for the Stormveil Castle map.
 * Inherits from the {@link TileMap} class.
 * @author Marcus Apetreor, Vincent Vuelva
 */
public class StormveilCastle extends TileMap {
    /**
     * Constructor to initialize the Stormveil Castle map.
     * Constructs the Stormveil Castle map with its specific layout and features.
     */
    public StormveilCastle(){
        super(new String[][][] 
        {
            {
                {"emptyTile", "emptyTile", "SFastTravelTile1", "emptyTile", "emptyTile"}, 
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
                 {"emptyTile", "SFastTravelTile0", "emptyTile"}
            }
        }, 2, 6, 1, 0, 0, 2, 1, "S", "Stormveil Castle");
    }
}
