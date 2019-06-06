package kotlin实战.ch02

import kotlin实战.ch02.Color.*
import java.io.BufferedReader
import java.util.*

/**
 * <li>枚举</li>
 * <li>when</li>
 * <li>循环</li>
 * <li>类型合并</li>
 * <li></li>
 * <li></li>
 * <li></li>
 * <li></li>
 * @author VincentJ
 * @date 2019-06-06
 */

fun main() {
    println(Color2.BLUE.rgb())

    println(getMnemonic(RED))
    println(getMnemonic(GREEN))
    println(getMnemonic(YELLOW))

    // 注意这2个是等价的
    println(mix(RED, YELLOW))
    println(mix(YELLOW, RED))
    // println(mix(RED, BLUE))
    println(mixOptimized(RED, YELLOW))

    testWhile()
    testFor()

    testMap()

}

// Kotlin中的异常
fun testException() {
    val percent = 101
    if (percent !in 0..100) {
        throw IllegalArgumentException("百分比必须在0和100之间：$percent")
    }
    // 和上面是等价的
    val p2 = if (percent in 0..100) percent else throw  IllegalArgumentException("百分比必须在0和100之间：$percent")
}

// try-catch-finally:不必显示地指定可能抛出的异常，可以处理也可以不处理
// try-with-resource被实现为一个库函数了
fun readNumber(reader: BufferedReader): Int? {
    try {
        val line = reader.readLine()
        return Integer.parseInt(line)
    } catch (e: NumberFormatException) {
        return null
    } finally {
        reader.close()
    }
}

fun readNumber2(reader: BufferedReader) {
    val number = try {
        Integer.parseInt(reader.readLine())
    } catch (e: java.lang.NumberFormatException) {
        // 如果想继续后面的代码，可以直接改null
        // null
        return
    }
    println(number)
}

// in和!in
fun isLetter(c: Char) = c in 'a'..'z' || c in 'A'..'Z'

fun isNotNumber(i: Char) = i !in '0'..'9'
fun recognize(c: Char) = when (c) {
    in '0'..'9' -> "是一个数字"
    in 'a'..'z', in 'A'..'Z' -> "是个字母"
    else -> "I donnot known"
}

// 迭代map
fun testMap() {
    // TreeMap按键排序
    val binaryReps = TreeMap<Char, String>()
    for (c in 'A'..'Z') {
        val binary = Integer.toBinaryString(c.toInt())
        binaryReps[c] = binary
    }

    for ((letter, binary) in binaryReps) {
        println("$letter = $binary")
    }
    // 还可以根据索引进行遍历
    val list = arrayListOf("A", "B", "C")
    for ((index, binary) in list.withIndex()) {
        println("$index: $binary")
    }
}

enum class Color {
    RED, ORANGE, YELLOW, GREEN, BLUE, INDIGO, VIOLET
}

enum class Color2(val r: Int, val g: Int, val b: Int) {
    // 注意最后有一个分号(因为后面有函数)
    RED(255, 0, 0),
    ORANGE(255, 165, 0), BLUE(0, 0, 255);

    // 定义方法
    fun rgb() = (r * 256 + g) * 256 + b
}

// 相当于Java中的switch
fun getMnemonic(color: Color) = when (color) {
    RED -> "红的"
    // 可以直接合并
    BLUE, GREEN -> "蓝的或绿的"
    else -> "其他颜色"
}

// 其实when允许使用任意对象--但下面的例子每次都会创建set，这是不好的
fun mix(color1: Color, color2: Color) = when (setOf(color1, color2)) {
    setOf(RED, YELLOW) -> YELLOW
    setOf(YELLOW, BLUE) -> GREEN
    else -> throw Exception("Dirty color")
}

// 不带参数的When--也就是对上面的优化
// 如果没有给when表达式提供参数，分支条件就是布尔表达式
fun mixOptimized(c1: Color, c2: Color) = when {
    (c1 == RED && c2 == YELLOW) || (c1 == YELLOW && c2 == RED) -> YELLOW
    (c1 == YELLOW && c2 == BLUE) || (c1 == BLUE && c2 == YELLOW) -> GREEN
    else -> throw Exception("Dirty color")
}


// 合并类型检查和转换
interface Expr

class Num(val value: Int) : Expr
//
class Sum(val left: Expr, val right: Expr) : Expr

fun eval(e: Expr): Int {
    if (e is Num) {
        // 经过is判断就已经转化为Num了
        return e.value
    }

    if (e is Sum) {
        return eval(e.left) + eval(e.right)
    }

    throw IllegalArgumentException("Unknown expression")
}

// 下面对eval进行重构
fun eval1(e: Expr): Int {
    if (e is Num) {
        return e.value
    } else if (e is Sum) {
        return eval1(e.left) + eval1(e.right)
    } else {
        throw IllegalArgumentException("Unknown expression")
    }
}

fun eval2(e: Expr): Int = when (e) {
    is Num -> e.value
    is Sum -> eval2(e.left) + eval2(e.right)
    else ->
        throw IllegalArgumentException("Unknown expression")
}

// 2.4 while循环和for循环
// while和do-while和Java一样
fun testWhile() {
    var count = 0
    while (count != 6) {
        println("while:${++count}")
    }

    var count2 = 0
    do {
        println("do-while:${++count2}")
    } while (count2 != 5)
}

// for循环只有一种，和for-each一致
fun testFor() {
    val names = arrayListOf("A", "B", "C")
    for (name in names) {
        println("for:$name")
    }

    // 区间：都是闭合的
    for (i in 1..5) {
        println("for都是闭合:$i")
    }
    // 这是不包含5
    for (i in 0 until 5) {
        println("for左闭右开:$i")
    }
    for (i in 10 downTo 0 step 2) {
        println("for倒序:$i")
    }
}