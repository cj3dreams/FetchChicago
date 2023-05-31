package com.cj3dreams.fetchchicago.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cj3dreams.fetchchicago.model.FetchListModel
import kotlinx.coroutines.flow.Flow

@Dao
interface FetchListDao {

    @Query("SELECT * FROM fetchList WHERE name <> '' AND name IS NOT NULL ORDER BY listId, cast(name as unsigned) ASC")
    fun getFetchList(): Flow<List<FetchListModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFetchList(list: List<FetchListModel>)
}