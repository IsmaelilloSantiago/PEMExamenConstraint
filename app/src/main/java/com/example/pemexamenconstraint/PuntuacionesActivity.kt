package com.example.pemexamenconstraint

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import com.example.pemexamenconstraint.databinding.ActivityMainBinding
import com.example.pemexamenconstraint.databinding.ActivityPuntuacionesBinding

class PuntuacionesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPuntuacionesBinding
    private var listaPuntuaciones:java.util.ArrayList<Int>? = ArrayList()
    private var mayorPuntuacion : Int = 0
    private var menorPuntuacion : Int = 0
    private val key = "MyKey"





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPuntuacionesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        listaPuntuaciones = ArrayList()
        listaPuntuaciones = intent.getIntegerArrayListExtra("puntuaciones")


        Log.e("a verl", listaPuntuaciones.toString())

        binding.Puntuaciones.text = intent.getIntegerArrayListExtra("puntuaciones").toString()


        for ((i,value) in listaPuntuaciones?.withIndex()!!) {
            if(i == 0){
                mayorPuntuacion = listaPuntuaciones!!.get(i)
            }else if(listaPuntuaciones!!.get(i)> mayorPuntuacion){
                mayorPuntuacion = listaPuntuaciones!!.get(i)
            }
        }



        for ((i,value) in listaPuntuaciones?.withIndex()!!) {
            if(i == 0){
                menorPuntuacion = listaPuntuaciones!!.get(i)
            }else if(listaPuntuaciones!!.get(i)< menorPuntuacion){
                menorPuntuacion = listaPuntuaciones!!.get(i)
            }
        }





        binding.puntAlta.text = "Mayor puntuación: " + mayorPuntuacion.toString()
        binding.puntBaja.text = "Menor puntuación: " + menorPuntuacion.toString()



        //Obtener preferenceManager
        val prefs = PreferenceManager.getDefaultSharedPreferences(this)

        //guardamos la preferencia
        val editor = prefs.edit()
        editor.putInt(key,mayorPuntuacion)
        editor.apply()

        //recuperamos preferencia
        val myPref = prefs.getInt(key,mayorPuntuacion)
        binding.puntAlta.text = "Mayor puntuación: $myPref"


    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this,MainActivity::class.java)
        intent.putExtra("mayorPunt",mayorPuntuacion)
        Log.e("mm",mayorPuntuacion.toString())
        startActivity(intent)
    }
}