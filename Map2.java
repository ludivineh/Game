public class Map2 extends Map {
    public Map2(int width, int height) {
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
        setPath(19, 16, 14, 16);   
        setPath(14, 16, 14, 2);   
        setPath(14, 2, 10, 2);   
        setPath(10, 2, 10, 11);  
        setPath(10, 11, 3, 11);  
        setPath(3, 11, 3, 1);  

    }

    @Override
    public void initializeEnnemi() {
        int x = this.chemin.toArray(new Case[0])[0].getX();
        int y = this.chemin.toArray(new Case[0])[0].getY();
        PokemonEnnemi E1 = new Haunter(x,y);
        this.ennemiList.add(E1);
        PokemonEnnemi E7 = new Gengar(x,y);
        this.ennemiList.add(E7);
        PokemonEnnemi E2 = new Haunter(x,y);
        this.ennemiList.add(E2);
        PokemonEnnemi E3 = new Gengar(x,y);
        this.ennemiList.add(E3);
        PokemonEnnemi E4 = new Haunter(x,y);
        this.ennemiList.add(E4);
        PokemonEnnemi E8 = new Gengar(x,y);
        this.ennemiList.add(E8);
        PokemonEnnemi E5 = new Haunter(x,y);
        this.ennemiList.add(E5);
        PokemonEnnemi E6 = new Gengar(x,y);
        this.ennemiList.add(E6);
    }

    @Override
    public void updatePokemonPositions(int pas) {
        for (PokemonEnnemi pokemonEnnemi : ennemiList) {
            int index = ((pas - (ennemiList.indexOf(pokemonEnnemi)*4)));
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