package com.appwesome.users.ui.view.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.appwesome.users.data.model.StateModel


class SpinnerStateAdapter(context: Context, private val states: List<StateModel>) : ArrayAdapter<StateModel>(context, android.R.layout.simple_spinner_item, states) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = super.getView(position, convertView, parent)
        val state = getItem(position)
        view.findViewById<TextView>(android.R.id.text1).text = state?.nameState
        return view
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = super.getDropDownView(position, convertView, parent)
        val state = getItem(position)
        view.findViewById<TextView>(android.R.id.text1).text = state?.nameState
        return view
    }
}
