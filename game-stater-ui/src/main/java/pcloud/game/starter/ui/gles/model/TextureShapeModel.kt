package pcloud.game.starter.ui.gles.model

import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer
import java.nio.ShortBuffer

val VERTEX_COORDS: FloatArray = floatArrayOf(
    -1f, 1f, 0.0f,
    -1f, -1f, 0.0f,
    1f, -1f, 0.0f,
    1f, 1f, 0.0f
)

val DRAW_ORDER: ShortArray = shortArrayOf(
    0, 1, 2, 0, 2, 3
)

val VERTEX_BUFFER: FloatBuffer = arrayToBuffer(VERTEX_COORDS)
val DRAW_ORDER_BUFFER: ShortBuffer = arrayToBuffer(DRAW_ORDER)

fun arrayToBuffer(floatArray: FloatArray):FloatBuffer {
    val buf: ByteBuffer = ByteBuffer.allocateDirect(floatArray.size * 4)
    buf.order(ByteOrder.nativeOrder())
    val fBuf: FloatBuffer = buf.asFloatBuffer()
    fBuf.put(floatArray)
    fBuf.position(0)
    return fBuf
}

fun arrayToBuffer(shortArray: ShortArray): ShortBuffer {
    val buf: ByteBuffer = ByteBuffer.allocateDirect(shortArray.size * 4)
    buf.order(ByteOrder.nativeOrder())
    val sBuf: ShortBuffer = buf.asShortBuffer()
    sBuf.put(shortArray)
    sBuf.position(0)

    return sBuf
}