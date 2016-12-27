package org.lightfor.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * 文件工具类
 * Created by Light on 2016/8/11.
 */
public enum  FileUtils {
    INSTANCE;

    public static String fileToStringJDK6elow(String filePath){
        try {
            InputStream is = new FileInputStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String line = br.readLine();
            StringBuilder sb = new StringBuilder();

            while(line != null) {
                sb.append(line).append("\n");
                line = br.readLine();
            }
            return sb.toString();
        } catch (Exception e) {
            return null;
        }
    }

    public static String fileToStringJDK7(String filePath){
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (Exception e) {
            return null;
        }
    }

/*    public String fileToStringJDK8(String filePath){
        try {
            Files.lines(Paths.get(filePath)).forEach(System.out::println);
            return null;
        } catch (Exception e) {
            return null;
        }
    }*/

    public static boolean deleteFile(String fileName){
        File file = new File(fileName);
        return (file.isFile() && file.exists() && file.delete());
    }


    public static boolean deleteDirectory(String dir){
        if(!dir.endsWith(File.separator)){
            dir = dir+File.separator;
        }
        File dirFile = new File(dir);

        if(!dirFile.exists() || !dirFile.isDirectory()){
            return false;
        }
        boolean flag = true;
        File[] files = dirFile.listFiles();
        if(files != null){
            for(File file : files){
                if(file.isFile()){
                    flag = deleteFile(file.getAbsolutePath());
                    if(!flag){
                        break;
                    }
                } else if(file.isDirectory()){
                    deleteDirectory(file.getAbsolutePath());
                }
            }
        }
        return flag && dirFile.delete();
    }

}
