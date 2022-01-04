package com.example.android.marsphotos.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.marsphotos.databinding.GridViewItemBinding
import com.example.android.marsphotos.network.MarsPhoto

class PhotoGridAdapter : ListAdapter<MarsPhoto, PhotoGridAdapter.ViewHolder>(DiffCallback) {

    private companion object DiffCallback : DiffUtil.ItemCallback<MarsPhoto>() {
        override fun areItemsTheSame(oldItem: MarsPhoto, newItem: MarsPhoto): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: MarsPhoto, newItem: MarsPhoto): Boolean =
            oldItem.imageSource == newItem.imageSource
    }

    inner class ViewHolder(private val binding : GridViewItemBinding)
        : RecyclerView.ViewHolder(binding.root) {
            fun bind(marsPhoto: MarsPhoto) = binding.run {
                photo = marsPhoto
                executePendingBindings() }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(GridViewItemBinding.inflate(LayoutInflater.from(parent.context)))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) : Unit =
        holder.bind(getItem(position))

}