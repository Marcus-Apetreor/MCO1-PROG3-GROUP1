/**
 * Data for the Raya Lucaria Academy map.
 * Inherits from the {@link TileMap} class.
 * @author Marcus Apetreor
 */
public class RayaLucariaAcademy extends TileMap{
    public RayaLucariaAcademy(){
        super(new String[][][] 
        {
            {
                {"emptyTile","emptyTile","RFastTravelTile0","emptyTile","emptyTile"},
                {"emptyTile","emptyTile","emptyTile","emptyTile","emptyTile"},
                {"emptyTile","emptyTile","emptyTile","emptyTile","emptyTile"},
                {"emptyTile","spawnTile","emptyTile","spawnTile","emptyTile"},
                {"emptyTile","emptyTile","doorTile0","emptyTile","emptyTile"}
            },
            {
                {"OOBTile","OOBTile","emptyTile","RFastTravelTile1","emptyTile","OOBTile","OOBTile"},
                {"emptyTile","emptyTile","emptyTile","emptyTile","emptyTile","emptyTile","emptyTile"},
                {"emptyTile","spawnTile","emptyTile","spawnTile","emptyTile","spawnTile","emptyTile"},
                {"emptyTile","emptyTile","emptyTile","emptyTile","emptyTile","emptyTile","emptyTile"},
                {"emptyTile","spawnTile","emptyTile","bossTile","emptyTile","spawnTile","emptyTile"},
                {"emptyTile","emptyTile","emptyTile","emptyTile","emptyTile","emptyTile","emptyTile"},
                {"emptyTile","spawnTile","emptyTile","emptyTile","emptyTile","spawnTile","emptyTile"},
                {"emptyTile","emptyTile","emptyTile","doorTile3","emptyTile","emptyTile","emptyTile",}
            },
            {
                {"emptyTile", "doorTile0", "emptyTile"},
                {"spawnTile", "emptyTile", "emptyTile"},
                {"emptyTile", "emptyTile", "emptyTile"},
                {"spawnTile", "emptyTile", "doorTile1"},
                {"emptyTile", "emptyTile", "emptyTile"},
                {"spawnTile", "emptyTile", "emptyTile"},
                {"emptyTile", "emptyTile", "emptyTile"}
            },
            {
                {"OOBTile","emptyTile", "doorTile3", "emptyTile","OOBTile"},
                {"OOBTile","emptyTile", "spawnTile", "emptyTile","OOBTile"},
                {"emptyTile","emptyTile","emptyTile","emptyTile","emptyTile"},
                {"doorTile1","emptyTile","emptyTile","emptyTile","doorTile2"},
                {"emptyTile","emptyTile","emptyTile","emptyTile","emptyTile"},
                {"OOBTile","emptyTile", "spawnTile", "emptyTile","OOBTile"},
                {"OOBTile","emptyTile","emptyTile","emptyTile","OOBTile"}
            },
            {
                {"emptyTile","emptyTile","spawnTile","emptyTile","spawnTile","emptyTile"},
                {"doorTile2","emptyTile","emptyTile","emptyTile","emptyTile","emptyTile"},
                {"emptyTile","emptyTile","spawnTile","emptyTile","spawnTile","emptyTile"},
            },
        }, 0, 0, 2, 1, 0, 3, 2, "R", "Raya Lucaria Academy");
    }
}
