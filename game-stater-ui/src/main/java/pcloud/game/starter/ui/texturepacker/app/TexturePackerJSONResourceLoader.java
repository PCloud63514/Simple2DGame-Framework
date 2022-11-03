package pcloud.game.starter.ui.texturepacker.app;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

import pcloud.game.starter.ui.texturepacker.model.TextureAtlas;
import pcloud.game.starter.ui.utils.JsonObjectProvider;

public class TexturePackerJSONResourceLoader implements TexturePackerResourceLoader<TexturePackerJSONResourceLoader.GetJSONResourceRequest> {
    private static final JsonObjectProvider jsonObjectProvider = new JsonObjectProvider();

    @Override
    public TextureAtlas getResource(GetJSONResourceRequest request) throws IOException {
        final InputStream bitmapStream = openInputStream(request.getContext().getAssets(), request.getFileName() + "/data.png");
        final InputStream jsonStream = openInputStream(request.getContext().getAssets(), request.getFileName() + "/data.json");

        final Bitmap bitmap = BitmapFactory.decodeStream(bitmapStream);
        final JSONObject jsonObject = jsonObjectProvider.create(jsonStream);
        return TextureAtlas.Factory.create(bitmap, jsonObject);
    }

    private InputStream openInputStream(final AssetManager assets, final String fileName) throws IOException {
        return assets.open(fileName);
    }

    public static class GetJSONResourceRequest extends TexturePackerResourceLoader.GetResourceRequest {
        private final String fileName;
        private final Context context;
        public GetJSONResourceRequest(final String fileName, final Context context) {
            this.fileName = fileName;
            this.context = context;
        }

        @Override
        String getFileName() {
            return fileName;
        }

        public Context getContext() {
            return context;
        }
    }
}

