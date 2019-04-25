package com.lichaobao.test;

import com.lichaobao.simpleioc.annotations.SimpleAutowire;
import com.lichaobao.simpleioc.annotations.SimpleCompotent;

/**
 * @author lichaobao
 * @date 2019/4/18
 * @QQ 1527563274
 */
@SimpleCompotent
public class TestA {
    @SimpleAutowire
    private Test test;
    void watch(){
        System.out.println("======================");
        System.out.println("我看看test属性被注入了没");
        test.doSomething();
        System.out.println("上面打印了，属实注入了呀");
    }
}
