package com.example.ejercicio1_aleman

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ejercicio1_aleman.databinding.ActivityDatosBinding
import com.example.ejercicio1_aleman.databinding.ActivityReservacionBinding
import java.text.NumberFormat
import java.util.Locale
import kotlin.random.Random

class ReservacionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReservacionBinding
    private var gmonto=0
    private var asientoIda=asiento()
    private var asientoVuelta=asiento()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReservacionBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val params = intent.extras
        params?.let { args ->
            val name = args.getString("name", "")
            val surname = args.getString("surname", "")
            val viajero = args.getBoolean("viajero", false)
            val destino = args.getString("destino", "")
            val origen = args.getString("origen", "")
            val fecha_ida = args.getString("fecha_ida", "")
            val hora_ida = args.getString("hora_ida", "")
            val fecha_vuelta = args.getString("fecha_vuelta", "")
            val hora_vuelta = args.getString("hora_vuelta", "")
            binding.tvNombre.text=getString(R.string.nombre_completo, name, surname)
            binding.tvCiudadVueloIda.text=getString(R.string.vuelo_ciudades, CityConverter(origen), CityConverter(destino))
            binding.tvDateTimeVueloIda.text=getString(R.string.dateTimeIda, fecha_ida, hora_ida)
            binding.tvAsientoVueloIda.text=getString(R.string.asientoIda,asientoIda)
            binding.tvCiudadVueloVuelta.text=getString(R.string.vuelo_ciudades, CityConverter(destino), CityConverter(origen))
            binding.tvDateTimeVueloVuelta.text=getString(R.string.dateTimeIda, fecha_vuelta, hora_vuelta)
            binding.tvAsientoVueloVuelta.text=getString(R.string.asientoIda,asientoVuelta)
            binding.tvMontoInicial.text=getString(R.string.montoInicial,generarMontoAleatorio())
            if (viajero){
                binding.tvDescuento.text=getString(R.string.descuento,descuentoViajero())
                binding.tvMontoFinal.text=getString(R.string.montoFinal,montoFinal())
            }

        }

        binding.btnReservar.setOnClickListener {
            params?.let { args ->
                val name = args.getString("name", "")
                val surname = args.getString("surname", "")
                val viajero = args.getBoolean("viajero", false)
                val destino = args.getString("destino", "")
                val origen = args.getString("origen", "")
                val fecha_ida = args.getString("fecha_ida", "")
                val hora_ida = args.getString("hora_ida", "")
                val fecha_vuelta = args.getString("fecha_vuelta", "")
                val hora_vuelta = args.getString("hora_vuelta", "")
                val paramsFinales = bundleOf(
                    "name" to getString(R.string.nombre_completo,name,surname),
                    "destino" to CityConverter(destino),
                    "origen" to CityConverter(origen),
                    "fecha_ida" to fecha_ida,
                    "hora_ida" to hora_ida,
                    "fecha_vuelta" to fecha_vuelta,
                    "hora_vuelta" to hora_vuelta,
                    "asientoIda" to asientoIda,
                    "asientoVuelta" to asientoVuelta
                )
                Log.d("APPLOGS", "asiento enviado: $asientoIda")
                val intent = Intent(this, BoletosActivity::class.java)

                intent.putExtras(paramsFinales)

                startActivity(intent)
            }

        }


    }
    private fun CityConverter(city: String): String {
        var code=""
        when (city) {
            "Cancún" -> {code="CUN"}
            "Ciudad de México" -> {code="MEX"}
            "Guadalajara" -> {code="GDL"}
            "Monterrey" -> {code="MTY"}
            "Los Cabos" -> {code="SJD"}
            "Tijuana" -> {code="TIJ"}
            else -> {code=""}
        }
        return code
    }
    @SuppressLint("DefaultLocale")
    private fun asiento(): String{
        // Generar un número aleatorio entre 1 y 25
        val numero = Random.nextInt(1, 26)
        // Generar una letra aleatoria entre 'A' y 'Z'
        val letra = ('A'..'D').random()

        // Formatear el número con 2 dígitos
        val numeroFormateado = String.format("%02d", numero)

        // Combinar el número y la letra en el formato deseado
        return "$numeroFormateado$letra"
    }
    private fun generarMontoAleatorio(): String {
        // Generar un número aleatorio entre 3000 y 10000
        val monto = Random.nextInt(3000, 10001) // El rango es inclusivo en el límite inferior y exclusivo en el superior
        gmonto=monto
        // Formatear el número como moneda
        val formatoMoneda = NumberFormat.getCurrencyInstance(Locale.US) // Puedes cambiar el locale si lo deseas
        return formatoMoneda.format(monto)
    }
    private fun descuentoViajero(): String{
        val descuento = gmonto*0.15
        val formatoMoneda = NumberFormat.getCurrencyInstance(Locale.US)
        return formatoMoneda.format(descuento)
    }
    private fun montoFinal(): String{
        val montoFinal = gmonto*0.85
        val formatoMoneda = NumberFormat.getCurrencyInstance(Locale.US)
        return formatoMoneda.format(montoFinal)
    }
}