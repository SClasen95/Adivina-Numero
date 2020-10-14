/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juego.modelo;

import java.util.Random;
import juego.excepciones.NumeroFueraDeRangoException;

/**
 *
 * @author Simon
 */
public class Juego {

    private final int NUMERO;
    private int valMin;
    private int valMax;
    private int cantVidasRestantes;
    private final int CANT_VIDAS_INICIALES;
    private final String REGLAS;

    public Juego() {
        valMin = 1;
        valMax = 100;
        CANT_VIDAS_INICIALES = 7;
        cantVidasRestantes = CANT_VIDAS_INICIALES;
        NUMERO = generarNumero();
        REGLAS = "ESTAS SON LAS REGLAS\n-El objetivo del juego es adivinar un numero generado por la computadora.\n-Tenes 7 intentos.\n-El numero puede ser cualquiera entre el " + getValMin() + " y el " + getValMax() + ".";
    }

    private int generarNumero() {
        Random rand = new Random();
        int ret = rand.nextInt((valMax - valMin) + 1) + valMin;
        return ret;
    }

    public boolean validarNumero(int n) {
        if (n < valMin || n > valMax) {
            throw new NumeroFueraDeRangoException("El numero ingresado no esta dentro del rango esperado.");
        }
        return true;
    }

    public boolean hayVidas() {
        return cantVidasRestantes > 0;
    }

    public Resultado procesarAdivinanza(int adivinado) {
        Resultado ret;
        if (NUMERO > adivinado) {
            ret = Resultado.MAYOR;
            valMin = adivinado + 1;
            
        } else if (NUMERO < adivinado) {
            ret = Resultado.MENOR;
            valMax = adivinado - 1;
            
        } else {
            ret = Resultado.GANASTE;        
        }
        restarVida();
        
        return ret;
    }

    private void restarVida() {
        cantVidasRestantes--;
    }

    public int getValMin() {
        return valMin;
    }

    public int getValMax() {
        return valMax;
    }

    public int getCantVidasRestantes() {
        return cantVidasRestantes;
    }

    public int getCantVidasIniciales() {
        return CANT_VIDAS_INICIALES;
    }

    public int getNumero() {
        return NUMERO;
    }
    public int getVidasUsadas(){
        return CANT_VIDAS_INICIALES-cantVidasRestantes;
    }

    public String getReglas() {
        return REGLAS;
    }

}
