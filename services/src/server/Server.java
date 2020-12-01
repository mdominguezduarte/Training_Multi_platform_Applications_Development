/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Se toma Cómo referencia el manual de la asignatura y los documentos de videotutorías
 * @author MARDD
 */
public class Server {
    //declaración de constantes de conexión
    private final int PUERTO =9876;
    //socket correspondiente al resvidor
    private ServerSocket ss;
    //socket correspondiente al cliente
    private Socket s;   
    
    
//Definimos la conexión e iniciamos el cliente en el constructor
    public Server() throws IOException {
        ss = new ServerSocket(PUERTO);
        s = new Socket();
    }
    
    public void iniServer() throws  IOException {
        
        while(true){
            System.out.println("Esperando la conexión del cliente...");
            //En socket, guardamos la petición que llegue al servidor
            s = ss.accept();
            System.out.println("Conexión aceptada\n");
            
            //se solicita nombre, se recibe y se imprime
            PrintWriter pr = new PrintWriter(s.getOutputStream());
            pr.println("Solicita nombre");
            pr.flush();
            System.out.println("Se solicita nombre al cliente");
            
            InputStreamReader  in  = new InputStreamReader(s.getInputStream());
            BufferedReader bf = new BufferedReader(in);
            String str = bf.readLine();
            System.out.println("Respuesta Cliente: "+str+"\n");
            
            
            
             //se solicita número de tareas, se recibe y se imprime
            pr.println("Solicita número de tareas");
            pr.flush();
            System.out.println("Se solicita número de tareas al cliente");
            String tarea = bf.readLine();
            
            //Requerido pasarlo a entero para la descripción y estado de cada tarea
            int nTarea= Integer.parseInt(tarea);
            System.out.println("Respuesta Cliente: "+ nTarea +"\n");
          
           
           
            
            List<String> tareaList = new ArrayList<>();
            
		
		//Solicitamos tareas
           for(int i = 1; i <= nTarea; i++) {        
        
                    
                //se envia tarea y recibir mensaje cliente
                pr.println("Tarea " + i);
                pr.flush();

                //se pide y se recibe descripcion del cliente , qué lo ha solicitado al usuario
                pr.println("Descripcion Tarea " +i+"...");
                pr.flush();
                System.out.println("Se solicita descripción de la tarea "+i+" al cliente...");
                String desTarea = bf.readLine();
                System.out.println("Respuesta Cliente: "+ desTarea +"\n");
                
                 //se pide y se recibe estado del cliente , qué lo ha solicitado al usuario
                pr.println("Estado Tarea " +i+"...");
                pr.flush();
                System.out.println("Se solicita estado de la tarea "+i+" al cliente...");
                String  estadoTarea = bf.readLine();
                System.out.println("Respuesta Cliente: "+ estadoTarea +"\n");
               
                    //Se almacena
                   Tarea iTarea = new Tarea();
                        iTarea.setDescripción(desTarea);
                        iTarea.setEstado(estadoTarea);
                    
                    tareaList.add("Descripción tarea "+i+ ": "+iTarea.getDescripción() + ", Estado tarea "+i+": "+iTarea.getEstado());
                   
		} 
            
            //Se envíalas tareas
            System.out.println("Se informa del envío de tareas al cliente...");
            pr.println("Se procede al envío de tareas...\n");
            pr.flush();
           for(String t:tareaList){
                pr.println(t);
                pr.flush();
                System.out.println(t);
           }
             
           break;  
           
        }                 
        
    }
    
    public void cerrarServidor() throws IOException {
        s.close();
        ss.close();
    }
       
   
}
        
    

