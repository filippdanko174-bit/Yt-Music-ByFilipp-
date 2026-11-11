package app.filipp.patches.music.branding

import app.revanced.patcher.patch.resourcePatch
import org.w3c.dom.Element

private const val YT_MUSIC_PACKAGE = "com.google.android.apps.youtube.music"

// Первый патч-заглушка для проверки конвейера сборки: меняет название приложения.
// Настоящие патчи (интерфейс, реклама, фон) добавим после анализа APK конкретной версии.
@Suppress("unused")
val customAppNamePatch = resourcePatch(
    name = "Custom app name",
    description = "Changes the app name shown under the launcher icon.",
) {
    compatibleWith(YT_MUSIC_PACKAGE)

    execute {
        document("AndroidManifest.xml").use { document ->
            val application = document.getElementsByTagName("application").item(0) as Element
            application.setAttribute("android:label", "YT Music ByFilipp")
        }
    }
}
