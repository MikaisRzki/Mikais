package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class itemadapter(val tour: ArrayList<item>) : RecyclerView.Adapter<itemadapter.TourViewHolder>() {
    class TourViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textTitle: TextView = itemView.findViewById(R.id.text_title)
        val textdesk: TextView = itemView.findViewById(R.id.text_sub_title)
        val imgview: ImageView = itemView.findViewById(R.id.image_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TourViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_view_template, parent, false)
        return TourViewHolder(view)
    }

    override fun getItemCount(): Int {
        return tour.size
    }

    override fun onBindViewHolder(holder: TourViewHolder, position: Int) {
        val (title, description, imageView) = tour[position]
        holder.textTitle.text = title
        holder.textdesk.text = description
        holder.imgview.setImageResource(imageView)
    }
}