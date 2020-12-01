
------------------------- INICIO PAC DESARROLLO---------------------------

-- En el ejercicio 1, se requiere  que las tablas ALUMNOS y PROFESORES existan.
-- La siguiente sentencia, muestra si  el usuario que estamos utilizando posee la tabla alumnos y la tabla profesores.
-- Para ello, hemos usado el recurso https://www.oracletutorial.com/oracle-administration/oracle-show-tables/
SELECT table_name
FROM user_tables
WHERE table_name='ALUMNOS' or table_name='PROFESORES'
ORDER BY table_name;

--Crear usuario Miguel.
CREATE USER Miguel 
IDENTIFIED BY m1g23l;

-- Privilegios de sesión con GRANT para que pueda conectarse a la base de datos.
GRANT CREATE SESSION TO Miguel;


-- Crear usuario Marta
CREATE USER Marta
IDENTIFIED BY M1rt2;
-- Privilegios de sesión, borrado y editado de tablas con el comando GRANT
-- Se incluyen la selección, editado y borrado de datos, para realizar el apartado 3
GRANT CREATE SESSION, ALTER ANY TABLE, DROP ANY TABLE, SELECT ANY TABLE, UPDATE ANY TABLE, DELETE ANY TABLE,INSERT ANY TABLE TO Marta;

-- Conceder permisos de selección al usuario Miguel sobre la tabla ALUMNOS
GRANT SELECT ON ALUMNOS TO Miguel;

-- Cambiar de conexión a Marta
CONNECT Marta/M1rt2;
-- inserto datos
insert into system.alumnos(dni, nombre)
values ('11223344T', 'ANA');
-- Modifico datos
update system.alumnos 
set nombre = 'ANA MARÍA', PALABRA_CLAVE='1N1M1R11'
where dni = '11223344T';
commit;

-- Compruebo si los datos están modificaDPS;
select * from system.alumnos where DNI ='11223344T';

-- Borrado de datos
delete from system.alumnos where dni = '11223344T';
commit;
--Compruebo que ya no existe la entrada
select * from system.alumnos where DNI ='11223344T';

------------------------Fin Ejercicio 1---------------------------

------------------------- Ejercicio 2.1 ------------------------- 
--mostrar  año actual  con SYSDATE. Documentación oficial ORACLE https://docs.oracle.com/cd/B19306_01/server.102/b14200/functions172.htmL
create or replace procedure año_actual is
begin
 DBMS_OUTPUT.PUT_LINE(TO_CHAR(SYSDATE, 'YYYY'));
end;

--Para mostrar el resultado, hay que ejecutar primero lo siguiente
set serveroutput on 
--Realizar prueba procedimiento
begin
  año_actual;
end;


------------------------- Ejercicio 2.2 ------------------------- 
-- Procedimiento que cuenta cada ejecución que se realiza utilizando creando un package que permita encapsular el procedimiento
-- Se toma como referencia el tutorial  https://www.oracletutorial.com/plsql-tutorial/plsql-package-body/
-- https://www.oracletutorial.com/plsql-tutorial/plsql-package-specification/

--En el package body se define el procedimiento
create or replace package body contar_ejecucion as
    procedure num_ejecucion is
    begin
        numero :=  numero + 1;
    end;
end;

--  En el de especificación, se declara la variable y se aplica el procedimiento definido en el body.
create or replace package contar_ejecucion as
    numero integer :=0;
    procedure num_ejecucion;
end;

--Probar a contar el número de ejecuciones y mostrarlo
set serveroutput on 
begin
    contar_ejecucion.num_ejecucion;
    DBMS_OUTPUT.put_line( 'Se ha contado ' ||contar_ejecucion.numero || ' ejecuciones');
end;


------------------------- Ejercicio 2.3 ------------------------- 

--Definición del procedimiento con dos parámetros cadena1 y cadena2
create or replace procedure concat_mayusc (cadena1 in varchar,cadena2 in varchar) is
begin
dbms_output.put_line(upper(cadena1) || upper (cadena2));
end;

