package app.unbound.android

import android.content.Context
import android.util.Log
import com.google.gson.JsonElement
import com.google.gson.annotations.SerializedName
import java.io.BufferedReader
import java.io.InputStreamReader

data class Addon(
    val bundle: Any,
    val manifest: Manifest
)

data class Theme(
    val bundle: ThemeJSON,
    val manifest: Manifest
)

data class ThemeJSON(
    @SerializedName("raw") val raw: JsonElement?,
    @SerializedName("semantic") val semantic: JsonElement?,
    @SerializedName("background") val background: JsonElement?
)

data class Manifest(
    @SerializedName("name") var name: String,
    @SerializedName("id") var id: String,
    @SerializedName("description") var description: String,
    @SerializedName("version") var version: String,
    @SerializedName("authors") var authors: ArrayList<Authors>,
    @SerializedName("bundle") var bundle: String
)

data class Authors(
    @SerializedName("name") var name: String,
    @SerializedName("id") var id: String
)


class Constants {
    companion object {
        const val DISCORD_SERVER = "https://discord.com/invite/rMdzhWUaGT"

        const val CLASS = "com.facebook.react.bridge.CatalystInstanceImpl"
        const val ACTIVITY_CLASS = "android.app.Instrumentation"
        const val LIGHT_THEME = "com.discord.theme.LightTheme"
        const val DARK_THEME = "com.discord.theme.DarkTheme"

        const val FILE_LOAD = "jniLoadScriptFromFile"
        const val ASSET_LOAD = "jniLoadScriptFromAssets"
        const val NEW_ACTIVITY = "newActivity"

        
         
        fun loadAsset(context: Context, fileName: String): String {
            return try {
                
                val assetManager = context.assets
                val inputStream = assetManager.open(fileName)
                val bufferedReader = BufferedReader(InputStreamReader(inputStream))
                val stringBuilder = StringBuilder()
                var line: String?

                
                while (bufferedReader.readLine().also { line = it } != null) {
                    stringBuilder.append(line).append("\n")
                }

                bufferedReader.close()
                stringBuilder.toString() 
            } catch (e: Exception) {
                
                Log.e("AssetLoader", "Error loading asset '$fileName': ${e.message}")
                "Error loading asset '$fileName': ${e.message}"
            }
        }
    }
}
