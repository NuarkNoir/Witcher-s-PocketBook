package xyz.nuark.witcherspocketbook;

import android.content.Context;
import android.graphics.drawable.Drawable;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Nuark with love on 09.09.2017.
 * Protected by QPL-1.0
 */

public class Utils {
    public static String getJsonString(Context context, String path) throws IOException{
        InputStream stream = context.getAssets().open(path);
        byte[] jsonByteArray = new byte[stream.available()];
        stream.read(jsonByteArray);
        stream.close();
        return new String(jsonByteArray);
    }

    public static Drawable getDrawableFromAssets(Context context, String path){
        try {
            return Drawable.createFromStream(context.getAssets().open("images/" + path), null);
        } catch (Exception ex) {
            return null;
        }
    }
}
