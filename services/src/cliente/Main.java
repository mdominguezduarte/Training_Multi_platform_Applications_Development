/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.io.IOException;

/**
 *
 * @author MarDD
 */
public class Main {
     public static void main(String[] args) throws IOException {
        Client cli = new Client();
        System.out.println("Iniciando cliente...\n");
        
        //Iniciamos la conexión del cliente
        cli.iniciarCliente();
        
        //Se cierra conexión del cliente
        cli.cerrarCliente();
        System.out.println("\nSe cierra conexión.");
     }
    
}
