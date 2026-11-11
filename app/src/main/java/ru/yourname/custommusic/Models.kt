package ru.yourname.custommusic

import androidx.compose.ui.graphics.Color

data class Track(
    val id: Int,
    val title: String,
    val artist: String,
    val durationSec: Int,
    val colorA: Color,
    val colorB: Color,
)

// Тестовые данные, пока нет настоящей библиотеки
object SampleData {
    val tracks = listOf(
        Track(1, "Ночной город", "Артист Один", 214, Color(0xFF3A1C71), Color(0xFFD76D77)),
        Track(2, "Тихий берег", "Артист Два", 187, Color(0xFF0F2027), Color(0xFF2C7A7B)),
        Track(3, "Горизонт", "Артист Три", 243, Color(0xFF42275A), Color(0xFF734B6D)),
        Track(4, "Летний дождь", "Артист Один", 201, Color(0xFF134E5E), Color(0xFF71B280)),
        Track(5, "Огни", "Артист Четыре", 176, Color(0xFF8E2DE2), Color(0xFF4A00E0)),
        Track(6, "Рассвет", "Артист Два", 259, Color(0xFFF2994A), Color(0xFFEB5757)),
        Track(7, "Эхо", "Артист Пять", 192, Color(0xFF1D2B64), Color(0xFFF8CDDA)),
        Track(8, "Дальний путь", "Артист Три", 228, Color(0xFF232526), Color(0xFF616161)),
    )
}
