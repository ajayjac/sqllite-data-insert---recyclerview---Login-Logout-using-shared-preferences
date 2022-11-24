package com.example.task5.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.task5.dataclass.EmpModelClass
import com.example.task5.R
import com.google.android.material.textview.MaterialTextView

class CustomAdapter( val mList: List<EmpModelClass>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val EmpModelClass = mList[position]

        holder.name.text = EmpModelClass.userName
        holder.phone.text = EmpModelClass.userPhone
        holder.address.text = EmpModelClass.userAddress
        holder.location.text = EmpModelClass.userLocation


    }


    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val name: MaterialTextView = itemView.findViewById(R.id.textName)
        val phone: MaterialTextView = itemView.findViewById(R.id.textPhone)
        val address: MaterialTextView = itemView.findViewById(R.id.textAddress)
        val location: MaterialTextView = itemView.findViewById(R.id.textLocation)
    }

    override fun getItemCount(): Int {
        return mList.size
    }
}
