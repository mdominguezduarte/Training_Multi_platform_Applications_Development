/*
 * Definición clase abstracta dinero.
 *
 * Esta clase contiene atributos métodos
 * que no pueden ser instanciados directamente.
 */

/**
 * @author Mar DD
 */
public abstract class Dinero {
    
    //Atributos dinero y descripción
    protected double dinero;
    protected String description;
    
    //métodos getter y setter para dinero
    public double getDinero(){
        return dinero;
    }
    public void setDinero(double dinero){
        this.dinero = dinero;
    }
    
    //métodos getter y setter para descripción
    public String getDescription(){
        return description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    
}
