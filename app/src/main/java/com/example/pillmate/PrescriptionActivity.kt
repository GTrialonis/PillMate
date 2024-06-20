package com.example.pillmate

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.pillmate.database.DatabaseHelper
import com.example.pillmate.ui.theme.MedicineAdapter

class PrescriptionActivity : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prescription)

        dbHelper = DatabaseHelper(this)

        val medicineListView = findViewById<ListView>(R.id.medicine_list_view)

        val medicines = dbHelper.getAllMedicines()
        val adapter = MedicineAdapter(this, medicines)
        medicineListView.adapter = adapter
    }
}
