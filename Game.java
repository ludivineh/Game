import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

public class Game{
	
    public static void main(String[] args) {
        Map ourMap = new Map1(20,20);
        Player player = new Player();
        GameWindow window = new GameWindow(player);
    }
}
