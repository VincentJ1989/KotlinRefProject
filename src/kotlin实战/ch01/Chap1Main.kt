package kotlin实战.ch01

/**
 *
 * @author VincentJ
 * @date 2019-06-06
 */
fun main() {
    val persons = listOf(Person("Alice"), Person("Bob", 20))
    val oldest = persons.maxBy { it.age ?: 0 }
    println(oldest)

    if (oldest?.name is String) {
        print(oldest.name)
    }
}