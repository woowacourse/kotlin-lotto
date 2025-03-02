package lotto.domain

class Money(
    private val amount: Int,
) {
    init {
        require(amount >= Lotto.PRICE) { ERROR_MESSAGE_NOT_ENOUGH_MONEY }
    }

    operator fun div(other: Int): Int = amount.div(other)

    companion object {
        private const val ERROR_MESSAGE_NOT_ENOUGH_MONEY = "최소 구입 금액은 ${Lotto.PRICE}원입니다."
    }
}
