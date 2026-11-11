package ru.yourname.custommusic

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

// Пока это заглушка: звука нет, прогресс идёт по таймеру.
// На следующем этапе внутрь подключим Media3 (ExoPlayer).
class PlayerState {
    var queue by mutableStateOf(SampleData.tracks)
    var currentIndex by mutableIntStateOf(-1)
    var isPlaying by mutableStateOf(false)
    var positionSec by mutableFloatStateOf(0f)

    val current: Track? get() = queue.getOrNull(currentIndex)

    fun play(index: Int) {
        currentIndex = index
        positionSec = 0f
        isPlaying = true
    }

    fun playTrack(track: Track) {
        val index = queue.indexOfFirst { it.id == track.id }
        if (index >= 0) play(index)
    }

    fun toggle() {
        if (current != null) isPlaying = !isPlaying
    }

    fun next() {
        if (queue.isNotEmpty()) play((currentIndex + 1) % queue.size)
    }

    fun previous() {
        if (queue.isEmpty()) return
        // Если трек играет больше 3 секунд, возвращаемся в его начало
        if (positionSec > 3f) {
            positionSec = 0f
        } else {
            play((currentIndex - 1 + queue.size) % queue.size)
        }
    }

    fun seek(sec: Float) {
        positionSec = sec
    }

    fun tick(deltaSec: Float) {
        val track = current ?: return
        if (!isPlaying) return
        positionSec += deltaSec
        if (positionSec >= track.durationSec) next()
    }
}
