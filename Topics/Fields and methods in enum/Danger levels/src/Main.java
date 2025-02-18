enum DangerLevel {
    HIGH(3),
    MEDIUM(2),
    LOW(1);
    private final int level;

    DangerLevel(int level){
        this.level = level;
    };

    public int getLevel(){
        return this.level;
    }
}