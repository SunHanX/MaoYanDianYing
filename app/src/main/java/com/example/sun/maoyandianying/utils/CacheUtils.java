package com.example.sun.maoyandianying.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by sun on 2016/11/30.
 * 缓存工具类
 */
public class CacheUtils {

    /**
     * 缓存文本数据
     *
     * @param context
     * @param key
     * @param values
     */
    public static void putString(Context context, String key, String values) {
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            //sdcard可用-用文件缓存

            //http:/19lkllsl.hslo.json-->MD5加密-->sllkkklskklkks(文件的名称)
            try {
                String fileName = MD5Encoder.encode(key);
                //mnt/sdcard/beijingnews/file/localfile/sllkkklskklkks
                File file = new File(Environment.getExternalStorageDirectory() + "/beijingnews/file/localfile/" + fileName);

                File parent = file.getParentFile();////mnt/sdcard/beijingnews/file/localfile/
                if (!parent.exists()) {
                    parent.mkdirs();//创建多层目录
                }

                if(!file.exists()){
                    file.createNewFile();
                }

                FileOutputStream fos = new FileOutputStream(file);
                fos.write(values.getBytes());
                fos.flush();
                fos.close();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }else{
            SharedPreferences sp = context.getSharedPreferences("atguigu", Context.MODE_PRIVATE);
            sp.edit().putString(key, values).commit();
        }

    }

    /**
     * 得到缓存文本信息
     *
     * @param context
     * @param key
     * @return
     */
    public static String getString(Context context, String key) {
        String result = "";
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            //读取sdcard的文件
            try {
                String fileName = MD5Encoder.encode(key);
                //mnt/sdcard/beijingnews/file/localfile/sllkkklskklkks
                File file = new File(Environment.getExternalStorageDirectory() + "/beijingnews/file/localfile/" + fileName);

                if(file.exists()){

                    FileInputStream fis = new FileInputStream(file);
                    byte[] buffer = new byte[1024];
                    int length;
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    while ( (length=fis.read(buffer))!=-1){
                        stream.write(buffer,0,length);
                    }

                    result =  stream.toString();

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            SharedPreferences sp = context.getSharedPreferences("atguigu", Context.MODE_PRIVATE);
            result =  sp.getString(key, "");
        }

        return  result;

    }

}
