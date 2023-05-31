package com.cj3dreams.fetchchicago.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cj3dreams.fetchchicago.model.FetchListModel

@Database(entities = [FetchListModel::class], version = 1, exportSchema = false)
abstract class AppDb: RoomDatabase() {

    abstract fun fetchListDao(): FetchListDao?
}