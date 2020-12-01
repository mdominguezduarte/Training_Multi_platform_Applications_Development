/*
 * 
 * Clase ingreso que hereda tributos de dinero
 * 
 */

/**
 *
 * @author Mar DD
 */
public class Ingreso extends Dinero{
    
    //Constructor
    public Ingreso(double ingreso,String description){
        this.setDinero(ingreso);
        super.setDescription(description);
    }
    
    //Devolver contenido de la clase
    @Override
    public String toString(){
        return " Ingreso: " + super.getDescription() +   
               ", cantidad:" +this.getDinero()+ "\u20AC";
    }
    
}
