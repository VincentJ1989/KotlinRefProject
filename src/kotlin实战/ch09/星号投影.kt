package kotlin实战.ch09

import kotlin.reflect.KClass

/**
 * 星号投影
 * @author VincentJ
 * @date 2019-07-25
 */
// 接口定义成在T上逆变,T 只在in位置使用
interface FieldValidator<in T> {
    fun validate(input: T): Boolean
}

object DefaultStringValidator : FieldValidator<String> {
    override fun validate(input: String): Boolean {
        return input.isEmpty()
    }
}

object DefaultIntValidator : FieldValidator<Int> {
    override fun validate(input: Int): Boolean {
        return input >= 0
    }
}

// 整合2个验证
object Validators {
    private val validators = mutableMapOf<KClass<*>, FieldValidator<*>>()

    // 这样确保正确的键值对
    fun <T : Any> registerValidator(kClass: KClass<T>, fieldValidator: FieldValidator<T>) {
        validators[kClass] = fieldValidator
    }

    @Suppress("UNCHECKED_CAST")
    operator fun <T : Any> get(kClass: KClass<T>): FieldValidator<T> {
        return validators[kClass] as? FieldValidator<T>
            ?: throw IllegalArgumentException("No validator for ${kClass.simpleName}")
    }
}


fun main() {
    Validators.registerValidator(String::class, DefaultStringValidator)
    Validators.registerValidator(Int::class, DefaultIntValidator)

    Validators[String::class].validate("123")
    Validators[Int::class].validate(1)
}