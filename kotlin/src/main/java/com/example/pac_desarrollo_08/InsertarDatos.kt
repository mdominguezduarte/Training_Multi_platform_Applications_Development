package com.example.pac_desarrollo_08

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pac_desarrollo_08.CrearTabla.Companion.campo1
import com.example.pac_desarrollo_08.CrearTabla.Companion.campo2
import com.example.pac_desarrollo_08.CrearTabla.Companion.nombreTabla
import kotlinx.android.synthetic.main.insertardatos.*
import kotlinx.android.synthetic.main.insertardatos.buttonVolver1Desde2
import kotlinx.android.synthetic.main.insertardatos.buttonInsertar

class InsertarDatos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.insertardatos)

        /**OJO la siguiente declaración de variables posiblemente no era necesario, revisando el código del ejemplo de la viodeutoria 8, pero
         * por falta de tiempo de modificar a última  algo que ya funciona, he decidido avanzar con la
         * muestra de datos
         **/
        val editTextT = findViewById<EditText>(R.id.editTextInsertTabla)
        val editTextC1 = findViewById<EditText>(R.id.editTextInsertCampo_1)
        val editTextC2 = findViewById<EditText>(R.id.editTextInsertCampo_2)

        buttonInsertar.setOnClickListener {

            //inicializar base de datos.
            val admin = MySQLOpenHelper(this,"administracion", null, 1)
            val bd = admin.writableDatabase

            //asignar los valores insertados a la variables previamente definidas en Companion en la actividad CrearTabla
            nombreTabla = editTextT.text.toString()

            val registro = ContentValues()
                registro.put(campo1, editTextC1.text.toString())
                registro.put(campo2, editTextC2.text.toString())

            //insertar datos en la base de datos
            try{
                bd.insert(nombreTabla,null, registro)

            }catch(e:InterruptedException) {
                e.printStackTrace();
            }

            bd.close()

            //limpiar el editText
            editTextInsertTabla.setText("")
            editTextInsertCampo_1.setText("")
            editTextInsertCampo_2.setText("")

            Toast.makeText(this, "Se ha insertado los datos enla tabla"+nombreTabla+ "...", Toast.LENGTH_SHORT).show()

        }

        buttonVolver1Desde2.setOnClickListener {

            Toast.makeText(this, "Volver atrás", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, GestionarDatos::class.java)
            startActivity(intent)

        }
    }
}