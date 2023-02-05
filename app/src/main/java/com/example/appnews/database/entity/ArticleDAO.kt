package com.example.appnews.database.entity

import androidx.room.*
import com.example.appnews.network.networkmodel.Article

@Dao
interface ArticleDAO {

    @Query("SELECT * FROM article")
    suspend fun getAll() : List<Article>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTopic(vararg topics : Article)

    @Delete
    suspend fun deleteTopic(topic : Article)
}