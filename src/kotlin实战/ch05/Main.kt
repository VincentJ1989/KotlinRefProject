package kotlin实战.ch05

/**
 *
 * @author VincentJ
 * @date 2019-06-11
 */
fun main() {
    val list = listOf(Person("A", 10), Person("B", 20))
    // 注意下面一步步的省略

    list.maxBy({ p: Person -> p.age })
    // 如果lambda表达式是函数调用的最后一个实参，可以放到括号外
    list.maxBy() { p: Person -> p.age }
    // 如果lambda是函数唯一的实参时，还可以去掉调用代码中的空括号
    list.maxBy { p: Person -> p.age }
    // 和局部变量一样，如果lambda参数的类型可以被推导出来，就不用显示地指定
    list.maxBy { p -> p.age }
    // 使用默认形参it代替命名参数
    list.maxBy { it.age }
    // 当然可以使用成员引用
    list.maxBy(Person::age)


}

data class Person(val name: String, val age: Int)

// 成员引用的使用
val action = { person: Person, message: String -> sendEmail(person, message) }
// 1.可以简化--把lambda委托给sendEmail函数
val nextAction = ::sendEmail

fun sendEmail(person: Person, message: String): String {
    return ""
}
// 2.构造方法引用
val createPerson = ::Person
