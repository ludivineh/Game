import java.util.ArrayList;
import java.util.List;

public class Player {
    private String Name;
    private int pokedollars;
    private int level;
    public List<PokemonFriend> pokemonFriendList;
    private boolean buyingModeCARAPUCE;
    private boolean buyingModeSNORLAX;

    public Player() {
        this.level = 1; // niveau de départ
        this.pokedollars = 80; // monnaie initiale
        this.pokemonFriendList = new ArrayList<>(); // la liste de pokemons amis est vide au début
        this.buyingModeCARAPUCE = false; // mode achat non activé
        this.buyingModeSNORLAX = false;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public void setPokedollars(int money) {
        this.pokedollars = money;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setbuyingModeCARAPUCE(boolean buyingModeCARAPUCE) {
        this.buyingModeCARAPUCE = buyingModeCARAPUCE;
    }

    public void setbuyingModeSNORLAX(boolean buyingModeSNORLAX) {
        this.buyingModeSNORLAX = buyingModeSNORLAX;
    }    

    public String getName() {
        return Name;
    }

    public int getPokedollars() {
        return pokedollars;
    }

    public int getLevel() {
        return level;
    }

    public boolean getbuyingModeCARAPUCE() {
        return buyingModeCARAPUCE;
    }

    public boolean getbuyingModeSNORLAX() {
        return buyingModeSNORLAX;
    }

    public List<PokemonFriend> getPokemonFriendList() {
        return pokemonFriendList;
    }

    public void addPokemonFriend(PokemonFriend pokemon) {
        pokemonFriendList.add(pokemon);
    }

    // METHODE POUR L'ACHAT D'UN POKEMON
    public boolean buy(PokemonFriend pokemon) {
        int pokemonPrice = pokemon.getPrice();
        if (pokedollars >= pokemonPrice) {
            pokedollars -= pokemonPrice;
            return true; // achat réussi
        }
        return false; // achat non réussi si le joueur n'a pas assez d'argent
    }

}

