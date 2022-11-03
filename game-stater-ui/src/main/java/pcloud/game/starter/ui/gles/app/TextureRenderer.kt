package pcloud.game.starter.ui.gles.app

import android.opengl.GLSurfaceView
import pcloud.game.starter.ui.gles.model.TextureResource
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class TextureRenderer(private val textureResource: TextureResource):GLSurfaceView.Renderer {
    private val textureShape = TextureShape()
    private var currentMethodName = ""
    private var currentMethodId = 0

    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
        gl!!.let {
            textureShape.destroy(it)
            textureShape.surfaceCreated(it, textureResource)
        }
    }

    override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
        gl!!.glViewport(0, 0, width, height)
    }

    var preT = System.currentTimeMillis()


    override fun onDrawFrame(gl: GL10?) {
        val after = System.currentTimeMillis()

        textureResource.methods[currentMethodName]?.let {
            textureShape.draw(gl!!, it[currentMethodId])
            if (30 < after - preT) {
                preT = after
                currentMethodId = if(currentMethodId + 1 < it.size) currentMethodId + 1 else 0
            }
        }
    }

    fun setMethod(methodName:String) {
        if (!textureResource.methods.containsKey(methodName)) throw RuntimeException("Not Contains Key in Method")
        currentMethodId = 0
        currentMethodName = methodName
    }
}