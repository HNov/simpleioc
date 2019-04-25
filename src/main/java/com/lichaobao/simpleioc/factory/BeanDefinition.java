package com.lichaobao.simpleioc.factory;

import java.util.HashMap;
import java.util.Map;
/**
 * @author lichaobao
 * @date 2019/4/18
 * @QQ 1527563274
 * 存放bean的数据结构
 */
public class BeanDefinition {
   private Object bean;
   private String className;
   private Class beanClass;
   private Map<String,Object> propertyMap = new HashMap<String, Object>();
    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
        try{
            this.beanClass = Class.forName(className);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }

    public Map<String, Object> getPropertyMap() {
        return propertyMap;
    }

    public void setPropertyMap(Map<String, Object> propertyMap) {
        this.propertyMap = propertyMap;
    }
}
