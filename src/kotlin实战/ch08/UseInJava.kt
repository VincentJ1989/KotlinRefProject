package kotlin实战.ch08

/**
 *
 * @author VincentJ
 * @date 2019-07-21
 */
class UseInJava {
    fun processTheAnswer(f: (Int) -> Int) {
        println(f(42))
    }
}