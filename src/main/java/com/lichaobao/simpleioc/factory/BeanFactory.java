package com.lichaobao.simpleioc.factory;

import com.lichaobao.simpleioc.annotations.SimpleBean;
import com.lichaobao.simpleioc.annotations.SimpleConfigure;
import org.reflections.Reflections;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author lichaobao
 * @date 2019/4/18
 * @QQ 1527563274
 */
public class BeanFactory  {
    /**
     * 构造函数私有化  防止被调用
     */
    private BeanFactory(){
    }

    /**
     * 单例模式  内部静态类
     */
    private static class BeanFactorySingle{
        private static BeanFactory beanFactory = new BeanFactory();
    }
    /**
     * 存放bean实例
     */
    private static Map<String,BeanDefinition> beanMap = new HashMap<String, BeanDefinition>();
    /**
     * 扫描被SimpleConfigure 标记的类 找到其中被SimpleBean标记的方法，进行bean的初始化以及保存
     */
    public void init(String packageName) throws Exception {
        if(beanMap.size()==0){
            Reflections reflections = new Reflections(packageName);
            Set<Class<?>> classesList = reflections.getTypesAnnotatedWith(SimpleConfigure.class);
            for (Class clazz:classesList){
                Method[] methods = clazz.getDeclaredMethods();
                for(Method method:methods){
                    if(method.isAnnotationPresent(SimpleBean.class)){
                        Type type = method.getGenericReturnType();
                        String className = type.toString().split(" ")[1];
                        SimpleBean simpleBean = method.getAnnotation(SimpleBean.class);
                        Object o = clazz.newInstance();
                        Object bean =  method.invoke(o);
                        String beanName = "".equals(simpleBean.name())? className:simpleBean.name();
                        registry(beanName,className,bean,null);
                    }
                }
            }
        }
    }

    /**
     * 获得 bean
     * @param beanName bean名称（bean的自定义名称（如果添加的话）默认为类的全限定名）
     * @return Object
     * @throws Exception Not found
     */
    public Object getBean(String beanName)throws Exception{
        BeanDefinition beanDefinition = beanMap.get(beanName);
        if(beanDefinition == null){
            throw new Exception("No beans found name "+ beanName);
        }
        Object bean = beanDefinition.getBean();
        if(bean == null){
            bean = doCreate(beanDefinition);
        }
        return bean;
    }

    /**
     * 如果bean是空  则根据 beanDefinition中记录的bean结构 进行bean的初始化
     * @param beanDefinition 存放bean结构
     * @return Object
     * @throws Exception  InstantiationException, IllegalAccessException
     */
    private Object doCreate(BeanDefinition beanDefinition) throws Exception{
        Object bean = beanDefinition.getBeanClass().newInstance();
        bean =  addProperty(bean,beanDefinition);
        return bean;
    }

    /**
     * 根据beanDefinition 中的记录 将属性的值赋给要生成的bean
     * @param bean  bean
     * @param beanDefinition 存放bean数据结构
     * @return object
     * @throws Exception NoSuchFieldException, SecurityException
     */
    private Object addProperty(Object bean,BeanDefinition beanDefinition)throws Exception{
        Map<String,Object> map = beanDefinition.getPropertyMap();
        Field[] fields = beanDefinition.getBeanClass().getDeclaredFields();
        for(Field field : fields){
            Field declaredField = bean.getClass().getDeclaredField(field.getName());
            declaredField.setAccessible(true);
            declaredField.set(bean,map.get(field.getName()));
        }
        return bean;
    }
    void registry(String beanName,String className,Object bean,Map<String,Object> map) throws Exception {
        BeanDefinition beanDefinition = new BeanDefinition();
        beanDefinition.setClassName(className);
        beanDefinition.setBean(bean);
        beanDefinition.setPropertyMap(map);
        if(beanMap.containsKey(beanName)){
            throw new Exception("重复注册bean");
        }
        beanMap.put(beanName,beanDefinition);
    }

    public static BeanFactory getInstance(){
        return BeanFactorySingle.beanFactory;
    }
}
