import java.util.ArrayList;
import java.util.HashMap;

public class PokemonFriend extends Pokemon {

    private int attackPoints;
    private int attackRange;
    private int attackSpeed;
    private int price;
    

    public PokemonFriend(String name, int x, int y, int attackPoints, int attackRange, int attackSpeed, int price) {
        super(name, x, y);
        this.attackPoints = attackPoints;
        this.attackRange = attackRange;
        this.attackSpeed = attackSpeed;
        this.price = price;
    }

    public ArrayList<Case> getRange() {
        ArrayList<Case> caseRange = new ArrayList<>();
        int x = super.getX();
        int y = super.getY();
        int attackRange = getAttackRange(); 
        for (int i = x - attackRange; i <= x + attackRange; i++) {
            for (int j = y - attackRange; j <= y + attackRange; j++) {
                caseRange.add(new Case(i, j, CaseType.RANGE));

            }
        }
    
        return caseRange;
    }
    

    public boolean attack(PokemonEnnemi enemy) {
        int x = enemy.getX();
        int y = enemy.getY();
        boolean attackSuccessful = false; 
    
        for (Case Case : getRange()) {
            // condition de portée 
            if (Case.getX() == x && Case.getY() == y) {
            // l'ennemi est dans la portée
                int HP = enemy.getHealthPoints();
                HP -= attackPoints;
                enemy.setHealthPoints(HP);
                attackSuccessful = true;
            }
        }
        return attackSuccessful; 
    }
    

    public int getPrice() {
        return price;
    }

    public int getAttackRange() {
        return attackRange;
    }

    public int getAttackPoints() {
        return attackPoints;
    }

    public int getAttackSpeed() {
        return attackSpeed;
    }
}
