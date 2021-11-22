package ru.aasmc.jetnotes.viewmodel

import androidx.lifecycle.ViewModel
import ru.aasmc.jetnotes.data.repository.Repository

/**
 * ViewModel used for storing the global app state.
 * This ViewModel is used for all apps.
 */
class MainViewModel(
    private val repository: Repository
): ViewModel() {

}