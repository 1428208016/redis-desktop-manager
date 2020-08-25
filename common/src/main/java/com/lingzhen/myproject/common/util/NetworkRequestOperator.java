package com.lingzhen.myproject.common.util;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.zip.GZIPInputStream;

/**
 * 网络请求工具类
 * @date 2020-08-18
 * @author lingz
 */
public class NetworkRequestOperator {

    private String inputCharset = "UTF-8";
    private String outputCharset = "UTF-8";
    private Map<String, String> headers;
    private int connectTimeout = 15000;
    private int readTimeout = 20000;

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public String post(String url, String body) {
        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;
        BufferedOutputStream bufferedOutputStream = null;

        try {
            httpURLConnection = this.buildConnection(url, "POST");

            bufferedOutputStream = new BufferedOutputStream(httpURLConnection.getOutputStream());
            bufferedOutputStream.write(body.getBytes(this.outputCharset));
            bufferedOutputStream.flush();

            inputStream = httpURLConnection.getInputStream();

            if("gzip".equals(httpURLConnection.getContentEncoding())) {
                inputStream = new GZIPInputStream((InputStream)inputStream);
            }

            StringWriter sw = new StringWriter();
            InputStreamReader in = new InputStreamReader(inputStream, this.inputCharset);
            char[] buffer = new char[1024*4];
            int n = 0;
            while (-1 != (n = in.read(buffer))) {
                sw.write(buffer, 0, n);
            }
            return sw.toString();

        } catch (IOException e) {
            if (null != bufferedOutputStream) {
                try {
                    bufferedOutputStream.close();
                } catch (IOException e2) {}
            }
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (Exception e2) {}
            }
            if (null != httpURLConnection) {
                httpURLConnection.disconnect();
            }
        }

        return null;
    }


    /**
     * Get请求
     * @param url
     * @return
     */
    public String get(String url) {
        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;
        try {
            httpURLConnection = this.buildConnection(url, "GET");

            inputStream = httpURLConnection.getInputStream();

            if("gzip".equals(httpURLConnection.getContentEncoding())) {
                inputStream = new GZIPInputStream((InputStream)inputStream);
            }

            StringWriter sw = new StringWriter();
            InputStreamReader in = new InputStreamReader(inputStream, this.inputCharset);
            char[] buffer = new char[1024*4];
            int n = 0;
            while (-1 != (n = in.read(buffer))) {
                sw.write(buffer, 0, n);
            }
            return sw.toString();
        } catch (IOException e) {
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (Exception e2) {}
            }
            if (null != httpURLConnection) {
                httpURLConnection.disconnect();
            }
        }

        return null;
    }

    //构建连接属性
    private HttpURLConnection buildConnection(String urlStr, String method) throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();

//        if(this.sslContext != null && urlConnection instanceof HttpsURLConnection) {
//            ((HttpsURLConnection)urlConnection).setSSLSocketFactory(this.sslContext.getSocketFactory());
//        }

        if(this.headers != null && !this.headers.isEmpty()) {
            Iterator iterator = this.headers.entrySet().iterator();
            while(iterator.hasNext()) {
                Map.Entry entry = (Map.Entry)iterator.next();
                urlConnection.setRequestProperty((String)entry.getKey(), (String)entry.getValue());
            }
        }

        urlConnection.setRequestMethod(method);
        urlConnection.setDoOutput(true);
        urlConnection.setConnectTimeout(this.connectTimeout);
        urlConnection.setReadTimeout(this.readTimeout);

        return urlConnection;
    }
}