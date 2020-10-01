package com.pcloud63514.simple2dgame_framework.core.views

import android.content.Context
import android.graphics.Bitmap
import com.pcloud63514.simple2dgame_framework.data.Meta
import com.pcloud63514.simple2dgame_framework.core.gl10.GL10View
import com.pcloud63514.simple2dgame_framework.core.utils.AnimationController

class CreatureView private constructor(
    val animationController:AnimationController,
    context: Context,
    bitmap: Bitmap,
    meta:Meta
): GL10View(context, bitmap, meta), ICreatureView {
    init {
        initialize()
    }
    final override fun initialize() {
        animationController.addPropertyChangeListener("currentState",
            gl10Renderer.stateChangeListener)
        super.initialize()
    }

    class CreatureViewBuilder(
        private val animationController: AnimationController,
        private val context: Context,
        private val bitmap: Bitmap,
        private val meta: Meta
    ) {

        fun build() : CreatureView{
            return CreatureView(animationController, context, bitmap, meta)
        }
    }
}