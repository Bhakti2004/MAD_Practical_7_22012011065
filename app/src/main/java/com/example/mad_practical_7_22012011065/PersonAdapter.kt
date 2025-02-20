package com.example.mad_practical_7_22012011065

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PersonAdapter(private val persons: Array<Person>) : RecyclerView.Adapter<PersonAdapter.PersonViewHolder>() {

    class PersonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView= itemView.findViewById(R.id.name)
        val phoneTextView: TextView = itemView.findViewById(R.id.phone)
        val emailTextView: TextView = itemView.findViewById(R.id.email)
        val addressTextView: TextView = itemView.findViewById(R.id.address)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.listview_component, parent, false)
        return PersonViewHolder(view)
    }

    override fun getItemCount(): Int = persons.size

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val person = persons[position]
        holder.nameTextView.text = person.name
        holder.phoneTextView.text = person.phoneNo
        holder.emailTextView.text = person.emailId
        holder.addressTextView.text = person.address
    }
}
