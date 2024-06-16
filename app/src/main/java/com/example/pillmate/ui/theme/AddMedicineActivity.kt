package com.example.pillmate.ui.theme

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TimePicker
import androidx.appcompat.app.AppCompatActivity
import com.example.pillmate.R
import com.example.pillmate.database.DatabaseHelper
import com.example.pillmate.models.Medicine

class AddMedicineActivity : AppCompatActivity() {

    private lateinit var nameEditText: EditText
    private lateinit var formSpinner: Spinner
    private lateinit var quantityEditText: EditText
    private lateinit var frequencySpinner: Spinner
    private lateinit var timePicker: TimePicker
    private lateinit var saveButton: Button
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_medicine)

        dbHelper = DatabaseHelper(this)
        nameEditText = findViewById(R.id.name_edit_text)
        formSpinner = findViewById(R.id.form_spinner)
        quantityEditText = findViewById(R.id.quantity_edit_text)
        frequencySpinner = findViewById(R.id.frequency_spinner)
        timePicker = findViewById(R.id.time_picker)
        saveButton = findViewById(R.id.save_button)

        // Set up form spinner
        ArrayAdapter.createFromResource(
            this,
            R.array.dosage_forms_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            formSpinner.adapter = adapter
        }

        // Set up frequency spinner
        ArrayAdapter.createFromResource(
            this,
            R.array.frequencies_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            frequencySpinner.adapter = adapter
        }

        saveButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val form = formSpinner.selectedItem.toString()
            val quantity = quantityEditText.text.toString().toInt()
            val frequency = frequencySpinner.selectedItem.toString()
            val time = "${timePicker.hour}:${timePicker.minute}"

            val medicine = Medicine(0, name, form, quantity, frequency, time)
            dbHelper.addMedicine(medicine)
            finish()
        }
    }
}
