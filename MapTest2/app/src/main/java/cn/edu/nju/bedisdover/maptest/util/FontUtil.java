package cn.edu.nju.bedisdover.maptest.util;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by song on 16-10-7.
 * <p>
 * 改变全局字体
 */
public class FontUtil {

    private FontUtil() {}

    private static Typeface typeface = null;

    public static Typeface getTypeface(Context context) {
        if (typeface == null) {
            typeface = Typeface.createFromAsset(context.getAssets(), "fzltxh_gbk.ttf");
        }

        return typeface;
    }
}
