package com.example.dogswelove.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dogswelove.R
import com.example.dogswelove.model.Dog

class DogAdapter : RecyclerView.Adapter<DogAdapter.DogViewHolder>() {
    private var dogs: List<Dog> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_dog, parent, false)
        return DogViewHolder(view)
    }

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        val dog = dogs[position]
        holder.textViewDogName.text = dog.dogName
        holder.textViewDogDescription.text = dog.description
        holder.textViewDogAge.text = "almost ${dog.age} years"
        Glide.with(holder.imageViewDog.context).load(dog.image).into(holder.imageViewDog)
    }

    override fun getItemCount(): Int = dogs.size

    fun setDogs(dogs: List<Dog>) {
        this.dogs = dogs
        notifyDataSetChanged()
    }

    class DogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewDogName: TextView = itemView.findViewById(R.id.textViewDogName)
        val textViewDogDescription: TextView = itemView.findViewById(R.id.textViewDogDescription)
        val textViewDogAge: TextView = itemView.findViewById(R.id.textViewDogAge)
        val imageViewDog: ImageView = itemView.findViewById(R.id.imageViewDog)
    }
}