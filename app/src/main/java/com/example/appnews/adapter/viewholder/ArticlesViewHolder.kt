package com.example.appnews.adapter.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.example.appnews.R
import com.example.appnews.network.networkmodel.Article

class ArticlesViewHolder(val view : View) : ViewHolder(view) {

    val titleView = view.findViewById<TextView>(R.id.article_title)
    val descriptionView = view.findViewById<TextView>(R.id.article_description)
    val dataView = view.findViewById<TextView>(R.id.article_date)
    val imageView = view.findViewById<ImageView>(R.id.article_image)

    fun bindArticle(article : Article){
        titleView.text = article.title
        descriptionView.text = article.description
        dataView.text = article.publishedAt
        imageView.load(article.urlToImage)
    }
}