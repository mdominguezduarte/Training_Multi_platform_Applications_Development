

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

/*
 * Programa principal donde se define como el usuario interactúa con la consola y sus posibles opciones.
 *
 * La pac abarca los temas 1, 2, 3 y 4 ; por lo que no se  han incluído prácticamente validaciones.
 * Se utiliza más métodos de los que realmente sería posiblemente necesario para un proyecto cómo éste.
 * Se añaden  metodos "demas" teniendo en cuenta que es una actividad formativa y que en proyectos más grandes se requiere.
 * 
 * Se omite deliberadamente las tildes dentro de los comentarios.
 */

/*
 * @author Mar DD
 */
public class Main {
    
    //literales para nuevo usuario
    private static final String INTRO_NOMBRE= "Introduce el nombre de usuario";
    private static final String INTRO_EDAD = "Introduce la edad del usuario";
    private static final String INTRO_DNI = "Introduce el DNI del usuario";
    private static final String DNI_VAL = "Introduce el DNI del usuario válido";
    private static final String USR_CREADO = "Usuario creado correctamente";
    private static final String ERROR_DNI = "DNI introducido incorrecto";
        
    //literales menu
    private static final String MENU = "Realiza una nueva acción\n1 Introduce un nuevo gasto\n2 Introduce un nuevo ingreso\n3 Mostrar gastos\n4 Mostrar ingresos\n5 Mostrar saldo\n0 Salir\n"; 
    private static final String ERROR_ACCION = "Accion no valida, elija una nueva acción";
	
    //Literales para gasto, ingreso y saldo. Aquí se crea variables con literales iguales, porque en un futuro puede ser diferentes.
    private static final String DESC_GASTO = "Introduce la descripción";
    private static final String DESC_INGRESO = "Introduce la descripción";
    private static final String NUEVO_GASTO = "Introduzca cantidad";
    private static final String NUEVO_INGRESO = "Introduce la cantidad";
    private static final String SALDO_RESTANTE = "Saldo restante: ";
    private static final String SALDO_ACTUAL = "El saldo actual de la cuenta es: ";

    //Literal fin de programa
    private static final String FIN_PRG = "Fin del programa.\nGracias por utilizar la aplicación.";
    
    //Variable global porque se utiliza en varios métodos
    private static Cuenta unaCuenta;
    private static Usuario nuevoUser;
    private static List<Gasto> listaGastos;
    private static List<Ingreso> listaIngresos;
    
     
    
    // Inicio definicion metodo main  
    public static void main(String[] args) {
        //variables locales salir y accion
        boolean salir = true;
	int accion; 
        
        
        crearUsuario();
	unaCuenta= new Cuenta(nuevoUser);
	do {
		mostrarMenu();
		accion = elegirAccion();
		salir = ejecutarAccion(accion); 
                        
	} while (salir);
        
    }
    //Final definicion metodo main
    
    
    //solicitar datos por consola, y crearlo cuando se tenga un DNI valido.
    private static void crearUsuario() {
        //Crear objeto y definir variables requerida para bucle
	boolean asignadoDni = false;
	nuevoUser = new Usuario();
		
	String nombre = introString(INTRO_NOMBRE); 
	int edad = introEntero(INTRO_EDAD);
	nuevoUser.setNombre(nombre);
	nuevoUser.setEdad(edad);
			
	//Bucle dentro de condicion para comprobar que se cumpla las condiciones de un DNI valido segun enunciado de PAC
	
        String DNI = introString(INTRO_DNI); 
        nuevoUser.setDNI(DNI);
			
        if(nuevoUser.setDNI(DNI)) {
                
            asignadoDni = true; 
                
        }else {
                
            System.out.println(ERROR_DNI);
            
            while (!asignadoDni){
                
                DNI = introString(DNI_VAL); 
                nuevoUser.setDNI(DNI);
                
                if(nuevoUser.setDNI(DNI)){         
                    asignadoDni = true; 
                }else{
                    System.out.println(ERROR_DNI);
                }
            }
	};
		
	System.out.println(USR_CREADO);        
    }
    
    
    private static void mostrarMenu() {
        
	System.out.print(MENU);
    }
	
