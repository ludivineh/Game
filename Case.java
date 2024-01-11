public class Case {
    private int x, y; // coordonnées
    private CaseType type; // FREE, OCCUPIED ou PATH

    public Case(int x, int y, CaseType type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public CaseType getType() {
        return type;
    }

    public void setType(CaseType type) {
        this.type = type;
    }
 
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}