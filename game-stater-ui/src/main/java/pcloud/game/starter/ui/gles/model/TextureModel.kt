package pcloud.game.starter.ui.gles.model

import android.graphics.Bitmap

/**
 * @param id method index
 * @param methodName method name / TextureAtlas.Meta.Frames.frame.name
 * @param secondName method-{secondName}
 * @param x view point X of the sprite in texture / TextureAtlas.Meta.Frames.frame.x
 * @param y view point Y of the sprite in texture / TextureAtlas.Meta.Frames.frame.y
 * @param w sprite width  / TextureAtlas.Meta.Frames.frame.w
 * @param h sprite height / TextureAtlas.Meta.Frames.frame.h
 */
data class MethodInfo(
    val id: Int,
    val methodName: String,
    val secondName: String,
    val x: Float,
    val y: Float,
    val w: Float,
    val h: Float
)

/**
 * @param domain Unit Name
 * @param texture Texture Bitmap
 * @param textureWidth Texture Size Width   / TextureAtlas.Meta.Size.W
 * @param textureHeight Texture Size Height / TextureAtlas.Meta.Size.H
 * @param methods TextureView Action Info / TextureAtlas.Frames
 */
data class TextureResource(
    val domain: String,
    val texture: Bitmap,
    val textureWidth: Float,
    val textureHeight: Float,
    val methods: Map<String, List<MethodInfo>>
)
