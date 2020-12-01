package com.example.pac_desarrollo_08

import android.app.IntentService
import android.content.Intent
import android.content.Context
import android.media.MediaPlayer
import android.widget.Toast

// TODO: Rename actions, choose action names that describe tasks that this
// IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS


// TODO: Rename parameters


/**
  Subproceso que será llamado

 */
class MiIntentService : IntentService("MiIntentService") {

    //Inicializar instancia
    init{
        instance = this
    }

    override fun onHandleIntent(intent: Intent?) {
        try {
            mediaPlayer = MediaPlayer.create(this, R.raw.konstentyn_summers_end)

            isrunning = true
            while (isrunning) {
                Thread.sleep(15000)
            }
        }catch (e:InterruptedException) {
                e.printStackTrace()
        }
    }



    /**Para acceder a todos estos objetos y funciones a nivel de proyecto**/
    companion object {

        lateinit var instance: MiIntentService
        lateinit var mediaPlayer:MediaPlayer

        var isrunning = false

        /** Inicio de funciones customizadas que se llamaran en las pantallas de servicios y música**/
        fun StopServicio(){
            try {
                if (mediaPlayer != null) {
                    mediaPlayer.release()
                }

                isrunning = false
                instance.stopSelf()
            }catch(e:InterruptedException){
                e.printStackTrace()
            }

        }

        /** En este caso se ha especificado mediaPlayer.pause() para que continúe por dónde se quedó el hilo musical
         * si lo que se quiere es parar la música completamente se puede descomentar mediaPlayer.stop() y comentar mediaPlayer.pause()**/
        fun  Pausarmusica(){
            try {
                if (mediaPlayer != null && mediaPlayer.isPlaying) {
                    mediaPlayer.pause()
                    //mediaPlayer.stop()
                }
            }catch(e:InterruptedException){
                e.printStackTrace()
            }
        }


        fun  Iniciarmusica(){
            try {
                if (!mediaPlayer.isPlaying){
                mediaPlayer.start()
                }
            }catch(e:InterruptedException){
                e.printStackTrace()
            }
        }


       /**Si la música está sonando se va a parar , y se va a volver a retomar dónde estaba sonando,tras los 15 segundos
        En caso contrario,simplemente se duerme el hilo **/
        fun  Sleepmusica(){
            try {
                if (mediaPlayer.isPlaying){
                    Pausarmusica()
                    Thread.sleep(15000)
                    Iniciarmusica()
                }else{
                    Thread.sleep(15000)
                }
            }catch(e:InterruptedException){
                e.printStackTrace()
            }
        }

        /** Final de funciones customizadas que se llamaran en las pantallas de servicios y música**/

    }
}