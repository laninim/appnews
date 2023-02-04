package com.example.appnews.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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
import com.example.appnews.network.networkmodel.NewsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor


class NewsFragment : Fragment() {

    private var _binding : FragmentNewsBinding? = null
    val binding get() = _binding!!

    private lateinit var adapter : ArticleAdapter

    private lateinit var response : NewsResponse

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    val listener =  object : OnArticleClick {
        override fun onClickArticle(article: Article) {
            Log.d("Article", "Article: $article")
            val action = NewsFragmentDirections.actionNewsFragmentToArticleDetails(article)
            findNavController().navigate(action)
        }

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

        addSearchButtonListener()

        val networkcall = GlobalScope.launch(Dispatchers.IO) {
            try {
                response = NetworkService.loadInstance().getArticleByKeyWord("bitcoin")
                response?.let {
                    withContext(Dispatchers.Main){
                        adapter = ArticleAdapter(response.articles, listener, requireContext())
                        binding.newslist.adapter = adapter
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
        networkcall.invokeOnCompletion {
            addTextListener(response.articles)
            Log.d("NewsFragment","Invoke listener")
        }
    }

    private fun addSearchButtonListener(){
        binding.searchButton.setOnClickListener {
            showSearchBar()
        }
    }

    private fun showSearchBar() {
        if(binding.searchBar.visibility == View.INVISIBLE){
            binding.searchBar.visibility = View.VISIBLE
        }else{
            binding.searchBar.visibility = View.INVISIBLE
        }
    }

    private fun addTextListener(listArticle : List<Article>){

        binding.searchBar.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }


            override fun afterTextChanged(s: Editable?) {
               val newList = listArticle.filter {
                   it.title.toString().contains(s.toString())
               }
                adapter = ArticleAdapter(newList, listener, requireContext())
                binding.newslist.adapter = adapter
                adapter.notifyDataSetChanged()
                Log.d("Newsfragment","Invoke text listener")


            }

        })
    }


}