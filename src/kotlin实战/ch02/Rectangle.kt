package kotlin实战.ch02

import java.util.*

/**
 * 2.2.2 自定义访问器
 * @author VincentJ
 * @date 2019-06-06
 */
class Rectangle(val height: Int, val width: Int) {
    val isSquare: Boolean
        get() {
            return height == width
        }

    val isNotSquare: Boolean
        get() = height != width
}

fun createRectange(): Rectangle {
    val random = Random()
    return Rectangle(random.nextInt(), random.nextInt())
}