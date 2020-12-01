/*
 * 
 * Definición de la clase Gasto que hereda atributos de la clase Dinero
 * 
 */

/**
 *
 * @author Mar DD 
 */
public class Gasto extends Dinero{
    //Constructor
   
    public Gasto(double gasto,String description){
        this.setDinero(gasto);
        super.setDescription(description);
        
        
    }
    
    //Devolver contenido de la clase
    @Override
    public String toString(){
        return " Gasto: " + super.getDescription() +   
               ", cantidad:" +this.getDinero()+"\u20AC"; 
    }
    
}
