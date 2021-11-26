package ru.aasmc.jetnotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.launch
import ru.aasmc.jetnotes.routing.JetNotesRouter
import ru.aasmc.jetnotes.routing.Screen
import ru.aasmc.jetnotes.theme.JetNotesTheme
import ru.aasmc.jetnotes.ui.components.AppDrawer
import ru.aasmc.jetnotes.ui.components.Note
import ru.aasmc.jetnotes.ui.screens.NotesScreen
import ru.aasmc.jetnotes.ui.screens.SaveNoteScreen
import ru.aasmc.jetnotes.ui.screens.TrashScreen
import ru.aasmc.jetnotes.viewmodel.MainViewModel
import ru.aasmc.jetnotes.viewmodel.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    @DelicateCoroutinesApi
    private val viewModel: MainViewModel by viewModels(
        factoryProducer = {
            MainViewModelFactory(
                this,
                (application as JetNotesApplication).dependencyInjector.repository
            )
        }
    )

    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            JetNotesTheme {
                MainActivityScreen(viewModel = viewModel)
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
private fun MainActivityScreen(
    viewModel: MainViewModel
) {
    Surface {
        when (JetNotesRouter.currentScreen) {
            is Screen.Notes -> {
                NotesScreen(viewModel = viewModel)
            }
            is Screen.Trash -> {
                TrashScreen(viewModel = viewModel)
            }
            is Screen.SaveNote -> {
                SaveNoteScreen(viewModel = viewModel)
            }
        }
    }
}

@Composable
private fun ShowScaffold() {
    val coroutineScope = rememberCoroutineScope()
    val scaffoldState: ScaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        drawerContent = {
            AppDrawer(
                currentScreen = Screen.Notes,
                closeDrawerAction = {
                    coroutineScope.launch {
                        scaffoldState.drawerState.close()
                    }
                }
            )
        },
        content = {

        }
    )
}
