package ru.aasmc.jetnotes.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.aasmc.jetnotes.R
import ru.aasmc.jetnotes.domain.model.ColorModel
import ru.aasmc.jetnotes.domain.model.NEW_NOTE_ID
import ru.aasmc.jetnotes.domain.model.NoteModel
import ru.aasmc.jetnotes.routing.JetNotesRouter
import ru.aasmc.jetnotes.routing.Screen
import ru.aasmc.jetnotes.ui.components.NoteColor
import ru.aasmc.jetnotes.util.fromHex
import ru.aasmc.jetnotes.viewmodel.MainViewModel

@Composable
fun SaveNoteScreen(
    viewModel: MainViewModel
) {
    val noteEntry: NoteModel by viewModel.noteEntry
        .observeAsState(NoteModel())

    Scaffold(
        topBar = {
            val isEditingMode: Boolean = noteEntry.id != NEW_NOTE_ID
            SaveNoteTopAppBar(
                isEditingMode = isEditingMode,
                onBackClick = {
                    JetNotesRouter.navigateNo(Screen.Notes)
                },
                onSaveNoteClick = {

                },
                onOpenColorPickerClick = {

                },
                onDeleteNoteClick = {

                }
            )
        },
        content = {

        }
    )
}

@Composable
private fun SaveNoteTopAppBar(
    isEditingMode: Boolean,
    onBackClick: () -> Unit,
    onSaveNoteClick: () -> Unit,
    onOpenColorPickerClick: () -> Unit,
    onDeleteNoteClick: () -> Unit
) {

    TopAppBar(
        title = { Text(text = "Save Note", color = MaterialTheme.colors.onPrimary) },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Save Note Back Button",
                    tint = MaterialTheme.colors.onPrimary
                )
            }
        },
        actions = {
            // save note icon
            IconButton(onClick = onSaveNoteClick) {
                Icon(
                    imageVector = Icons.Default.Check,
                    tint = MaterialTheme.colors.onPrimary,
                    contentDescription = "Save Note Button"
                )
            }
            // open color picker icon
            IconButton(onClick = onOpenColorPickerClick) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_color_lens_24),
                    tint = MaterialTheme.colors.onPrimary,
                    contentDescription = "Open Color Picker Button"
                )
            }
            // Delete action icon (shown only in editing mode)
            if (isEditingMode) {
                IconButton(onClick = onDeleteNoteClick) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        tint = MaterialTheme.colors.onPrimary,
                        contentDescription = "Delete Note Button"
                    )
                }
            }
        }
    )
}

@Preview
@Composable
fun SaveNoteTopAppBarPreview() {
    SaveNoteTopAppBar(
        isEditingMode = true,
        onBackClick = { },
        onSaveNoteClick = { },
        onOpenColorPickerClick = { }) {

    }
}

@Composable
fun ColorPicker(
    colors: List<ColorModel>,
    onColorSelect: (ColorModel) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Color Picker",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(8.dp)
        )
        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            items(colors.size) { itemIndex ->
                val color = colors[itemIndex]
                ColorItem(
                    color = color,
                    onColorSelect = onColorSelect
                )
            }
        }
    }
}

@Preview
@Composable
fun ColorPickerPreview() {
    ColorPicker(
        colors = listOf(
            ColorModel.DEFAULT,
            ColorModel.DEFAULT,
            ColorModel.DEFAULT
        ),
        onColorSelect = {}
    )
}

@Composable
fun ColorItem(
    color: ColorModel,
    onColorSelect: (ColorModel) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                onClick = { onColorSelect(color) }
            )
    ) {
        NoteColor(
            modifier = Modifier.padding(10.dp),
            color = Color.fromHex(color.hex),
            size = 80.dp,
            border = 2.dp
        )
        Text(
            text = color.name,
            fontSize = 22.sp,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .align(alignment = Alignment.CenterVertically)
        )
    }
}

@Preview
@Composable
fun ColorItemPreview() {
    ColorItem(color = ColorModel.DEFAULT, onColorSelect = {})
}

















