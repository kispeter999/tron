/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tron;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.JPanel;

/**
 *
 * @author Meeoro
 */
public class Player extends JPanel {
    
    private int x, y, w, h;
    private String direction;
    private String givenColor;
    private Color color;
    
    public Player(int x, int y, int w, int h, String givenColor){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.givenColor = givenColor;
        //System.out.println(this.givenColor + " lmfao");
        
        if (givenColor == "RED"){color = Color.RED;}
        else if (givenColor == "BLU") {color = Color.BLUE;}
        else if (givenColor == "GRN") {color = Color.GREEN;}
        else if (givenColor == "BLK") {color = Color.BLACK;}
        else if (givenColor == "WHT") {color = Color.WHITE;}
        else {color = Color.ORANGE;}
        
//        switch (this.givenColor){
//            case "RED": color = Color.RED;
//            case "BLU": color = Color.BLUE;
//            case "GRN": color = Color.GREEN;
//            case "BLK": color = Color.BLACK;
//            case "WHT": color = Color.WHITE;
//            default: color = Color.ORANGE;
//        }
    }
    
    public Color getColor(){
        return color;
    }
    
    public boolean intersects(Player p){
        Rectangle r1 = new Rectangle(x, y ,w ,h);
        Rectangle r2 = new Rectangle(p.x, p.y, p.w, p.h);
        return r1.intersects(r2);
    }
    
    public void draw(Graphics g){
        super.paintComponents(g);
        g.setColor(color);
        g.drawRect(x, y, w, h);
        //System.out.println(color);
        g.fillRect(x, y, w, h);

    }
    
    public void move(){
        if (direction == "up"){
            this.y = y - h;
        }
        else if (direction == "down"){
            this.y = y + h;
        }
        else if (direction == "left"){
            this.x = x - h;
        }
        else if (direction == "right"){
            this.x = x + h;
        }
    }
    
    public void moveUp(){
        if (direction != "down"){
            direction = "up";
        }
    }
    
    public void moveDown(){
        if (direction != "up"){
            direction = "down";
        }
    }
    
    public void moveLeft(){
        if (direction != "right"){
            direction = "left";
        }
    }
    
    public void moveRight(){
        if (direction != "left"){
            direction = "right";
        }
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
}
