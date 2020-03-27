package siyi.game.utill;


import java.lang.reflect.Method;

/**
 * The type Reflect operate.
 */
public class ReflectOperate {

    /**
     * 通过属性名调用get方法获取属性值.
     *
     * @param obj
     * @param name 属性名称
     * @return the get method value
     * @throws Exception the exception
     */
    public static Object getGetMethodValue(Object obj , String name)throws Exception{
        Method[] m = obj.getClass().getMethods();
        for(int i = 0;i < m.length;i++){
            if(("get"+name).toLowerCase().equals(m[i].getName().toLowerCase())){
                return m[i].invoke(obj);
            }
        }
        return null;
    }
}
