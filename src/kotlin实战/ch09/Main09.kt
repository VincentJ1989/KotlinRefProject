package kotlin实战.ch09

/**
 * 泛型
 * @author VincentJ
 * @date 2019-07-23
 */

fun main() {
    // 实参需要显式声明或者能被编译器推导--以下2者是等价的
    val readers: MutableList<String> = mutableListOf()
    val readers2 = mutableListOf<String>()

    //
    val authors = listOf("Bob", "Kitty")
    val reader = mutableListOf<String>("Ha", "Ne", "Bob")
    readers.filter { it !in reader }

    // inline实化
    println(isA<String>("123"))
    println(isA<String>(123))
}

// 多约束--where
fun <T> enture(seq: T) where T : CharSequence, T : Appendable {
    if (!seq.endsWith('.')) {
        seq.append('.')
    }
}

// 默认上界
class Processor<T> {
    fun process(value: T) {
        value?.hashCode()
    }
}

// 如果确定非空，可以显式地指定为Any
class Processor2<T : Any> {
    fun process(value: T) {
        value.hashCode()
    }
}

//inline实化 类型参数
inline fun <reified T> isA(value: Any) = value is T