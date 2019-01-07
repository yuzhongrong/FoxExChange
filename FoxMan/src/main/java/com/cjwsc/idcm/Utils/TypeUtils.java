package com.cjwsc.idcm.Utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by admin-2 on 2018/3/30.
 */

public class TypeUtils {

    public static Type getPresnterClazz(final Class clazz, final int index) {
        //返回表示此 Class 所表示的实体（类、接口、基本类型或 void）的直接超类的 Type。
        Type genType = clazz.getGenericSuperclass();
        //返回表示此类型实际类型参数的 Type 对象的数组。
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        return params[index];
    }

}
