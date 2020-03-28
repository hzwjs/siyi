package siyi.game.utill;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * The type Reflect operate.
 */
public class ReflectOperate {

    /**
     * The Log.
     */
    private final Logger log = LoggerFactory.getLogger(ReflectOperate.class);

    /**
     * 通过属性名调用get方法获取属性值.
     *
     * @param obj  the obj
     * @param name 属性名称
     * @return the get method value
     * @throws Exception the exception
     */
    public static Object getGetMethodValue(Object obj , String name){
        Method[] m = obj.getClass().getMethods();
        for(int i = 0;i < m.length;i++){
            if(("get"+name).toLowerCase().equals(m[i].getName().toLowerCase())){
                try {
                    return m[i].invoke(obj);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * 通过属性名调用set方法给变量赋值.
     *
     * @param obj       the obj
     * @param fieldName the field name
     * @param param     the param
     */
    public static void setValueByFieldName(Object obj, String fieldName, Object param) {
        PropertyDescriptor descriptor = null;
        try {
            descriptor = new PropertyDescriptor(fieldName, obj.getClass());
            Method method = descriptor.getWriteMethod();
            method.invoke(obj, param);
        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
