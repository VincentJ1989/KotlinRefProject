package kotlin实战.ch07

import java.time.LocalDate

/**
 * 运算符重载及其他约定
 * @author VincentJ
 * @date 2019-07-21
 */

fun main() {
    // 重载算术运算符
    println(Point(1, 2) + Point(1, 2))

    // 重载一元运算符
    println(-Point(1, 2))
    // 比较运算符
    println(Point2(1, 2) > Point2(1, 3))

    // in
    val rect = Rectangle(Point(0, 0), Point(5, 5))
//    println(Point(1, 1) in rect)


    //rangeTo
    val now = LocalDate.now()
    val vocation = now..now.plusDays(10)
    println(now.plusWeeks(1) in vocation)

    // 解构声明
    val p = Point(2, 2)
    val (x, y) = p
    println(x)
    println(y)

    val (name, ext) = splitFilename("Main07.kt")
    println(name)
    println(ext)

    // 解构声明在循环中的应用
    printMapData(mapOf("Oracle" to "Java", "JetBrains" to "Kotlin"))

}

// 委托属性在自订对象中的使用--在map中保存属性值
class Stuff() {
    private val _attributes = hashMapOf<String, String>()
    fun setAttribute(attrName: String, value: String) {
        _attributes[attrName] = value
    }

    val name: String
        get() = _attributes["name"]!!
    // 可以使用属性委托简化
    val name2: String by _attributes
}


// 惰性初始化
fun loadEmails(person: Person): List<Email> {
    println("Load emails for ${person.name}")
    return listOf()
}

data class Person(val name: String) {
    // 使用支持属性来实现惰性初始化---不是很推荐，太啰嗦了
    private var _emails: List<Email>? = null
    val emails: List<Email>
        get() {
            if (_emails == null) {
                _emails = loadEmails(this)
            }
            return _emails!!
        }

    // 使用委托属性实现惰性初始化
    val emailss by lazy { loadEmails(this) }
}


class Email()


fun printMapData(map: Map<String, String>) {
    for ((k, v) in map) {
        println("$k-->$v")
    }
}


data class NameComponents(val name: String, val extension: String)

fun splitFilename(fileName: String): NameComponents {
    val result = fileName.split('.', limit = 2)
    return NameComponents(result[0], result[1])
}

class Point6(val x: Int, val y: Int) {
    // 显示声明
    operator fun component1() = x

    operator fun component2() = y
}

data class Point(val x: Int, val y: Int) {

    // 重载算术运算符
    operator fun plus(other: Point): Point {
        return Point(x + other.x, y + other.y)
    }

    // 重载一元运算符
    operator fun unaryMinus(): Point {
        return Point(-x, -y)
    }

    // 重载equals
    override operator fun equals(other: Any?): Boolean {
        if (other === this) {
            return false
        }
        if (other !is Point) {
            return false
        }

        return other.x == x && other.y == y
    }

    override fun hashCode(): Int {
        var result = x
        result = 31 * result + y
        return result
    }

    // 通过下标访问元素
    operator fun Point.get(index: Int): Int {
        return when (index) {
            0 -> x
            1 -> y
            else ->
                throw IndexOutOfBoundsException("Invalid coordinate $index")
        }
    }

}

data class MutablePoint(var x: Int, var y: Int) {
    // set
    operator fun MutablePoint.set(index: Int, value: Int) {
        when (index) {
            0 -> x = value
            1 -> y = value
            else ->
                throw IndexOutOfBoundsException("Invalid coordinate $index")
        }
    }
}

data class Point2(val x: Int, val y: Int) : Comparable<Point2> {

    // 重载排序运算符compareTo
    override fun compareTo(other: Point2): Int {
        // 这里表示的是先比较x的值，一样的话在比较y的值
        return compareValuesBy(this, other, Point2::x, Point2::y)
    }
}

// in运算符
data class Rectangle(val upperLeft: Point, val lowerRight: Point) {
    operator fun Rectangle.contains(p: Point): Boolean {
        return p.x in upperLeft.x until lowerRight.x &&
                p.y in upperLeft.y until lowerRight.y
    }
}


