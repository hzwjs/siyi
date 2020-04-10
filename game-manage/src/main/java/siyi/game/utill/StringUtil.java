package siyi.game.utill;

public class StringUtil {

    /**
     * xxx_xxx 格式转换驼峰命名法
     *
     * @param sourceStr
     * @return
     */
    public static String getCamelCase(String sourceStr) {
        sourceStr = sourceStr.toUpperCase();
        StringBuilder result = new StringBuilder();
        String strArray[] = sourceStr.split("_");
        for (String s : strArray) {
            // 如果最终结果长度为零，则将第一个单词转小写进行频接
            if (result.length() == 0) {
                result.append(s.toLowerCase());
            } else {
                // 后续单词，首字母大写，其余转小写
                result.append(s.substring(0, 1).toUpperCase());
                result.append(s.substring(1).toLowerCase());
            }
        }
        return result.toString();
    }
}
