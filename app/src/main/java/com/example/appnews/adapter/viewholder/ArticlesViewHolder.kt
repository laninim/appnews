package com.example.appnews.adapter.viewholder

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.appnews.database.entity.Database
import androidx.room.Room
import coil.load
import com.example.appnews.R
import com.example.appnews.adapter.OnArticleClick
import com.example.appnews.network.networkmodel.Article
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ArticlesViewHolder(val view : View) : ViewHolder(view) {

    val titleView = view.findViewById<TextView>(R.id.article_title)
    val descriptionView = view.findViewById<TextView>(R.id.article_description)
    val dataView = view.findViewById<TextView>(R.id.article_date)
    val imagearticle = view.findViewById<ImageView>(R.id.article_image)
    val starButton = view.findViewById<ImageView>(R.id.starImageView)

    fun bindArticle(article : Article, listener : OnArticleClick, context:Context){
        titleView.text = article.title
        descriptionView.text = article.content
        dataView.text = article.publishedAt
        imagearticle.load(article.urlToImage)
        imagearticle.setOnClickListener { listener.onClickArticle(article) }
        if(article.favorite){
            starButton.setImageResource(R.drawable.ic_baseline_star_24)
        }

        addToPreferite(article, context)

    }

    private fun addToPreferite(article: Article, context:Context){
        starButton.setOnClickListener {
            article.favorite = true
            starButton.setImageResource(R.drawable.ic_baseline_star_24)
            val addPreferiteJob = GlobalScope.launch(Dispatchers.IO) {
                savePreferite(article, context)
                withContext(Dispatchers.Main){
                    Toast.makeText(context,"Add to favorites",Toast.LENGTH_SHORT).show()
                }
            }

        }
    }

    suspend fun savePreferite(article: Article, context:Context){
        val db = Room.databaseBuilder(
            context,Database::class.java, "favorites"
        ).build()
        db?.let {
            db.topicDao().insertTopic(article)
        }

    }
}