package kotlin实战.ch09

/**
 *
 * @author VincentJ
 * @date 2019-07-25
 */


/**
 * 带不变型类型参数的数据拷贝函数
 * 版本1：支持同种类型T
 */
fun <T> copy(ori: MutableList<T>, des: MutableList<T>) {
    for (t in ori) {
        des.add(t)
    }
}

/**
 * 带不变类型参数的数据拷贝函数
 * 版本2：可以目标集合不仅支持原始集合，
 */
fun <T : R, R> copy2(ori: MutableList<T>, des: MutableList<R>) {
    for (t in ori) {
        des.add(t)
    }
}

/**
 * Kotlin中较为优雅的实现方式
 * 带out投影类型参数的数据拷贝函数
 * 版本3：可以给类型的用法上加上out关键字：没有使用那些T用在in位置上的方法
 */
fun <T> copy3(ori: MutableList<out T>, des: MutableList<T>) {
    for (t in ori) {
        des.add(t)
    }
}

/**
 * Kotlin中较为优雅的实现方式
 * 带in投影类型参数的数据拷贝函数
 * 版本4：可以给类型的用法上加上in关键字：允许目标元素的类型是来源类型的超类型
 *
 * 和版本2是一个作用
 */
fun <T> copy4(ori: MutableList<T>, des: MutableList<in T>) {
    for (t in ori) {
        des.add(t)
    }
}