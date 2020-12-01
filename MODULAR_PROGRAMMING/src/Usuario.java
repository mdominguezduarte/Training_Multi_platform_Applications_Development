
import java.util.regex.Pattern;

/*
 *Clase encargada de gestionar un único usuario.
 */

/**
 *
 * @author Mar DD
 */
public class Usuario {
    
    //Atributos de la clase
    private String nombre;
    private int edad;
    private String DNI;
    
    //Constructor
    public Usuario(){
        
    }
    
    //métodos getter and setters para nombre
    public String getNombre() {
	return nombre;
    }

    public void setNombre(String nombre) {
	this.nombre = nombre;
    }
    
    //métodos getter and setters para edad
    public int getEdad() {
	return edad;
    }

    public void setEdad(int edad) {
	this.edad = edad;
    }
    
    //métodos getter and setters para DNI
    public String getDNI() {
	return this.DNI;
    }
    
    
    // Asigna DNI si éste es válido y devuelve si ha sido asignado o no.
    public boolean setDNI(String DNI) {

	boolean asignadoDni = false;
	if (validarDNI(DNI)) {
            
            this.DNI = DNI;
            asignadoDni = true;
            
	}
                
	return asignadoDni;
    }

    
    //validación DNI adaptando método pág 116 del libro
    public boolean validarDNI(String DNI) { 
        String regex = "^[0-9]{8}-?[A-Z]{1}$"; 
        Pattern pattern = Pattern.compile(regex); 
        return pattern.matcher(DNI).matches();     
    }
    
    
    //Obtener contenido de la clase
    @Override
    public String toString() {
        
	return " Usuario: " + getNombre() + 
                ", edad: " + this.getEdad() + 
                ", DNI: " + this.getDNI();
	}

}
