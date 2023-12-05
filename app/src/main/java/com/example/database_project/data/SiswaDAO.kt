package com.example.database_project.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
//import java.util.concurrent.Flow
import kotlinx.coroutines.flow.Flow

@Dao
interface SiswaDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert (siswa: Siswa)

    @Delete
    suspend fun delete(siswa: Siswa)

    @Update
    suspend fun update(siswa: Siswa)

    @Query("SELECT * from tblSiswa WHERE id = :id")
    fun getSiswa(id: Int): Flow<Siswa>

    @Query("SELECT * from tblSiswa ORDER BY nama ASC")
    fun getAllSiswa(): Flow<List<Siswa>>
}