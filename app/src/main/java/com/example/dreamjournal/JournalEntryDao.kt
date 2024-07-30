package com.example.dreamjournal

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface JournalEntryDao {
    @Query("SELECT * FROM journal_entries ORDER BY id DESC")
    suspend fun getAll(): List<JournalEntry>

    @Insert
    suspend fun insert(entry: JournalEntry)
}
