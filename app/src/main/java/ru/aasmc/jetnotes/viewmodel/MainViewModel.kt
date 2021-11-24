package ru.aasmc.jetnotes.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.aasmc.jetnotes.data.repository.Repository
import ru.aasmc.jetnotes.domain.model.NoteModel

/**
 * ViewModel used for storing the global app state.
 * This ViewModel is used for all apps.
 */
class MainViewModel(
    private val repository: Repository
) : ViewModel() {

    val notesNotInTrash: LiveData<List<NoteModel>> by lazy {
        repository.getAllNotesNotInTrash()
    }

    //<editor-fold desc="Events that the view can pass">
    fun onCreateNewNoteClick() {
        // TODO
    }

    fun onNoteClick(note: NoteModel) {
        // TODO
    }

    fun onNoteCheckedChange(note: NoteModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertNote(note)
        }
    }
    //</editor-fold>


}



























