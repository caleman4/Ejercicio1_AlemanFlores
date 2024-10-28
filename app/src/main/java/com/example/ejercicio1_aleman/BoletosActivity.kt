package com.example.ejercicio1_aleman

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ejercicio1_aleman.databinding.ActivityBoletosBinding
import com.example.ejercicio1_aleman.databinding.ActivityReservacionBinding

class BoletosActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBoletosBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBoletosBinding.inflate(layoutInflater)
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
            val destino = args.getString("destino", "")
            val origen = args.getString("origen", "")
            val fecha_ida = args.getString("fecha_ida", "")
            val hora_ida = args.getString("hora_ida", "")
            val fecha_vuelta = args.getString("fecha_vuelta", "")
            val hora_vuelta = args.getString("hora_vuelta", "")
            val asientoIda = args.getString("asientoIda", "00")
            val asientoVuelta = args.getString("asientoVuelta", "")
            Log.d("APPLOGS", "asiento recibido: $asientoIda")
            binding.tvNombre1.text=getString(R.string.cadena_simple, name)
            binding.tvAsientoIda1.text=getString(R.string.cadena_simple, asientoIda)
            binding.tvAsientoIda2.text=getString(R.string.cadena_simple, asientoIda)
            binding.tvFechaIda1.text=getString(R.string.cadena_simple, fecha_ida)
            binding.tvFechaIda2.text=getString(R.string.cadena_simple, fecha_ida)
            binding.tvHoraIda1.text=getString(R.string.cadena_simple, hora_ida)
            binding.tvHoraIda2.text=getString(R.string.cadena_simple, hora_ida)
            binding.tvOrigenIda1.text=getString(R.string.cadena_simple, origen)
            binding.tvOrigenIda2.text=getString(R.string.cadena_simple, origen)
            binding.tvDestinoIda1.text=getString(R.string.cadena_simple, destino)
            binding.tvDestinoIda2.text=getString(R.string.cadena_simple, destino)

            binding.tvNombre2.text=getString(R.string.cadena_simple, name)
            binding.tvAsientoVuelta1.text=getString(R.string.cadena_simple, asientoVuelta)
            binding.tvAsientoVuelta2.text=getString(R.string.cadena_simple, asientoVuelta)
            binding.tvFechaVuelta1.text=getString(R.string.cadena_simple, fecha_vuelta)
            binding.tvFechaVuelta2.text=getString(R.string.cadena_simple, fecha_vuelta)
            binding.tvHoraVuelta1.text=getString(R.string.cadena_simple, hora_vuelta)
            binding.tvHoraVuelta2.text=getString(R.string.cadena_simple, hora_vuelta)
            binding.tvOrigenVuelta1.text=getString(R.string.cadena_simple, destino)
            binding.tvOrigenVuelta2.text=getString(R.string.cadena_simple, destino)
            binding.tvDestinoVuelta1.text=getString(R.string.cadena_simple, origen)
            binding.tvDestinoVuelta2.text=getString(R.string.cadena_simple, origen)
        }
        binding.btnReservar.setOnClickListener {
            startActivity(Intent(this, DestinoActivity::class.java))
        }
    }
}