    private static int elegirAccion() {
        
	return leerEntero();
       
    }
    
    private static boolean ejecutarAccion(int accion) {
		
	switch (accion) {
            case 1:
		nuevoGasto(); 
		break;
            case 2:
		nuevoIngreso();
		break;
            case 3:
		mostrarGastos();
		break;
            case 4:
		mostrarIngresos();
		break;
            case 5:
		mostrarSaldo();
		break;
            case 0:
		System.out.println(FIN_PRG);
		return false; 
			
            default:
		System.out.println(ERROR_ACCION);
		break;
            }
        
	return true; 
    }

        
        
    // Inicio Métodos que se llaman en ejecutarAccion.
    
    //En este caso se solicito capturar la excepción.
    // El simbolo euro viene de  //https://stackoverflow.com/questions/34977949/%E2%82%AC-symbol-java-unicode
    private static void nuevoGasto() {
	//Las variables description y cantidad decidí usarlas como variables locales porque solo se usan en dos métodos.
	String description = introString(DESC_GASTO); 
	double cantidad = introDouble(NUEVO_GASTO);
		
	try {
            double saldo = unaCuenta.addGastos(description,cantidad);
            System.out.println(SALDO_RESTANTE + saldo);
            
	} catch (GastoException e) {
            
            System.out.println(e.getMessage());
            
	}		
    }
    
     //https://stackoverflow.com/questions/34977949/%E2%82%AC-symbol-java-unicode
    private static void nuevoIngreso() {
      
        String description = introString(DESC_INGRESO);
	double cantidad = introDouble(NUEVO_INGRESO);
        
	double saldo = unaCuenta.addIngresos(description,cantidad);
	System.out.println(SALDO_RESTANTE + saldo +"\u20AC");

    }
	
    private static void mostrarIngresos() {
       	
	listaIngresos = unaCuenta.getIngresos(); 	
        
       for (int i = 0; i < listaIngresos.size(); i++) {
            System.out.println(listaIngresos.get(i));
	}		
    }

    private static void mostrarGastos() {
	
        listaGastos = unaCuenta.getGastos();
		
	for (int i = 0; i < listaGastos.size(); i++) {
	System.out.println(listaGastos.get(i));
        
	}
    }
	
    //https://stackoverflow.com/questions/34977949/%E2%82%AC-symbol-java-unicode
    private static void mostrarSaldo() {
       
	System.out.println(SALDO_ACTUAL + unaCuenta.getSaldo()+"\u20AC");
                
    }
    //Fin métodos que se llaman en ejecutarAccion.
         
    
    
    //Inicio metodos de solicitud de datos al usuario. 
    private static String introString(String mensaje) {
	System.out.println(mensaje);
	return leerString();
    }
    
    private static int introEntero(String mensaje) {
        
        System.out.println(mensaje);
        return leerEntero();
    }
	
    private static double introDouble(String mensaje) {
        
        System.out.println(mensaje);
        return leerDouble();       
    }
    //Fin metodos de solicitud de datos al usuario.
	

    
    //Inicio métodos de lectura de datos.     
    private static String leerString() {
        
	return new Scanner(System.in).nextLine(); 
    }

                
    private static int leerEntero() {
		
	return new Scanner(System.in).nextInt(); 
		
    }
    
    /*Se requiere de funcion locale para que reconozca como double '.'
    *Más info en https://docs.oracle.com/javase/8/docs/api/java/util/Scanner.html
    y https://docs.oracle.com/javase/7/docs/api/java/util/InputMismatchException.html*/ 
   private static double leerDouble() {
        
        return new Scanner(System.in).useLocale(Locale.US).nextDouble();
    }
    
    //Fin metodos de lectura de datos. 
	
}




       

    
    

