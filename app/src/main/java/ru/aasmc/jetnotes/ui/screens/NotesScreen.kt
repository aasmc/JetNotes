package ru.aasmc.jetnotes.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import ru.aasmc.jetnotes.domain.model.NoteModel
import ru.aasmc.jetnotes.ui.components.Note
import ru.aasmc.jetnotes.ui.components.TopAppBar
import ru.aasmc.jetnotes.viewmodel.MainViewModel

@Composable
fun NotesScreen(
    viewModel: MainViewModel
) {
    val notes: List<NoteModel> by viewModel
        .notesNotInTrash
        .observeAsState(listOf())

    Column {
        TopAppBar(
            title = "Jet Notes",
            icon = Icons.Filled.List,
            onIconClick = {}
        )
        NoteList(
            notes = notes,
            onNoteCheckedChange = { viewModel.onNoteCheckedChange(it) },
            onNoteClick = { viewModel.onNoteClick(it) }
        )
    }

}

@Composable
private fun NoteList(
    notes: List<NoteModel>,
    onNoteCheckedChange: (NoteModel) -> Unit,
    onNoteClick: (NoteModel) -> Unit
) {
    LazyColumn {
        items(count = notes.size) { noteIndex ->
            val note = notes[noteIndex]
            Note(
                note = note,
                onNoteClick = onNoteClick,
                onNoteCheckedChange = onNoteCheckedChange
            )
        }
    }
}

@Preview
@Composable
private fun NoteListPreview() {
    NoteList(
        notes = listOf(
            NoteModel(1, "Note 1", "Content 1", null),
            NoteModel(2, "Note 2", "Content 2", false),
            NoteModel(3, "Note 3", "Content 3", true),
        ),
        onNoteCheckedChange = {},
        onNoteClick = {}
    )
}























