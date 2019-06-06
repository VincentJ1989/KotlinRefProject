package kotlin实战.ch02

/**
 *
 * @author VincentJ
 * @date 2019-06-06
 */
fun main() {
    println("Hello World")

    println(max(1, 2))
    println(max1(3, 4))
    println(max2(5, 6))

    // 不直接初始化的需要显示地指定类型
    // var允许变量改变自己的值，但类型是不能改变的额
    val answer: Int
    answer = 42

    // val对应的是final，虽然val引用自身是不可变得，但其指向的对象可能是可变的
    val languages = arrayListOf("Chinese")
    languages.add("English")

    // 转义
    println("\$")
}

fun max(a: Int, b: Int): Int {
    return if (a > b) a else b
}

fun max1(a: Int, b: Int): Int = if (a > b) a else b

fun max2(a: Int, b: Int) = if (a > b) a else b


fun main2(args: Array<String>) {
    val name = if (args.isNotEmpty()) args[0] else "Bob"
    println("Hello, $name")

    if (args.isNotEmpty()) {
        println("Hello, ${args[0]}")
    }

    println("Hello, ${if (args.isNotEmpty()) args[0] else "someone"}")
}

