public class Weapons {
    private Stats weaponStats;
    private String name;
    private int cost;
    public Weapons(String Name, int Cost, int Vigor, int Endurance, int Dexterity, int Strength, int Intelligence, int Faith){
        this.name=Name;
        this.cost=Cost;
        this.weaponStats = new Stats();
        this.weaponStats.setStats(Vigor, Endurance, Dexterity, Strength, Intelligence, Faith);
    }
    public int getPrice(){
        return cost;
    }
    public String getName(){
        return name;
    }
    public Stats getStats(){
        return weaponStats;
    }
}
