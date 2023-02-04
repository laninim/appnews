package com.example.appnews.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appnews.R
import com.example.appnews.adapter.viewholder.ArticlesViewHolder
import com.example.appnews.network.networkmodel.Article
import kotlinx.coroutines.withContext

interface OnArticleClick {
    fun onClickArticle(article : Article)
}

class ArticleAdapter(val articles : List<Article>, val onArticleClick: OnArticleClick, val context : Context): RecyclerView.Adapter<ArticlesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticlesViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.article_layout,parent,false)
        return ArticlesViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticlesViewHolder, position: Int) {
        holder.bindArticle(articles[position], onArticleClick, context)
    }

    override fun getItemCount(): Int {
        return articles.size
    }
}