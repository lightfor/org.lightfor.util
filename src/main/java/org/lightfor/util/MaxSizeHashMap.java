package org.lightfor.util;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 有最大值限制的HashMap
 * Created by Light on 2016/9/6.
 */
public class MaxSizeHashMap<K, V> extends LinkedHashMap<K, V> {
    private  final int maxSize;

    public MaxSizeHashMap(int maxSize){
        this.maxSize = maxSize;
    }

    @Override
    protected  boolean removeEldestEntry(Map.Entry<K,V> eldest){
        return size() > maxSize;
    }
}
