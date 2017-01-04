package com.du.newsforme;

import android.util.Log;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

/**
 * Created by Administrator on 2016/11/3.
 */

public class Testac {

    public static void get() {
        try {
            URL url = new URL("http://news.58che.com/news/1605265.html?from=baidu.000087");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(5000);
            connection.setConnectTimeout(5000);
            connection.connect();
            if (connection.getResponseCode() == 200) {
                InputStream inputStream = connection.getInputStream();
//                FileOutputStream fos =new FileOutputStream("");
//                byte[] b=new byte[1024];
//
//
//                int len;
//                while((len=inputStream.read(b))!=-1){
//                    fos.write(b,0,len);
//
//                }
//                fos.close();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String s = reader.readLine();
                System.out.println(s + "---------------------");
                Log.e("dayinsha ", s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
