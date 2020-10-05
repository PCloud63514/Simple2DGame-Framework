package com.pcloud63514.simple2dgame_framework.utils

class FPSManager(
    private val thetaFrame:Float = 0.13F
) {
    private var fpsStartTime:Long = 0
    //private var timeElapsed:Double = 0.0
    fun initialize() {
        fpsStartTime = System.currentTimeMillis()
    }

    fun isElapsed():Boolean {
        val fpsEndTime:Long = System.currentTimeMillis()
        val timeDelta:Float = (fpsEndTime - fpsStartTime) * 0.001f

        if(timeDelta >= thetaFrame) {
            fpsStartTime = System.currentTimeMillis()
            return true
        }
        return false
    }
}