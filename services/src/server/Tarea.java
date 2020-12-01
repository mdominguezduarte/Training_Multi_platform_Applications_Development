/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

/**
 *Se toma Cómo referencia el manual de la asignatura y los documentos de videotutorías
 * @author MarDD
 */
public class Tarea {
    //Declaración de variables
    private String descripción;
    private String estado;

    
    public Tarea (){
    
    }
    
    //Métodos getter and setters
    
    public String getDescripción() {
        return descripción;
    }

    public void setDescripción(String descripción) {
        this.descripción = descripción;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
    @Override
	public String toString() {
		return "Tarea [descripción=" + descripción + ", estado=" + estado + "]";
	}
  
    
}
