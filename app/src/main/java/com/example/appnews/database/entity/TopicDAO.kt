package com.example.appnews.database.entity

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.appnews.network.networkmodel.Article

@Dao
interface TopicDAO {

    @Query("SELECT * FROM article")
    suspend fun getAll() : List<Article>

    @Insert
    suspend fun insertTopic(vararg topics : Article)

    @Delete
    suspend fun deleteTopic(topic : Article)
}