package com.lingzhen.rdm.util;

import java.util.Random;

/**
 * @date 20201127
 * @author lingz
 */
public class RandomUtil {

    public static String randomNumber(int length) {
        StringBuffer result = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < length; ++i) {
            int tmp = random.nextInt(10);
            result.append(tmp);
        }
        return result.toString();
    }

}
