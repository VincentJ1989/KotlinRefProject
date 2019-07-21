package kotlin实战.ch08;

import kotlin.jvm.functions.Function1;

/**
 * @author VincentJ
 * @date 2019-07-21
 */
public class Main {
    public static void main(String[] args) {
        UseInJava useInJava = new UseInJava();
        useInJava.processTheAnswer(new Function1<Integer, Integer>() {
            @Override
            public Integer invoke(Integer integer) {
                return integer + 1;
            }
        });

        // 简化版
        useInJava.processTheAnswer(integer -> integer + 1);
    }
}
