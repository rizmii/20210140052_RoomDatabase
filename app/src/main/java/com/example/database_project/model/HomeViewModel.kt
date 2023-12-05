package com.example.database_project.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.database_project.data.Siswa
import com.example.database_project.repository.RepositorySiswa
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class HomeViewModel(private val repositorySiswa: RepositorySiswa) : ViewModel() {

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
        val homeUiState: StateFlow<HomeUiState> = repositorySiswa.getAllSiswaStream()
            .filterNotNull()
            .map { HomeUiState(listSiswa = it.toList()) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = HomeUiState()
            )
    data class HomeUiState(
        val listSiswa: List<Siswa> = listOf()
    )
}