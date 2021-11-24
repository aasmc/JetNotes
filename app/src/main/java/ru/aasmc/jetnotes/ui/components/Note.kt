package ru.aasmc.jetnotes.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.aasmc.jetnotes.domain.model.NoteModel
import ru.aasmc.jetnotes.theme.rwGreen
import ru.aasmc.jetnotes.util.fromHex

@Composable
fun Note(
    note: NoteModel,
    onNoteClick: (NoteModel) -> Unit = {},
    onNoteCheckedChange: (NoteModel) -> Unit = {}
) {
    val backgroundShape: Shape = RoundedCornerShape(4.dp)
    Row(
        modifier = Modifier
            .padding(8.dp)
            .shadow(1.dp, backgroundShape)
            .fillMaxWidth()
            .heightIn(min = 64.dp)
            .background(Color.White, backgroundShape)
            .clickable(onClick = { onNoteClick(note) })
    ) {
        NoteColor(
            modifier = Modifier
                .align(alignment = Alignment.CenterVertically)
                .padding(start = 16.dp, end = 16.dp),
            color = Color.fromHex(note.color.hex),
            size = 40.dp,
            border = 1.dp
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .align(alignment = Alignment.CenterVertically)
        ) {
            Text(
                text = note.title,
                maxLines = 1,
                color = Color.Black,
                style = TextStyle(
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                    letterSpacing = 0.15.sp
                )
            )
            Text(
                text = note.content,
                maxLines = 1,
                color = Color.Black.copy(alpha = 0.75f),
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    letterSpacing = 0.25.sp
                )
            )
        }
        if (note.isCheckedOff != null) {
            Checkbox(
                checked = note.isCheckedOff,
                onCheckedChange = { isChecked ->
                    val newNote = note.copy(isCheckedOff = isChecked)
                    onNoteCheckedChange(newNote)
                },
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.CenterVertically)
            )
        }
    }
}

@Preview
@Composable
private fun NotePreviewChecked() {
    Note(
        note = NoteModel(1L, "Title", "Content", true)
    )
}

@Preview
@Composable
private fun NotePreviewNotCheckable() {
    Note(
        note = NoteModel(1L, "Title", "Content", null)
    )
}