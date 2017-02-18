package com.cyclic_order.amarshohor.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by cyclic_order on 1/3/2016.
 */
public class HttpManager {
    public String getData(String uri) throws IOException {
        BufferedReader reader=null;
        InputStream inputStream=null;
        HttpURLConnection connection=null;
        String httpData=null;
        try {
            URL url=new URL(uri);
            connection= (HttpURLConnection) url.openConnection();
            connection.connect();
            inputStream=connection.getInputStream();
            StringBuffer sb=new StringBuffer();
            reader=new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line +"\n");
            }
            httpData = sb.toString();
            reader.close();
        } catch (Exception e) {
           e.printStackTrace();
            return null;
        } finally {
            inputStream.close();
            connection.disconnect();
        }
        return httpData;
    }



}
