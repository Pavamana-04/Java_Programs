package java_example

internal interface Inface1 {
    fun add(a: Int)
    fun sub()
    fun multi()
    fun test()
}

internal class Child1 : Inface1 {
    override fun add(a: Int) {
        println("Add: " + a)
    }

    override fun sub() {
        println("Subtraction")
    }

    override fun multi() {
        println("Multiplication")
    }

    override fun test() {
        println("Test method")
    }
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val obj = Child1()
            obj.add(10)
            obj.sub()
            obj.multi()
            obj.test()
        }
    }
}