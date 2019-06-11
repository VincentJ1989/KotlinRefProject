package kotlin实战.ch05

/**
 * 集合的函数式API
 * @author VincentJ
 * @date 2019-06-11
 */
fun main() {
    val list = listOf(1, 2, 3, 4, 5, 6, 7)
    println(list.filter { it % 3 == 0 })
    println(list.map { it * it })

    val pets = listOf(Pet("A", 1), Pet("B", 2), Pet("C", 2))
    println(pets.all { it.age > 2 })
    println(!pets.all { it.age > 2 })
    println(pets.any { it.age > 2 })

    println(pets.count { it.age >= 2 })
    println(pets.groupBy { it.age })
    println(pets.groupBy(Pet::age))

    val lists = listOf("abc", "def")
    println(lists.flatMap { it.toList() })

    val books = listOf(Book("111", listOf("a", "b")), Book("222", listOf("b", "c")), Book("333", listOf("d", "e")))
    println(books.flatMap(Book::authors).toSet())

    // 序列
    println(books.asSequence().map(Book::title).filter { it.startsWith("2") }.toList())
    println(listOf(1, 2, 3, 4).asSequence().map { it * it }.find { it > 3 })
    // 创建序列2种方式
    listOf(1, 2, 3).asSequence()
    // 表示ongoing1开始，后面每个都在前一个的基础上加1
    val se = generateSequence(0) { it + 1 }
    println(se.takeWhile { it < 4 }.sum())


}

// 带接收者的lambda
// 原始代码
fun alp(): String {
    val result = StringBuilder()
    for (letter in 'A'..'Z') {
        result.append(letter)
    }
    result.append("\n Now I know it")
    return result.toString()
}

// with库函数--其实就是带2参数的函数，一个result，一个lambda
fun alp2(): String {
    val result = StringBuilder()
    return with(result) {
        for (letter in 'A'..'Z') {
            this.append(letter)
        }
        this.append("\n Now I know it")
        this.toString()
    }

}

// 继续简化
fun alp3() = with(StringBuilder()) {
    for (letter in 'A'..'Z') {
        append(letter)
    }
    append("\n Now I know it")
    toString()
}

// 用apply来重构下上面函数
fun alp4() = StringBuilder().apply {
    for (letter in 'A'..'Z') {
        append(letter)
    }
    append("\n Now I know it")
}.toString()


fun creat(): Runnable {
    // 如果返回的是一个函数式接口，不能直接返回lambda，要显示转换一下
    return Runnable { print("Hello Kotlin") }
}

data class Pet(val name: String, val age: Int)
data class Book(val title: String, val authors: List<String>)