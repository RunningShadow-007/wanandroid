package com.feiyang.wanandroid.core.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Base64;

import com.feiyang.wanandroid.App;
import com.feiyang.wanandroid.core.constants.Constants;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import static com.feiyang.wanandroid.core.constants.Constants.SP_KEY_LOGIN_TOKEN;
import static com.feiyang.wanandroid.core.constants.Constants.SP_KEY_LOGIN_USER_NAME;
import static com.feiyang.wanandroid.core.constants.Constants.SP_KEY_USER_NAME;

public class SpUtils {
    private static final String SP_NAME_WAN_ANDROID = "sp_tag";



    private static SharedPreferences sp;

    private static SharedPreferences getSp() {
        if (sp == null) {
            synchronized (SpUtils.class) {
                if (sp == null) {
                    sp = App.getApp().getSharedPreferences(SP_NAME_WAN_ANDROID, Context.MODE_PRIVATE);
                }
            }
        }
        return sp;
    }

    public static String getToken() {
        return getString(SP_KEY_LOGIN_TOKEN);
    }

    public static void setToken(String data) {
        putString(SP_KEY_LOGIN_TOKEN, data);
    }

    public static void invalidLogin() {
        setUserName("");
        setToken("");
    }

    public static void setUserName(String userName) {
        putString(SP_KEY_LOGIN_USER_NAME, userName);
        putString(SP_KEY_USER_NAME,userName);
    }

    public static String getUserName() {
        return getString(SP_KEY_LOGIN_USER_NAME);
    }


    /**
     * 存入字符串
     *
     * @param key   字符串的键
     * @param value 字符串的值
     */
    public static void putString(@Constants.SP_KEYS String key, String value) {
        SharedPreferences preferences = getSp();
        //存入数据
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    /**
     * 移除键值对
     *
     * @param key
     */
    public static void removeString(@Constants.SP_KEYS String key) {
        SharedPreferences preferences = getSp();
        //存入数据
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(key);
        editor.commit();
    }

    /**
     * 获取字符串
     *
     * @param key 字符串的键
     * @return 得到的字符串
     */
    public static String getString(@Constants.SP_KEYS String key) {
        SharedPreferences preferences = getSp();
        return preferences.getString(key, "");
    }

    /**
     * 获取字符串
     *
     * @param key   字符串的键
     * @param value 字符串的默认值
     * @return 得到的字符串
     */
    public static String getString(@Constants.SP_KEYS String key, String value) {
        SharedPreferences preferences = getSp();
        return preferences.getString(key, value);
    }

    /**
     * 保存布尔值
     *
     * @param key   键
     * @param value 值
     */
    public static void putBoolean(@Constants.SP_KEYS String key, boolean value) {
        SharedPreferences        sp     = getSp();
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    /**
     * 获取布尔值
     *
     * @param key      键
     * @param defValue 默认值
     * @return 返回保存的值
     */
    public static boolean getBoolean(@Constants.SP_KEYS String key, boolean defValue) {
        SharedPreferences sp = getSp();
        return sp.getBoolean(key, defValue);
    }

    /**
     * 保存long值
     *
     * @param key   键
     * @param value 值
     */
    public static void putLong(@Constants.SP_KEYS String key, long value) {
        SharedPreferences        sp     = getSp();
        SharedPreferences.Editor editor = sp.edit();
        editor.putLong(key, value);
        editor.commit();
    }

    /**
     * 获取long值
     *
     * @param key      键
     * @param defValue 默认值
     * @return 保存的值
     */
    public static long getLong(@Constants.SP_KEYS String key, long defValue) {
        SharedPreferences sp = getSp();
        return sp.getLong(key, defValue);
    }

    /**
     * 保存int值
     *
     * @param key   键
     * @param value 值
     */
    public static void putInt(@Constants.SP_KEYS String key, int value) {
        SharedPreferences        sp     = getSp();
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    /**
     * 获取long值
     *
     * @param key      键
     * @param defValue 默认值
     * @return 保存的值
     */
    public static int getInt(@Constants.SP_KEYS String key, int defValue) {
        SharedPreferences sp = getSp();
        return sp.getInt(key, defValue);
    }

    /**
     * 保存对象
     *
     * @param key 键
     * @param obj 要保存的对象（Serializable的子类）
     * @param <T> 泛型定义
     */
    public static <T extends Serializable> void putObject(@Constants.SP_KEYS String key, T obj) {
        try {
            put(key, obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取对象
     *
     * @param key 键
     * @param <T> 指定泛型
     * @return 泛型对象
     */
    public static <T extends Serializable> T getObject(@Constants.SP_KEYS String key) {
        try {
            return (T) get(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 存储List集合
     *
     * @param key  存储的键
     * @param list 存储的集合
     */
    public static void putList(@Constants.SP_KEYS String key, List<? extends Serializable> list) {
        try {
            put(key, list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取List集合
     *
     * @param key 键
     * @param <E> 指定泛型
     * @return List集合
     */
    public static <E extends Serializable> List<E> getList(@Constants.SP_KEYS String key) {
        try {
            return (List<E>) get(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 存储Map集合
     *
     * @param key 键
     * @param map 存储的集合
     * @param <K> 指定Map的键
     * @param <V> 指定Map的值
     */
    public static <K extends Serializable, V extends Serializable> void putMap(@Constants.SP_KEYS String key, Map<K, V> map) {
        try {
            put(key, map);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static <K extends Serializable, V extends Serializable> Map<K, V> getMap(@Constants.SP_KEYS String key) {
        try {
            return (Map<K, V>) get(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 存储对象
     */
    private static void put(@Constants.SP_KEYS String key, Object obj)
            throws IOException {
        if (obj == null) {//判断对象是否为空
            return;
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream    oos  = null;
        oos = new ObjectOutputStream(baos);
        oos.writeObject(obj);
        // 将对象放到OutputStream中
        // 将对象转换成byte数组，并将其进行base64编码
        String objectStr = new String(Base64.encode(baos.toByteArray(), Base64.DEFAULT));
        baos.close();
        oos.close();

        putString(key, objectStr);
    }

    /**
     * 获取对象
     */
    private static Object get(@Constants.SP_KEYS String key)
            throws IOException, ClassNotFoundException {
        String wordBase64 = getString(key);
        // 将base64格式字符串还原成byte数组
        if (TextUtils.isEmpty(wordBase64)) { //不可少，否则在下面会报java.io.StreamCorruptedException
            return null;
        }
        byte[]               objBytes = Base64.decode(wordBase64.getBytes(), Base64.DEFAULT);
        ByteArrayInputStream bais     = new ByteArrayInputStream(objBytes);
        ObjectInputStream    ois      = new ObjectInputStream(bais);
        // 将byte数组转换成product对象
        Object obj = ois.readObject();
        bais.close();
        ois.close();
        return obj;
    }
}
