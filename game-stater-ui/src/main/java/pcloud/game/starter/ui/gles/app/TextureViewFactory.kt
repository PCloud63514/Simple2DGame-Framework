package pcloud.game.starter.ui.gles.app

import android.content.Context
import pcloud.game.starter.ui.gles.model.MethodInfo
import pcloud.game.starter.ui.gles.model.TextureResource
import pcloud.game.starter.ui.texturepacker.app.TexturePackerJSONResourceLoader
import pcloud.game.starter.ui.texturepacker.app.TexturePackerJSONResourceLoader.GetJSONResourceRequest
import pcloud.game.starter.ui.texturepacker.model.Frame

class TextureViewFactory {
    companion object Factory {
        private val tpResourceLoader = TexturePackerJSONResourceLoader()

        fun create(domain: String, context: Context): TextureView {
            val textureAtlas = tpResourceLoader.getResource(GetJSONResourceRequest(domain, context))

            val textureResource = TextureResource(
                domain = domain,
                texture = textureAtlas.bitmap,
                textureWidth = textureAtlas.meta.imageSize.w,
                textureHeight = textureAtlas.meta.imageSize.h,
                methods = convertMethods(textureAtlas.frames)
            )
            return TextureView(
                context = context,
                textureResource = textureResource
            )
        }

        private fun convertMethods(frames: List<Frame>): Map<String, List<MethodInfo>> {
            val methodMap: MutableMap<String, MutableList<MethodInfo>> = mutableMapOf()

            for (frame: Frame in frames) {
                // {methodName}/id
                // {methodName}/{secondName}-id
                val split:List<String> = frame.name.split("/")
                val methodName = split[0]
                val id = split[1].split(".")[0].toInt()
                if (!methodMap.containsKey(methodName)) {
                    methodMap[methodName] = mutableListOf<MethodInfo>()
                }

                val methodInfo = MethodInfo(
                    id = id,
                    methodName = methodName,
                    secondName = "second",
                    x = frame.frame.x,
                    y = frame.frame.y,
                    w = frame.frame.w,
                    h = frame.frame.h
                )

                methodMap[methodName]?.add(methodInfo)
            }

            return methodMap
        }
    }


}