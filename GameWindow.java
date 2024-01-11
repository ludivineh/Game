import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameWindow extends JFrame {
    private MapPanel mapPanel;
    private PlayerPanel playerPanel;
    private String playerName;

    public GameWindow(Player player) {
        // FENETRE PRINCIPALE
        setTitle("Pokimon Defenders");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 640);
        setLayout(new BorderLayout());

        // INTRODUCTION
        JLabel introImageLabel = new JLabel(new ImageIcon("IMAGES/intro.png"));
        introImageLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();

                // SI LE JOUEUR CLIQUE SUR "Commencer le jeu" ET LE JEU N'A PAS ENCORE COMMENCÉ
                if (x >= 300 && x <= 500 && y >= 500 && y <= 580) {
                    playerName = JOptionPane.showInputDialog("Entrez votre nom :");
                    if (playerName != null && !playerName.isEmpty()) {
                        player.setName(playerName);
                        remove(introImageLabel);

                        // AFFICHER L'IMAGE "choixniveau.png"
                        JLabel choixNiveauLabel = new JLabel(new ImageIcon("IMAGES/choixniveau.png"));
                        add(choixNiveauLabel, BorderLayout.CENTER);
                        revalidate();
                        repaint();

                        choixNiveauLabel.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                int x = e.getX();
                                int y = e.getY();
                                if (x >= 0 && x <= 400) {
                                    // DEBUT DU JEU MAP1 (Easy Mode)
                                    remove(choixNiveauLabel);
                                    Map gameMap = new Map1(20, 20);
                                    initializeGame(gameMap, player);
                                } else {
                                    // DEBUT DU JEU MAP2 (Difficult Mode)
                                    remove(choixNiveauLabel);
                                    Map gameMap = new Map2(20, 20); // Remplacez Map2 par le nom de votre classe pour le mode difficile
                                    player.setLevel(2);
                                    initializeGame(gameMap, player);
                                }
                            }
                        });
                    }
                }
            }
        });
        add(introImageLabel, BorderLayout.CENTER);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initializeGame(Map gameMap, Player player) {
        mapPanel = new MapPanel(gameMap, player); // création du panneau carte
        playerPanel = new PlayerPanel(player, mapPanel); // création du panneau joueur
        // division de la fenêtre
        // MapPanel à gauche
        // PlayerPanel à droite
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, mapPanel, playerPanel);
        splitPane.setDividerLocation(600);
        splitPane.setEnabled(false);
        getContentPane().add(splitPane, BorderLayout.CENTER);
        revalidate();
        repaint();
        GameEvents events = new GameEvents(player, gameMap);
        events.combatDynamics();
    }
}
    