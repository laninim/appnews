package com.example.appnews.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Topic(
    @PrimaryKey(autoGenerate = true) val uid: Int=0,
    @ColumnInfo("topic_name") val topicName : String,
    @ColumnInfo("language_pref") val languagePref : String,
    @ColumnInfo("active_topic") val activeTopic : Boolean)
