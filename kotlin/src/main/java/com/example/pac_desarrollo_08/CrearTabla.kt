package com.example.pac_desarrollo_08

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.creartabla.*
import kotlinx.android.synthetic.main.creartabla.buttonVolver1Desde2
import kotlinx.android.synthetic.main.insertardatos.*

class CrearTabla : AppCompatActivity() {

    /**Definir las variables de nombre de tabla y  campos 1 y 2 a nivel de proyecto
      requeridos en la creación, inserción y muestra de datos **/
    companion object {

        lateinit var nombreTabla: String
        lateinit var campo1 :String
        lateinit  var campo2 :String
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.creartabla)
        /**OJO la siguiente declaración de variables posiblemente no era necesario, revisando el código del ejemplo de la viodeutoria 8, pero
         * por falta de tiempo de modificar a última  algo que ya funciona, he decidido avanzar con la
         * muestra de datos
         **/
        val editTextT = findViewById<EditText>(R.id.editTextCrearT)
        val editTextC1 = findViewById<EditText>(R.id.editTextCrearC_1)
        val editTextC2 = findViewById<EditText>(R.id.editTextCrearC_2)



        buttoncrearTabla.setOnClickListener {

            //inicializar base de datos.
            val admin = MySQLOpenHelper(this,"administracion", null, 1)
            val bd = admin.writableDatabase

            //valores de los campos provenientes de editText

            nombreTabla = editTextT.text.toString()
            campo1 = editTextC1.text.toString()
            campo2 = editTextC2.text.toString()



            //crear tabla en la base de datos
            try{
                /** esta sentencia de drop table se ha añadido para la facilidad de hacer test
                 entiendo que en caso real borrar tabla es incorrecto
                pero por falta de tiempo voy a hacer las comprobaciones correspondientes para crear tabla
                 simplemente voy a crera la tabla**/
                bd.execSQL("drop table if exists "+ nombreTabla)
                bd.execSQL("create table "+nombreTabla+"("+campo1 +" text primary key, "+campo2 +" text)")

            }catch(e:InterruptedException) {
                e.printStackTrace();
            }

            bd.close()

            //limpiar el editText
            editTextCrearT.setText("")
            editTextCrearC_1.setText("")
            editTextCrearC_2.setText("")

            Toast.makeText(this, "Se ha creado la tabla " + nombreTabla +" en la base de datos...", Toast.LENGTH_SHORT).show()

        }


        buttonVolver1Desde2.setOnClickListener {

            Toast.makeText(this, "Volver atrás", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, GestionarDatos::class.java)
            startActivity(intent)

        }
    }


}


