package pcloud.game.starter.ui.gles.app

import android.opengl.GLUtils
import pcloud.game.starter.ui.gles.model.*
import java.nio.FloatBuffer
import javax.microedition.khronos.opengles.GL10

class TextureShape {
    private var textureHandle = -1
    private var textureWidth = -1f
    private var textureHeight = -1f
    private var viewPointX = 0f
    private var viewPointY = 0f

    fun surfaceCreated(gl: GL10, textureResource: TextureResource) {
        val ints = IntArray(1)
        this.textureWidth = textureResource.textureWidth
        this.textureHeight = textureResource.textureHeight

        gl.glGenTextures(1, ints, 0)
        gl.glActiveTexture(GL10.GL_TEXTURE0)
        gl.glBindTexture(GL10.GL_TEXTURE_2D, ints[0])
        gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST)
        gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_NEAREST)
        gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S, GL10.GL_REPEAT)
        gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T, GL10.GL_REPEAT)
        GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, textureResource.texture, 0)
        textureHandle = ints[0]
        gl.glClearColor(0f, 0f, 0f, 0f)
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT or GL10.GL_DEPTH_BUFFER_BIT)
        gl.glMatrixMode(GL10.GL_TEXTURE)
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY)
        gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY)
        gl.glEnable(GL10.GL_DEPTH_TEST)
        gl.glEnable(GL10.GL_COLOR_BUFFER_BIT)
        gl.glEnable(GL10.GL_TEXTURE_2D)
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, VERTEX_BUFFER)
        gl.glBindTexture(GL10.GL_TEXTURE_2D, textureHandle)
    }

    fun draw(gl: GL10, methodInfo:MethodInfo) {
        val translateX = (methodInfo.x - viewPointX).toInt() / textureWidth
        val translateY = (methodInfo.y - viewPointY).toInt() / textureHeight

        gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, getLookTextureBuffer(methodInfo))
        gl.glDrawElements(GL10.GL_TRIANGLES, DRAW_ORDER.size, GL10.GL_UNSIGNED_SHORT, DRAW_ORDER_BUFFER)
        gl.glTranslatef(translateX, translateY, 0f)

        viewPointX = methodInfo.x
        viewPointY = methodInfo.y
    }

    fun destroy(gl: GL10) {
        gl.glClearColor(0f, 0f, 0f, 0f)
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT or GL10.GL_DEPTH_BUFFER_BIT)
        gl.glDisable(GL10.GL_DEPTH_TEST)
        gl.glDisable(GL10.GL_COLOR_BUFFER_BIT)
        gl.glDisable(GL10.GL_TEXTURE_2D)
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY)
        gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY)
    }

    private fun getLookTextureBuffer(methodInfo:MethodInfo): FloatBuffer {
        val w = methodInfo.w / textureWidth
        val h = methodInfo.h / textureHeight

        val lookTexture = floatArrayOf(
            0.0f, 0.0f,
            0.0f, h,
            w, h,
            w, 0.0f
        )
        return arrayToBuffer(lookTexture)
    }
}