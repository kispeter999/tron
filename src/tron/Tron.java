/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tron;

import UI.MainMenu;

/**
 *
 * @author Meeoro
 */
public class Tron {

    private static MainMenu mainMenu;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        mainMenu = new MainMenu();
        mainMenu.setVisible(true);
        mainMenu.setLocationRelativeTo(null);
        //new MainMenu();
    }
    
}
