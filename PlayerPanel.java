import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayerPanel extends JPanel {
    private JLabel playerNameLabel;
    private JLabel playerLevelLabel;
    private JLabel playerDollarsLabel;
    private JLabel pokedollarIconLabel;
    private JButton buyCarapuceButton;
    private JButton buySnorlaxButton;
    private JLabel messageLabel;
    private Player player;

    public PlayerPanel(Player player, MapPanel mapPanel) {
        this.player = player;
        messageLabel = new JLabel("");
        messageLabel.setForeground(Color.RED); 
        add(messageLabel);

        setLayout(new FlowLayout(FlowLayout.CENTER));

        // Player information labels
        playerNameLabel = new JLabel("Joueur : " + player.getName());
        playerLevelLabel = new JLabel("Niveau : " + player.getLevel());
        playerDollarsLabel = new JLabel("Pokédollars : " + player.getPokedollars());
        ImageIcon pokedollarIcon = new ImageIcon("IMAGES/pokedollarplayer.png");
        pokedollarIconLabel = new JLabel(pokedollarIcon);

        add(playerNameLabel);
        add(playerLevelLabel);
        add(playerDollarsLabel);
        add(pokedollarIconLabel);

        // BOUTONS POUR ACHAT DE NOUVEAUX POKEMONS
        ///////////////// carapuce ////////////////////
        ImageIcon carapuceIcon = new ImageIcon("IMAGES/smallcarapuce.png");
        buyCarapuceButton = new JButton("Carapuce (30 pokédollars)",carapuceIcon);
        buyCarapuceButton.setVerticalTextPosition(SwingConstants.BOTTOM); 
        buyCarapuceButton.setHorizontalTextPosition(SwingConstants.CENTER);
        buyCarapuceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buyCarapuce();
            }
        });

        //////////////// ronflex //////////////////////
        ImageIcon snorlaxIcon = new ImageIcon("IMAGES/smallsnorlax.png");
        buySnorlaxButton = new JButton("Ronflex (100 pokédollars)",snorlaxIcon);
        buySnorlaxButton.setVerticalTextPosition(SwingConstants.BOTTOM); 
        buySnorlaxButton.setHorizontalTextPosition(SwingConstants.CENTER);
        buySnorlaxButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buySnorlax();
            }
        });

        JLabel boutiqueLabel = new JLabel("BOUTIQUE");
        boutiqueLabel.setFont(new Font("Arial", Font.BOLD, 14)); 
        add(boutiqueLabel);
        // Ajout des boutons d'achat
        add(buyCarapuceButton);
        add(buySnorlaxButton);

        // rafraichissement du panneau toutes les secondes
        Timer timer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updatePlayerInfo();
            }
        });

        timer.start(); // Démarrez le timer
    }

    private void buyCarapuce() {
        System.out.println("Buying Carapuce");
        Carapuce carapuceToBuy = new Carapuce(0, 0); // Create a new Carapuce
        if (player.buy(carapuceToBuy)) { // check if enough pokedollars
            updatePlayerInfo();
            player.setbuyingModeCARAPUCE(true);
        } else {
            setMessage("Pas assez d'argent !");
        }
    }
    
    private void buySnorlax() {
        System.out.println("Buying Snorlax");
        Snorlax snorlaxToBuy = new Snorlax(0, 0); // Create a new Snorlax
        if (player.buy(snorlaxToBuy)) { // check if enough pokedollars
            updatePlayerInfo();
            player.setbuyingModeSNORLAX(true);
        } else {
            setMessage("Pas assez d'argent !");
        }
    }

    public void setMessage(String message) {
        messageLabel.setText(message);
    }

    // METHODE POUR METTRE À JOUR LES INFOS DU JOUEUR
    public void updatePlayerInfo() {
        playerNameLabel.setText("Joueur : " + player.getName());
        playerLevelLabel.setText("Niveau : " + player.getLevel());
        playerDollarsLabel.setText("Pokédollars : " + player.getPokedollars());
    }
}
