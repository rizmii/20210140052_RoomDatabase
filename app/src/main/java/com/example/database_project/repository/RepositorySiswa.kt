package com.example.database_project.repository

import com.example.database_project.data.Siswa
import kotlinx.coroutines.flow.Flow
//import java.util.concurrent.Flow

interface RepositorySiswa {
    fun getAllSiswaStream(): Flow<List<Siswa>>
    fun getSiswaStream(id : Int ): Flow<Siswa?>
    suspend fun insertSiswa(siswa: Siswa)
    suspend fun deleteSiswa(siswa: Siswa)
    suspend fun updateSiswa(siswa: Siswa)
}