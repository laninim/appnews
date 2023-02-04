package com.example.appnews.database.entity

import androidx.room.RoomDatabase
import androidx.room.Database


@Database(entities = [Topic::class], version = 1, exportSchema = false)
abstract class Database : RoomDatabase() {
    abstract fun topicDao() : TopicDAO
}