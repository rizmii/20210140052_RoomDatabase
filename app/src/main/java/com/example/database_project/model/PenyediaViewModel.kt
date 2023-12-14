package com.example.database_project.model

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.database_project.AplikasiSiswa
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory

object PenyediaViewModel {
    val Factory = viewModelFactory{
        initializer{
            HomeViewModel(AplikasiSiswa().container.repositorySiswa)
                }
        initializer{
            EntryViewModel(AplikasiSiswa().container.repositorySiswa)
        }
        initializer {
            DetailsViewModel(
                createSavedStateHandle(),
                aplikasiSiswa().container.repositorySiswa,
            )
        }
        initializer {
            EditViewModel(
                createSavedStateHandle(),
                aplikasiSiswa().container.repositorySiswa
            )
        }
            }
        }
fun CreationExtras.aplikasiSiswa(): AplikasiSiswa =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AplikasiSiswa)