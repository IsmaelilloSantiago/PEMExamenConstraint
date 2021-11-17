package com.example.pemexamenconstraint

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.pemexamenconstraint.databinding.ActivityAcertarValorBinding
import kotlin.math.round


class AcertarValorActivity : AppCompatActivity() {

    //global
    private lateinit var binding: ActivityAcertarValorBinding
    private var valorObjetivo: Int = (0 until 255).random()
    private  var valorDado: Int = 0
    private var intentos = 0
    private var ultPuntuacion: Double = 0.0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAcertarValorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //actualizamos texto con num aleatorio
        val textobase = binding.valoraBuscar.text
        binding.valoraBuscar.text = "$textobase" + "$valorObjetivo"

        //escuchamos cambio en seekBar
        escucharSeekBar()
        escucharBoton()


    }

    private fun escucharBoton() {
        binding.button.setOnClickListener {

            var score = calcularPuntuacion()

            intentos++;
            ultPuntuacion = score

            if(ultPuntuacion == 100.0){
                basicAlert(ultPuntuacion)
                valorObjetivo = (0 until 255).random()
                intentos = 0
                val textobase = binding.valoraBuscar.text
                binding.valoraBuscar.text = "Acierta este valor: " + "$valorObjetivo"
                binding.lastScore.text = "Última puntuación: "
                binding.tvIntentos.text = "Intentos: "

            }else{

                binding.lastScore.text = "Última puntuación: " + ultPuntuacion.toString()
                binding.tvIntentos.text = "Intentos: " + intentos.toString()
            }





            //Toast.makeText(this, "$score", Toast.LENGTH_LONG).show()
        }
    }

    private fun calcularPuntuacion(): Double {
        var difValorAbsoluto = valorObjetivo - valorDado

        if(difValorAbsoluto < 0){
            difValorAbsoluto = - difValorAbsoluto
        }
        var dif:Double = difValorAbsoluto/255.0
        var score = round((1.0-dif)*100)
        return score
    }

    private fun escucharSeekBar() {

        binding.seekBarPuntuacion.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                valorDado = progress;

            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })
    }
    fun basicAlert(puntuacion: Double){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Your Score!")
        builder.setMessage("$puntuacion")
       // builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = basicAlert(puntuacion)))

        builder.show()
    }
}