/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juego.excepciones;

/**
 *
 * @author Simon
 */
public class NumeroFueraDeRangoException extends RuntimeException{
    
    public NumeroFueraDeRangoException(String msg){
        super(msg);
    }
    
}
