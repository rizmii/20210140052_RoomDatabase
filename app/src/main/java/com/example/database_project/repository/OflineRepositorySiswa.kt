package com.example.database_project.repository

import com.example.database_project.data.Siswa
import com.example.database_project.data.SiswaDAO
//import java.util.concurrent.Flow
import kotlinx.coroutines.flow.Flow

class OflineRepositorySiswa (private val siswaDAO: SiswaDAO): RepositorySiswa{
    override fun getAllSiswaStream(): Flow<List<Siswa>> = siswaDAO.getAllSiswa()

    override fun getAllSiswaStream(id : Int): Flow<Siswa?> {
        return siswaDAO.getSiswa(id)
    }

    override suspend fun deleteSiswa(siswa: Siswa) = siswaDAO.delete(siswa)

    override suspend fun insertSiswa(siswa: Siswa) = siswaDAO.insert(siswa)

    override suspend fun updateSiswa(siswa: Siswa) =siswaDAO.update(siswa)
}