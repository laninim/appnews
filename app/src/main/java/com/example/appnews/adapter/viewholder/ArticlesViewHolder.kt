package com.example.appnews.adapter.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.example.appnews.R
import com.example.appnews.network.networkmodel.Article

class ArticlesViewHolder(val view : View) : ViewHolder(view) {

    val titleView = view.findViewById<TextView>(R.id.article_title)
    val descriptionView = view.findViewById<TextView>(R.id.article_description)
    val dataView = view.findViewById<TextView>(R.id.article_date)
    val constraintlayout = view.findViewById<ConstraintLayout>(R.id.mainlayout)
    val imagearticle = view.findViewById<ImageView>(R.id.article_image)

    fun bindArticle(article : Article){
        titleView.text = article.title
        descriptionView.text = article.content
        dataView.text = article.publishedAt
        imagearticle.load(article.urlToImage)
    }
}