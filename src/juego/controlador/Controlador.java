/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juego.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import juego.excepciones.NumeroFueraDeRangoException;
import juego.modelo.Juego;
import juego.modelo.Resultado;
import juego.vista.Vista;

/**
 *
 * @author Simon
 */
public class Controlador {

    private Vista miVista;
    private Juego miJuego;

    public Controlador(Vista ventana, Juego miJuego) {
        this.miVista = ventana;
        this.miJuego = miJuego;
    }

    public void iniciar() {
        this.miVista.setVisible(true);
        this.miVista.addHandlerActionListenerAdivinar(new Adivinar());
        this.miVista.addHandlerActionListenerReiniciar(new Reiniciar());
        this.miVista.addHandlerActionListenerReglas(new Reglas());
        this.miVista.actualizarPista("Nuevo juego");
        this.miVista.mostrarIntentos(this.miJuego.getCantVidasRestantes());
    }

    private void procesarAdivinanza() {
        Resultado resultado = null;
        
        try {
            int numeroAdivinado = Integer.parseInt(miVista.getNumeroIngresado());
            if (miJuego.validarNumero(numeroAdivinado)) {
                resultado = miJuego.procesarAdivinanza(numeroAdivinado);
            }
            miVista.informarResultado(miJuego.getVidasUsadas(), miJuego.getNumero(), miJuego.hayVidas(), resultado.name());
            if (resultado.equals(Resultado.GANASTE) || !miJuego.hayVidas()) {
                reiniciar();
            }
        } catch (NumberFormatException nfe) {
            miVista.mostrarMensajeError("El caracter ingresado no es un numero entero.");
        } catch (NumeroFueraDeRangoException nfdre) {
            miVista.mostrarMensajeError(nfdre.getMessage());
        }
        miVista.limpiarNumero();
        miVista.mostrarIntentos(miJuego.getCantVidasRestantes());
    }

    private void reiniciar() {
        this.miJuego = new Juego();
        this.miVista.reiniciar(miJuego.getCantVidasRestantes());
    }

    private class Reglas implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            miVista.mostrarMensaje(miJuego.getReglas());
        }
    }

    private class Reiniciar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            reiniciar();
        }
    }

    private class Adivinar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            procesarAdivinanza();
        }
    }
}
