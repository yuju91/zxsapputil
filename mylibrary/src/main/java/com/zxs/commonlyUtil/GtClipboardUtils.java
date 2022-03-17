package com.zxs.commonlyUtil;

import android.content.Context;

/**
 * Created by zxs
 * on 2022/2/11
 */
public class GtClipboardUtils {
    /**
     * 复制内容到剪切板
     *
     * @param context
     * @param text
     */
    public static void clipboardCopyText(Context context, CharSequence text) {
        android.text.ClipboardManager cm = (android.text.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        if (cm != null) {
            cm.setText(text);
        }
    }

    /**
     * 获取剪切板内容长度
     *
     * @param context
     * @return
     */
    public static int clipboardTextLength(Context context) {
        android.text.ClipboardManager cm = (android.text.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        CharSequence text = cm != null ? cm.getText() : null;
        return text != null ? text.length() : 0;
    }


}
