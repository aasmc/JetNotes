package ru.aasmc.jetnotes.di

import android.content.Context
import androidx.room.Room
import kotlinx.coroutines.DelicateCoroutinesApi
import ru.aasmc.jetnotes.data.database.AppDatabase
import ru.aasmc.jetnotes.data.database.dbmapper.DbMapper
import ru.aasmc.jetnotes.data.database.dbmapper.DbMapperImpl
import ru.aasmc.jetnotes.data.repository.Repository
import ru.aasmc.jetnotes.data.repository.RepositoryImpl

class DependencyInjector(applicationContext: Context) {

    @DelicateCoroutinesApi
    val repository: Repository by lazy {
        provideRepository(database)
    }
    private val database: AppDatabase by lazy {
        provideDatabase(applicationContext)
    }

    private val dbMapper: DbMapper = DbMapperImpl()
    private fun provideDatabase(applicationContext: Context): AppDatabase =
        Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        ).build()

    @DelicateCoroutinesApi
    private fun provideRepository(database: AppDatabase): Repository {
        val noteDao = database.noteDao()
        val colorDao = database.colorDao()
        return RepositoryImpl(noteDao, colorDao, dbMapper)
    }
}









