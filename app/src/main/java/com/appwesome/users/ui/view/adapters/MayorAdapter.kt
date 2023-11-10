package com.appwesome.users.ui.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import com.appwesome.users.R
import com.appwesome.users.data.model.MayorModel
import com.appwesome.users.databinding.ItemViewBinding

class MayorAdapter(
    private val selectListener : (item : MayorModel)->Unit,
    private val deleteListener : (item : MayorModel)->Unit,
    private val updateListener : (item : MayorModel)->Unit,
) : RecyclerView.Adapter<MayorAdapter.ViewHolder>() {

    private var mayors : List<MayorModel> = listOf()

    fun sentData(mayors:List<MayorModel>){
        this.mayors = mayors
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return  mayors.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mayor =  mayors[position]
        itemCount.let {
            holder.itemView.setOnClickListener{
                selectListener(mayor)
            }
            holder.bind(mayor)
        }

        holder.itemView.findViewById<ImageButton>(R.id.delete_item).setOnClickListener{
            deleteListener(mayor)
        }

        holder.itemView.findViewById<ImageButton>(R.id.edit_item).setOnClickListener{
            updateListener(mayor)
        }
    }

    class ViewHolder( v : View) : RecyclerView.ViewHolder(v){
        private val bing = ItemViewBinding.bind(v)

        fun bind(item: MayorModel){
            bing.itemTitle.text = item.nameMayor
        }

    }
}