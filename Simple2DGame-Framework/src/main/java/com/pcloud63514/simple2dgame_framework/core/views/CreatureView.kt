package com.pcloud63514.simple2dgame_framework.core.views

import android.content.Context
import android.graphics.Bitmap
import com.pcloud63514.simple2dgame_framework.core.gl10.GL10View
import com.pcloud63514.simple2dgame_framework.data.*
import java.beans.PropertyChangeListener

class CreatureView : GL10View {
    private val spriteSheetConfig: SpriteSheetConfig
    private val stateCollection:MutableMap<Any, AnimationState> = mutableMapOf()
    private val creatureInfo: CreatureInfo

    private constructor(context:Context, bitmap: Bitmap, spriteSheetConfig: SpriteSheetConfig, startStateFlag:Any): super(context, bitmap, spriteSheetConfig.meta) {
        this.creatureInfo = CreatureInfo(context)
        this.spriteSheetConfig = spriteSheetConfig
        initialize()
        setCurrentState(startStateFlag)
    }
    private constructor(context:Context, bitmap:Bitmap, spriteSheetConfig: SpriteSheetConfig, startStateFlag:Any, creatureInfo: CreatureInfo): super(context, bitmap, spriteSheetConfig.meta) {
        this.creatureInfo = creatureInfo
        this.spriteSheetConfig = spriteSheetConfig
        initialize()
        setCurrentState(startStateFlag)
    }

    override fun initialize() {
        addPropertyChangeListener("currentState", gl10Renderer.stateChangeListener)
        spriteSheetConfig.frames.iterator().forEach {
            stateCollection[it.key] = createState(it.key, it.value)
        }

        super.initialize()
    }

    private fun setCurrentState(stateFlag:Any) {
        creatureInfo.currentState = getState(stateFlag)
    }

    private fun getState(stateFlag:Any): State {
        return stateCollection[stateFlag]!!
    }

    private fun createState(stateFlag:Any, frames:MutableList<Frame>): AnimationState {
        return AnimationState(stateFlag, frames)
    }

    fun addPropertyChangeListener(propName:String, listener: PropertyChangeListener) {
        this.creatureInfo.addPropertyChangeListener(propName, listener)
    }
    fun removePropertyChangeListener(propName:String, listener: PropertyChangeListener) {
        this.creatureInfo.removePropertyChangeListener(propName, listener)
    }

    class CreatureViewBuilder (
        private val context:Context,
        private val bitmap:Bitmap,
        private val spriteSheetConfig: SpriteSheetConfig) {
        private var creatureInfo: CreatureInfo? = null

        fun mountCreatureInfo(creatureInfo: CreatureInfo) {
            this.creatureInfo = creatureInfo
        }

        fun build(startFlag:Any) : CreatureView {
            return if(creatureInfo == null) CreatureView(context, bitmap, spriteSheetConfig, startFlag) else CreatureView(context, bitmap, spriteSheetConfig, startFlag, creatureInfo!!)
        }
    }
}