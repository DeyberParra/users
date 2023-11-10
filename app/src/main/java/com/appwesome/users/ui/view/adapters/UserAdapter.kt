package com.appwesome.users.ui.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import com.appwesome.users.R
import com.appwesome.users.data.model.UserModel
import com.appwesome.users.databinding.ItemViewBinding

class UserAdapter(
    private val selectListener : (item : UserModel)->Unit,
    private val deleteListener : (item : UserModel)->Unit,
    private val updateListener : (item : UserModel)->Unit,
) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    private var users : List<UserModel> = listOf()

    fun sentData(users:List<UserModel>){
        this.users = users
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return  users.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user =  users[position]
        itemCount.let {
            holder.itemView.setOnClickListener{
                selectListener(user)
            }
            holder.bind(user)
        }

        holder.itemView.findViewById<ImageButton>(R.id.delete_item).setOnClickListener{
            deleteListener(user)
        }

        holder.itemView.findViewById<ImageButton>(R.id.edit_item).setOnClickListener{
            updateListener(user)
        }
    }

    class ViewHolder( v : View) : RecyclerView.ViewHolder(v){
        private val bing = ItemViewBinding.bind(v)

        fun bind(item: UserModel){
            bing.itemTitle.text = item.name
        }

    }
}