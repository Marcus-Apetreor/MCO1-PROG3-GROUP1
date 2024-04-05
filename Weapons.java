/**
 * Represents a weapon in the game.
 * Each weapon has its own name, cost, type, stats, and image path.
 * 
 * @author Marcus Apetreor
 */
public class Weapons {
    private Stats weaponStats; // Stats of the weapon
    private String name; // Name of the weapon
    private int cost; // Cost of the weapon
    private String type; // Type of the weapon
    protected String imagePath; // Image path of the weapon

    /**
     * Constructs a new Weapons object with the specified parameters.
     *
     * @param type        The type of the weapon.
     * @param name        The name of the weapon.
     * @param cost        The cost of the weapon.
     * @param vigor       The vigor stat attribute of the weapon.
     * @param endurance   The endurance stat attribute of the weapon.
     * @param dexterity   The dexterity stat attribute of the weapon.
     * @param strength    The strength stat attribute of the weapon.
     * @param intelligence The intelligence stat attribute of the weapon.
     * @param faith       The faith stat attribute of the weapon.
     */
    public Weapons(String type, String name, int cost, int vigor, int endurance, int dexterity, int strength, int intelligence, int faith) {
        this.type = type;
        this.name = name;
        this.cost = cost;
        this.weaponStats = new Stats();
        this.weaponStats.setStats(vigor, endurance, dexterity, strength, intelligence, faith);
        this.imagePath = this.name + ".png";
    }

    /**
     * Gets the price of the weapon.
     *
     * @return The price of the weapon.
     */
    public int getPrice() {
        return cost;
    }

    /**
     * Gets the name of the weapon.
     *
     * @return The name of the weapon.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the stats of the weapon.
     *
     * @return The stats of the weapon.
     */
    public Stats getStats() {
        return weaponStats;
    }

    /**
     * Gets the type of the weapon.
     *
     * @return The type of the weapon.
     */
    public String getType() {
        return type;
    }

    /**
     * Gets the image path of the weapon.
     *
     * @return The image path of the weapon.
     */
    public String getImagePath() {
        return imagePath;
    }
}
