package org.lightfor.util;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author Light
 * @date 2018-02-07
 */
public enum ZipUtils {
    /** 实例 */
    INSTANCE;

    public static void compress(String src) {
        ZipOutputStream dst = null;
        try {
            dst = new ZipOutputStream(new FileOutputStream(src + ".zip"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        compress(src, dst);
        if(dst != null){
            try {
                dst.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void compress(String src, ZipOutputStream dst) {
        File srcFile = new File(src);
        compress(srcFile, dst);
    }

    public static void compress(File src, ZipOutputStream dst) {
        File[] files = {src};
        if(src.isDirectory()){
            files = src.listFiles();
        }
        if(files == null){
            return ;
        }
        for(File file : files){
            if(file.isFile()){
                FileInputStream fis = null;
                String filePath = file.getAbsolutePath();
                ZipEntry entry = new ZipEntry(filePath.substring(filePath.lastIndexOf("\\") + 1));
                byte[] buffer = new byte[1024000];
                try {
                    dst.putNextEntry(entry);
                    fis = new FileInputStream(file);
                    int flag;
                    //FIXME
                    while ( (flag = fis.read(buffer)) != -1 ) {
                        dst.write(buffer, 0, flag);
                    }
                    dst.flush();
                    dst.closeEntry();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if(fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }  else {
                compress(file, dst);
            }
        }
    }
}
