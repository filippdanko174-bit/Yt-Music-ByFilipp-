# Yt Music ByFilipp

Мод для YouTube Music в стиле ReVanced: патчи, которые применяются к официальному APK.

## Как это устроено

- Основа: готовые патчи [inotia00/revanced-patches](https://github.com/inotia00/revanced-patches) (GPL-3.0, проект заброшен, версия зафиксирована в `.github/workflows/build-patches.yml`).
- Свои изменения лежат в папке `overlay/`: при сборке они копируются поверх основы.
- GitHub Actions собирает файл патчей `ytmusic-patches.rvp` и публикует его в Releases (тег `latest`).

## Старое

Ранний эксперимент, отдельное приложение с нуля, сохранён в ветке `old-custom-app`.

## Лицензия

GPL-3.0, как и у патчей, на которых это основано.