--Código para probar el procedimiento
begin
    concat_mayusc('eng-','uk');
end;

------------------------- Ejercicio 2.4 ------------------------- 
-- Primero reviso la definición de los campos de la tabla empleado
-- Se consulta https://docs.oracle.com/database/121/ADMQS/GUID-5D8AE3A1-1B05-4848-888D-EEDD61CD32E2.htm#ADMQS0823
-- Para obtener el rango de digitos y número de decimales https://www.oracletutorial.com/oracle-basics/oracle-number-data-type/#:~:text=The%20Oracle%20NUMBER%20data%20type%20has%20precision%20and%20scale.,decimal%20point%20in%20a%20number.
select column_name, data_type, data_precision, data_scale from all_tab_columns 
where table_name = 'EMP';

-- Se procede a crear variables  bloque anónimo dónde tomando la documentación para aplicar %TYPE
--https://docs.oracle.com/cd/B28359_01/appdev.111/b28370/type_attribute.htm#LNPLS01352
declare
-- variables con la misma precisión que la columna definida
 v_emp_no number(4):= 0;
 v_salario emp.salario %TYPE;
begin
    --solicitar codigo empleado 
    v_emp_no:= &CODIGO_EMPLEADO;
    select salario into v_salario from emp where emp_no= v_emp_no;
    dbms_output.put_line('El salario registrado del empleado: '||v_emp_no||' es '||v_salario);
    v_salario := v_salario - (v_salario * 1/3);
    -- Asignar el nuevo valor de la variable al campo salario
    update emp  set salario = v_salario  where emp_no= v_emp_no;
    dbms_output.put_line('El salario actualizado del empleado: '||v_emp_no||' es '||v_salario);
    -- si no encuentra el código lanzamos una excepción
exception
        when NO_DATA_FOUND  then
        dbms_output.put_line('Código de empleado no encontrado');
end;

-- Consultar código existente
select * from emp where emp_no= 7900;

------------------------- Ejercicio 2.5 ------------------------- 
-- Se Considera 1 para el lunes, 2 para el martes...
-- En caso de otro valor distinto al [1,7] se considera error (Pág 85 del libro)
CREATE OR REPLACE FUNCTION dia_semana_if_else(entero INT)
RETURN VARCHAR2 --Devuelve día dela semana 
IS
    dia_semana VARCHAR2(15);-- variable length requerido
BEGIN
    IF (entero = 1) THEN
        dia_semana :='Lunes';
    ELSIF (entero = 2) THEN
        dia_semana:='Martes';
    ELSIF (entero = 3) THEN
        dia_semana:='Miercoles';
    ELSIF (entero = 4) THEN
        dia_semana:='Jueves';
    ELSIF (entero = 5) THEN
        dia_semana:='Viernes';
    ELSIF (entero = 6) THEN
        dia_semana:='Sabado';
    ELSIF (entero = 7) THEN
        dia_semana:='Domingo';
    ELSE
        dia_semana:='Incorrecto';        
    END IF;
    
    RETURN(dia_semana);
    dbms_output.put_line('El día de la semana devuelto es ' || dia_semana );
END;

-- Para ejecutar el procedimiento
begin
DBMS_OUTPUT.PUT_LINE(dia_semana_if_else(0));
DBMS_OUTPUT.PUT_LINE(dia_semana_if_else(1));
DBMS_OUTPUT.PUT_LINE(dia_semana_if_else(2));
DBMS_OUTPUT.PUT_LINE(dia_semana_if_else(7));
end;


------------------------- Ejercicio 2.6 ------------------------- 
-- Se toma cómo ejemplo el ejercicio resuelto del manual en la página 85
-- El ejercicio sigue el mismo razonamiento que el ejercicio del apartado anterior pero con la función case.
CREATE OR REPLACE FUNCTION dia_semana__case (entero INT)
RETURN VARCHAR2 
IS
    dia_semana VARCHAR2(15); -- variable length requerido
