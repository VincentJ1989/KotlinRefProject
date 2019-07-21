package kotlin实战.ch08

import java.util.concurrent.locks.Lock

/**
 * 内联函数
 * @author VincentJ
 * @date 2019-07-21
 */
inline fun <T> synchroized(lock: Lock, action: () -> T): T {
    lock.lock()
    try {
        return action()
    } finally {
        lock.unlock()
    }
}