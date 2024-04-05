/**
 * Class that represents player statistics and provides methods to manage them.
 * It houses attributes and methods related to player stats.
 * 
 * @author Marcus Apetreor
 */
public class Stats {
    private int vigor;
    private int endurance;
    private int dexterity;
    private int strength;
    private int intelligence;
    private int faith;

    /** 
     * Setter for the player's stats.
     * 
     * @param vigor The vigor stat attribute to set.
     * @param endurance The endurance stat attribute to set.
     * @param dexterity The dexterity stat attribute to set.
     * @param strength The strength stat attribute to set.
     * @param intelligence The intelligence stat attribute to set.
     * @param faith The faith stat attribute to set.
     */
    public void setStats(int vigor, int endurance, int dexterity, int strength, int intelligence, int faith){
        this.vigor = vigor;
        this.endurance = endurance;
        this.dexterity = dexterity;
        this.strength = strength;
        this.intelligence = intelligence;
        this.faith = faith;
    }

    
    /** 
     * @param stats
     */
    public void setStats(Stats stats){
        this.vigor = stats.vigor;
        this.endurance = stats.endurance;
        this.dexterity = stats.dexterity;
        this.strength = stats.strength;
        this.intelligence = stats.intelligence;
        this.faith = stats.faith;
    }

    /** 
     * Method to add stats to the player's existing stats.
     * 
     * @param vigor The amount of stat point to add for vigor.
     * @param endurance The amount of stat point to add for endurance.
     * @param dexterity The amount of stat point to add for dexterity.
     * @param strength The amount of stat point to add for strength.
     * @param intelligence The amount of stat point to add for intelligence.
     * @param faith The amount of stat point to add for faith.
     */
    public void addStats(int vigor, int endurance, int dexterity, int strength, int intelligence, int faith){
        this.vigor += vigor;
        this.endurance += endurance;
        this.dexterity += dexterity;
        this.strength += strength;
        this.intelligence += intelligence;
        this.faith += faith;
    }

    public void addStats(Stats stats){
        this.vigor += stats.getVigor();
        this.endurance += stats.getEndurance();
        this.dexterity += stats.getDexterity();
        this.strength += stats.getStrength();
        this.intelligence += stats.getIntelligence();
        this.faith += stats.getFaith();
    }

    public String getStatName(int i){
        switch(i){
            case 0:
                return "vigor";
            case 1:
                return "endurance";
            case 2:
                return "dexterity";
            case 3:
                return "strength";
            case 4:
                return "intelligence";
            case 5:
                return "faith";
        }
        return null;
    }

    public int getStatValue(int i){
        switch(i){
            case 0:
                return vigor;
            case 1:
                return endurance;
            case 2:
                return dexterity;
            case 3:
                return strength;
            case 4:
                return intelligence;
            case 5:
                return faith;
        }
        return 0;
    }
    
    public String printStats(){
        return "<html><b>Vigor:</b> " + vigor + "<br/>" +
        "<b>Endurance:</b> " + endurance + "<br/>" +
        "<b>Dexterity:</b> " + dexterity + "<br/>" +
        "<b>Strength:</b> " + strength + "<br/>" +
        "<b>Intelligence:</b> " + intelligence + "<br/>" +
        "<b>Faith:</b> " + faith + "</html>";
    }

    public int getVigor() {
        return vigor;
    }
    
    public int getEndurance() {
        return endurance;
    }
    
    public int getDexterity() {
        return dexterity;
    }
    
    public int getStrength() {
        return strength;
    }
    
    public int getIntelligence() {
        return intelligence;
    }
    
    public int getFaith() {
        return faith;
    }
    
}
