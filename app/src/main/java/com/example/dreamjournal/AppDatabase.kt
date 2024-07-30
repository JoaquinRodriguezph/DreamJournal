package com.example.dreamjournal

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [JournalEntry::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun journalEntryDao(): JournalEntryDao
}
