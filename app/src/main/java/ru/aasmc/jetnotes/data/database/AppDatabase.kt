package ru.aasmc.jetnotes.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.aasmc.jetnotes.data.database.dao.ColorDao
import ru.aasmc.jetnotes.data.database.dao.NoteDao
import ru.aasmc.jetnotes.data.database.model.ColorDbModel
import ru.aasmc.jetnotes.data.database.model.NoteDbModel

@Database(entities = [NoteDbModel::class, ColorDbModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        const val DATABASE_NAME = "note_maker_database.db"
    }

    abstract fun noteDao(): NoteDao

    abstract fun colorDao(): ColorDao
}