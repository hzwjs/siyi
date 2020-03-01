package siyi.game.utill;

import java.io.UnsupportedEncodingException;
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

    public static void main(String[] args) {
//        System.out.println(getRandomNumInTwoIntNum(10,12));
        System.out.println(isHit("50%"));
    }
}
