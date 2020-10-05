package com.pcloud63514.simple2dgame_framework.core

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.pcloud63514.simple2dgame_framework.core.views.CreatureView
import com.pcloud63514.simple2dgame_framework.utils.TexturePackerJSONParser

class GameObjectFactory(private val context: Context) {
    private val texturePackerJSONParser: TexturePackerJSONParser = TexturePackerJSONParser(context)

    fun createCreatureViewBuilder(domain:String) : CreatureView.CreatureViewBuilder? {
        val spriteSheetConfig = texturePackerJSONParser.getSpriteSheetConfig(domain + "/data.json")
        spriteSheetConfig?.let {
            return CreatureView.CreatureViewBuilder(
                context = context,
                bitmap = getGameSpriteSheetImage(domain),
                spriteSheetConfig = it
            )
        }
        return null
    }

    private fun getGameSpriteSheetImage(domain:String) : Bitmap {
        return context.resources.assets.open(domain + "/data.png").let {
            BitmapFactory.decodeStream(it)
        }
    }
}