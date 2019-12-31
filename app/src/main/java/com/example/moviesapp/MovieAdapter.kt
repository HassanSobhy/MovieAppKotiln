package com.example.moviesapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.databinding.MovieListItemBinding
import com.example.moviesapp.network.Results

class MovieAdapter(val onClickListener: OnClickListener) : ListAdapter<Results, MovieAdapter.MovieViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<Results>() {
        override fun areItemsTheSame(oldItem: Results, newItem: Results): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Results, newItem: Results): Boolean {
            return oldItem.id == newItem.id
        }
    }

    class MovieViewHolder(private val binding: MovieListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(results: Results) {
            binding.results = results
            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            binding.executePendingBindings()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(MovieListItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val results = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(results)
        }
        holder.bind(results)

    }
    /**
     * Custom listener that handles clicks on [RecyclerView] items.  Passes the [Results]
     * associated with the current item to the [onClick] function.
     * @param clickListener lambda that will be called with the current [Results]
     */
    class OnClickListener(val clickListener: (results:Results)->Unit){
        fun onClick(results: Results) = clickListener(results)
    }
}