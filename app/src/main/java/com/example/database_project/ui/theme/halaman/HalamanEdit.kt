package com.example.database_project.ui.theme.halaman

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.database_project.R
import com.example.database_project.model.EditViewModel
import com.example.database_project.model.PenyediaViewModel
import com.example.database_project.navigasi.DestinasiNavigasi
import com.example.database_project.navigasi.SiswaTopAppBar
import kotlinx.coroutines.launch

object ItemEditDestination : DestinasiNavigasi {
    override val route = "item_edit"

    override val titleRes = R.string.edit_siswa


        const val itemIdArg = "itemId"
        val routeWithArgs: String = "Sroute/{$itemIdArg}"

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemEditScreen(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: EditViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            SiswaTopAppBar(
                title = stringResource(id = ItemEditDestination.titleRes),
                canNavigateBack = true,
                navigateUp = onNavigateUp,

            )
        },
        modifier = modifier
    ) { innerPadding ->
        EntrySiswaBody(
            uiStateSiswa = viewModel.siswaUIState,
            onSiswaValueChange = viewModel::updateUIState,
            onSaveClick = {
                // Note: If the user rotates the screen very fast, the operation may get cancelled
                // and the item may not be updated in the database. This is because when config
                // change occurs, the activity be recreated and the rememberCoroutineScope will
                // be cancelled since the scope is bound to composition.
                coroutineScope.launch { viewModel.updateSiswa()
                navigateBack}
            },
            modifier = Modifier.padding(innerPadding)
        )
    }
}
