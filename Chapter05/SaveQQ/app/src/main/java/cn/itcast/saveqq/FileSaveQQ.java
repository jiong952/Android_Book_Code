package cn.itcast.saveqq;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FileSaveQQ {
    //保存QQ账号和登录密码到data.txt文件中
    public static boolean saveUserInfo(Context context, String account, String
            password) {
        FileOutputStream fos = null;
        try {
            //获取文件的输出流对象fos
            fos = context.openFileOutput("data.txt",
                    Context.MODE_PRIVATE);
            //将数据转换为字节码的形式写入data.txt文件中
            fos.write((account + ":" + password).getBytes());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }finally {
            try {
                if(fos != null){
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    //从data.txt文件中获取存储的QQ账号和密码
    public static Map<String, String> getUserInfo(Context context) {
        String content = "";
        FileInputStream fis = null;
        try {
            //获取文件的输入流对象fis
            fis = context.openFileInput("data.txt");
            //将输入流对象中的数据转换为字节码的形式
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);//通过read()方法读取字节码中的数据
            content = new String(buffer); //将获取的字节码转换为字符串
            Map<String, String> userMap = new HashMap<String, String>();
            String[] infos = content.split(":");//将字符串以“：”分隔后形成一个数组的形式
            userMap.put("account", infos[0]);   //将数组中的第一个数据放入userMap集合中
            userMap.put("password", infos[1]); //将数组中的第二个数据放入userMap集合中
            return userMap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally {
            try {
                if(fis != null){
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

