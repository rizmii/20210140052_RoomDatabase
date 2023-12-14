package com.example.database_project.model

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.database_project.repository.RepositorySiswa
import com.example.database_project.ui.theme.halaman.DetailsDestination
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class DetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositorySiswa: RepositorySiswa
): ViewModel(){


    private val siswaId:Int = checkNotNull(savedStateHandle[DetailsDestination.siswaIdArg])
    val uiState: StateFlow<ItemDetailsUiState> =
            repositorySiswa.getSiswaStream(siswaId)
    .filterNotNull()
    .map{
        ItemDetailsUiState(detailSiswa = it.toDetailSiswa())
    }.stateIn(
        scope = viewModelScope,
        initialValue = ItemDetailsUiState(),
    started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
    )
    suspend fun deleteItem(){
        repositorySiswa.deleteSiswa(uiState.value.detailSiswa.toSiswa())
    }
    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

data class ItemDetailsUiState(
    val outOfStock:Boolean=true,
    val detailSiswa: DetailSiswa = DetailSiswa()
)


