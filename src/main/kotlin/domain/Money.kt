package domain

class Money private constructor(val value: Int) {
    companion object {
        private const val MAXIMUM_AMOUNT = 100000
        private const val ERROR_CREATE_COUNT = "돈은 0원 이상 100000원 이하로 보유할 수 있습니다.\n잘못된 값: %d"
        private val AMOUNTS: MutableMap<Int, Money> = mutableMapOf()
        fun from(value: Int): Money {
            require(value in 0..MAXIMUM_AMOUNT) { ERROR_CREATE_COUNT.format(value) }
            return AMOUNTS.getOrPut(value) { Money(value) }
        }
    }
}
