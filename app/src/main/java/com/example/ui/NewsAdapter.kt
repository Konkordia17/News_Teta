package com.example.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.model.News
import com.example.ui.databinding.ItemNewBinding
import java.text.SimpleDateFormat

class NewsAdapter : ListAdapter<News, NewsAdapter.NewsHolder>(DiffUtilCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemNewBinding.inflate(inflater, parent, false)
        return NewsHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class NewsHolder(private val binding: ItemNewBinding) : RecyclerView.ViewHolder(binding.root) {
        val format = SimpleDateFormat("yyyy-MM-dd")
        val formatConverter = SimpleDateFormat("dd/MM/yyyy")

        fun bind(new: News) {
            val dateForm = format.parse(new.publishedAt)
            with(binding) {
                date.text = formatConverter.format(dateForm)
                titleTV.text = new.title
                articleTV.text = new.description
                Glide.with(itemView)
                    .load(new.urlToImage)
                    .into(image)
            }
        }
    }

    class DiffUtilCallBack : DiffUtil.ItemCallback<News>() {
        override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem == newItem
        }
    }
}
