public class JobClass {
    private Stats jobStats;
    private String jobName;
    private int jobLevel;

    public JobClass(int jobLevel, String jobName, int Vigor, int Endurance, int Dexterity, int Strength, int Intelligence, int Faith){
        this.jobName=jobName;
        this.jobLevel=jobLevel;
        this.jobStats = new Stats();
        this.jobStats.setStats(Vigor, Endurance, Dexterity, Strength, Intelligence, Faith);
    }

    public Stats getJobStats(){
        return jobStats;
    }

    public String getJobName(){
        return jobName;
    }

    public int getJobLevel(){
        return jobLevel;
    }
}
