
import java.util.ArrayList;
import java.util.List;

/*
 * 
 * Clase que gestiona los movimientos de dinero
 */

/**
 *
 * @author Mar DD
 */
public class Cuenta{
    private double saldo;
    private Usuario usuario;
    List<Gasto> gastos;
    List<Ingreso> ingresos;
    
    //Consctructor con parámetro usuarioy saldo a 0
    //Se inicializa también los ingresos y los gastos
    Cuenta(Usuario usuario){
        this.usuario = usuario;
        this.saldo = 0.0;
        this.ingresos = new ArrayList<>();
        this.gastos = new ArrayList<>();
    }
    
    //métodos getter y setter para usuario
    public Usuario getUsuario(){
        return this.usuario;
    }
    public void setUsuario(Usuario usuario){
        this.usuario = usuario;
    }
    

    //método añadir ingreso y mostrar el saldo
    public double addIngresos(String description, double cantidad){
        ingresos.add(new Ingreso(cantidad,description));
        this.saldo += cantidad;
        return this.getSaldo();
    }
    
    //métodos getter y setter para saldo
    public double getSaldo(){
        return this.saldo;
    }
    public void setSaldo(double saldo){
        this.saldo = saldo;
    }
    
    //método añadir gasto
    public double addGastos(String description, double cantidad) throws GastoException{
        
        
        if (this.getSaldo()<cantidad){
            throw new GastoException();
        }
            
        gastos.add(new Gasto(cantidad, description));
        this.saldo -= cantidad;
       
        
        return this.getSaldo();
    }

    //obtener ingreso
    public List<Ingreso>  getIngresos(){
        return ingresos;
    }
    
    //obtener gasto
    public List<Gasto>  getGastos(){
        return gastos;
    }
    
    //Obtener info de la clase
    @Override
    public String toString() {
        return "Usuario: " + this.getUsuario() +
               ", saldo: "+ this.getSaldo();
    }   
}
