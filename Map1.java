public class Map1 extends Map {
    public Map1(int width, int height) {
        super(width, height);
    }

    @Override
    public void initializeMap() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                CaseType type = CaseType.FREE; 
                this.mapGrid[y][x] = new Case(x, y, type);
            }
        }
        setPath(0, 17, 8, 17);   // Chemin de [0,17] à [8,17]
        setPath(8, 17, 8, 7);   // Chemin de [8,17] à [8,7]
        setPath(8, 7, 16, 7);   // Chemin de [8,7] à [16,7]
        setPath(16, 7, 16, 2);  // Chemin de [16,7] à [16,3]
    }

    @Override
    public void initializeEnnemi() {
        int x = this.chemin.toArray(new Case[0])[0].getX();
        int y = this.chemin.toArray(new Case[0])[0].getY();
        PokemonEnnemi E1 = new Haunter(x,y);
        this.ennemiList.add(E1);
        PokemonEnnemi E2 = new Haunter(x,y);
        this.ennemiList.add(E2);
        PokemonEnnemi E3 = new Gengar(x,y);
        this.ennemiList.add(E3);
        PokemonEnnemi E4 = new Haunter(x,y);
        this.ennemiList.add(E4);
        PokemonEnnemi E5 = new Haunter(x,y);
        this.ennemiList.add(E5);
        PokemonEnnemi E6 = new Gengar(x,y);
        this.ennemiList.add(E6);
    }

    @Override
    public void updatePokemonPositions(int pas) {
        for (PokemonEnnemi pokemonEnnemi : ennemiList) {
            int index = ((pas - (ennemiList.indexOf(pokemonEnnemi)*8)));
            if (index >= 0 && index < chemin.size()) {
                Case Case = (Case) chemin.toArray()[index];
                pokemonEnnemi.setX(Case.getX());
                pokemonEnnemi.setY(Case.getY());
                System.out.println(pokemonEnnemi.getX());
                System.out.println(pokemonEnnemi.getY());
            }
        }
    }
}