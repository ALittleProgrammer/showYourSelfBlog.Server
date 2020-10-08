package com.showyourselfblog.server.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

/**
 * 转自 https://blog.csdn.net/qq_42685333/article/details/83051519
 */

/**
 * @Description 将实体属性转化为Map
 * @program ShowYourselfBlogServer
 * @Author Peng Jiankun
 * @Date 2020-09-25 14:26
 **/

public class MapBeanUtil {

    static String[] toMapIgN ={"password","realName","address"};
    /**
     * 实体对象转成Map
     *
     * @param obj 实体对象
     * @return
     */

    public static Map<String, Object> objectToMap(Object obj) {
        Map<String, Object> map = new HashMap<>();
        if (obj == null) {
            return map;
        }
        Class clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                if (isContains(toMapIgN,field.getName())||field.get(obj)==null){
                    continue;
                }
                map.put(field.getName(), field.get(obj));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    private static boolean isContains(String[] args,String item){
        for (String c:args){
            if (c.equalsIgnoreCase(item)){
                return true;
            }
        }
        return false;
    }

    /**
     * Map转成实体对象
     *
     * @param map   实体对象包含属性
     * @param clazz 实体对象类型
     * @return
     */
    public static Object map2Object(Map<String, Object> map, Class<?> clazz) {
        if (map == null) {
            return null;
        }
        Object obj = null;
        try {
            obj = clazz.newInstance();

            Field[] fields = obj.getClass().getDeclaredFields();
            for (Field field : fields) {
                int mod = field.getModifiers();
                if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                    continue;
                }
                field.setAccessible(true);
                field.set(obj, map.get(field.getName()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

}
