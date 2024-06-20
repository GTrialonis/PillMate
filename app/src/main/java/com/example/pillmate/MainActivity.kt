package com.example.pillmate

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.pillmate.database.DatabaseHelper
import com.example.pillmate.ui.theme.AddMedicineActivity

class MainActivity : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("MainActivity", "onCreate called")

        dbHelper = DatabaseHelper(this)

        val addMedicineButton = findViewById<Button>(R.id.add_medicine_button)
        addMedicineButton.setOnClickListener {
            val intent = Intent(this, AddMedicineActivity::class.java)
            startActivity(intent)
        }

        val viewPrescriptionButton = findViewById<Button>(R.id.view_prescription_button)
        viewPrescriptionButton.setOnClickListener {
            val intent = Intent(this, PrescriptionActivity::class.java)
            startActivity(intent)
        }
    }
}
