package com.myapps.news

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.myapps.news.databinding.NewsCardViewBinding
import java.util.*


class NewsAdapter(val news: MutableList<Results>, val context: Context): RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdapter.ViewHolder {
        val binding = NewsCardViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsAdapter.ViewHolder, position: Int) {
        val rnd = Random()
        val color: Int = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
        holder.binding.newsLayout.setBackgroundColor(color)

        holder.binding.news = news[position]
        holder.binding.newsTextView.setOnClickListener {
            val intent = Intent(context, WebActivity::class.java)
            intent.putExtra("url", news[position].webUrl)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = this.news.size

    inner class ViewHolder(val binding: NewsCardViewBinding) : RecyclerView.ViewHolder(binding.root) {}

}