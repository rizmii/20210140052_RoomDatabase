package com.example.database_project

import android.app.Application
import com.example.database_project.repository.ContainerApp
import com.example.database_project.repository.ContainerDataApp

class AplikasiSiswa : Application() {

    // AppContainer instance used by other classes to get dependencies
    lateinit var container: ContainerApp

    override fun onCreate() {
        super.onCreate()

        // Initialize the AppContainer
        container = ContainerDataApp(this)
    }
}