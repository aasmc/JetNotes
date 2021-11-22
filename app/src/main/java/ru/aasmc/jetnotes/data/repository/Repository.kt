package ru.aasmc.jetnotes.data.repository

import androidx.lifecycle.LiveData
import ru.aasmc.jetnotes.domain.model.ColorModel
import ru.aasmc.jetnotes.domain.model.NoteModel

interface Repository {

    fun getAllNotesNotInTrash(): LiveData<List<NoteModel>>

    fun getAllNotesInTrash(): LiveData<List<NoteModel>>

    fun getNote(id: Long): LiveData<NoteModel>

    fun insertNote(note: NoteModel)

    fun deleteNote(id: Long)

    fun deleteNotes(noteIds: List<Long>)

    fun moveNoteToTrash(noteId: Long)

    fun restoreNotesFromTrash(noteIds: List<Long>)

    fun getAllColors(): LiveData<List<ColorModel>>

    fun getAllColorsSync(): List<ColorModel>

    fun getColor(id: Long): LiveData<ColorModel>

    fun getColorSync(id: Long): ColorModel
}