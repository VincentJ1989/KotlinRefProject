package kotlin实战.ch08

/**
 * 返回函数的函数
 * @author VincentJ
 * @date 2019-07-21
 */
enum class Delivery { STANDARD, EXPEDITED }

class Order(val itemCount: Int)


fun getShippingCostCalculator(delivery: Delivery): (Order) -> Double {// 声明一个返回函数的函数
    when (delivery) {
        // 返回lambda
        Delivery.STANDARD -> return { order -> 6 + 2.1 * order.itemCount }
        Delivery.EXPEDITED -> return { order -> 1.2 * order.itemCount }
    }
}