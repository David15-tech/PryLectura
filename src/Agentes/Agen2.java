/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Agentes;

import InitialAgentes.Contenedor;
import Interface.GUIpry;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;

/**
 *
 * @author davpa
 */
public class Agen2 extends Agent{

    
    //necesita un comportamiento
    @Override
    public void setup() {//metodo que se ejecuta siempre primero, todo lo que agamos fuera hay que meterlo a llamar aqui
        //configuracion inicial
        
        
        //CyclicBehaviour beha = new comportamiento(this);
        addBehaviour(new comportamiento());

        

        
    }
    //tenemos los comportamientos, que se pueden controlar del agente sea ciclico secuencial, etc
    //podemos crear una clase(interna subclase) solo de comportamientos

    @Override
    protected void takeDown() {//ultima accion antes de morir, este se ejecuta una vez llamado doDelete()
        Contenedor c = (Contenedor)getArguments()[0];//obtenemos el conocimiento
        int i = Integer.parseInt(getArguments()[1].toString());
        i++;
        c.crearHijos("AgenteHijo"+i, new Object[]{c,i});//habra de hacer un bucle para que el alias vaya cambiando, con el i++ ya se hizo
        System.out.println("Muere");
    }

    
    class comportamiento extends Behaviour{//comportamiento controlado

        boolean terminado = false;
        
        @Override
        public void action() {
            //todo lo que necesite hacer el agente, red neuronal, AG, Bayes, if-else
            
            GUIpry Ag2Gui = (GUIpry)getArguments()[0];
            
            
        }

        @Override
        public boolean done() {
            return terminado;
        }

        
        
    }
}
