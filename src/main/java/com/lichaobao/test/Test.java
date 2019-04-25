package com.lichaobao.test;

/**
 * @author lichaobao
 * @date 2019/4/18
 * @QQ 1527563274
 */
public class Test {
    private String a;

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public Test(String a) {
        this.a = a;
    }
    public void doSomething(){
        System.out.println(a);
    }
}
