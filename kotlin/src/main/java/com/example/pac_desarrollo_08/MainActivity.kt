package com.example.pac_desarrollo_08

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonIr2.setOnClickListener {

            Toast.makeText(this, "Entrando a gestión de datos.", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, GestionarDatos::class.java)
            startActivity(intent)

        }

        buttonIr3.setOnClickListener {

            Toast.makeText(this, "Entrando a configuración servicio.", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, Servicios::class.java)
            startActivity(intent)

        }
    }
}