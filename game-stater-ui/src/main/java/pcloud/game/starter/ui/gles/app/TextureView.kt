package pcloud.game.starter.ui.gles.app

import android.content.Context
import android.opengl.GLSurfaceView
import pcloud.game.starter.ui.gles.model.TextureResource

class TextureView(context: Context, textureResource: TextureResource):GLSurfaceView(context) {
    private val textureRenderer = TextureRenderer(textureResource)

    fun start() {
        debugFlags = DEBUG_CHECK_GL_ERROR
        setRenderer(textureRenderer)
        renderMode = RENDERMODE_CONTINUOUSLY
        isFocusable = true
    }

    fun setMethod(methodName:String) {
        textureRenderer.setMethod(methodName)
    }
}