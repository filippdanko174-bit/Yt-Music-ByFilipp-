# Yt Music ByFilipp: патчи для YouTube Music

Личные патчи для YouTube Music в формате ReVanced (Kotlin, revanced-patcher).
Ветка `main` содержит старый эксперимент с отдельным приложением, патчи живут в ветке `patches`.

## Статус

- [x] Конвейер сборки: GitHub Actions собирает файл патчей `.rvp`
- [x] Патч-заглушка `Custom app name` (проверка конвейера)
- [ ] Анализ APK конкретной версии YouTube Music (нужен оригинальный APK)
- [ ] Патчи интерфейса, скрытие лишних кнопок
- [ ] Патчи рекламы и воспроизведения в фоне

## Как получить файл патчей

После каждого пуша в `patches` файл появляется в Releases под тегом `patches-latest`:
`ytmusic-byfilipp-patches.rvp`.

Применять: ReVanced Manager (Настройки, свой источник патчей) или ReVanced CLI на компьютере.
Оригинальный APK YouTube Music нужно взять самому.

Основа проекта: [revanced-patches-template](https://github.com/ReVanced/revanced-patches-template), лицензия GPLv3.
