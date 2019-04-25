package com.lichaobao.test;

import com.lichaobao.simpleioc.factory.AutowireFactory;
import com.lichaobao.simpleioc.factory.BeanFactory;

/**
 * @author lichaobao
 * @date 2019/4/18
 * @QQ 1527563274
 */
public class TestMain {
    public static void main(String[] args)throws Exception{
        AutowireFactory autowireFactory = new AutowireFactory();
        BeanFactory.getInstance().init("com.lichaobao");
        autowireFactory.init("com.lichaobao");
        Test test = (Test) BeanFactory.getInstance().getBean("com.lichaobao.test.Test");
        TestA testA = (TestA) BeanFactory.getInstance().getBean("com.lichaobao.test.TestA");
        test.doSomething();
        testA.watch();
    }
}
