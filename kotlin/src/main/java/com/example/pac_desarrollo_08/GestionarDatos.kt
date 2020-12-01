package com.example.pac_desarrollo_08

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.gestionardatos.*

class GestionarDatos: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.gestionardatos)




        buttonCrearT.setOnClickListener {

            Toast.makeText(this, "Crear Tabla...", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, CrearTabla::class.java)
            startActivity(intent)

        }

        buttonInsertD.setOnClickListener {

            Toast.makeText(this, "Insertar Datos...", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, InsertarDatos::class.java)
            startActivity(intent)

        }



        buttonMostrarD.setOnClickListener {

            Toast.makeText(this, "Mostrar Datos...", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MostrarDatos::class.java)
            startActivity(intent)

        }

        buttonVolver1Desde2.setOnClickListener {

            Toast.makeText(this, "Volver inicio", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }

    }
}