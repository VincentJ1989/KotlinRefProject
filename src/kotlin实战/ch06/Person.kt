package kotlin实战.ch06

/**
 *
 * @author VincentJ
 * @date 2019-06-13
 */
data class Person2(val name: String, val age: Int? = null) {
    // 因为上面使用Int?可空，所以这里的判断不能简单的直接对比2个age值，需要判空
    fun isOlderThan(other: Person2): Boolean? {
        if (age == null || other.age == null) {
            return null
        }
        return age > other.age
    }
}