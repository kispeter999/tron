/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tron;

import UI.GameWindow;
import UI.PlayMenu;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.Timer;

/**
 *
 * @author Meeoro
 */
public class Game extends JPanel{
    
    private ArrayList<Player> line1;
    private ArrayList<Player> line2;
    private Player player1;
    private Player player2;
    private int gameWidth, gameHeight;
    private int playerX, playerY;
    private String playerDirection;
    private String player1Name, player2Name, player1Color, player2Color;
    private int score = 0;
    private Image background;
    private PlayMenu playMenu;
    
    
    public Game(String name1, String name2, String color1, String color2, String map) throws FileNotFoundException{
        super();
        line1 = new ArrayList<Player>();
        line2 = new ArrayList<Player>();
        this.player1Name = name1;
        this.player1Color = color1;
        this.player2Name = name2;
        this.player2Color = color2;
        //System.out.println(color1);
        //System.out.println(color2);
        
        
        Scanner sc = new Scanner(new FileReader("src/resources/maps/" + map + ".txt"));
        gameHeight = sc.nextInt();
        gameWidth = sc.nextInt();
        gameWidth = 800;
        gameHeight = 800;
        
        playerY = sc.nextInt();
        playerX = sc.nextInt();
        playerDirection = sc.next();
        player1 = new Player(playerX, playerY, 10, 10, player1Color);
        switch(playerDirection){
            case "up": player1.moveUp();
                break;
            case "down": player1.moveDown();
                break;
            case "right": player1.moveRight();
                break;
            case "left": player1.moveLeft();
                break;
        }
        //System.out.println("sese:" + player1Color);
        //System.out.println(player1.getColor() + " xd1");
        
        
        playerY = sc.nextInt();
        playerX = sc.nextInt();
        playerDirection = sc.next();
        player2 = new Player(playerX, playerY, 10, 10, player2Color);
        switch(playerDirection){
            case "up": player2.moveUp();
                break;
            case "down": player2.moveDown();
                break;
            case "right": player2.moveRight();
                break;
            case "left": player2.moveLeft();
                break;
        }
        //System.out.println("sese2:" + player2Color);
        //System.out.println(player2.getColor() + " xd2");
        
        background = new ImageIcon("src/resources/maps/" + map + ".jpg").getImage();
        makeKeyBinds();
        startGame();
    }
    
