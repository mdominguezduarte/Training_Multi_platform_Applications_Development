package com.example.pac_desarrollo_08

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pac_desarrollo_08.CrearTabla.Companion.campo1
import com.example.pac_desarrollo_08.CrearTabla.Companion.campo2
import kotlinx.android.synthetic.main.mostrardatos.*
import com.example.pac_desarrollo_08.CrearTabla.Companion.nombreTabla
import kotlinx.android.synthetic.main.insertardatos.*
import kotlinx.android.synthetic.main.mostrardatos.buttonVolver1Desde2


class MostrarDatos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mostrardatos)
        val editTextT = findViewById<EditText>(R.id.editNombreTabla)

        buttonMostrarDatos.setOnClickListener {
            //inicializar base de datos.
            val admin = MySQLOpenHelper(this,"administracion", null, 1)
            val bd = admin.writableDatabase


            //asignar los valores insertados a la variables previamente definidas en Companion en la actividad CrearTabla
            nombreTabla = editTextT.text.toString()


          /** mostrar datos en la base de datos. En este caso se comprueba si no hay, o
            hay **/
            try{
                val datosTabla = bd.rawQuery("select * from "+nombreTabla, null)
                if(datosTabla.moveToFirst()){

                    campo1= datosTabla.getString(0)
                    campo2=datosTabla.getString(1)
                    Toast.makeText(this, "Los datos de la tabla "+nombreTabla+ " son: "+campo1+ " y "+campo2,  Toast.LENGTH_SHORT).show()

                    } else
                    Toast.makeText(this, "No existen datos",  Toast.LENGTH_SHORT).show()


            }catch(e:InterruptedException) {
                e.printStackTrace();
            }

            bd.close()


            //limpiar el editText
            editTextT.setText("")




        }


        buttonVolver1Desde2.setOnClickListener {

            Toast.makeText(this, "Volver atrás", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, GestionarDatos::class.java)
            startActivity(intent)

        }
    }
}
