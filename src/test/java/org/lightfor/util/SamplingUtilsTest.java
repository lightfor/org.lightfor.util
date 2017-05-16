package org.lightfor.util;

import org.junit.Test;

/**
 * 采样测试类
 * Created by Light on 2017/5/16.
 */
public class SamplingUtilsTest {
    @Test
    public void test(){
        for(int i = 0 ; i < 100 ; i++){
            if(SamplingUtils.isSelected()){
                System.out.println(i);
            }
        }
    }
}
