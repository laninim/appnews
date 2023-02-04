package com.example.appnews.database.entity

import androidx.room.RoomDatabase
import androidx.room.Database
import com.example.appnews.network.networkmodel.Article


@Database(entities = [Article::class], version = 1, exportSchema = false)
abstract class Database : RoomDatabase() {
    abstract fun topicDao() : TopicDAO
}