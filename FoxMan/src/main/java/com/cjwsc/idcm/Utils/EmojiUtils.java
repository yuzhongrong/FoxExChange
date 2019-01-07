package com.cjwsc.idcm.Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Morning on 2017/9/14.
 */

public class EmojiUtils {

    public static boolean isEmoji(String string) {
        Pattern p = Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",
                Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(string);
        return m.find();
    }
}
