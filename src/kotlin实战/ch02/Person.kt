package kotlin实战.ch02

/**
 * 值对象
 * @author VincentJ
 * @date 2019-06-06
 */
// 属性默认的是public,其中name只有get，isMarried有get和set--在Java中就可以看到效果
class Person(val name: String,var isMarried:Boolean)
