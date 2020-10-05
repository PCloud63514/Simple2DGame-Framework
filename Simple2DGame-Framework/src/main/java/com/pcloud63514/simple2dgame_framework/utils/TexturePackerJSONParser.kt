package com.pcloud63514.simple2dgame_framework.utils

import android.content.Context
import com.pcloud63514.simple2dgame_framework.data.Frame
import com.pcloud63514.simple2dgame_framework.data.Location
import com.pcloud63514.simple2dgame_framework.data.Meta
import com.pcloud63514.simple2dgame_framework.data.SpriteSheetConfig
import org.json.JSONArray
import org.json.JSONObject

class TexturePackerJSONParser(private val context: Context) {

    fun getSpriteSheetConfig(jsonPath:String):SpriteSheetConfig? {
        if(isJsonExtension(jsonPath)) {
            val tpString = getTPJsonString(jsonPath)
            tpString?.let {
                val jsonObject = JSONObject(it)
                val meta = getMetaInstance(jsonObject.getJSONObject("meta"))
                val frames = getFrames(jsonObject.getJSONArray("frames"))
                return SpriteSheetConfig(meta=meta, frames=frames)
            }
        }
        return null
    }

    private fun getFrames(framesJSONArray: JSONArray):MutableMap<String, MutableList<Frame>> {
        var frameMap = mutableMapOf<String, MutableList<Frame>>()
        for(i in 0 until framesJSONArray.length()) {
            val frameJSONObject = framesJSONArray.getJSONObject(i)
            val frame = getFrame(frameJSONObject)
            val key = getKeyName(frame)

            if(frameMap[key].isNullOrEmpty()) {
                frameMap[key] = mutableListOf<Frame>()
            }
            frameMap[key]!!.add(frame)
        }
        return frameMap
    }

    private fun getKeyName(frame:Frame):String {
        return frame.fileName.split("/")[0]
    }

    private fun getFrame(frameJSONObject: JSONObject): Frame {
        return Frame(
            fileName = frameJSONObject.getString("filename"),
            frame = getLocation(frameJSONObject.getJSONObject("frame")),
            rotated = frameJSONObject.getBoolean("rotated"),
            trimmed = frameJSONObject.getBoolean("trimmed"),
            spriteSourceSize = getLocation(frameJSONObject.getJSONObject("spriteSourceSize")),
            sourceSize = getLocation(frameJSONObject.getJSONObject("sourceSize")))
    }

    private fun getMetaInstance(metaJSONObject: JSONObject): Meta {
        val sourceSize:Location = getLocation(metaJSONObject.getJSONObject("sourceSize"))
        val size:Location = getLocation(metaJSONObject.getJSONObject("size"))
        return Meta(
            app=metaJSONObject.getString("app"),
            version=metaJSONObject.getString("version"),
            image=metaJSONObject.getString("image"),
            format=metaJSONObject.getString("format"),
            sourceSize= sourceSize,
            size=size,
            scale=metaJSONObject.getInt("scale"),
            smartUpdate = metaJSONObject.getString("smartupdate"))
    }

    private fun getLocation(jsonObject: JSONObject):Location {

        return Location(
            x= if(jsonObject.isNull("x")) 0.0f else jsonObject.getDouble("x").toFloat(),
            y= if(jsonObject.isNull("y")) 0.0f else jsonObject.getDouble("y").toFloat(),
            w=jsonObject.getDouble("w").toFloat(),
            h=jsonObject.getDouble("h").toFloat())
    }

    private fun getTPJsonString(jsonPath:String):String? {
        return context.resources.assets.open(jsonPath).let { it ->
            it.bufferedReader().use { it.readText() }
        }
    }

    private fun isJsonExtension(jsonPath:String):Boolean {
        var extensionName = jsonPath.substringBeforeLast(".", "None")
        return "None" != extensionName
    }
}