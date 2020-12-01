package com.example.pac_desarrollo_08

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pac_desarrollo_08.MiIntentService.Companion.mediaPlayer
import kotlinx.android.synthetic.main.musica.*

class Musica : AppCompatActivity(){



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.musica)


        //Iniciar servicio e ir a la actividad de música
        buttonIniMusica.setOnClickListener {
            Intent(this, MiIntentService::class.java).also {intent  ->

              MiIntentService.Iniciarmusica()
               Toast.makeText(this, "Escuchando música..", Toast.LENGTH_SHORT).show()
          }
        }


        //Iniciar servicio e ir a la actividad de música
        buttonStopMusica.setOnClickListener {
               if (mediaPlayer.isPlaying) {
                   MiIntentService.Pausarmusica()
                   Toast.makeText(this, "Música en pausa", Toast.LENGTH_SHORT).show()
               }else{
                   Toast.makeText(this, "No había música sonando", Toast.LENGTH_SHORT).show()
               }
        }

        //Se está durmiendo el servicio durante 15 segundos,y vamos a parar la música durante 15 segundos
        buttonSleepMusica.setOnClickListener{
            //Nótese que aunque para acceder a esta actividad se requiere haber iniciado el servicio, se comprueba si sigue corriendo el susodicho servicio
            if (MiIntentService.isrunning) {
                Toast.makeText(this, "Dormir servicio durante 15 segundos ", Toast.LENGTH_SHORT).show()
                MiIntentService.Sleepmusica()
            }else{
                Toast.makeText(this, "No se hace Thread.sleep, parece que el servicio iniciado miintentService no está actualmente disponible... ", Toast.LENGTH_SHORT).show()
            }
           }


        // Volver a la actividad anterio
        buttonVolver3Desde7.setOnClickListener {

            Toast.makeText(this, "Volver atrás", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, Servicios::class.java)
            startActivity(intent)

        }




        }

    }




