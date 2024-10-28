package com.example.ejercicio1_aleman

import android.app.DatePickerDialog
import android.content.Intent
import android.icu.util.Calendar
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.get
import com.example.ejercicio1_aleman.databinding.ActivityDestinoBinding
import com.example.ejercicio1_aleman.databinding.ActivityLauncherBinding

class DestinoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDestinoBinding
    private var selectedDepartureDate: Long = 0
    private var selectedReturnDate: Long = 0
    var fecha_ida=""
    var fecha_vuelta=""
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityDestinoBinding.inflate(layoutInflater)

        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        var destino = ""
        var origen = ""
        var hora_ida = ""
        var hora_vuelta = ""
        binding.datePickerButtonVuelta.isEnabled = false
        binding.datePickerButtonVuelta.backgroundTintList
        binding.selectDestino.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                destino=parent.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
        binding.selectOrigen.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                origen=parent.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
        binding.selectHoraIda.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                hora_ida=parent.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
        binding.selectHoraVuelta.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                hora_vuelta=parent.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
        binding.btnDatos.setOnClickListener {
            if(destino==origen){
                Log.d("MiApp", "Mismo lugar")
                Toast.makeText(
                    this,
                    getString(R.string.mismo_lugar),
                    Toast.LENGTH_SHORT
                ).show()
            }else{
                Log.d("MiApp", "Correcto")
                if((selectedDepartureDate.toInt() != 0) and (selectedReturnDate.toInt() != 0)){
                    val params = bundleOf(
                        "origen" to origen,
                        "destino" to destino,
                        "fecha_ida" to fecha_ida,
                        "fecha_vuelta" to fecha_vuelta,
                        "hora_ida" to hora_ida,
                        "hora_vuelta" to hora_vuelta
                    )

                    val intent = Intent(this, DatosActivity::class.java)

                    intent.putExtras(params)

                    startActivity(intent)
                }else{
                    Log.d("MiApp", "Falta fecha")
                    Toast.makeText(
                        this,
                        getString(R.string.falta_fecha),
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }
        }
        val datePickerButtonIda: Button = findViewById(R.id.datePickerButton_ida)
        val datePickerButtonVuelta: Button = findViewById(R.id.datePickerButton_vuelta)

        datePickerButtonIda.setOnClickListener {
            showDatePickerDialog_ida()
        }
        datePickerButtonVuelta.setOnClickListener {
            showDatePickerDialog_vuelta()
        }
    }
    private fun showDatePickerDialog_ida() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay ->
                calendar.set(selectedYear, selectedMonth, selectedDay)
                selectedDepartureDate = calendar.timeInMillis

                // Aquí puedes hacer algo con la fecha seleccionada
                val selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                binding.datePickerButtonIda.text = selectedDate  // Actualiza el texto del botón con la fecha seleccionada
                fecha_ida=selectedDate
                binding.datePickerButtonVuelta.isEnabled = true
            },
            year, month, day
        )
        datePickerDialog.datePicker.minDate = calendar.timeInMillis+86400000
        datePickerDialog.show()
    }
    private fun showDatePickerDialog_vuelta() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay ->
                calendar.set(selectedYear, selectedMonth, selectedDay)
                selectedReturnDate = calendar.timeInMillis
                // Aquí puedes hacer algo con la fecha seleccionada
                val selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                binding.datePickerButtonVuelta.text = selectedDate  // Actualiza el texto del botón con la fecha seleccionada
                fecha_vuelta=selectedDate
            },
            year, month, day
        )
        datePickerDialog.datePicker.minDate = selectedDepartureDate+86400000
        datePickerDialog.show()
    }
}