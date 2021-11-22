package ru.aasmc.jetnotes.routing

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

/**
 * Defines all possible screens in the app.
 */
sealed class Screen {
    object Notes : Screen()
    object SaveNote : Screen()
    object Trash : Screen()
}

/**
 * Allows us to change the screen in the [MainActivity]
 * and keeps track of the current screen.
 */
object JetNotesRouter {
    var currentScreen: Screen by mutableStateOf(Screen.Notes)

    fun navigateNo(destination: Screen) {
        currentScreen = destination
    }
}








