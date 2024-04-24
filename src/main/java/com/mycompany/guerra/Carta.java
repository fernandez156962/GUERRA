/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.guerra;

/**
 *
 * @author alumno
 */
public class Carta {
    
    private int valor;
    private PaloEspanol palo;

    public Carta(int valor, PaloEspanol palo) {
        this.valor = valor;
        this.palo = palo;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public PaloEspanol getPalo() {
        return palo;
    }

    public void setPalo(PaloEspanol palo) {
        this.palo = palo;
    }

    @Override
    public String toString() {
        String nombreValor="";
        switch(valor) {
            case 1:
                nombreValor = "As";
                break;
            case 10:
                nombreValor = "Sota";
                break;
            case 11:
                nombreValor = "Caballo";
                break;
            case 12:
                nombreValor = "Rey";
                break;
            default:
                nombreValor = String.valueOf(valor);
        }
        return nombreValor + " de " + palo;
    }
    
    
    
}