BEGIN
    CASE entero 
        WHEN    1   THEN    dia_semana :='Lunes';
        WHEN    2   THEN    dia_semana :='Martes';
        WHEN    3   THEN    dia_semana :='Miercoles';
        WHEN    4   THEN    dia_semana :='Jueves';
        WHEN    5   THEN    dia_semana :='Viernes';
        WHEN    6   THEN    dia_semana :='Viernes';
        WHEN    7   THEN    dia_semana :='Domingo';
        ELSE 
            dia_semana :='Incorrecto';
    END CASE;
    RETURN(dia_semana);
END;


-- Para ejecutar el procedimiento utilizaremos las mismas pruebas que en ejercicio anterior, ya que esperamos el mismo resultado
begin
DBMS_OUTPUT.PUT_LINE(dia_semana__case(0));
DBMS_OUTPUT.PUT_LINE(dia_semana__case(1));
DBMS_OUTPUT.PUT_LINE(dia_semana__case(2));
DBMS_OUTPUT.PUT_LINE(dia_semana__case(7));
end;

------------------------- Ejercicio 2.7 -------------------------
--Pasar 3 números como parámetros enteros y definir una varable al que se le asigna el valor mayor
create or replace function mayor_num(num1 INT, num2 INT, num3 INT)
return INT
is
mayor INT;
begin
    if (num1 >= num2) then 
        mayor:= num1;
    else
        mayor:= num2;
    end if;
    if (num3 >= mayor) then 
        mayor :=num3;
    end if;
return (mayor);
 dbms_output.put_line('El número mayor es' || mayor );
end;
--Comprobación de función con posibles valores distintos e iguales
set serveroutput on 
begin
    DBMS_OUTPUT.PUT_LINE(mayor_num(0,3,9));
    DBMS_OUTPUT.PUT_LINE(mayor_num(17,3,9));
    DBMS_OUTPUT.PUT_LINE(mayor_num(17,35,9));
    DBMS_OUTPUT.PUT_LINE(mayor_num(5,5,5));
end;

------------------------- Ejercicio 2.8 -------------------------
--Para sumar dado un numero lasuma de los numeros enteros, se usa el bucle for
-- En realidad, se está considerando un número entero no negativo 
--ya que no tendría sentido por ejemplo para  el entero -100 .
create or replace procedure suma_mumeros_enteros(numero int) is
x int;
suma int:=0;
begin
for x in 0..numero -- se suma desde 0 a n
    loop
        suma:=suma + x;
    end loop;
    dbms_output.put_line('El resultado de la suma es '||suma);
end;
-- Al procedimiento también se le puede llamar con execute
-- Notese que no hay una única forma de llamar a los procedimientos cómo bien se especifica en el manual
execute suma_mumeros_enteros(2);
execute suma_mumeros_enteros(300);


------------------------- Ejercicio 2.9 -------------------------
-- Función que devuelve si es verdadero o falso que es un número es primo
--Si es verdadero,1;Si es falso,0.
create or replace function esPrimo (numero int) 
return int
is
primo int;
begin
    --El 1  no es primo
    if numero = 1 then
        primo:= 0;
        return(primo);
    end if;
    for x in 2..numero/2 loop
        if mod (numero,x)=0 then
            primo:= 0;
            return (primo);
        end if;
    end loop;
    primo:= 1;
    return (primo);
end;
 
begin
    DBMS_OUTPUT.PUT_LINE('Es numero primo el 31? '||esPrimo(31));
    DBMS_OUTPUT.PUT_LINE('Es numero primo el 32? '||esPrimo(32));
    DBMS_OUTPUT.PUT_LINE('Es numero primo el 1? '||esPrimo(1));
end;


