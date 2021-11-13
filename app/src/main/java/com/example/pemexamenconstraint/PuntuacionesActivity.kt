package com.example.pemexamenconstraint

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.pemexamenconstraint.databinding.ActivityMainBinding
import com.example.pemexamenconstraint.databinding.ActivityPuntuacionesBinding

class PuntuacionesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPuntuacionesBinding
    private var listaPuntuaciones:java.util.ArrayList<Int>? = ArrayList()
    private var mayorPuntuacion : Int = 0
    private var menorPuntuacion : Int = 0



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

    }
}