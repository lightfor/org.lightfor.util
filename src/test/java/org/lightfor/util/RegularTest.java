package org.lightfor.util;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 * Created by Light on 2017/3/22.
 */
public class RegularTest {

    public static void main(String[] args) {
        System.out.println(args[0].matches("^(0|[1-9][0-9]*)(\\.[0-9]+|)$"));
    }

    @Test
    public void test(){
        List<String> list = RegexUtils.returnAllMatch("1a2b3c", "(\\d+)");
        System.out.println(list);
        for(String ele : list){
            System.out.println(ele);
        }
    }

}
