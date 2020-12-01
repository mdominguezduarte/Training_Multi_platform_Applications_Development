/*

 */
package server;

import java.io.IOException;

/**
 * Se toma cómo referencia el manual de la asignatura y los documentos de videotutorías
 * @author MarDD
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        //Definimos el objeto servidor
        Server serv = new Server();
        System.out.println("Iniciando servidor...");
        //Iniciamos el servidor
        serv.iniServer();
        
       //Se cierra servidor
        serv.cerrarServidor();
        System.out.println("\nSe cierra conexión.");
    }
    
}
