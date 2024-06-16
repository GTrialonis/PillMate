package com.example.pillmate.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.pillmate.models.Medicine

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "pillmate.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_MEDICINES = "medicines"

        private const val COLUMN_ID = "id"
        private const val COLUMN_NAME = "name"
        private const val COLUMN_FORM = "form" // Use "form" instead of "dosage_form"
        private const val COLUMN_QUANTITY = "quantity"
        private const val COLUMN_FREQUENCY = "frequency"
        private const val COLUMN_TIME = "time"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = ("CREATE TABLE $TABLE_MEDICINES ("
                + "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "$COLUMN_NAME TEXT, "
                + "$COLUMN_FORM TEXT, " // Use "form" instead of "dosage_form"
                + "$COLUMN_QUANTITY INTEGER, "
                + "$COLUMN_FREQUENCY TEXT, "
                + "$COLUMN_TIME TEXT)")
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_MEDICINES")
        onCreate(db)
    }

    fun addMedicine(medicine: Medicine) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_NAME, medicine.name)
        values.put(COLUMN_FORM, medicine.dosageForm) // Use "form" instead of "dosage_form"
        values.put(COLUMN_QUANTITY, medicine.quantity)
        values.put(COLUMN_FREQUENCY, medicine.frequency)
        values.put(COLUMN_TIME, medicine.time)

        db.insert(TABLE_MEDICINES, null, values)
        db.close()
    }

    fun getAllMedicines(): List<Medicine> {
        val medicines = mutableListOf<Medicine>()
        val selectQuery = "SELECT * FROM $TABLE_MEDICINES"
        val db = this.readableDatabase
        val cursor = db.rawQuery(selectQuery, null)

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
                val name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME))
                val form = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FORM)) // Use "form" instead of "dosage_form"
                val quantity = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_QUANTITY))
                val frequency = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FREQUENCY))
                val time = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TIME))

                val medicine = Medicine(id, name, form, quantity, frequency, time) // Use "form" instead of "dosage_form"
                medicines.add(medicine)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return medicines
    }
}
