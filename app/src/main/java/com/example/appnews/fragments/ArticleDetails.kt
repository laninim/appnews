package com.example.appnews.fragments

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.appnews.R
import com.example.appnews.databinding.FragmentArticleDetailsBinding
import com.example.appnews.network.networkmodel.Article


class ArticleDetails : Fragment() {

    private var _binding : FragmentArticleDetailsBinding? = null
    val binding get() = _binding!!

    val args : ArticleDetailsArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentArticleDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val currentArticle = args.article
        loadArticle(article = currentArticle)

    }

    private fun loadArticle(article: Article){
        binding.detailTitle.text = article.title
        binding.imageView.load(article.urlToImage)
        binding.textView.text = article.content
    }
}