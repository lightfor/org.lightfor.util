package org.lightfor.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * URL Utils Test
 * Created by Light on 2017/6/7.
 */
public class URLUtilsTest {

    @Test
    public void test(){
        Assert.assertEquals(URLUtils.FixedLength("http://www.infoq.com/cn/articles/solution-of-distributed-system-transaction-consistency"), "http://www.infoq.com/cn/articles/soluti ... onsistency");
    }
}