    private void makeKeyBinds(){
        this.getInputMap().put(KeyStroke.getKeyStroke('w'), "player1 up");
        this.getActionMap().put("player1 up", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                player1.moveUp();
                repaint();
            }
        });
        
        this.getInputMap().put(KeyStroke.getKeyStroke('s'), "player1 down");
        this.getActionMap().put("player1 down", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                player1.moveDown();
                repaint();
            }
        });
        
        this.getInputMap().put(KeyStroke.getKeyStroke('a'), "player1 left");
        this.getActionMap().put("player1 left", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                player1.moveLeft();
                repaint();
            }
        });
        
        this.getInputMap().put(KeyStroke.getKeyStroke('d'), "player1 right");
        this.getActionMap().put("player1 right", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                player1.moveRight();
                repaint();
            }
        });
        
        this.getInputMap().put(KeyStroke.getKeyStroke("UP"), "player2 up");
        this.getActionMap().put("player2 up", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                player2.moveUp();
                repaint();
            }
        });
        
        this.getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "player2 down");
        this.getActionMap().put("player2 down", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                player2.moveDown();
                repaint();
            }
        });
        
        this.getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "player2 left");
        this.getActionMap().put("player2 left", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                player2.moveLeft();
                repaint();
            }
        });
        
        this.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "player2 right");
        this.getActionMap().put("player2 right", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                player2.moveRight();
                repaint();
            }
        });
    }
    
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(background, 0, 0, gameWidth, gameHeight, null);
        for (Player linee : line1){
            linee.draw(g);
        }
        
        for (Player linee2 : line2){
            linee2.draw(g);
        }
        //this.player1.draw(g);
        //this.player2.draw(g);
    }
    
    public boolean movedOutOfPlayArea(Player p){
            int x = p.getX();
            int y = p.getY();
            //int height = 20;
            return (x < 0 || x > gameWidth || y < 0 || y > gameWidth);
        }
    
    public void startGame(){
        Timer timer = new Timer(25, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                score++;
                Player player1Line = new Player(player1.getX(), player1.getY(), 10, 10, player1Color);
                if(movedOutOfPlayArea(player1)){
                    ((Timer)ae.getSource()).stop();
                    JLabel winMessage = new JLabel(player2Name + " is victorious!");
                    winMessage.setHorizontalAlignment(SwingConstants.CENTER);
                    JOptionPane.showMessageDialog(null, winMessage, "GAME OVER", JOptionPane.PLAIN_MESSAGE);
                    endGame(player2Name);
                    return;
                }else{
                    line1.add(player1Line);
                    player1.move();
                }
                
                Player player2Line = new Player(player2.getX(), player2.getY(), 10, 10, player2Color);
                if(movedOutOfPlayArea(player2)){
                    ((Timer)ae.getSource()).stop();
                    JLabel winMessage = new JLabel(player1Name + " is victorious!");
                    winMessage.setHorizontalAlignment(SwingConstants.CENTER);
                    JOptionPane.showMessageDialog(null, winMessage, "GAME OVER", JOptionPane.PLAIN_MESSAGE);
                    endGame(player1Name);
                    return;
                }else{
                    line2.add(player2Line);
                    player2.move();
                }
                repaint();
                if (player1.intersects(player2)) {
                    ((Timer)ae.getSource()).stop();
                    JLabel winMessage = new JLabel("No winner, you're both bad");
                    winMessage.setHorizontalAlignment(SwingConstants.CENTER);
                    JOptionPane.showMessageDialog(null, winMessage, "GAME OVER", JOptionPane.PLAIN_MESSAGE);
                    return;
                }
                
                for (Player lines1 : line1){
                    if (player2.intersects(lines1)){
                        ((Timer)ae.getSource()).stop();
                        JLabel label = new JLabel(player1Name + " is victorious!");
                        label.setHorizontalAlignment(SwingConstants.CENTER);
                        JOptionPane.showMessageDialog(null, label,"GAME OVER", JOptionPane.PLAIN_MESSAGE);
                        endGame(player1Name);
                        return;
                    }
                }
                
                for (Player lines2 : line2){
                    if (player1.intersects(lines2)){
                        ((Timer)ae.getSource()).stop();
                        JLabel label = new JLabel(player2Name + " is victorious!");
                        label.setHorizontalAlignment(SwingConstants.CENTER);
                        JOptionPane.showMessageDialog(null, label,"GAME OVER", JOptionPane.PLAIN_MESSAGE);
                        endGame(player2Name);
                        return;
                    }
                }
                
                for (int i = 0; i < line1.size()-2; i++){
                    if (player1.intersects(line1.get(i))){
                        ((Timer)ae.getSource()).stop();
                        JLabel label = new JLabel(player2Name + " is victorious!");
                        label.setHorizontalAlignment(SwingConstants.CENTER);
                        JOptionPane.showMessageDialog(null, label,"GAME OVER", JOptionPane.PLAIN_MESSAGE);
                        endGame(player2Name);
                        return;
                    }
                }
                
                for (int i = 0; i < line2.size()-2; i++){
                    if (player2.intersects(line2.get(i))){
                        ((Timer)ae.getSource()).stop();
                        JLabel label = new JLabel(player1Name + " is victorious!");
                        label.setHorizontalAlignment(SwingConstants.CENTER);
                        JOptionPane.showMessageDialog(null, label,"GAME OVER", JOptionPane.PLAIN_MESSAGE);
                        endGame(player1Name);
                        return;
                    }
                }
            }
        });
        
        timer.setRepeats(true);
        timer.setCoalesce(true);
        //timer.setInitialDelay(0);
        timer.start();
    }
    
    public void endGame(String winner){
        //database implementaion for highscores
        //playMenu = new PlayMenu(player1Name, player2Name, player1Color, player2Color); 
        //playMenu.setVisible(true);
        //System.exit(0);
        GameWindow.hidePanel();
    }
    
    public int getWidth(){
        return gameWidth;
    }
    
    public int getHeight(){
        return gameHeight;
    }
    
    class NewFrameListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            player1.move();
            repaint();
            player2.move();
            repaint();
        }
    }
}
