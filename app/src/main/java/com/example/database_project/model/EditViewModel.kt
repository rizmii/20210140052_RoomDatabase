package com.example.database_project.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.database_project.repository.RepositorySiswa
import com.example.database_project.ui.theme.halaman.ItemEditDestination
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class EditViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositorySiswa: RepositorySiswa
): ViewModel(){

    var siswaUIState by mutableStateOf(UIStateSiswa())
        private set

    private val itemId:Int = checkNotNull(savedStateHandle[ItemEditDestination.itemIdArg])

    init {
        viewModelScope.launch {
            siswaUIState = repositorySiswa.getSiswaStream(itemId)
                .filterNotNull()
                .first()
                .toUiStateSiswa(true)
        }
    }
    suspend fun updateSiswa(){
        if (validasiInput(siswaUIState.detailSiswa)){
            repositorySiswa.updateSiswa(siswaUIState.detailSiswa.toSiswa())
        }
        else{
            println("Data Tidak Valid")
        }
    }

    fun updateUIState(detailSiswa: DetailSiswa){
        siswaUIState =
            UIStateSiswa(detailSiswa = detailSiswa, isEntryValid = validasiInput(detailSiswa))
    }

    private fun validasiInput(uiState: DetailSiswa = siswaUIState.detailSiswa): Boolean{
        return with(uiState){
            nama.isNotBlank() && alamat.isNotBlank()&& telpon.isNotBlank()
        }
    }
}