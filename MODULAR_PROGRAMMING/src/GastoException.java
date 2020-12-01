/*
 * Clase que hereda características de la clase Excepción 
 * Más información en https://docs.oracle.com/javase/7/docs/api/java/lang/Exception.html
 */

/**
 *
 * @author Mar DD
 */
public class GastoException extends Exception{
    
    public GastoException() {
	super("No tiene saldo suficiente para el gasto solicitado");
    }


}
