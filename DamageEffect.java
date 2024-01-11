public class DamageEffect {
    private int x;
    private int y;
    private long endTime; 

    public DamageEffect(int x, int y, int duration) {
        this.x = x;
        this.y = y;
        this.endTime = System.currentTimeMillis() + duration;
    }

    public boolean isExpired() {
        return System.currentTimeMillis() >= endTime;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public long getEndTime() {
        return endTime;
    }
}
