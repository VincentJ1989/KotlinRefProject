package kotlin实战.ch03

/**
 *
 * @author VincentJ
 * @date 2019-06-08
 */
// 相当于public static final
const val UNIX_LINE = "\n"

fun main() {
    val set = hashSetOf(1, 2, 3)
    val list = arrayListOf(4, 5, 6)
    val map = hashMapOf(1 to "one", 2 to "two")

    println(set.javaClass)
    println(set.last())
    println(list)
    println(list.joinToString("-"))
    println(list.max())

    println("Kotlin".lastChar())

    // 扩展运算符--*
    val testArray = arrayOf("A", "B")
    test(*testArray)

    // 结构声明
    val (name, age) = "a" to 12

    // 正则--支持.的分割
    // 以下是显示的创建一个正则表达式
    println("12.345-6.A".split("\\.|-".toRegex()))
    // 指定多个分隔符
    println("12.345-6.A".split(".", "-"))
    val bookPath = "/Users/yole/kotlin-book/chapter.doc"
    parse1(bookPath)
    parse2(bookPath)

    // 局部函数和扩展
}

/**
 * 使用正则解析
 */
fun parse2(path: String) {
    val regex = """(.+)/(.+)\.(.+)""".toRegex()
    val matchResult = regex.matchEntire(path)
    if (matchResult != null) {
        val (directory, fileName, ext) = matchResult.destructured
        println("Dir:$directory, fileName:$fileName, ext:$ext")
    }
}

/**
 * 使用String的扩展函数解析
 */
fun parse1(path: String) {
    val directory = path.substringBeforeLast("/")
    val fullName = path.substringAfterLast("/")

    val fileName = fullName.substringBeforeLast(".")
    val ext = fullName.substringAfterLast(".")

    println("Dir:$directory, name:$fullName, fileName:$fileName, ext:$ext")
}


fun test(vararg arg: String) {
    arg.forEach(::println)
}