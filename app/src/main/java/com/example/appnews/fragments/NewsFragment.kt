package com.example.appnews.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appnews.R
import com.example.appnews.adapter.ArticleAdapter
import com.example.appnews.adapter.OnArticleClick
import com.example.appnews.databinding.FragmentNewsBinding
import com.example.appnews.network.networkmodel.Article
import com.example.appnews.network.networkmodel.NetworkService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor


class NewsFragment : Fragment() {

    private var _binding : FragmentNewsBinding? = null
    val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentNewsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val networkcall = GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = NetworkService.loadInstance().getArticleByKeyWord("bitcoin")
                response?.let {
                    withContext(Dispatchers.Main){
                        binding.newslist.adapter = ArticleAdapter(response.articles, object : OnArticleClick {
                            override fun onClickArticle(article: Article) {
                                Log.d("Article", "Article: $article")
                                val action = NewsFragmentDirections.actionNewsFragmentToArticleDetails(article)
                                findNavController().navigate(action)
                            }

                        })
                        binding.newslist.layoutManager = LinearLayoutManager(requireContext())
                    }
                }
            }catch (e : java.lang.Exception){
                withContext(Dispatchers.Main){
                    Toast.makeText(requireContext(),"Network error",Toast.LENGTH_SHORT).show()
                    Log.d("Network error", "Error: ${e.printStackTrace()}")
                }
            }
        }
    }


}