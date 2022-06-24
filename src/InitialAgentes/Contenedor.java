/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package InitialAgentes;

import Interface.GUIpry;
import Agentes.Agen1;
import Agentes.Agen2;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentContainer;
import jade.wrapper.StaleProxyException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author davpa
 */
public class Contenedor {
    
    
    //var global para
    AgentContainer agentContainer;
    GUIpry gui;
    public void contenedor (){
        //rutina en tiempo de ejecucion
        jade.core.Runtime runtime = jade.core.Runtime.instance(); //contenedor en tiempio de ejecucion
        
        Profile p = new ProfileImpl(null, 1099, null); //null corre la ip de la maquina, el puerto es cualquiera, y el id
        agentContainer =runtime.createMainContainer(p);//configuracion del perfil de contenedor
        //solo se puede usar un solo puerto en cada ejecucion
        
        
        //CONTENEDOR PRINCIPAL QUE INICIA ANTES DE Nada
        //y ahora agregamos agentes
        agregarAgentes();
        
    }
    private void agregarAgentes(){
        try {
            //en esta siguiente linea se agrega el agente
            agentContainer.createNewAgent("Ag2", Agen2.class.getName(), new Object[] {gui}).start(); 
            agentContainer.createNewAgent("Ag1", Agen1.class.getName(), new Object[] {gui}).start(); //nombre(nick), nombre de la clase donde pertenece                                                                                  // con .start() ya se da vida al agente
            
             //creamos otro agente
            
            
            
            
            //NOTA: de la siguiente manera, estaria mal, requerimos que el receptor nasca primero, mas adelante se vera que hay que bloquearlo hasta que los que los emisores se creen
        } catch (StaleProxyException ex) {
            Logger.getLogger(Contenedor.class.getName()).log(Level.SEVERE, null, ex);//en caso de que no sea un agente
        }
    }
    
    public void crearHijos(String alias,Object[] conocimiento){
        try {
            agentContainer.createNewAgent(alias, Agen1.class.getName(), conocimiento).start();
        } catch (StaleProxyException ex) {
            Logger.getLogger(Contenedor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
