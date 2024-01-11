import java.util.*;

// Classe abstraite Map
public abstract class Map {
    protected int width, height;
    protected Case[][] mapGrid;
    protected LinkedHashSet<Case> chemin = new LinkedHashSet<>();
    protected ArrayList<PokemonEnnemi> ennemiList = new ArrayList<>();
    protected ArrayList<Prize> prizeList = new ArrayList<>();
    protected List<DamageEffect> damageEffects;

    public Map(int width, int height) {
        this.width = width;
        this.height = height;
        this.mapGrid = new Case[height][width];
        this.damageEffects = new ArrayList<>();
        initializeMap();
        initializeEnnemi();
    }

    public abstract void initializeMap(); // Méthode abstraite pour initialiser la carte
    public abstract void initializeEnnemi(); // Méthode abstraite pour initialiser les ennemis
    public abstract void updatePokemonPositions(int pas);

    public ArrayList<PokemonEnnemi> getennemiList() {
        return ennemiList;
    }

    public ArrayList<Prize> getPrizeList() {
        return prizeList;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Case getCaseAt(int y, int x) {
        return mapGrid[y][x];
    }

    public List<DamageEffect> getDamageEffects() {
        return damageEffects;
    }

    public LinkedHashSet<Case> getChemin() {
        return chemin;
    }

    public void setPath(int startX, int startY, int endX, int endY) {
        if (startX < 0 || startY < 0 || endX >= width || endY >= height) {
            throw new IllegalArgumentException("Coordonnées de chemin invalides.");
        }
    
        // Pour les chemins verticaux (startX == endX)
        if (startX == endX) {
            if (startY <= endY) {
                // Chemin de haut en bas
                for (int y = startY; y <= endY; y++) {
                    mapGrid[y][startX].setType(CaseType.PATH);
                    chemin.add(mapGrid[y][startX]);
                }
            } else {
                // Chemin de bas en haut
                for (int y = startY; y >= endY; y--) {
                    mapGrid[y][startX].setType(CaseType.PATH);
                    chemin.add(mapGrid[y][startX]);
                }
            }
        }
        // Pour les chemins horizontaux (startY == endY)
        else if (startY == endY) {
            if (startX <= endX) {
                // Chemin de gauche à droite
                for (int x = startX; x <= endX; x++) {
                    mapGrid[startY][x].setType(CaseType.PATH);
                    chemin.add(mapGrid[startY][x]);
                }
            } else {
                // Chemin de droite à gauche
                for (int x = startX; x >= endX; x--) {
                    mapGrid[startY][x].setType(CaseType.PATH);
                    chemin.add(mapGrid[startY][x]);
                }
            }
        } else {
            throw new IllegalArgumentException("Les chemins diagonaux ne sont pas pris en charge.");
        }
    }
    

}
