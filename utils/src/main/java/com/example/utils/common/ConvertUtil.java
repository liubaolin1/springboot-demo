package com.example.utils.common;

import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.core.Converter;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;

/**
 * @Description: 实体bean和自定义类转换
 * @author: liubao
 * @Date: Created in 2018/6/20 8:37
 */
public class ConvertUtil {

    /**
     * @des 转换对象
     *  1.来源对象或者目标对象为null不做数据处理
     *  2.entity->dto date转换为long
     *  3.dto->entity long转换为date
     * @param targetObj 目标对象
     * @param fromObj 来源对象
     */
    public static <T> void convert(T targetObj,Object fromObj) {
        if(null == fromObj) {
            return;
        }
        try {
            BeanCopier copier = BeanCopier.create(fromObj.getClass(), targetObj.getClass(), true);
            MyConverter converter = new MyConverter();
            copier.copy(fromObj, targetObj, converter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @des 转换集合对象
     *  1.来源集合或者目标集合为null不做数据处理
     *  2.entity->dto date转换为long
     *  3.dto->entity long转换为date
     * @param targetObj 目标对象
     * @param fromObjList 来源集合
     * @param <T>
     * @return list<T> 返回对象集合
     */
    public static <T> List<T> convertList(T targetObj ,Collection fromObjList) {
        List<T> tempList = new ArrayList<T>();
        if(null == fromObjList) {
            return tempList;
        }
        try {
            for(Object fromObj : fromObjList) {
                T target = (T)targetObj.getClass().newInstance();
                convert(target,fromObj);
                tempList.add(target);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tempList;
    }
    // Map --> Bean 1: 利用Introspector,PropertyDescriptor实现 Map --> Bean
    public static void transMap2Bean(Map<String, Object> map, Object obj) {
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

            java.util.Iterator it = map.entrySet().iterator();
            int i=0;
            for (PropertyDescriptor property : propertyDescriptors) {

                String key = property.getName();
                Object type = property.getPropertyType();
                if("page".equals(key) || "pagesize".equals(key) || "startRownum".equals(key) || "endRownum".equals(key)){
                    continue;
                }

                System.out.println("第"+i+"个key:"+key);
                i++;
                if (map.containsKey(key)) {
                    Object value = map.get(key);
                    if("indicatorCate".equals(key)){
                        value=(Byte)value;
                    }
                    // 得到property对应的setter方法
                    Method setter = property.getWriteMethod();
                    setter.invoke(obj, value);
                }

            }

        } catch (Exception e) {
            System.out.println("transMap2Bean Error " + e);
        }

        return;

    }

    // Bean --> Map 1: 利用Introspector和PropertyDescriptor 将Bean --> Map
    public static Map<String, Object> transBean2Map(Object obj) {

        if(obj == null){
            return null;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();

                // 过滤class属性
                if (!key.equals("class")) {
                    // 得到property对应的getter方法
                    Method getter = property.getReadMethod();
                    Object value = getter.invoke(obj);

                    map.put(key, value);
                }

            }
        } catch (Exception e) {
            System.out.println("transBean2Map Error " + e);
        }

        return map;

    }

    static class MyConverter implements Converter {

        @SuppressWarnings("rawtypes")
        @Override
        public Object convert(Object value, Class target, Object context) {
            if(value instanceof Long && target.getName().equals(Timestamp.class.getName())) { //long转换为timestamp
                return new Timestamp((Long)value);
            } else if(value instanceof Date && target.getName().equals(Long.class.getName())) { //date转换为long
                return ((Date) value).getTime();
            } else if (value instanceof Integer) {
                return (Integer) value;
            } else if (value instanceof Timestamp && target.getName().equals(Long.class.getName())) { //timestamp转换为long
                return Timestamp.valueOf(value.toString()).getTime();
            } else if (value instanceof BigDecimal) {
                BigDecimal bd = (BigDecimal) value;
                return bd;
            }else if(value instanceof String){
                return value.toString();
            } else if(value instanceof Double) {
                return (Double) value;
            } else if(value instanceof Float) {
                return (Float) value;
            } else if(value instanceof Long) {
                return (Long) value;
            } else if(value instanceof Byte) {
                return (Byte) value;
            }

            return null;
        }
    }
}