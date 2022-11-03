package pcloud.game.starter.ui.utils

import org.json.JSONObject
import java.io.IOException
import java.io.InputStream
import kotlin.jvm.Throws

class JsonObjectProvider {

    @Throws(IOException::class)
    fun create(inputStream: InputStream): JSONObject {
        val jsonString = inputStream.let {
            it.bufferedReader().use { buffReader ->
                buffReader.readText()
            }
        }
        return JSONObject(jsonString)
    }
}