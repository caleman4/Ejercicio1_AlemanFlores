package com.example.ejercicio1_aleman

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ejercicio1_aleman.databinding.ActivityDatosBinding
import com.example.ejercicio1_aleman.databinding.ActivityDestinoBinding

class DatosActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDatosBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDatosBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.btnDatos.setOnClickListener {
            if (binding.etName.text.toString().isNotEmpty()) {
                if (binding.etSurname.text.toString().isNotEmpty()) {
                    if (binding.etEmail.text.toString().isNotEmpty()) {
                        val prevParams = intent.extras
                        prevParams?.let{ args ->
                            val destino = args.getString("destino", "")
                            val origen = args.getString("origen", "")
                            val fecha_ida = args.getString("fecha_ida", "")
                            val hora_ida = args.getString("hora_ida", "")
                            val fecha_vuelta = args.getString("fecha_vuelta", "")
                            val hora_vuelta = args.getString("hora_vuelta", "")

                            val params = bundleOf(
                                "name" to binding.etName.text.toString(),
                                "surname" to binding.etSurname.text.toString(),
                                "email" to binding.etEmail.text.toString(),
                                "destino" to destino,
                                "origen" to origen,
                                "fecha_ida" to fecha_ida,
                                "hora_ida" to hora_ida,
                                "fecha_vuelta" to fecha_vuelta,
                                "hora_vuelta" to hora_vuelta
                            )
                            if (binding.etViajero.text.toString().isNotEmpty()) {
                                params.putBoolean("viajero", true)
                            }

                            val intent = Intent(this, ReservacionActivity::class.java)

                            intent.putExtras(params)

                            startActivity(intent)
                        }

                    }else{
                        Toast.makeText(
                            this,
                            getString(R.string.ingresa_email),
                            Toast.LENGTH_SHORT
                        ).show()
                        binding.etEmail.error = getString(R.string.email_requerido)
                        binding.etEmail.requestFocus()
                    }
                }else{
                    Toast.makeText(
                        this,
                        getString(R.string.ingresa_apellido),
                        Toast.LENGTH_SHORT
                    ).show()
                    binding.etSurname.error = getString(R.string.apellido_requerido)
                    binding.etSurname.requestFocus()
                }
            }else{
                Toast.makeText(
                    this,
                    getString(R.string.ingresa_nombre),
                    Toast.LENGTH_SHORT
                ).show()
                binding.etName.error = getString(R.string.nombre_requerido)
                binding.etName.requestFocus()
            }

        }
    }
}