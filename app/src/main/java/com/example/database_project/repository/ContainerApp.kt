package com.example.database_project.repository

import android.content.Context
import com.example.database_project.data.DatabaseSiswa

interface ContainerApp {
    val repositorySiswa : RepositorySiswa
}

class ContainerDataApp(private val context: Context):ContainerApp{
    override val repositorySiswa: RepositorySiswa by lazy {
        OflineRepositorySiswa(DatabaseSiswa.getDatabase(context).siswaDAO())
    }

}