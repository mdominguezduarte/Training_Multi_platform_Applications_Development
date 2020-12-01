package com.example.pac_desarrollo_08

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.servicios.*

class Servicios : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.servicios)



        //Iniciar servicio e ir a la actividad de música
        buttonIniService.setOnClickListener {
          try {
              if (MiIntentService.isrunning){
                  Toast.makeText(this, "Servicio ya estaba iniciado...", Toast.LENGTH_SHORT).show()
              }else{
                  Intent(this, MiIntentService::class.java).also {instance ->
                      startService(instance)
                      Toast.makeText(this, "Entrando a música...", Toast.LENGTH_SHORT).show()
                      val music = Intent(this, Musica::class.java)
                      startActivity(music)
                }
              }
            } catch(e:InterruptedException) {
                e.printStackTrace();
            }

        }


        //Parar servicio
        buttonStopService.setOnClickListener {
            if (MiIntentService.isrunning){
                Toast.makeText(this, "Parando servicio..", Toast.LENGTH_SHORT).show()
                    MiIntentService.StopServicio()
                }
        }



        //Volver inicio
        buttonVolver1Desde3.setOnClickListener {

            Toast.makeText(this, "Volver inicio", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }

    }
}