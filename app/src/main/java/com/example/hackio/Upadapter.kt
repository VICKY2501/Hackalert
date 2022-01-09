package com.example.hackio

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hackio.databinding.UpdataBinding


class Upadapter(val listener:Uplisten) :
    ListAdapter<ContestsItem, Upadapter.FoodPhotosViewHolder>(DiffCallback) {

    /**
     * The MarsPhotosViewHolder constructor takes the binding variable from the associated
     * GridViewItem, which nicely gives it access to the full [MarsPhoto] information.
     */
    inner class FoodPhotosViewHolder(
        private var binding: UpdataBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(foodPhoto: ContestsItem) {
            binding.codes= foodPhoto
            binding.executePendingBindings()
            itemView.setOnClickListener{
                listener.onclicked(foodPhoto)
            }
            binding.setReminder.setOnClickListener{
              listener.timerupcoming(foodPhoto)
            }
        }
    }

    /**
     * Allows the RecyclerView to determine which items have changed when the [List] of
     * [MarsPhoto] has been updated.
     */
    companion object DiffCallback : DiffUtil.ItemCallback<ContestsItem>() {
        override fun areItemsTheSame(oldItem: ContestsItem, newItem: ContestsItem): Boolean {
            return oldItem.duration == newItem.duration
        }

        override fun areContentsTheSame(oldItem: ContestsItem, newItem: ContestsItem): Boolean {
            return oldItem.end_time== newItem.end_time
        }
    }

    /**
     * Create new [RecyclerView] item views (invoked by the layout manager)
     */
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FoodPhotosViewHolder {
        return FoodPhotosViewHolder(
            UpdataBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    /**
     * Replaces the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(holder: FoodPhotosViewHolder, position: Int) {
        val foodPhoto = getItem(position)
        holder.bind(foodPhoto)
    }
}
interface Uplisten
{
    fun onclicked(hit:ContestsItem)
     fun timerupcoming(foodPhoto: ContestsItem)
}