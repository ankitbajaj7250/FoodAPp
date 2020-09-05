package com.example.foodapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.R
import com.example.foodapp.model.itemlist

class Menu_adaper(
    val context: Context,
    private val menuList: ArrayList<itemlist>,
    private val listener: OnItemClickListener
) :
    RecyclerView.Adapter<Menu_adaper.MenuViewHolder>() {

    companion object {
        var isCartEmpty = true
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MenuViewHolder {
        val itemView = LayoutInflater.from(p0.context)
            .inflate(R.layout.menu_single_row, p0, false)

        return MenuViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return menuList.size
    }

    interface OnItemClickListener {
        fun onAddItemClick(itemlist: itemlist)
        fun onRemoveItemClick(itemlist: itemlist)
    }


    override fun onBindViewHolder(p0: MenuViewHolder, p1: Int) {
        val menuObject = menuList[p1]
        p0.foodItemName.text = menuObject.name
        val cost = "Rs. ${menuObject.cost?.toString()}"
        p0.foodItemCost.text = cost
        p0.sno.text = (p1 + 1).toString()
        p0.addToCart.setOnClickListener {
            p0.addToCart.visibility = View.GONE
            p0.removeFromCart.visibility = View.VISIBLE
            listener.onAddItemClick(menuObject)
        }

        p0.removeFromCart.setOnClickListener {
            p0.removeFromCart.visibility = View.GONE
            p0.addToCart.visibility = View.VISIBLE
            listener.onRemoveItemClick(menuObject)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    class MenuViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val foodItemName: TextView = view.findViewById(R.id.txtItemName)
        val foodItemCost: TextView = view.findViewById(R.id.txtItemCost)
        val sno: TextView = view.findViewById(R.id.txtSNo)
        val addToCart: Button = view.findViewById(R.id.btnAddToCart)
        val removeFromCart: Button = view.findViewById(R.id.btnRemoveFromCart)
    }
}