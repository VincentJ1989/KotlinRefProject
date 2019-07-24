package kotlin实战.ch09

/**
 * 协变相关
 * @author VincentJ
 * @date 2019-07-24
 */

open class Animal() {
    fun feed() {
        println("feeding")
    }
}

// 第一个使用var编译不了，第二个因为是private，既不是in，也不是out
class Herd<out T : Animal>(val animals: List<T>, private var aa: T) {
    val size: Int
        get() = animals.size

    operator fun get(i: Int): T {
        return animals[i]
    }
}

fun feedAll(animals: Herd<Animal>) {
    for (i in 0 until animals.size) {
        animals[i].feed()
    }
}


class Cat : Animal() {
    fun clean() {
        println("cat")
    }
}

fun takeCareCat(cats: Herd<Cat>) {
    for (i in 0 until cats.size) {
        cats[i].clean()
    }
    // 如果Herd不使用out声明协变，这里编译无法通过
    feedAll(cats)
}