------------------------- Ejercicio 2.10 -------------------------
-- Suma de los primeros n úmeros primos empezando por 1
-- Para ello tenemos una variable nprimo  pasad apor parámetro, y tres variables; una para la suma, otra qué incrementará con el bucle for
-- y cont, contará el número de primos encontrados.
CREATE OR REPLACE FUNCTION suma_n_primos(nprimo int)
return int
is 
suma int:=0;
cont int:=0;
i int:=0;
begin 
    for i IN 1.. 10000 loop    --porque es la suma de los primeros n numeros primos , no queremos sumar simplemente los primeros nnumeros
        if esPrimo(i) = 1 then     -- recordamos que la funcion es Primo devuelve 1 si es primo, y falso si no es pimo
                                -- si es verdadero, es primo, lo sumamos e incrementamos el contador              
            suma:= suma+ i;
            dbms_output.put_line('Es primo, y la suma hasta ahora es ' ||suma);
            cont:= cont + 1;
            if cont = nprimo then-- si se han sumado nprimo veces, salir
                exit;
            end if;
        end if;
    end loop;
    return suma;
end;


    
begin
 dbms_output.put_line('Suma  de primeros  números primos ' ||suma_n_primos(4));
end;   



------------------------- Ejercicio 3.1 -------------------------
--bloque anónimo que hace uso de cursores parapoder visualizar los datos de la consulta
-- Procedemos a declarar nuestra variable y nuestro cursor
-- Se toma como ejemplo a seguir pag99 del libro

declare
cursor c_dept is select dnombrebre, lugar from dept;
vlugar dept.lugar%type;
vnombre dept.dnombrebre%type;

begin 
    open c_dept; --se abre cursor
    
    loop
        fetch c_dept into vnombre,vlugar; -- fetch especifica número de datos a devolver
        exit when c_dept %NOTFOUND;
        dbms_output.put_line('Departamento:'||vnombre ||' en '||vlugar);
    end loop;
    
    close c_dept; -- cierro cursor
end;
        


------------------------- Ejercicio 3.2-------------------------
-- Declaramos el cursor, las variables apellidos,y la variable linea para visualizar el número de linea
--El cursor va a seleccionar todos los apellidos que pertenencen al departamento de ventas,y requiere de union de tablas
declare
vapellido emp.apellido%TYPE;
linea int:=0;
cursor c_emp is
    select  emp.apellido
    from emp
        inner join dept on dept.dept_no = emp.dept_no
    where dept.dnombrebre = 'VENTAS';
begin
    open c_emp; -- se abre cursor
    loop
         fetch c_emp into vapellido;--fetch va a devolver datos hasta qu no se encuentre
         exit when c_emp%notfound;
         linea := linea +1;
          dbms_output.put_line('Linea:'||linea ||' Apellido:'||vapellido);
    end loop;
      close c_emp; -- se cierra cursor
end;

------------------------- Ejercicio 3.3-------------------------SIN FOR
create or replace procedure empleados_dpto_for(nombre_dept dept.dnombrebre%TYPE)is
vapellido emp.apellido%TYPE;
vdept_no dept.dept_no%TYPE;
departamento int:=0;
--Se pasa a seleccionar los empleados que hayan en el departamento indicado, si no hay empleados, no devolverá ninguno.
--Eñ cursor se definirá conla selección mencionada
cursor c_emp is
select emp.apellido from emp right join dept on dept.dept_no = emp.dept_no
where dept.dnombrebre = nombre_dept;
begin
    open c_emp;
    
    loop
        fetch c_emp into vapellido;
        exit when c_emp%notfound;
        departamento := departamento +1;
        
        if vapellido is null then 
             dbms_output.put_line('No hay empleados en el departamento '||nombre_dept);
        else
             dbms_output.put_line(vapellido);
        end if;
    end loop;
    --En caso de que no exista el departamento, se muestra por consola
    if departamento = 0 then
         dbms_output.put_line('No existes el departamento '||nombre_dept);
    end if;
    close c_emp;-- se cierra cursor    
end;

begin
    empleados_dpto_for('CONTABILIDAD');
    empleados_dpto_for('INFORMATICA');
