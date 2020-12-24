package com.lingzhen.rdm.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * 文件操作工具类
 * @author lingz
 * @date 2020-09-27
 */
public class FileUtil {

    // 缓冲大小
    private static byte[] BUFFER = new byte[1024*1024];

    /**
     *  输出文件到指定路径
     */
    public static long saveFileFromInputStream(InputStream stream, String path, String fileName) {
        long bytesum = 0;
        // 判断目录是否存在
        File mlFile = new File(path);
        if (!mlFile.exists()) {
            mlFile.mkdirs();
        }
        try (FileOutputStream fs = new FileOutputStream(path+"/"+fileName)) {
            int byteread;
            while ((byteread=stream.read(BUFFER))!=-1) {
                bytesum += byteread;
                fs.write(BUFFER,0,byteread);
            }
            fs.flush();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return bytesum;
    }

}
