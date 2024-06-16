package com.example.pillmate.ui.theme

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.pillmate.R
import com.example.pillmate.models.Medicine

class MedicineAdapter(private val context: Context, private val dataSource: List<Medicine>) : BaseAdapter() {

    override fun getCount(): Int {
        return dataSource.size
    }

    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = convertView ?: LayoutInflater.from(context).inflate(R.layout.medicine_item, parent, false)

        val medicine = getItem(position) as Medicine

        val nameTextView = view.findViewById<TextView>(R.id.medicine_name_text_view)
        val dosageFormTextView = view.findViewById<TextView>(R.id.medicine_dosage_form_text_view)
        val quantityTextView = view.findViewById<TextView>(R.id.medicine_quantity_text_view)
        val frequencyTextView = view.findViewById<TextView>(R.id.medicine_frequency_text_view)
        val timeTextView = view.findViewById<TextView>(R.id.medicine_time_text_view)

        nameTextView.text = medicine.name
        dosageFormTextView.text = medicine.dosageForm
        quantityTextView.text = context.getString(R.string.dosage_format, medicine.dosageForm, medicine.quantity)
        frequencyTextView.text = medicine.frequency
        timeTextView.text = medicine.time

        return view
    }
}
