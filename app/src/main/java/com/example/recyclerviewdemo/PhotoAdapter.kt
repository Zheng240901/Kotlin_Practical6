package com.example.recyclerviewdemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load

class PhotoAdapter (
        val fn: (ViewHolder, Photo) -> Unit = { _,_ ->Unit}

        ): ListAdapter<Photo, PhotoAdapter.ViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<Photo>(){
        override fun areItemsTheSame(a: Photo, b: Photo)    = a.id == b.id
        override fun areContentsTheSame(a: Photo, b: Photo) = a == b

    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val root = view
        val imagePhoto: ImageView = view.findViewById(R.id.imgPhoto)
        val txtAuthor: TextView = view.findViewById(R.id.txtAuthor)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.photo_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.imagePhoto.load(item.photo) { placeholder(R.drawable.loading_ani) }
        holder.txtAuthor.text = item.author

        fn(holder, item)
    }
}