package com.example.jokesapp.ui.jokes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.jokesapp.databinding.JokeItemBinding
import com.example.jokesapp.network.Value

class JokesListAdapter() : ListAdapter<Value, JokesListAdapter.JokesViewHolder>(DiffCallback) {

    class JokesViewHolder(private var binding: JokeItemBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(joke: Value) {
            binding.joke = joke
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Value>() {
        override fun areItemsTheSame(oldItem: Value, newItem: Value): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Value, newItem: Value): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): JokesViewHolder {
        return JokesViewHolder(JokeItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: JokesViewHolder, position: Int) {
        val joke = getItem(position)
        holder.bind(joke)
    }
}