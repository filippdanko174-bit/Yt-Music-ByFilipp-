package ru.yourname.custommusic

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
private fun ScreenTitle(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.headlineMedium,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
    )
}

@Composable
private fun SectionTitle(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
    )
}

@Composable
fun HomeScreen(player: PlayerState) {
    val moods = listOf("Энергия", "Расслабиться", "Фокус", "Тренировка", "Дорога")
    var selectedMood by remember { mutableStateOf<String?>(null) }

    LazyColumn(Modifier.fillMaxSize().statusBarsPadding()) {
        item { ScreenTitle("Главная") }

        item {
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                items(moods) { mood ->
                    FilterChip(
                        selected = selectedMood == mood,
                        onClick = { selectedMood = if (selectedMood == mood) null else mood },
                        label = { Text(mood) },
                    )
                }
            }
        }

        item { Spacer(Modifier.height(16.dp)) }
        item { SectionTitle("Быстрый выбор") }
        item {
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                items(player.queue) { track ->
                    Column(
                        Modifier
                            .width(150.dp)
                            .clickable { player.playTrack(track) },
                    ) {
                        Cover(track, Modifier.size(150.dp), corner = 12.dp)
                        Spacer(Modifier.height(8.dp))
                        Text(
                            track.title,
                            style = MaterialTheme.typography.bodyLarge,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                        Text(
                            track.artist,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                    }
                }
            }
        }

        item { Spacer(Modifier.height(16.dp)) }
        item { SectionTitle("Недавнее") }
        items(player.queue) { track ->
            TrackRow(
                track = track,
                isCurrent = player.current?.id == track.id,
                onClick = { player.playTrack(track) },
            )
        }
    }
}

@Composable
fun SearchScreen(player: PlayerState) {
    var query by remember { mutableStateOf("") }
    val results = if (query.isBlank()) emptyList() else player.queue.filter {
        it.title.contains(query, ignoreCase = true) || it.artist.contains(query, ignoreCase = true)
    }

    Column(Modifier.fillMaxSize().statusBarsPadding()) {
        ScreenTitle("Поиск")
        OutlinedTextField(
            value = query,
            onValueChange = { query = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            placeholder = { Text("Треки и исполнители") },
            leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null) },
            singleLine = true,
            shape = RoundedCornerShape(28.dp),
        )
        Spacer(Modifier.height(8.dp))
        LazyColumn {
            items(results) { track ->
                TrackRow(
                    track = track,
                    isCurrent = player.current?.id == track.id,
                    onClick = { player.playTrack(track) },
                )
            }
        }
    }
}

@Composable
fun LibraryScreen(player: PlayerState) {
    LazyColumn(Modifier.fillMaxSize().statusBarsPadding()) {
        item { ScreenTitle("Библиотека") }
        item {
            Text(
                text = "Сейчас здесь тестовые треки. Дальше подключим сканирование музыки на телефоне.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
            )
            Spacer(Modifier.height(8.dp))
        }
        items(player.queue) { track ->
            TrackRow(
                track = track,
                isCurrent = player.current?.id == track.id,
                onClick = { player.playTrack(track) },
            )
        }
    }
}
