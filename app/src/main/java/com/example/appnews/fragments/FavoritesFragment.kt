package com.example.appnews.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.appnews.R
import com.example.appnews.adapter.ArticleAdapter
import com.example.appnews.adapter.FavoritesAdapter
import com.example.appnews.adapter.OnArticleClick
import com.example.appnews.database.entity.Database
import com.example.appnews.databinding.FragmentFavoritesBinding
import com.example.appnews.network.networkmodel.Article
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class FavoritesFragment : Fragment() {

    var _binding : FragmentFavoritesBinding? = null
    val binding get() = _binding!!
    var favoriteArticleList : List<Article> = mutableListOf()

    val listener =  object : OnArticleClick {
        override fun onClickArticle(article: Article) {
            Log.d("Article", "Article: $article")
            val action = FavoritesFragmentDirections.actionTopicFragmentToArticleDetails(article)
            findNavController().navigate(action)
        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoritesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val populateListJob = GlobalScope.launch(Dispatchers.IO) {
            loadFavoriteArticle()
        }
    }

    suspend fun loadFavoriteArticle() {
        val db = Room.databaseBuilder(
            requireContext(), Database::class.java, "favorites"
        ).build()
        db?.let {
            favoriteArticleList = db.topicDao().getAll()
            if(favoriteArticleList.isNotEmpty()){
                withContext(Dispatchers.Main){
                    binding.favoriteList.adapter = FavoritesAdapter(favoriteArticleList,listener,requireContext())
                    binding.favoriteList.layoutManager = LinearLayoutManager(requireContext())
                }
            }
        }
    }



}