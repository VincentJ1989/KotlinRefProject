package kotlin实战.ch04

/**
 *
 * @author VincentJ
 * @date 2019-06-11
 */

fun main() {
    // 伴生对象
    InnerCall.call()

    Car2.newBMWCar(2)
    Car2.newQQCar("QQ")

    Text.fromJSON("")

    val call = MyCall()
    var count = 0
    call.call(object : ICall() {
        override fun hello() {
            count++
        }

        override fun goodbye() {
        }

    })

}