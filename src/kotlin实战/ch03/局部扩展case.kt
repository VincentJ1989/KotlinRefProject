package kotlin实战.ch03

/**
 * 局部扩展前的类
 * @author VincentJ
 * @date 2019-06-08
 */
class User1(val id: Int, val name: String, val address: String) {
    fun saveUser(user1: User1) {
        if (user1.name.isEmpty()) {
            throw IllegalArgumentException("Cann't save user ${user1.id}:empty Name")
        }
        if (user1.address.isEmpty()) {
            throw IllegalArgumentException("Cann't save user ${user1.id}:empty Address")
        }
    }
}

/**
 * 局部函数简化代码
 * @author VincentJ
 * @date 2019-06-08
 */
class User2(val id: Int, val name: String, val address: String) {
    fun saveUser(user2: User2) {

        fun validate(value: String, fieldName: String) {
            if (value.isEmpty()) {
                throw IllegalArgumentException("Cann't save user ${user2.id}:empty ${fieldName}")
            }
        }

        validate(user2.name, "Name")
        validate(user2.address, "Address")
    }
}

// 再进一步提取到扩展函数
class User3(val id: Int, val name: String, val address: String)

fun User3.validateBeforSave() {
    fun validate(value: String, fieldName: String) {
        if (value.isEmpty()) {
            throw IllegalArgumentException("Cann't save user ${this.id}:empty ${fieldName}")
        }
    }

    validate(this.name, "Name")
    validate(this.address, "Address")
}