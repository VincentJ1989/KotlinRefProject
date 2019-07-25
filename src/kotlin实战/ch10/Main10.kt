package kotlin实战.ch10

/**
 * 注解
 * @author VincentJ
 * @date 2019-07-25
 */
@Deprecated("Use removeAt(index) instead.", ReplaceWith("removeAt(index)"))
fun remove(index: Int) {
}

fun removeAt(index: Int) {}
fun main() {
    // 可以快速切换到我们ReplaceWith的函数
    remove(1)

    // Kotlin中的反射API
    val com = Com("Google")
    println(com.javaClass.kotlin.simpleName)

    // 动态获取com.name
    val kp = Com::name
    println(kp.get(com))

//    var counter = 0
//    val kProperty = ::counter
//    kProperty.setter.call(21)
//    println(kProperty.get())


}


data class Com(var name: String) {

}

// 参数只能是val，不能有其他代码(空实现)
// Java无法使用PROPERTY，需要再指定FIELD放可以
@Target(AnnotationTarget.PROPERTY, AnnotationTarget.FIELD)
annotation class MyAnnotation(val name: String)

