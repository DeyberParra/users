package com.appwesome.users.ui.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import com.appwesome.users.R
import com.appwesome.users.data.model.StateModel
import com.appwesome.users.databinding.ItemViewBinding

class StateAdapter(
    private val selectListener : (item : StateModel)->Unit,
    private val deleteListener : (item : StateModel)->Unit,
    private val updateListener : (item : StateModel)->Unit,
) : RecyclerView.Adapter<StateAdapter.ViewHolder>() {

    private var states : List<StateModel> = listOf()

    fun sentData(states:List<StateModel>){
        this.states = states
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return  states.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val state =  states[position]
        itemCount.let {
            holder.itemView.setOnClickListener{
                selectListener(state)
            }
            holder.bind(state)
        }

        holder.itemView.findViewById<ImageButton>(R.id.delete_item).setOnClickListener{
            deleteListener(state)
        }

        holder.itemView.findViewById<ImageButton>(R.id.edit_item).setOnClickListener{
            updateListener(state)
        }
    }

    class ViewHolder( v : View) : RecyclerView.ViewHolder(v){
        private val bing = ItemViewBinding.bind(v)

        fun bind(item: StateModel){
            bing.itemTitle.text = item.nameState
        }

    }
}