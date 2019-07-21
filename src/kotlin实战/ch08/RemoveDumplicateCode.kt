package kotlin实战.ch08

/**
 * 通过lambda去除重复代码
 * @author VincentJ
 * @date 2019-07-21
 */
enum class OS { WINDOWS, MAC, IOS, ANDROID }

data class SiteVisit(val path: String, val duration: Double, val os: OS)

val log = listOf(
    SiteVisit("/", 34.0, OS.WINDOWS),
    SiteVisit("/", 22.0, OS.MAC),
    SiteVisit("/login", 12.0, OS.WINDOWS),
    SiteVisit("/signup", 8.0, OS.IOS),
    SiteVisit("/", 16.3, OS.ANDROID)
)

// 需求1： 统计来自windows机器的平均访问时间
val averageWindowsDuration = log
    .filter {
        it.os == OS.WINDOWS
    }
    .map(SiteVisit::duration)
    .average()

// 需求2：除了windows还有MAC
fun List<SiteVisit>.averageDurationFor(os: OS) =
    filter { it.os == os }.map(SiteVisit::duration).average()


// 需求3：要统计移动平台
val averageMobileDuration = log
    .filter { it.os in setOf(OS.ANDROID, OS.IOS) }
    .map(SiteVisit::duration)
    .average()

// 可是如果后面又要获取类似"来自IOS平台对注册页访问的平均时间"这类需求呢?
// 使用高阶函数，用函数类型将需要的条件抽取到一个参数中
fun List<SiteVisit>.averageDurationFor(predicate: (SiteVisit) -> Boolean) =
    filter(predicate).map(SiteVisit::duration).average()


fun main() {
    println(averageWindowsDuration)

    println(log.averageDurationFor(OS.WINDOWS))
    println(log.averageDurationFor(OS.MAC))

    println(averageMobileDuration)

    // 高价函数简化后
    // 获取移动平台的
    println(log.averageDurationFor { it.os in setOf(OS.ANDROID, OS.IOS) })
    // 获取IOS注册页面访问时间
    println(log.averageDurationFor { it.os == OS.IOS && it.path == "/signup" })
}
