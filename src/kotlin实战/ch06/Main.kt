package kotlin实战.ch06

/**
 *
 * @author VincentJ
 * @date 2019-06-13
 */
fun main() {
    // 安全调用运算符?.
    printAllCap("abc")
    printAllCap(null)
    // Elvis运算符?:--可以当做是前者加强版，可以指定为空时的默认返回值
    foo("ABC")
    foo(null)
    // ?.和?:的一起使用
    len("ABC")
    len(null)
    // Elvis运算符?:和throw
    // getName(Note("AA", null))

    // 安全转换符as?--看Note的equals函数

    // 非空断言
    // ignoreNulls(null)

    // let函数
    val email: String? = null
    email?.let {
        sendEmail(it)
    }

    // lateinit延迟初始化的属性--Service

    // 类型参数可空性
    printHashCode(null)
    // printHashCode2(null)

    // 可空性和Java
    // yellAtSafe(Person(null))
    yellAtSafe2(Person(null))

    showProgress(-23)
    showProgress(123)

    // 数字转换需要显示地转换
    val i = 1
    val longNum = i.toLong()
    // i是不在list中的，因为没有显示地转换
    val list = listOf(1L, 2L, 3L)
    // 这样才能编译
    println(i.toLong() in list)

    //Nothing
    // val pric = Note("A", null).price ?: fail("No Price")

    // 集合可空

    // 基本类型数组
    val letter = Array<String>(26) { i -> ('a' + i).toString() }
    val types = listOf("a", "b", "c")
    println("%s/%s/%s".format(*types.toTypedArray()))

    val fiveZero = IntArray(5)
    val fiveZeros = intArrayOf(0, 0, 0, 0, 0)
    val intSquare = IntArray(5) { i -> i * i }


}

fun forEachOne(strs: Array<String>) {
    strs.forEachIndexed { index, s ->
        println("$index->$s")
    }
}

fun addValidNumber(numbers: List<Int?>) {
    // 取出其中非空的元素
    val validNumvers = numbers.firstOrNull()
    // 其他操作
}

fun fail(msg: String): Nothing {
    throw Exception(msg)
}


fun showProgress(progress: Int) {
    val p = progress.coerceIn(0, 100)
    println(p)
}

fun yellAtSafe2(p: Person) {
    println((p.name ?: "Anyone").toUpperCase())
}

fun yellAtSafe(p: Person) {
    println(p.name.toUpperCase())
}

// 因为T被推导成Any，所以不加？可以是允许为null
fun <T> printHashCode(t: T) {
    println(t?.hashCode())
}

// 要是类型参数非空，要制定一个非空的上界
fun <T : Any> printHashCode2(t: T) {
    println(t.hashCode())
}

class Service() {
    private lateinit var name: String
    fun init(pName: String) {
        this.name = pName
    }
}

fun sendEmail(email: String) {
    println(email)
}

fun ignoreNulls(s: String?) {
    val sNotNull = s!!
    println(sNotNull)
}

fun getName(note: Note) {
    val name = note?.price?.value ?: throw IllegalArgumentException("No Price")
    println(name)
}

data class Note(val name: String, val price: Price?) {

    override fun equals(other: Any?): Boolean {
        // 安全转换符和Elvis配合使用
        val otherNote = other as? Note ?: return false
        return super.equals(other)
    }
}

data class Price(val value: String)

fun len(str: String?) {
    println(str?.length ?: 0)
}

fun foo(str: String?) {
    println(str ?: "default")
}

fun printAllCap(str: String?) {
    println(str?.toUpperCase())
}