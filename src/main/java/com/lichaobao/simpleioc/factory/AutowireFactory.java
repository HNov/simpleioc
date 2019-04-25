package com.lichaobao.simpleioc.factory;

import com.lichaobao.simpleioc.annotations.SimpleAutowire;
import com.lichaobao.simpleioc.annotations.SimpleCompotent;
import org.reflections.Reflections;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author lichaobao
 * @date 2019/4/18
 * @QQ 1527563274
 * 扫描所有被SimpleCompotent标记的类
 * 根据其内部属性被SimpleAutowire标记的属性
 * 从BeanFactory中找到相应的bean进行注入并进行
 * bean的初始化。
 */
public class AutowireFactory {
    /**
     * 扫描指定包名下的类 根据SimpleCompotent 以及SimpleAutowire进行注入
     * @param packageName 包名
     * @throws Exception Exception
     */
    public void init(String packageName) throws Exception {
        Reflections reflections = new Reflections(packageName);
        Set<Class<?>> classesList = reflections.getTypesAnnotatedWith(SimpleCompotent.class);
        for(Class clazz:classesList){
            Object bean = clazz.newInstance();
            Field[] fields = clazz.getDeclaredFields();
            Map<String,Object> fieldMap = new HashMap<String, Object>();
            for(Field field :fields){
                field.setAccessible(true);
                if(field.isAnnotationPresent(SimpleAutowire.class)){
                    SimpleAutowire simpleAutowire = field.getAnnotation(SimpleAutowire.class);
                    Type type = field.getGenericType();
                    String beanName = "".equals(simpleAutowire.value()) ?
                            type.toString().split(" ")[1]:simpleAutowire.value();
                    Object fieldBean = BeanFactory.getInstance().getBean(beanName);
                    field.set(bean,fieldBean);
                    fieldMap.put(field.getName(),fieldBean);
                }else{
                    fieldMap.put(field.getName(),field.get(bean));
                }
                SimpleCompotent simpleCompotent = (SimpleCompotent)clazz.getAnnotation(SimpleCompotent.class);
                String beanName = "".equals(simpleCompotent.value()) ? clazz.getName():simpleCompotent.value();
                BeanFactory.getInstance().registry(beanName,clazz.getName(),bean,fieldMap);
            }
        }
    }
}
