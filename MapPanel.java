import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;


public class MapPanel extends JPanel {
    private Map gameMap;
    private Image map1Image;
    private Image map2Image;
    private Image degatImage;
    private Image pokedollarsImage;
    private Player player; 
    private Timer animationTimer;

    public MapPanel(Map gameMap, Player player) {
        this.gameMap = gameMap;
        this.player = player;
        
        // chargement des images
        map1Image = new ImageIcon("IMAGES/niveau1b.png").getImage();
        map2Image = new ImageIcon("IMAGES/niveau2.png").getImage();
        pokedollarsImage = new ImageIcon("IMAGES/pokedollars.png").getImage();
        degatImage = new ImageIcon("IMAGES/degat.png").getImage();
        
        // timer for animation
        int animationDelay = 200; 
        animationTimer = new Timer(animationDelay, new ActionListener() {


			@Override
			public void actionPerformed(ActionEvent e) {
                repaint();
				
			}

        });

        // taille du mappanel
        setPreferredSize(new Dimension(map1Image.getWidth(null), map1Image.getHeight(null)));

        // Mouse listener sur la map
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                if (x >= 0 && x < getWidth() && y >= 0 && y < getHeight()) {
                    // Convertir les coordonnées du clic en coordonnées de case
                    int cellX = (x * gameMap.getWidth()) / getWidth();
                    int cellY = (y * gameMap.getHeight()) / getHeight();
                    
                    if (player.getbuyingModeCARAPUCE()) { 
                        handleCarapucePlacement(cellX, cellY);
                    } else if (player.getbuyingModeSNORLAX()) {
                        handleSnorlaxPlacement(cellX, cellY);
                    }
                    else 
                        {
                        // Si le joueur n'est pas en mode achat, vérifier si la case est de type PATH
                        Case clickedCase = gameMap.getCaseAt(cellY, cellX);
                        if (clickedCase.getType() == CaseType.PATH) {
                            handlePrizeClick(cellX,cellY);
                        }
                    }
                }
            }
        });

        
        animationTimer.start();
    }
    
    //  stop the timer when the panel is no longer visible (e.g., when the game is over)
    public void stopAnimation() {
        if (animationTimer != null && animationTimer.isRunning()) {
            animationTimer.stop();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawMap(g,gameMap);
        drawPokemonFriends(g);
        drawPokemonEnnemi(g);
        drawPrizes(g);
    }
    
    // METHODE POUR DESSINER LA MAP
    private void drawMap(Graphics g, Map gameMap) {
        if (gameMap instanceof Map1) {
            g.drawImage(map1Image, 0, 0, this);
        }
        else {
            g.drawImage(map2Image,0,0, this);
        }
        
    }

    // METHODE POUR DESSINER LES POKEMON AMIS
    private void drawPokemonFriends(Graphics g) {
        List<PokemonFriend> pokemonFriendList = player.getPokemonFriendList();
        int cellSizeX = getWidth() / gameMap.getWidth();
        int cellSizeY = getHeight() / gameMap.getHeight();

        for (PokemonFriend pokemonFriend : pokemonFriendList) {
            int x = pokemonFriend.getX();
            int y = pokemonFriend.getY();
            int drawX = x * cellSizeX;
            int drawY = y * cellSizeY;

            String pokemonName = pokemonFriend.getName().toLowerCase();
            String imagePath = "IMAGES/" + pokemonName + ".png";
            Image pokemonImage = new ImageIcon(imagePath).getImage();
            g.drawImage(pokemonImage, drawX, drawY, cellSizeX, cellSizeY, this);
        }
    }

    // METHODE POUR DESSINER LES POKEMON ENNEMIS
    private void drawPokemonEnnemi(Graphics g) {
        ArrayList<PokemonEnnemi> ennemiList = gameMap.getennemiList();
        int cellSizeX = getWidth() /  gameMap.getWidth();
        int cellSizeY = getHeight() / gameMap.getHeight();
    
        for (PokemonEnnemi pokemonEnnemi : ennemiList) {
            int x = pokemonEnnemi.getX();
            int y = pokemonEnnemi.getY();
            int drawX = x * cellSizeX;
            int drawY = y * cellSizeY;
            String pokemonName = pokemonEnnemi.getName().toLowerCase();
            String imagePath = "IMAGES/" + pokemonName + ".png";
            Image pokemonImage = new ImageIcon(imagePath).getImage();
            g.drawImage(pokemonImage, drawX, drawY, cellSizeX, cellSizeY, this);
    
            // dessiner les dégâts lors des attaques
            for (DamageEffect damageEffect : gameMap.getDamageEffects()) {
                if (damageEffect.getX() == x && damageEffect.getY() == y) {
                    int damageDrawX = drawX; 
                    int damageDrawY = drawY; 
                    g.drawImage(degatImage, damageDrawX, damageDrawY, cellSizeX, cellSizeY, this);
                }
            }
        }
    }

    // METHODE POUR DESSINER LES POKEDOLLARS QUI PEUVENT ETRE GAGNES 
    private void drawPrizes(Graphics g) {
        ArrayList<Prize> prizeList = gameMap.getPrizeList();
        int cellSizeX = getWidth() /  gameMap.getWidth();
        int cellSizeY = getHeight() / gameMap.getHeight();
    
        for (Prize prize : prizeList) {
            int x = prize.getX();
            int y = prize.getY();
            int drawX = x * cellSizeX;
            int drawY = y * cellSizeY;
            g.drawImage(pokedollarsImage, drawX, drawY, cellSizeX, cellSizeY, this);
    
        }
    }

    // METHODE POUR PLACER UN CARAPUCE SUR LA MAP
    // le pokemon est ajouté à la liste PokemonFriend du joueur 
    private void handleCarapucePlacement(int x, int y) {
        Case clickedCase = gameMap.getCaseAt(y, x);
        System.out.println("Handling Carapuce placement");
        if (clickedCase.getType() == CaseType.FREE) {
            Carapuce newCarapuce = new Carapuce(x, y); 
            player.addPokemonFriend(newCarapuce); 
            player.setbuyingModeCARAPUCE(false);
            repaint();
        } else {
            System.out.println("Cannot place Carapuce here.");
        }
    }

    private void handleSnorlaxPlacement(int x, int y) {
        Case clickedCase = gameMap.getCaseAt(y, x);
        System.out.println("Handling Snorlax placement");
        if (clickedCase.getType() == CaseType.FREE) {
            Snorlax newSnorlax = new Snorlax(x, y); 
            player.addPokemonFriend(newSnorlax); 
            player.setbuyingModeSNORLAX(false); // Remettez le mode d'achat à false
            repaint();
        } else {
            System.out.println("Cannot place Snorlax here.");
        }
    }

    private void handlePrizeClick(int x, int y) {
        ArrayList<Prize> prizeList = gameMap.getPrizeList();
        Iterator<Prize> iterator = prizeList.iterator();
    
        while (iterator.hasNext()) {
            Prize prize = iterator.next();
            if (prize.getX() == x && prize.getY() == y) {
                iterator.remove(); 
                player.setPokedollars(player.getPokedollars() + prize.getPokedollars());
                repaint();
                System.out.println("joueur possède: " + player.getPokedollars());
                System.out.println("le montant du prix est: " + prize.getPokedollars());
                
                break;
            }
        }
    }

}