end;
 


------------------------- Ejercicio 3.3-------------------------CON FOR
-- Con un bucle for recorremos los registros, no necesitamos declarar las variables apellido y departamento como en el anterior
-- Sí declaramos la variable departamento,para comprobar si el departamento pasadopor parámetro existe.
create or replace procedure empleados_dpto_confor(nombre_dept dept.dnombrebre%TYPE) is
departamento int:=0;
cursor c_emp is
select emp.apellido from emp right join dept on dept.dept_no = emp.dept_no
where dept.dnombrebre = nombre_dept;

begin
-- Con el cursor recorremos el registro usando el bucle for
    for rdato in c_emp loop
        departamento := departamento +1;
        
        if rdato.apellido is null then 
             dbms_output.put_line('No hay empleados en el departamento '|| nombre_dept);
        else
             dbms_output.put_line(rdato.apellido);
        end if;
    end loop;
    --En caso de que no exista el departamento, se muestra por consola
    if departamento = 0 then
         dbms_output.put_line('No existe el departamento '||nombre_dept);
    end if;
   
end;

begin
    empleados_dpto_confor('CONTABILIDAD');
    empleados_dpto_confor('INFORMATICA');
end;


------------------------- Ejercicio 3.4---------------------------
-- Pasar por parametros el departamento y devolveer cuántos empleados tienen comisión
-- La parte de funcionalidad del loop y cursor, explicada en apartados anteriores
create or replace function comision_emp_por_departamento(dept_nombre dept.dnombrebre%TYPE)
    return integer is --Necesitamos un entero para devolver el número de empleados
    vcount int:= null; -- Al no poder usar la funcion count(), necesitamos una variable que cuente. Por defecto, es null
    departamento int:=0;
    vcomision emp.comision%TYPE;
    vdept_no emp.dept_no%TYPE;
    --Aplicando lo realizado en apartados anteriores, el cursor se define como
    cursor c_emp is
    select emp.comision, emp.dept_no
    from emp right join dept on dept.dept_no = emp.dept_no
    where dept.dnombrebre = dept_nombre;
begin
    open c_emp;
    loop
        fetch c_emp into vcomision, vdept_no;
        exit when c_emp%notfound;
        
        if vcomision is null or vcomision = 0 then
        if vcount is null then
            vcount :=0;
            dbms_output.put_line('No existe el departamento '||dept_nombre);
        end if;
        else
            departamento :=departamento +1;
            vcount :=departamento;
            dbms_output.put_line(dept_nombre ||' tiene empleados con comision:' ||vcount);
        end if;
    end loop;
    close c_emp;
    return vcount;
end;
begin
   dbms_output.put_line(comision_emp_por_departamento('VENTAS'));
   dbms_output.put_line(comision_emp_por_departamento('CONTABILIDAD')); 
end;


------------------------- Ejercicio 4.1---------------------------
--Creación de la tabla auditaemple tal y cómo se pide en el enunciado

CREATE TABLE auditaemple (
    id_cambio  NUMBER(5),
    descripcion_cambio VARCHAR2(100),
    fecha_cambio DATE
);

--Creación del trigger auditasueldo,tal y cómo se pide en el anunciado
create or replace trigger auditasueldo after
    update of salario on emp for each row -- se lanzará el trigger una vez que el salario de cualquier fla de emp se actualice
declare
    vmax_id auditaemple.id_cambio%TYPE;
begin
    -- Por definición del enunciado, se obtiene el id_cambio mayor
    select max(id_cambio) into vmax_id from auditaemple;
    
    if vmax_id is null then
        vmax_id:=1;
    else
        vmax_id:= vmax_id +1;
    end if;
    
    -- Insertar un registro
    insert into auditaemple (id_cambio,descripcion_cambio,fecha_cambio) 
        VALUES ( vmax_id, 'El empleado' || :old.emp_no ||' con antiguo salario '||:old.salario 
        ||' tiene el salario actualizado a' || :new.salario||' ', sysdate
        );
