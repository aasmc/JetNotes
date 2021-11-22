package ru.aasmc.jetnotes.domain.model

import ru.aasmc.jetnotes.data.database.model.ColorDbModel

data class ColorModel(
    val id: Long,
    val name: String,
    val hex: String
) {
    companion object {
       val DEFAULT: ColorModel = with(ColorDbModel.DEFAULT_COLOR) {
           ColorModel(id, name, hex)
       }
    }
}
