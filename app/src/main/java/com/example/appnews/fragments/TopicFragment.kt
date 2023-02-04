package com.example.appnews.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.room.Room
import com.example.appnews.R
import com.example.appnews.database.entity.Database
import com.example.appnews.database.entity.Topic
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class TopicFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_topic, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val db = Room.databaseBuilder(requireContext(), Database::class.java, "newsapp").build()
        Log.d("Database","database: $db")

        //insert topic
        val databaseInsertJob = GlobalScope.launch(Dispatchers.IO) {
            db.topicDao().insertTopic(Topic(topicName = "calcio", languagePref = "it"))
            Log.d("Database","Insert query spero")
        }

        databaseInsertJob.invokeOnCompletion {
           val dbRetrieveTopic = GlobalScope.launch(Dispatchers.IO) {
               val result = db.topicDao().getAll()
               if(result.isNotEmpty()){
                   Log.d("Database", "Topic: $result")
               }
           }
        }
    }


}