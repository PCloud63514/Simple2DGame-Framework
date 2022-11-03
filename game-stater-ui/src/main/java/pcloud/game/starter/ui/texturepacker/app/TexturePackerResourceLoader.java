package pcloud.game.starter.ui.texturepacker.app;

import java.io.IOException;

import pcloud.game.starter.ui.texturepacker.model.TextureAtlas;



public interface TexturePackerResourceLoader<T extends TexturePackerResourceLoader.GetResourceRequest> {
    TextureAtlas getResource(final T request) throws IOException;

    abstract class GetResourceRequest {
        abstract String getFileName();
    }
}
