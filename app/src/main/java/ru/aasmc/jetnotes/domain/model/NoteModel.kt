package ru.aasmc.jetnotes.domain.model

import kotlinx.coroutines.CoroutineStart

const val NEW_NOTE_ID = -1L

/**
 * Model class that represents one Note.
 */
data class NoteModel(
    val id: Long = NEW_NOTE_ID, // this value is used for new notes
    val title: String = "",
    val content: String = "",
    val isCheckedOff: Boolean? = null,
    val color: ColorModel = ColorModel.DEFAULT
)
