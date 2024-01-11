import java.util.ArrayList;

public class PokemonEnnemi extends Pokemon {
	private int healthPoints;
	private boolean isDead;
	private int pokedollarsCollected;

	
	public PokemonEnnemi(String Name,int x ,int y ,int healthPoints,int pokedollarsCollected) {
		
		super(Name,x,y);
		this.healthPoints = healthPoints;
		this.isDead = false;
		this.pokedollarsCollected = pokedollarsCollected;
	}
	
	
	public int getHealthPoints() {
		return healthPoints;
	}

	public int getPokedollarsCollected() {
		return pokedollarsCollected;
	}
	
	public int setHealthPoints(int n) {
		return this.healthPoints = n;
	}
	public void setDeathStatus() {
        this.isDead = true;
    }

    public boolean getDeathStatus() {
        return this.isDead;
    }
}
