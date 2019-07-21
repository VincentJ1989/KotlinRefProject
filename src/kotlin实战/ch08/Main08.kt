package kotlin实战.ch08

import java.io.BufferedReader
import java.io.FileReader

/**
 * 第8章--高阶函数：Lambda作为形参和返回值
 * @author VincentJ
 * @date 2019-07-21
 */
fun main() {
    // 声明函数类型
    val sum = { x: Int, y: Int -> x + y }
    val action = { print(42) }
    // 显示类型声明如下
    val sumOri: (Int, Int) -> Int = { x, y -> x + y }
    val actionOri: () -> Unit = { print(42) }


    // 声明的是一个可空的函数类型的变量
    var canReturnNull: (Int) -> Int? = { null }
    // 声明的是一个返回值可空的函数类型
    var funOrNull: ((Int, Int) -> Int)? = null

    // 调用高阶函数
    twoAndThree { a, b -> a + b }
    twoAndThree { a, b -> a * b }

    // Simple的Filter
    println("ab1c".filter { it in 'a'..'z' })

    // 对比和Java中的调用
    processTheAnswer { i -> i + 1 }

    // 函数类型的参数默认值
    println(listOf("Alpha", "Beta").join2ToString())
    println(listOf("Alpha", "Beta").join2ToString {
        it.toLowerCase()
    })
    println(listOf("Alpha", "Beta").join2ToString(separator = "# ", prefix = "|", postfix = "|") {
        it.toUpperCase()
    })

}

// 定义一个简单的高阶函数
fun twoAndThree(operation: (Int, Int) -> Int) {
    val result = operation(2, 3)
    println("The result is $result")
}

// 实现一个Simple版本的filter
// String:是接收者类型，filter是函数名称，predicate是参数类型，
// Char是作为参数传递的函数的参数类型，Boolean是作为参数传递的函数的返回类型
// String是最后返回的返回类型
fun String.filter(predicate: (Char) -> Boolean): String {
    val sb = StringBuilder()
    for (index in 0 until length) {
        val element = get(index)
        if (predicate(element)) {
            sb.append(element)
        }
    }
    return sb.toString()
}

fun processTheAnswer(f: (Int) -> Int) {
    println(f(42))
}

// 函数类型的参数默认值
fun <T> Collection<T>.join2ToString(
    separator: String = ", ",
    prefix: String = "",
    postfix: String = "",
    // 更加细粒度地进行控制
    transform: (T) -> String = { it.toString() }
): String {
    val result = StringBuilder(prefix)
    for ((index, element) in this.withIndex()) {
        if (index > 0) {
            result.append(separator)
        }
        result.append(transform(element))
    }
    result.append(postfix)
    return result.toString()
}

// 函数类型的参数默认值可为null
fun <T> Collection<T>.join3ToString(
    separator: String = ", ",
    prefix: String = "",
    postfix: String = "",
    // 更加细粒度地进行控制
    transform: ((T) -> String)? = null
): String {
    val result = StringBuilder(prefix)
    for ((index, element) in this.withIndex()) {
        if (index > 0) {
            result.append(separator)
        }
        //使用invoke来处理null
        result.append(transform?.invoke(element) ?: element.toString())
    }
    result.append(postfix)
    return result.toString()
}

// 使用use用来操作可关闭的资源
fun readFirstLineFromFile(path: String): String {
    BufferedReader(FileReader(path)).use { br ->
        return br.readLine()
    }
}

// lambda中返回语句：从一个封闭的函数返回
data class Person(val name: String, val age: Int)

val people = listOf(Person("Alice", 29), Person("Bob", 31))

// 正常一个普通循环写法
fun lookForAlice(people: List<Person>) {
    for (person in people) {
        if (person.name == "Alice") {
            println("Found")
            return
        }
    }
    println("Alice is not found")
}

// for-each版本--也是可以的---非局部返回
fun lookForAlice2(people: List<Person>) {
    people.forEach {
        if (it.name == "Alice") {
            println("Found")
            return
        }
    }

    println("Alice is not found")
}

// 局部返回
fun lookForAlice3(people: List<Person>) {
    people.forEach label@{
        if (it.name == "Alice") {
            println("Found")
            return@label
        }
    }

    println("Alice is not found")
}

// 局部返回--直接使用函数名作为标签
fun lookForAlice4(people: List<Person>) {
    people.forEach {
        if (it.name == "Alice") {
            println("Found")
            return@forEach
        }
    }

    println("Alice is not found")
}

// 使用匿名函数来做局部返回
fun lookForAlice5(people: List<Person>) {
    people.forEach(fun(person) {
        if (person.name == "Alice") {
            return
        }
        println("${person.name} is not found")
    })
}

fun filterPerson(people: List<Person>) {
    people.filter(fun(person): Boolean {
        return person.age < 30
    })
}