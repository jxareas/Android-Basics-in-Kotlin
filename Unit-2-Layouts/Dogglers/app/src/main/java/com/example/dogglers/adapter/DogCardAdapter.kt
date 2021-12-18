
package com.example.dogglers.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dogglers.R
import com.example.dogglers.const.Layout
import com.example.dogglers.data.DataSource
import com.example.dogglers.model.Dog


class DogCardAdapter(
    private val context: Context?,
    private val layout: Int
): RecyclerView.Adapter<DogCardAdapter.DogCardViewHolder>() {

    // TODO: Initialize the data using the List found in data/DataSource
    val dataset : List<Dog> = DataSource.dogs

    class DogCardViewHolder(view: View?): RecyclerView.ViewHolder(view!!) {
        // TODO: Declare and initialize all of the list item UI components
        val photo : ImageView? = view?.findViewById(R.id.image_view_dog_photo)
        val name : TextView? = view?.findViewById(R.id.text_view_dog_name)
        val age : TextView? = view?.findViewById(R.id.text_view_dog_age)
        val hobbies : TextView? = view?.findViewById(R.id.text_view_dog_hobbies)


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogCardViewHolder
        {
            val layoutResource = if(layout == Layout.GRID) R.layout.grid_list_item else R.layout.vertical_horizontal_list_item

            return LayoutInflater
                .from(parent.context)
                .inflate(layoutResource, parent, false)
                .let {DogCardViewHolder(it)}
        }

    override fun getItemCount() = dataset.size //  return the size of the data set instead of 0

    override fun onBindViewHolder(holder: DogCardViewHolder, position: Int) {
        // Get the data at the current position
        val item = dataset[position]
        // Set the image resource for the current dog
        holder.photo?.setImageResource(item.imageResourceId)
        // Set the text for the current dog's name
        holder.name?.text = item.name


        val resources = context?.resources
        // Set the text for the current dog's hobbies by passing the hobbies to the

        // Set the text for the current dog's age
        holder.age?.text = resources?.getString(R.string.dog_age, item.age)
        holder.hobbies?.text = resources?.getString(R.string.dog_hobbies, item.hobbies)

    }
}