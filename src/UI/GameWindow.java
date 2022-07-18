/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.awt.Dimension;
import java.io.FileNotFoundException;
import javax.swing.JFrame;
import tron.Game;

/**
 *
 * @author Meeoro
 */
public class GameWindow {
    
    private static JFrame frame;
    private Game game;
    
    
    public GameWindow(String name1, String name2, String color1, String color2, String map) throws FileNotFoundException {
        frame = new JFrame("Tron game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game = new Game(name1, name2, color1, color2, map);
        
        frame.getContentPane().add(game);
        frame.setPreferredSize(new Dimension(game.getWidth(), game.getHeight()));
        frame.setSize(new Dimension(game.getWidth(), game.getHeight()));
        frame.setResizable(false);
        //frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        frame.setUndecorated(true);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
    }
    
    public static void hidePanel(){
        //frame.setVisible(false);
        frame.dispose();
    }
}
