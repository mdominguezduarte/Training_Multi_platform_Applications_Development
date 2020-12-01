using System;

namespace test
{
    class PAC_desarrollo_UF1
    {
        /**
        Programa realizado  
        para la pac de desarrollo UF1 de la asignatura PROGRAMACIÓN A.
        La programacion es secuencial.
        */
       


        static void Main(string[] args)
        {   
            //variables
            int[,] matriz1;
            int[,] matriz2;
            String linea;
            Boolean igual = true;
            

            //Solicitar al usuario dimension primera matriz.Se establece dimension.
            Console.WriteLine("Indique número de filas primera matriz: ");
            linea=Console.ReadLine();
            int matriz1F=int.Parse(linea);
            Console.WriteLine("Indique número de columnas primera matriz: ");
            linea=Console.ReadLine();
            int matriz1C=int.Parse(linea);
            matriz1 = new int[matriz1F, matriz1C];

            //Solicitar al usuario dimension segunda matriz. Se establece dimension
            Console.WriteLine("Indique número de filas segunda matriz: ");
            linea=Console.ReadLine();
            int matriz2F=int.Parse(linea);
            Console.WriteLine("Indique número de columnas segunda matriz: ");
            linea=Console.ReadLine();
            int matriz2C=int.Parse(linea);
            matriz2 = new int[matriz2F, matriz2C];


            //Solicitar valores primera matriz y guardarlos en la variable
            for (int f = 0; f < matriz1F; f++)
            {
                for (int c = 0; c < matriz1C; c++)
                {
                    Console.Write("Ingrese posicion primera matriz ["+(f+1)+","+(c+1)+"]: ");
                    linea = Console.ReadLine();
                    matriz1[f, c] = int.Parse(linea);
                }
            }

            //Solicitar segunda primera matriz y guardarlos en la variable
            for (int f = 0; f < matriz2F; f++)
            {
                for (int c = 0; c < matriz2C; c++)
                {
                    Console.Write("Ingrese posicion segunda matriz ["+(f+1)+","+(c+1)+"]: ");
                    linea = Console.ReadLine();
                    matriz2[f, c] = int.Parse(linea);
                }
            }

            //imprimir primera matriz
            Console.Write("Primera matriz: \n");
            for (int f = 0; f < matriz1F; f++)
            {
                for (int c = 0; c < matriz1C; c++)
                {
                    Console.Write(matriz1[f, c] + " ");
                }
                Console.WriteLine();

            }

            //imprimir segunda matriz
            Console.Write("Segunda matriz: \n");
            for (int f = 0; f < matriz2F; f++)
            {

                for (int c = 0; c < matriz2C; c++)
                {
                    Console.Write(matriz2[f, c] + " ");
                }

                Console.WriteLine();
            }

            // matrices son comparables si ambas tienen misma dimension
            if(matriz1F != matriz2F || matriz1C != matriz2C)
           
            {
                Console.WriteLine("Las matrices no pueden ser comparadas");
            } 
            else 
            {
                Console.WriteLine("Las matrices pueden ser comparadas");

                //son iguales las matrices?
                for (int f = 0; f < matriz2F; f++)
                {
                    for (int c = 0; c < matriz2C; c++)
                    {
                        if (matriz1[f,c] != matriz2[f,c])
                        {
                            igual = false;
                            break;
                        }
                    }
                }

                //imprimimos si son iguales
                if(igual)
                {
                    Console.WriteLine("Las matrices son iguales");
                }
                else
                {
                    Console.WriteLine("Las matrices no son iguales");
                }
            
        }
    }
}
}
