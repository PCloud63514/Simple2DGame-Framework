package com.pcloud63514.simple2dgame_framework.core

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.pcloud63514.simple2dgame_framework.core.utils.AnimationController
import com.pcloud63514.simple2dgame_framework.core.utils.TPJsonParser
import com.pcloud63514.simple2dgame_framework.core.views.CreatureView

class GameObjectFactory(private val context: Context) {
    private val mTPJsonParser: TPJsonParser = TPJsonParser(context)

    fun createCreatureViewBuilder(domain: String): CreatureView.CreatureViewBuilder? {
        val spriteSheetConfig = mTPJsonParser.getSpriteSheetConfig(domain + "/data.json")

        spriteSheetConfig?.let {
            return CreatureView.CreatureViewBuilder(
                animationController = AnimationController(context, spriteSheetConfig.frames),
                context = context,
                bitmap = getGameSpriteSheetImage(domain),
                meta = spriteSheetConfig.meta
            )
        }
        return null
    }

    private fun getGameSpriteSheetImage(domain: String): Bitmap {
        //TODO Image File 경로 수정할 것.
        return context.resources.assets.open(domain + "/data.png").let {
            BitmapFactory.decodeStream(it)
        }
    }
}