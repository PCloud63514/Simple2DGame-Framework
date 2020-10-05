package com.pcloud63514.simple2dgame_framework.core

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.pcloud63514.simple2dgame_framework.core.utils.AnimationController
import com.pcloud63514.simple2dgame_framework.core.utils.TPJsonParser
import com.pcloud63514.simple2dgame_framework.core.views.CreatureView
import com.pcloud63514.simple2dgame_framework.core.views.IGameObjectViewBuilder
import com.pcloud63514.simple2dgame_framework.core.views.TPView

class GameObjectFactory(private val context: Context) {
    private val mTPJsonParser: TPJsonParser = TPJsonParser(context)

    fun createGameObjectViewBuilder(domain: String): TPView.TPViewBuilder? {
        val spriteSheetConfig = mTPJsonParser.getSpriteSheetConfig(domain + "/data.json")
        spriteSheetConfig?.let {
            return TPView.TPViewBuilder (
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