package com.lingzhen.myproject.lifefolder.util;

import java.io.FileOutputStream;
import java.io.InputStream;

public class FileUtil {

    // 缓冲大小
    private static byte[] BUFFER = new byte[1024*1024];

    /**
     *  输出文件到指定路径
     */
    public static long saveFileFromInputStream(InputStream stream, String path) {
        long bytesum = 0;
        try (FileOutputStream fs = new FileOutputStream(path)) {
            int byteread;
            while ((byteread=stream.read(BUFFER))!=-1) {
                bytesum += byteread;
                fs.write(BUFFER,0,byteread);
            }
            fs.flush();
        } catch (Exception e) {
        }
        return bytesum;
    }

}
