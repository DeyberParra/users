package com.appwesome.users.ui.view.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.appwesome.users.data.model.MayorModel

class SpinnerMayorAdapter(context: Context, private val mayors: List<MayorModel>) : ArrayAdapter<MayorModel>(context, android.R.layout.simple_spinner_item, mayors) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = super.getView(position, convertView, parent)
        val mayor = getItem(position)
        view.findViewById<TextView>(android.R.id.text1).text = mayor?.nameMayor
        return view
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = super.getDropDownView(position, convertView, parent)
        val mayors = getItem(position)
        view.findViewById<TextView>(android.R.id.text1).text = mayors?.nameMayor
        return view
    }
}