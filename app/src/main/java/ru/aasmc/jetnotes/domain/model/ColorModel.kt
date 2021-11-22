package ru.aasmc.jetnotes.domain.model

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
