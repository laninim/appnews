package com.example.appnews.database.entity

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TopicDAO {

    @Query("SELECT * FROM topic")
    suspend fun getAll() : List<Topic>

    @Insert
    suspend fun insertTopic(vararg topics : Topic)

    @Delete
    suspend fun deleteTopic(topic : Topic)
}