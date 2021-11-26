package ru.aasmc.jetnotes.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.aasmc.jetnotes.data.repository.Repository
import ru.aasmc.jetnotes.domain.model.NoteModel
import ru.aasmc.jetnotes.routing.JetNotesRouter
import ru.aasmc.jetnotes.routing.Screen

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

    val notesInTrash by lazy {
        repository.getAllNotesInTrash()
    }

    private var _selectedNotes = MutableLiveData<List<NoteModel>>(listOf())
    val selectedNotes: LiveData<List<NoteModel>>
        get() = _selectedNotes

    //<editor-fold desc="Events that the view can pass">
    fun onCreateNewNoteClick() {
        JetNotesRouter.navigateNo(Screen.SaveNote)
    }

    fun onNoteClick(note: NoteModel) {

    }

    fun onNoteCheckedChange(note: NoteModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertNote(note)
        }
    }

    fun onNoteSelected(note: NoteModel) {
        _selectedNotes.value = _selectedNotes.value!!.toMutableList().apply {
            if (contains(note)) {
                remove(note)
            } else {
                add(note)
            }
        }
    }

    fun restoreNotes(notes: List<NoteModel>) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.restoreNotesFromTrash(notes.map { it.id })
            withContext(Dispatchers.Main) {
                _selectedNotes.value = listOf()
            }
        }
    }

    fun permanentlyDeleteNotes(notes: List<NoteModel>) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteNotes(notes.map { it.id })
            withContext(Dispatchers.Main) {
                _selectedNotes.value = listOf()
            }
        }
    }
    //</editor-fold>


}



























