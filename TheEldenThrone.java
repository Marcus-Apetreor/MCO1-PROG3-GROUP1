import javax.swing.JOptionPane;
/**
 * Data for the The Elden Throne map.
 * Inherits from the {@link TileMap} class.
 * @author Marcus Apetreor
 */
public class TheEldenThrone extends TileMap implements CreditsInterface{
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
                {"OOBTile","emptyTile","emptyTile","doorTile1","emptyTile","emptyTile","OOBTile"},
                {"emptyTile","emptyTile","emptyTile","emptyTile","emptyTile","emptyTile","emptyTile"},
                {"emptyTile","emptyTile","emptyTile","emptyTile","emptyTile","emptyTile","emptyTile"},
                {"emptyTile","emptyTile","emptyTile","bossTile","emptyTile","emptyTile","emptyTile"},
                {"emptyTile","emptyTile","emptyTile","emptyTile","emptyTile","emptyTile","emptyTile"},
                {"emptyTile","emptyTile","emptyTile","emptyTile","emptyTile","emptyTile","emptyTile"},
                {"OOBTile","emptyTile","emptyTile","doorTile0","emptyTile","emptyTile","OOBTile"}
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
                {"emptyTile", "TFastTravelTile0", "emptyTile"}
            },
        }, 2, 8, 1, 0, 0, 0, 3, "T", "The Elden Throne");
    }

    @Override
    public void spawnTile(){
        int gainedRunes = (50 + random.nextInt(101))*areaIndex;
        Model.getPlayer().addRunes(gainedRunes);
        JOptionPane.showMessageDialog(null, "You have gained " + gainedRunes + " runes!");
    }

    public void creditsTile(){
        new CreditScreen();
    }
}
