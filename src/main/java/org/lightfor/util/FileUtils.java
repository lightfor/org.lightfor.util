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
 * @author Light
 * @date 2016/8/11.
 */
public enum  FileUtils {
    /** 实例 */
    INSTANCE;

    public static final String NEW_LINE = "\n";

    public static String fileToStringJDK6elow(String filePath){
        InputStream is = null;
        BufferedReader br = null;
        try {
            is = new FileInputStream(filePath);
            br = new BufferedReader(new InputStreamReader(is));

            String line = br.readLine();
            StringBuilder sb = new StringBuilder();

            while(line != null) {
                sb.append(line).append(NEW_LINE);
                line = br.readLine();
            }
            return sb.toString();
        } catch (Exception e) {
            return null;
        } finally {
            if(br != null){
                try{
                    br.close();
                } catch (Exception e){
                    LogUtils.error("close reader", e);
                }
            }
            if(is != null){
                try{
                    is.close();
                } catch (Exception e){
                    LogUtils.error("close input stream", e);
                }
            }
        }
    }

    public static Long countLine(String filePath){
        Long count = 0L;
        InputStream is = null;
        BufferedReader br = null;
        try {
            is = new FileInputStream(filePath);
            br = new BufferedReader(new InputStreamReader(is));

            String line = br.readLine();

            while(line != null) {
                count++;
                line = br.readLine();
            }
            return count;
        } catch (Exception e) {
            return count;
        } finally {
            if(br != null){
                try{
                    br.close();
                } catch (Exception e){
                    LogUtils.error("close reader", e);
                }
            }
            if(is != null){
                try{
                    is.close();
                } catch (Exception e){
                    LogUtils.error("close input stream", e);
                }
            }
        }
    }


    public static String fileToStringJDK7(String filePath){
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (Exception e) {
            return null;
        }
    }

    public static String fileToStringJDK8(String filePath){
        try {
            StringBuilder sb = new StringBuilder();
            Files.lines(Paths.get(filePath)).forEach(s -> sb.append(s).append(NEW_LINE));
            return sb.toString();
        } catch (Exception e) {
            return null;
        }
    }

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

    public static int countPattern(String path, String pattern) {
        File pathFile = new File(path);
        if(!pathFile.exists() || !pathFile.isDirectory()){
            return 0;
        }
        File[] files = pathFile.listFiles();
        int result = 0;
        if(files != null){
            for(File file : files){
                if(file.isFile()){
                    if(file.getName().matches(pattern)){
                        result++;
                    }
                } else if(file.isDirectory()){
                    result += countPattern(file.getAbsolutePath(), pattern);
                }
            }
        }
        return result;
    }

}
