package com.example.appnews.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appnews.R
import com.example.appnews.adapter.viewholder.ArticlesViewHolder
import com.example.appnews.adapter.viewholder.FavoritesViewHolder
import com.example.appnews.network.networkmodel.Article

class FavoritesAdapter(val articles : List<Article>, val onArticleClick: OnArticleClick, val context : Context): RecyclerView.Adapter<FavoritesViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.article_layout,parent,false)
            return FavoritesViewHolder(view)
        }

        override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
            holder.bindArticle(articles[position], onArticleClick, context)
        }

        override fun getItemCount(): Int {
            return articles.size
        }
    }
