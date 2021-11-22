package ru.aasmc.jetnotes.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ru.aasmc.jetnotes.data.database.model.ColorDbModel

@Dao
interface ColorDao {

    @Query("SELECT * FROM ColorDbModel")
    fun getAll(): LiveData<List<ColorDbModel>>

    @Query("SELECT * FROM ColorDbModel")
    fun getAllSync(): List<ColorDbModel>

    @Query("SELECT * FROM ColorDbModel WHERE id LIKE :id")
    fun findById(id: Long): LiveData<ColorDbModel>

    @Query("SELECT * FROM ColorDbModel WHERE id LIKE :id")
    fun findByIdSync(id: Long): ColorDbModel

    @Insert
    fun insertAll(vararg colorDbModels: ColorDbModel)

}



























