package kotlin实战.ch04

import java.io.File

/**
 *
 * @author VincentJ
 * @date 2019-06-10
 */
interface Clickable {
    fun click()
    /**
     * 默认实现的函数
     */
    fun open() = print("Open Func")
}

interface ClickOther {
    fun open() = print("Open Func")
}

open class Button : Clickable, ClickOther {

    // 如果这里没有对click显示实现，会编译报错
    override fun click() = println("Interface")

    override fun open() {
        super<Clickable>.open()
        super<ClickOther>.open()
    }
}

class SmallBtn : Button() {
    override fun click() {
    }
}

// 内部嵌套类
class InnerClass {
    inner class ChildClass() {
        fun getOutRefClass() = this@InnerClass
    }
}

// sealed对子类做出严格限制--说白了就是在后面那个when语句不用来个else处理不是man和women的逻辑
sealed class Person {
    class Man : Person()
    class Woman : Person()
}

fun checkGende(person: Person) = when (person) {
    is Person.Man -> "is Man"
    is Person.Woman -> "is Women"
}

// 3中构造--一般也是使用最后最简单的方式构造
class A constructor(name: String) {
    val name: String

    init {
        this.name = name
    }
}

class B(pName: String) {
    val name = pName
}

class C(val name: String)

// 调用父类的构造函数
open class View {
    constructor(name: String)
    constructor(name: String, age: Int)
}

class MyView : View {
    //  constructor(name: String) : super(name)
    //  constructor(name: String, age: Int) : super(name, age)

    constructor(name: String) : this(name, -1)
    constructor(name: String, age: Int) : super(name, age)

}

// 在接口中声明属性
interface User {
    // 这个属性必须重写
    val nickName: String
    // 这个属性可以被继承--其实类比接口函数就行
    val firstName: String
        get() = nickName.substringBefore("-")
}


class PrivateUser(override val nickName: String) : User
class SubscribingUser(val email: String) : User {
    override val nickName: String
        get() = email.substringBefore('@')// 自定义get
}

class FacebookUser(val accountId: Int) : User {
    override val nickName: String
        get() = getFacebooName(accountId)

    private fun getFacebooName(accountId: Int) = "haha"
}

class Info(val name: String) {
    var address: String = "default"
        set(value) {
            println("$field--$value")
            field = value
        }

    var email: String = "abc"
        set(value) = if (value == null) {
            field = "孔圣人"
        } else {
            field = value
        }
}

// 修改访问器的可见性
class Teach {
    // 这样外部就不能修改id
    var id: Int = 0
        private set
}

// 数据类和类委托
data class Client(val name: String, val ip: String)

// 使用by来进行类委托，进而减少样板代码
class DelegatingCollections<T>(innerList: Collection<T> = ArrayList<T>()) : Collection<T> by innerList {}

// object的三个应用场景
// 场景1：单例
object DBManager {
    fun openDB() {}
    fun closeDB() {}
}

object CaseIgnoreComparator : Comparator<File> {
    override fun compare(o1: File?, o2: File?): Int {
        return o1!!.path.compareTo(o2!!.path, ignoreCase = true)
    }
}

data class Person2(val name: String) {
    object MyComparator : Comparator<Person2> {
        override fun compare(o1: Person2?, o2: Person2?) = o1!!.name.compareTo(o2!!.name)
    }
}

// 场景2：伴生对象--工厂方法和静态成员的地盘
// Kotlin不能有静态成员，java的static可不是Kotlin的一部分
// 其实有点像类中的静态函数
class InnerCall {
    companion object {
        fun call() {
            println("innerCall")
        }
    }
}

// 使用伴生+工厂处理
class Car {
    val name: String

    constructor(name1: String) {
        name = name1
    }

    constructor(id: Int) {
        name = id.toString()
    }
}

//主构造私有+工厂
class Car2 private constructor(val name: String) {
    companion object {
        fun newQQCar(name1: String) = Car2(name1)
        fun newBMWCar(id: Int) = Car2(id.toString())
    }
}

// 作为普通对象使用的伴生对象
class Book(val name: String) {
    companion object Loader {
        fun fromJson(jsonText: String): Book {
            // 这里做序列化的处理
            return Book("")
        }
    }
}

// 在伴生对象中实现接口
interface IJsonFactory<T> {
    fun fromJSON(jsonText: String): T
}

class Text(val content: String) {
    companion object : IJsonFactory<Text> {
        override fun fromJSON(jsonText: String): Text {
            TODO("not implemented")
        }
    }
}

// 伴生对象的扩展
class House {
    companion object {
        // 声明一个空的伴生对象
    }
}

fun House.Companion.fromJson(json: String): House {
    // 这里处理序列化
    return House()
}
// 场景3：改变写法的匿名内部类--注意这个可不是单例
abstract class ICall{
    abstract fun hello()
    abstract fun goodbye()
}

class MyCall{
    fun call(ca:ICall){

    }
}
