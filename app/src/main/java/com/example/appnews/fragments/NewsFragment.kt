package com.example.appnews.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.appnews.R
import com.example.appnews.network.networkmodel.NetworkService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor


class NewsFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val networkcall = GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = NetworkService.loadInstance().getArticleByKeyWord("bitcoin")
                response?.let {
                    Log.d(
                        "Network Response",
                        "First article: ${response.articles.first().toString()}"
                    )
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