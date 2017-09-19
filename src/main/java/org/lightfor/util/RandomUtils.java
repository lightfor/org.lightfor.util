package org.lightfor.util;

import java.util.Random;

public class RandomUtils {

    public static Integer random(Integer start){
        Random random = new Random();
        return random.nextInt(start);
    }

    public static Integer random(Integer start, Integer end){
        Random random = new Random();
        return random.nextInt(end - start) + start;
    }
}
