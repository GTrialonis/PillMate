package com.example.pillmate

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.pillmate.database.DatabaseHelper
import com.example.pillmate.ui.theme.AddMedicineActivity
import com.example.pillmate.ui.theme.MedicineAdapter

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

        val medicineListView = findViewById<ListView>(R.id.medicine_list_view)
        val emptyMessage = findViewById<TextView>(R.id.empty_message)

        val medicines = dbHelper.getAllMedicines()
        Log.d("MainActivity", "Retrieved medicines: $medicines")

        if (medicines.isEmpty()) {
            emptyMessage.visibility = TextView.VISIBLE
            medicineListView.visibility = ListView.GONE
        } else {
            emptyMessage.visibility = TextView.GONE
            medicineListView.visibility = ListView.VISIBLE

            val adapter = MedicineAdapter(this, medicines)
            medicineListView.adapter = adapter
        }
    }
}
