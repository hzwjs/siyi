package siyi.game.utill;

import java.util.Random;

/**
 * 生成随机整数
 *
 * @Author hzw
 */
public class RandomGeneratorNum {

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

    public static void main(String[] args) {
        System.out.println(getRandomNumInTwoIntNum(10,12));
    }
}
