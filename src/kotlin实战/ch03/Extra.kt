package kotlin实战.ch03


/**
 * 常用的扩展--一般用一个扩展文件来维护同类的扩展函数--扩展函数是静态函数
 * @author VincentJ
 * @date 2019-06-08
 */

// 外部引用的时候可以通过as关键字修改函数名称，这样可以避免重名
fun String.lastChar(): Char = this.get(this.length - 1)

// 可以直接访问被扩展的String的get()函数和length字段
fun String.lastChar2(): Char = get(length - 1)

// 扩展属性
var StringBuilder.lastChar: Char
    get() = get(length - 1)
    set(value) = setCharAt(length - 1, value)
//    set(value) {
//        setCharAt(length - 1, value)
//    }