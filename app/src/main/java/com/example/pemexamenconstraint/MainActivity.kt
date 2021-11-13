package com.example.pemexamenconstraint

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.pemexamenconstraint.databinding.ActivityMainBinding
import java.lang.reflect.Array
import kotlin.math.round
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var listaPuntuaciones:ArrayList<Int>

    private var colorR: Int = 0;
    private var colorG: Int = 0;
    private var colorB: Int = 0;

    private var colorRRandom: Int = 0
    private var colorGRandom: Int = 0
    private var colorBRandom: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.e("OnCreate","Main")

        listaPuntuaciones = ArrayList()

        ponerColorRandomObjetivo()
        ponerColorBuscar()
        actualizarTexto()


        escucharHitMe()
        escucharRojo()
        escucharVerde()
        escucharAzul()

        //viaje a otras pantallas
        escucharScore()

        
    }
    //Acertar puntuacion con seekBar
    private fun escucharScore() {
        binding.botonPuntuaciones.setOnClickListener{
            val intent = Intent(this,AcertarValorActivity::class.java)
            startActivity(intent)
        }
    }


    //PASAR LISTA CON PUNTUACIONES
    /*private fun escucharScore() {
        binding.botonPuntuaciones.setOnClickListener {
            stopTimer()
            val intent = Intent(this,PuntuacionesActivity::class.java)
            intent.putIntegerArrayListExtra("puntuaciones",listaPuntuaciones)
            startActivity(intent)
        }
    }*/

    private fun escucharHitMe() {
        binding.botonHitMe.setOnClickListener {
            calcularPuntuacion()
        }
    }


    private fun ponerColorBuscar() {
        binding.colorBuscar.setBackgroundColor(Color.rgb(colorR,colorG,colorB))
    }
    private fun startTimer(){
        cuentaAtras.start()
    }

    private fun stopTimer(){
        cuentaAtras.cancel()
    }

    private fun actualizarTexto(){
        binding.textoRGB.text = "R:$colorR G:$colorG B:$colorB"
    }

    private fun ponerColorRandomObjetivo() {
        colorRRandom = (0 until 255).random()
        colorGRandom = (0 until 255).random()
        colorBRandom = (0 until 255).random()
        binding.colorObjetivo.setBackgroundColor(Color.rgb(colorRRandom,colorGRandom,colorBRandom))
    }

    private fun escucharRojo() {
        binding.seekBarRed.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                colorR = progress;
                actualizarTexto()
                ponerColorBuscar();
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })
    }

    private fun escucharAzul() {
        binding.seekBarBlue.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                colorB = progress;
                actualizarTexto()
                ponerColorBuscar();
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })
    }

    private fun escucharVerde() {
        binding.seekBarGreen.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                colorG = progress;
                actualizarTexto()
                ponerColorBuscar();
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })

    }

    private var cuentaAtras = object:CountDownTimer(1000*60,1000){
        override fun onTick(millisUntilFinished: Long) {
            binding.timer.text = round(millisUntilFinished.toDouble()/1000.0).toInt().toString()
        }

        override fun onFinish() {
            calcularPuntuacion()
            startTimer()
        }

    }

    private fun calcularPuntuacion(){

        var score: Double = 0.0
        var difR : Int= colorR-colorRRandom
        var difG : Int= colorG-colorGRandom
        var difB : Int= colorB-colorBRandom

        //valores absolutos
        if(difB < 0){
            difB = -difB
        }
        if(difR < 0){
            difR = -difR
        }
        if(difG<0){
            difG = -difG
        }

        //calculo puntuacion
        var dif:Double = (difG/255.0 + difB/255.0 + difR/255.0)/3.0
        score = round((1.0-dif)*100)

        basicAlert(score)
        //se las paso a la lista
        listaPuntuaciones.add(score.toInt())
        Log.e("++",listaPuntuaciones.toString())



        Toast.makeText(this, " Diferencia R: $difR \n  Diferencia G: $difG \n" +
                "  Diferencia B: $difB \n  Score: $score \n", Toast.LENGTH_LONG).show()

        //Cambiar color una vez se ha dado la puntuación
        ponerColorRandomObjetivo()
        startTimer()
    }

    override fun onResume() {
        super.onResume()
        Log.e("OnResume","Main")
        ponerColorRandomObjetivo()
        startTimer()
    }


    fun basicAlert(puntuacion: Double){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Your Score!")
        builder.setMessage("$puntuacion")
        //builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = x))

        builder.show()
    }
}