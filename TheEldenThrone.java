public class TheEldenThrone extends Map implements CreditsInterface{
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
                {"emptyTile", "SFastTravelTile0", "emptyTile"}
            },
        }, 2, 8, 1, 0, 0, 0, 3, "T", "The Elden Throne");
    }

    @Override
    public void spawnTile(int areaIndex){
        int gainedRunes = (50 + random.nextInt(101))*areaIndex;
        Model.getPlayer().addRunes(gainedRunes);
    }

    public void creditsTile(){
        CreditScreen creditScreen = new CreditScreen();
    }
}
