package com.example.appnews.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.room.Room
import androidx.room.migration.Migration
import com.example.appnews.R
import com.example.appnews.database.entity.Database
import com.example.appnews.database.entity.Topic
import com.example.appnews.databinding.FragmentTopicBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class TopicFragment : Fragment() {

    var _binding : FragmentTopicBinding? = null
    val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTopicBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val db = Room.databaseBuilder(requireContext(), Database::class.java, "newsapp").build()
        Log.d("Database","database: $db")


        val dbRetrieveTopic = GlobalScope.launch(Dispatchers.IO) {
               val result = db.topicDao().getAll()
               if(result.isNotEmpty()){
                   Log.d("Database", "Topic: $result")
                   withContext(Dispatchers.Main){
                        binding.topicdisplay.text = result.first().topicName.toString()
                   }
               }else{
                   withContext(Dispatchers.Main){
                       binding.topicdisplay.text = "Nessun topic"
                   }
               }
           }
        }



}