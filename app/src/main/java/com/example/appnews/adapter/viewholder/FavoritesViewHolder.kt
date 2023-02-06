package com.example.appnews.adapter.viewholder

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.persistableBundleOf
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import coil.load
import com.example.appnews.R
import com.example.appnews.adapter.FavoritesAdapter
import com.example.appnews.adapter.OnArticleClick
import com.example.appnews.database.entity.Database
import com.example.appnews.network.networkmodel.Article
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoritesViewHolder(val view : View) : RecyclerView.ViewHolder(view) {

    val titleView = view.findViewById<TextView>(R.id.article_title)
    val descriptionView = view.findViewById<TextView>(R.id.article_description)
    val dataView = view.findViewById<TextView>(R.id.article_date)
    val imagearticle = view.findViewById<ImageView>(R.id.article_image)
    val starButton = view.findViewById<ImageView>(R.id.starImageView)

    fun bindArticle(article : Article, listener : OnArticleClick, context: Context, favoritesAdapter: FavoritesAdapter, position: Int){
        titleView.text = article.title
        descriptionView.text = article.content
        dataView.text = article.publishedAt
        imagearticle.load(article.urlToImage)
        imagearticle.setOnClickListener { listener.onClickArticle(article) }
        if(article.favorite){
            starButton.setImageResource(R.drawable.ic_baseline_star_24)
        }
        starButton.setOnClickListener {
            GlobalScope.launch(Dispatchers.IO) {
                val db = Room.databaseBuilder(
                    context, Database::class.java, "favorites"
                ).build()
                db.topicDao().deleteTopic(article)
                withContext(Dispatchers.Main){
                    Navigation.findNavController(view).navigate(R.id.action_topicFragment_to_newsFragment)
                }
                Log.d("FavoritesViewholder","Remove element")
            }
        }
    }

}