package com.pcloud63514.simple2dgame_framework.core.views

import android.content.Context
import android.graphics.Bitmap
import com.pcloud63514.simple2dgame_framework.CreatureInfo
import com.pcloud63514.simple2dgame_framework.core.gl10.GL10View
import com.pcloud63514.simple2dgame_framework.core.utils.AnimationController
import com.pcloud63514.simple2dgame_framework.data.Meta

open class TPView protected constructor (
    protected val animationController: AnimationController,
    context: Context,
    startStateFlag:Any,
    bitmap: Bitmap,
    meta: Meta
): GL10View(context, bitmap, meta) {
    init {
        initialize()
        animationController.setCurrentState(startStateFlag)
    }

    override fun initialize() {
        animationController.addPropertyChangeListener("currentState",
        gl10Renderer.stateChangeListener)
        super.initialize()
    }

    class TPViewBuilder (
        private val animationController:AnimationController,
        private val context:Context,
        private val bitmap:Bitmap,
        private val meta:Meta):IGameObjectViewBuilder {

        // TODO 만약 info 추가 메서드를 호출하면 반환타입은 다른 빌더.
        fun mountCreatureInfo(creatureInfo: CreatureInfo) : CreatureView.CreatureViewBuilder {
            return CreatureView.CreatureViewBuilder(creatureInfo, animationController, context, bitmap, meta)
        }

        override fun build(startStateFlag:Any) : GL10View {
            return TPView(animationController, context, startStateFlag, bitmap, meta)
        }
    }
}