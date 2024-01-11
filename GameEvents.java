import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class GameEvents {
    private Player player;
    private Map ourMap;
    private Timer combatTimer;

    public GameEvents(Player player, Map ourMap) {
        this.ourMap = ourMap;
        this.player = player;
        initializeCombatTimer();
    }

    private void initializeCombatTimer() {
        int delay = 500; // 0,5 seconds
        combatTimer = new Timer(delay, new ActionListener() {
            private int step = 0; // alternance pas/mouvements

            @Override
            public void actionPerformed(ActionEvent e) {
                if (step % 2 == 0) {
                    triggerAttacks();
                } else {
                    ourMap.updatePokemonPositions(step / 2);
                }

                if (ourMap.getennemiList().isEmpty()) {
                    showMessageBox("Partie gagnée!");
                    combatTimer.stop(); 
                }
                if (isEnemyOnLastPathTile()) {
                    System.out.println("Partie perdue!");
                    showMessageBox("Partie perdue!");
                    combatTimer.stop();
                }

                step++;
            }
        });
    }

    private void showMessageBox(String message) {
        Object[] options = {"Fermer le jeu"};
        int response = JOptionPane.showOptionDialog(null, message, "Fin de la partie",
                                                   JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE,
                                                   null, options, options[0]);
    
            System.exit(0);
        }


    public void combatDynamics() {
        combatTimer.start();
    }

    public void triggerAttacks() {
        List<PokemonEnnemi> ennemisASupprimer = new ArrayList<>();
    
        for (PokemonFriend friend : player.getPokemonFriendList()) {
            PokemonEnnemi ennemiLePlusProche = findClosestEnemy(friend);
    
            if (ennemiLePlusProche != null && friend.attack(ennemiLePlusProche)) {
                DamageEffect damageEffect = new DamageEffect(ennemiLePlusProche.getX(), ennemiLePlusProche.getY(), 200);
                ourMap.getDamageEffects().add(damageEffect);
    
                if (ennemiLePlusProche.getHealthPoints() <= 0) {
                    ennemisASupprimer.add(ennemiLePlusProche);
                }
            }
        }
    
        for (PokemonEnnemi ennemiASupprimer : ennemisASupprimer) {
            ourMap.getennemiList().remove(ennemiASupprimer);
            handleEnnemyDeath(ennemiASupprimer);
        }
    }
    
    private PokemonEnnemi findClosestEnemy(PokemonFriend friend) {
        List<PokemonEnnemi> ennemiList = ourMap.getennemiList();
        double minDistance = Double.MAX_VALUE;
        PokemonEnnemi ennemiLePlusProche = null;
    
        for (PokemonEnnemi ennemi : ennemiList) {
            double distance = calculateDistance(friend.getX(), friend.getY(), ennemi.getX(), ennemi.getY());
    
            if (distance < minDistance) {
                minDistance = distance;
                ennemiLePlusProche = ennemi;
            }
        }
    
        return ennemiLePlusProche;
    }
    
    private double calculateDistance(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    public void handleEnnemyDeath(PokemonEnnemi enemy) {
        ourMap.getennemiList().remove(enemy);
        if (!ourMap.getennemiList().isEmpty()) {
            Prize prize = new Prize(enemy.getX(), enemy.getY(), enemy.getPokedollarsCollected());
            ourMap.getPrizeList().add(prize);
        
            // timer pour supprimer le prix après 8 secondes
            Timer removeTimer = new Timer(8000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ourMap.getPrizeList().remove(prize);
                }
            });
            removeTimer.setRepeats(false); 
            removeTimer.start();
            } 

    }
    

    private boolean isEnemyOnLastPathTile() {
        LinkedHashSet<Case> chemin = ourMap.getChemin();
        ArrayList<Case> cheminArrayList = new ArrayList<>(chemin);
        Case lastPathTile = cheminArrayList.get(cheminArrayList.size() - 1);

        for (PokemonEnnemi enemy : ourMap.getennemiList()) {
            Case enemyCase = ourMap.getCaseAt(enemy.getY(), enemy.getX());
            if (enemyCase.getX() == lastPathTile.getX() && enemyCase.getY() == lastPathTile.getY()) {
                return true;
            }
        }
        return false;
    }

}
