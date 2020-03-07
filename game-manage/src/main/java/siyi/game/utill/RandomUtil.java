package siyi.game.utill;

import org.apache.poi.ss.formula.functions.T;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * 生成随机整数
 *
 * @Author hzw
 */
public class RandomUtil {

    /**
     * 随机生成[low,high]之间的随机整数
     * @param low
     * @param high
     * @return
     */
    public static int getRandomNumInTwoIntNum(int low, int high) {
        int randomNum = 0;
        Random random = new Random();
        int cha = Math.abs(high - low);
        if (cha > 0) {
            randomNum = random.nextInt(high)%(high-low+1) + low;
        } else if (cha == 0) {
            randomNum = low;
        }
        return randomNum;
    }

    /**
     * 根据传入的百分比，判读是否命中
     * @param percent
     * @return
     */
    public static boolean isHit(String percent) {
        int percentNum = 0;
        if (percent.contains("%"))
            percentNum = Integer.parseInt(percent.substring(0, percent.length() - 1));
        else percentNum = Integer.parseInt(percent);
        int randomNum = getRandomNumInTwoIntNum(0, 100);
        if (randomNum <= percentNum)
            return true;
        return false;
    }

    public static String getRandomChar() {
        String str = "";
        int highCode;
        int lowCode;

        Random random = new Random();

        highCode = (176 + Math.abs(random.nextInt(39))); //B0 + 0~39(16~55) 一级汉字所占区
        lowCode = (161 + Math.abs(random.nextInt(93))); //A1 + 0~93 每区有94个汉字

        byte[] b = new byte[2];
        b[0] = (Integer.valueOf(highCode)).byteValue();
        b[1] = (Integer.valueOf(lowCode)).byteValue();

        try {
            str = new String(b, "GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * description: 集合中随机获取n条数据 <br>
     * version: 1.0 <br>
     * date: 2020/3/3 21:40 <br>
     * author: zhengzhiqiang <br>
     *
     * @param resourceList 目标集合
     * @param infoNum 获取数据条数
     * @return java.util.List<java.lang.String>
     */
    public static List<String> getRandomList(List<String> resourceList, int infoNum) {
        //把随机取得的数据存储在 listRandom 中
        List<String> listRandom = new ArrayList<String>();
        // 如果获取数据条数大于等于源集合，则将源集合返回
        if (infoNum >= resourceList.size()) {
            return resourceList;
        }
        //随机取出n条不重复的数据,这里我设置随机取3条数据
        for (int i = infoNum; i >=1; i--) {
            Random random = new Random();
            //在数组大小之间产生一个随机数 j
            int j = random.nextInt(resourceList.size());
            //取得resourceList 中下标为j 的数据存储到 listRandom 中
            listRandom.add(resourceList.get(j));
            //把已取到的数据移除,避免下次再次取到出现重复
            resourceList.remove(j);
        }
        return listRandom;
    }

    /**
     * 动态生成16位不重复随机数
     *
     * @return
     */
    public synchronized static String generate16() {
        StringBuffer stringBuffer = new StringBuffer();
        String date = getDateyyyyMMddNone();
        String seconds = getDateHHmmssNone();
        stringBuffer.append(date).append(generate2()).append(seconds);
        return stringBuffer.toString();
    }

    private static String getDateyyyyMMddNone() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String fd = sdf.format(date);
        return fd;
    }

    private static String getDateHHmmssNone() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
        String fd = sdf.format(date);
        return fd;
    }

    /**
     * 随机创建2位ID
     *
     * @return String 2位随机数ID
     */
    private synchronized static String generate2() {
        Random rad = new Random();
        String result = rad.nextInt(100) + "";
        if (result.length() == 1) {
            result = "0" + result;
        }
        return result;
    }
}
