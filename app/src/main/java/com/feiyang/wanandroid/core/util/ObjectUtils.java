package com.feiyang.wanandroid.core.util;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Map;

/**
 * Copyright:wanandroid2
 * Author: liyang <br>
 * Date:2019/1/5 11:32 AM<br>
 * Desc: <br>
 */
public class ObjectUtils {
    public static boolean isNonNull(List list) {
        return list != null && list.size() > 0;
    }

    public static boolean isNonNull(Map map) {
        return map != null && map.size() > 0;
    }

    public static boolean isNonNull(Object[] arrays) {
        return arrays != null && arrays.length > 0;
    }

    public static boolean isNonNull(WeakReference reference) {
        return reference != null && reference.get() != null;
    }

    public static boolean isNonNull(Object o) {
        return o != null;
    }
}
