package org.lightfor.utils;

import java.io.File;
import java.io.FileInputStream;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;

/**
 * MD5 relative Class
 * Created by Light on 2016/1/29.
 */
public class MD5Utils {

    private static char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static MessageDigest messageDigest = null;
    static {
        try{
            messageDigest = MessageDigest.getInstance("MD5");
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public static String getFileMD5(File file) {
        try {
            FileInputStream fis = new FileInputStream(file);
            FileChannel fc = fis.getChannel();
            MappedByteBuffer byteBuffer = fc.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
            messageDigest.update(byteBuffer);
            return bufferToHex(messageDigest.digest());
        } catch(Exception e){
            e.printStackTrace();
        }
        return "";
    }

    public static String getStringMD5(String string){
        try {
            messageDigest.update(string.getBytes());
            return bufferToHex(messageDigest.digest());
        } catch(Exception e){
            e.printStackTrace();
        }
        return "";
    }

    private static String bufferToHex(byte bytes[]) {
        return bufferToHex(bytes, 0, bytes.length);
    }

    private static String bufferToHex(byte bytes[], int m, int n) {
        StringBuilder stringBuilder = new StringBuilder(2 * n);
        int k = m + n;
        for(int i = m; i < k; i++ ) {
            appendHexPair(bytes[i], stringBuilder);
        }
        return stringBuilder.toString();
    }

    private static void appendHexPair(byte bt, StringBuilder stringBuilder) {
        char c0 = hexDigits[(bt & 0xf0) >> 4];
        char c1 = hexDigits[bt & 0xf];
        stringBuilder.append(c0);
        stringBuilder.append(c1);
    }
}
