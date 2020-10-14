/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juego;

import juego.controlador.Controlador;
import juego.modelo.Juego;
import juego.vista.Vista;

/**
 *
 * @author Simon
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Vista ventanas = new Vista("Juego Adivinador");
        Juego juego = new Juego();
        Controlador c = new Controlador(ventanas, juego);
        c.iniciar();
    }
    
}
