package com.dommy.loading.util;

/**
 * 随机数工具
 */
public class RandomUtil {

    /**
     * 返回0-100的随机数
     *
     * @return int 随机数值
     */
    public static int getRandomPercent() {
        return (int) (Math.random() * 100);
    }
}
