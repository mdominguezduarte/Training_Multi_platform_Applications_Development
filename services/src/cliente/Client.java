/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 *  Se toma oómo referencia el manual de la asignatura y los documentos de videotutorías
 * @author MarDD
 */
public class Client{
    //declaración de constantes de conexión
    //socket correspondiente al cliente
    private Socket s;
    
    //por el enunciado entiendo que el cliente envía directamente el número de tareas, ya que no lo solicita al usuario
    static final int tarea = 2;

    
    //constructor 
    public Client() throws IOException {
        s = new Socket("localhost", 9876);
    }
    
    public void iniciarCliente() throws IOException {
       
        //Iniciar la entrada de datos por parte del servidor
        InputStreamReader  in  = new InputStreamReader(s.getInputStream());
        
        //Impresion mensaje servidor ,y envío de nombre al servidor
        BufferedReader bf = new BufferedReader(in);
        String str = bf.readLine();
        System.out.println("Respuesta Servidor: "+str);
        
        PrintWriter pr = new PrintWriter(s.getOutputStream());
        pr.println("MMDD");
        pr.flush();
        System.out.println("Nombre enviado al servidor\n");
        
        
        //se recibe mensaje acerca numero  tareas desde servidor
        str = bf.readLine();
        System.out.println("Respuesta Servidor: "+str);
        pr.println(tarea);
        pr.flush();
        System.out.println("Número de tareas enviado al servidor\n");
        
      
        for (int i =1; i <= tarea ; i++){ 
            //imprime número tarea
            str = bf.readLine();
            System.out.println("Respuesta Servidor: "+str);
        
            
            //solicitar descripcion tarea al usuario y enviarlo al servidor
            String desc = bf.readLine();
            System.out.println("Respuesta Servidor: "+ desc);
            Scanner solDescrip = new Scanner (System.in); 
            System.out.println ("Introduzca descripción tarea " + i +": ");
            String desTarea = solDescrip.nextLine ();
            pr.println(desTarea);
            pr.flush();
            System.out.println("Descripción de tarea "+i+" enviado al servidor\n");
           
            
            //solicitar estado tarea al usuario y enviarlo al servidor
            String estado = bf.readLine();
            System.out.println("Respuesta Servidor: "+ estado); 
            System.out.println ("Introduzca estado tarea " + i +": ");
            String estadoTarea = solDescrip.nextLine ();
            pr.println(estadoTarea);
            pr.flush();
            System.out.println("Estado de tarea "+i+" enviado al servidor\n");
            
   
        }
        String info = bf.readLine();
        System.out.println("Respuesta Servidor: "+ info); 
       
        bf.readLine();
        
        //Se recibe las tareas enviadas por el servidor
        for (int i =1; i <= tarea ; i++){    
            String listadoTareas = bf.readLine();
            System.out.println(listadoTareas);
        }
        
        //Cierro salidas y entradas y conexión al socket
       // outServer.close();
        //introServer.close();
        ;
        
    }
    
    public void cerrarCliente() throws IOException {
        s.close();
    }
    
}
