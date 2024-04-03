public class Weapons {
    private Stats weaponStats;
    private String name;
    private int cost;
    private String type;
    protected String imagePath;
    public Weapons(String type, String name, int cost, int vigor, int endurance, int dexterity, int strength, int intelligence, int faith){
        this.type=type;
        this.name=name;
        this.cost=cost;
        this.weaponStats = new Stats();
        this.weaponStats.setStats(vigor, endurance, dexterity, strength, intelligence, faith);
        this.imagePath = this.name + ".png";
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
    public String getType(){
        return type;
    }
    public String getImagePath(){
        return imagePath;
    }
}
