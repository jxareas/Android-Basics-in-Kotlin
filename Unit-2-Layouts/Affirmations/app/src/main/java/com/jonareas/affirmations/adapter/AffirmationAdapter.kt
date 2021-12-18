package com.jonareas.affirmations.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jonareas.affirmations.data.Datasource
import com.jonareas.affirmations.databinding.ItemAffirmationBinding
import com.jonareas.affirmations.model.Affirmation

class AffirmationAdapter(
    private val context: Context,
    private val list: List<Affirmation> = Datasource.loadAffirmations()
) : RecyclerView.Adapter<AffirmationAdapter.AffirmationViewHolder>() {

    inner class AffirmationViewHolder(private val binding: ItemAffirmationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(affirmation: Affirmation) = binding.run {
            affirmationTitle.text = context.resources.getString(affirmation.stringResourceId)
            affirmationImage.setImageResource(affirmation.imageResourceId)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AffirmationViewHolder =
        ItemAffirmationBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
            .let { AffirmationViewHolder(it) }

    override fun onBindViewHolder(holder: AffirmationViewHolder, position: Int) =
        list[position].run(holder::bind)

    override fun getItemCount(): Int = list.size

}
