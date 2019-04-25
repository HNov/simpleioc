package com.lichaobao.test;

import com.lichaobao.simpleioc.annotations.SimpleBean;
import com.lichaobao.simpleioc.annotations.SimpleConfigure;

/**
 * @author lichaobao
 * @date 2019/4/18
 * @QQ 1527563274
 */
@SimpleConfigure
public class Configure {
    @SimpleBean
    public Test test(){
        return new Test("我是配置类里生成的test");
    }
}
