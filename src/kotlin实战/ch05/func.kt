package kotlin实战.ch05

/**
 *
 * @author VincentJ
 * @date 2019-06-11
 */

fun printMsgWithPrefix(msg: Collection<String>, prefix: String) {
    msg.forEach { print("$prefix $it") }
}