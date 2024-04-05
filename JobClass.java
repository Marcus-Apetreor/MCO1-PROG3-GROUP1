/**
 * The JobClass class represents a job class with specific attributes such as stats, name, and level.
 * 
 * @author Marcus Apetreor, Vincent Vuelva
 */
public class JobClass {
    private Stats jobStats;
    private String jobName;
    private int jobLevel;

    /**
     * Constructs a JobClass object with the specified attributes.
     * 
     * @param jobLevel      The level of the job class.
     * @param jobName       The name of the job class.
     * @param Vigor         The vigor stat of the job class.
     * @param Endurance     The endurance stat of the job class.
     * @param Dexterity     The dexterity stat of the job class.
     * @param Strength      The strength stat of the job class.
     * @param Intelligence  The intelligence stat of the job class.
     * @param Faith         The faith stat of the job class.
     */
    public JobClass(int jobLevel, String jobName, int Vigor, int Endurance, int Dexterity, int Strength, int Intelligence, int Faith){
        this.jobName=jobName;
        this.jobLevel=jobLevel;
        this.jobStats = new Stats();
        this.jobStats.setStats(Vigor, Endurance, Dexterity, Strength, Intelligence, Faith);
    }

    /**
     * Gets the stats of the job class.
     * 
     * @return The stats of the job class.
     */
    public Stats getJobStats(){
        return jobStats;
    }

    /**
     * Gets the name of the job class.
     * 
     * @return The name of the job class.
     */
    public String getJobName(){
        return jobName;
    }

    /**
     * Gets the level of the job class.
     * 
     * @return The level of the job class.
     */
    public int getJobLevel(){
        return jobLevel;
    }
}