end;


--  se insertan valores y luego se actualizan, para revisar disparador

insert into emp (emp_no, salario, apellido,oficio, fecha_alta, comision, dept_no)
VALUES(3200,21000,'RODRIGUEZ','GOMEZ', SYSDATE,2,10);
COMMIT;
update emp set salario = 110001 where emp_no= 3200;
select *from emp where emp_no = 21000;
commit;


------------------------- Ejercicio 4.2---------------------------
--Creación del trigger auditaemple para que inserte un mensaje cuando un nuevo empleado es creado en la tabla emp

create or replace trigger auditaemple after
    insert on emp for each row
    
declare
    vmax_id auditaemple.id_cambio%TYPE;
begin
  -- Por definición del enunciado, se obtiene el id_cambio mayor
    select max(id_cambio) into vmax_id from auditaemple;
    
    if vmax_id is null then
        vmax_id:=1;
    else
        vmax_id:= vmax_id +1;
    end if;
    
    -- Insertar un registro con info del nuevo empleado
    insert into auditaemple (id_cambio,descripcion_cambio,fecha_cambio) 
        VALUES ( vmax_id, 'Nuevo empleado con código' || :new.emp_no , sysdate
        );
end;
--test 4.2-----
insert into emp (emp_no, salario, apellido,oficio, fecha_alta, comision, dept_no)
VALUES(3100,22000,'DIAZ','GOMEZ', SYSDATE, 0,10);
COMMIT;


------------------------- Ejercicio 4.3---------------------------
--Creación del trigger auditaemple2,tal y cómo se pide en el anunciado, regirtrar el aumento si es superior al 10%
create or replace trigger auditaemple2 after
    update of salario on emp for each row -- se lanzará el trigger una vez que el salario de cualquier fla de emp se actualice
declare
    vmax_id auditaemple.id_cambio%TYPE;
begin
    -- Por definición del enunciado, se obtiene el id_cambio mayor
    select max(id_cambio) into vmax_id from auditaemple;
    
    if vmax_id is null then
        vmax_id:=1;
    else
        vmax_id:= vmax_id +1;
    end if;
    -- Ahora registramos el nuevo salario siempre que sea superior al 10% del antiguo
    if :new.salario >:old.salario *1.10 then
    
      -- Insertar un registro solo si se cumplió la condicion
        insert into auditaemple (id_cambio,descripcion_cambio,fecha_cambio) 
        VALUES ( vmax_id, 'El salario del empleado' || :old.emp_no ||' antes era de '||:old.salario 
        ||' y ahora será de ' || :new.salario||' ', sysdate
        );
    end if;
end;

--test 4.3-----
update emp set salario = 140001 where emp_no= 3200;
select *from emp where emp_no = 21000;
commit;

------------------------- Ejercicio 4.4---------------------------
-- Trigger para verificar unidades haciendo usando como control de errores  RAISE_APPLICATION_ERROR
-- RAISE_APPLICATION_ERROR evitará errores descontrolados y no actualizará si la cantidad es mayor a 9999
-- Para ello se le asignará un código de error y una descripción delmismo
-- Tal y cómo se pide en el apartado b , si se piden hasta 999 unidades, el campo importe también será actualizado.
create or replace trigger verifica_unidades before update or insert on detalle for each row
begin
-- If Cantidad > 9999 se lanza error
    if :new.cantidad > 999 then
        raise_application_error(
        -2001,
        'La CANTIDAD no puede ser mayor a 999 y ha indicado ' || :new.cantidad
        );
    end if;
    --Apartado b)
    :new.importe := :new.precio_venda * :new.cantidad;
end;

-- test ejercicio 4.4------
update detalle set cantidad = 1000 where com_num= 614 and detalle_num = 1;
update detalle set cantidad = 999 where com_num= 614 and detalle_num = 1;


------------------------- FIN PAC DESARROLLO---------------------------

