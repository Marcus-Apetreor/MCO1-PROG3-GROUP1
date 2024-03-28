/**
 * Class that represents player statistics and provides methods to manage them.
 * It houses attributes and methods related to player stats.
 * 
 * @author Marcus Apetreor, Vincent Vuelva
 */
public class Stats {
    private int Vigor;
    private int Endurance;
    private int Dexterity;
    private int Strength;
    private int Intelligence;
    private int Faith;

    /** 
     * Setter for the player's stats.
     * 
     * @param Vigor The vigor stat attribute to set.
     * @param Endurance The endurance stat attribute to set.
     * @param Dexterity The dexterity stat attribute to set.
     * @param Strength The strength stat attribute to set.
     * @param Intelligence The intelligence stat attribute to set.
     * @param Faith The faith stat attribute to set.
     */
    public void setStats(int Vigor, int Endurance, int Dexterity, int Strength, int Intelligence, int Faith){
        this.Vigor = Vigor;
        this.Endurance = Endurance;
        this.Dexterity = Dexterity;
        this.Strength = Strength;
        this.Intelligence = Intelligence;
        this.Faith = Faith;
    }

    public void setStats(Stats stats){
        this.Vigor = stats.Vigor;
        this.Endurance = stats.Endurance;
        this.Dexterity = stats.Dexterity;
        this.Strength = stats.Strength;
        this.Intelligence = stats.Intelligence;
        this.Faith = stats.Faith;
    }

    /** 
     * Method to add stats to the player's existing stats.
     * 
     * @param Vigor The amount of stat point to add for vigor.
     * @param Endurance The amount of stat point to add for endurance.
     * @param Dexterity The amount of stat point to add for dexterity.
     * @param Strength The amount of stat point to add for strength.
     * @param Intelligence The amount of stat point to add for intelligence.
     * @param Faith The amount of stat point to add for faith.
     */
    public void addStats(int Vigor, int Endurance, int Dexterity, int Strength, int Intelligence, int Faith){
        this.Vigor += Vigor;
        this.Endurance += Endurance;
        this.Dexterity += Dexterity;
        this.Strength += Strength;
        this.Intelligence += Intelligence;
        this.Faith += Faith;
    }

    public void addStats(Stats stats){
        this.Vigor += stats.getVigor();
        this.Endurance += stats.getEndurance();
        this.Dexterity += stats.getDexterity();
        this.Strength += stats.getStrength();
        this.Intelligence += stats.getIntelligence();
        this.Faith += stats.getFaith();
    }

    public int getVigor() {
        return Vigor;
    }
    
    public int getEndurance() {
        return Endurance;
    }
    
    public int getDexterity() {
        return Dexterity;
    }
    
    public int getStrength() {
        return Strength;
    }
    
    public int getIntelligence() {
        return Intelligence;
    }
    
    public int getFaith() {
        return Faith;
    }
    
}
