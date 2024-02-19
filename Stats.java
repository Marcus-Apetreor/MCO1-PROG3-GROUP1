public class Stats {
    private int Vigor;
    private int Endurance;
    private int Dexterity;
    private int Strength;
    private int Intelligence;
    private int Faith;

    public void statCard(){
        System.out.println("VIG: " + Vigor);
        System.out.println("END: " + Endurance);
        System.out.println("DEX: " + Dexterity);
        System.out.println("STR: " + Strength);
        System.out.println("INT: " + Intelligence);
        System.out.println("FTH: " + Faith);
    }

    public void setStats(int Vigor, int Endurance, int Dexterity, int Strength, int Intelligence, int Faith){
        this.Vigor = Vigor;
        this.Endurance = Endurance;
        this.Dexterity = Dexterity;
        this.Strength = Strength;
        this.Intelligence = Intelligence;
        this.Faith = Faith;
    }

    public void addStats(int Vigor, int Endurance, int Dexterity, int Strength, int Intelligence, int Faith){
        this.Vigor =+ Vigor;
        this.Endurance =+ Endurance;
        this.Dexterity =+ Dexterity;
        this.Strength =+ Strength;
        this.Intelligence =+ Intelligence;
        this.Faith =+ Faith;
    }
}
