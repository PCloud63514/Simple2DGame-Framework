package pcloud.game.starter.ui.texturepacker.model

import android.graphics.Bitmap
import org.json.JSONObject

data class Size(val w: Float, val h: Float) {
    companion object Factory {
        fun create(jsonObject: JSONObject): Size {
            return Size(
                w = jsonObject.getDouble("w").toFloat(),
                h = jsonObject.getDouble("h").toFloat()
            )
        }
    }
}

data class SpriteSize(val x: Float, val y: Float, val w: Float, val h: Float) {
    companion object Factory {
        fun create(jsonObject: JSONObject): SpriteSize {
            return SpriteSize(
                x = jsonObject.getDouble("x").toFloat(),
                y = jsonObject.getDouble("y").toFloat(),
                w = jsonObject.getDouble("w").toFloat(),
                h = jsonObject.getDouble("h").toFloat()
            )
        }
    }
}

data class Frame(
    val name: String,
    val frame: SpriteSize,
    val rotated: Boolean,
    val trimmed: Boolean,
    val spriteSourceSize: SpriteSize,
    val sourceSIze: Size
) {
    companion object Factory {
        fun create(jsonObject: JSONObject): Frame {
            return Frame(
                name = jsonObject.getString("filename"),
                frame = SpriteSize.create(jsonObject.getJSONObject("frame")),
                rotated = jsonObject.getBoolean("rotated"),
                trimmed = jsonObject.getBoolean("trimmed"),
                spriteSourceSize = SpriteSize.create(jsonObject.getJSONObject("spriteSourceSize")),
                sourceSIze = Size.create(jsonObject.getJSONObject("sourceSize"))
            )
        }
    }
}

data class Meta(
    val app: String,
    val version: String,
    val image: String,
    val format: String,
    val imageSize: Size,
    val scale: String,
    val smartUpdate: String
) {
    companion object Factory {
        fun create(jsonObject: JSONObject): Meta {
            return Meta(
                app = jsonObject.getString("app"),
                version = jsonObject.getString("version"),
                image = jsonObject.getString("image"),
                format = jsonObject.getString("format"),
                imageSize = Size.create(jsonObject.getJSONObject("size")),
                scale = jsonObject.getString("scale"),
                smartUpdate = jsonObject.getString("smartupdate")
            )
        }
    }
}

data class TextureAtlas(val bitmap: Bitmap, val meta: Meta, val frames: List<Frame>) {
    companion object Factory {
        fun create(bitmap: Bitmap, jsonObject: JSONObject): TextureAtlas {
            val meta = Meta.create(jsonObject.getJSONObject("meta"))
            val jsonArray = jsonObject.getJSONArray("frames")
            val frames = ArrayList<Frame>()
            for (i in 0 until jsonArray.length()) {
                frames.add(Frame.create(jsonArray.getJSONObject(i)))
            }

            return TextureAtlas(
                bitmap = bitmap,
                meta = meta,
                frames = frames
            )
        }
    }
}