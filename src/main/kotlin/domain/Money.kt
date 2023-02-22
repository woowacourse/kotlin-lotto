package domain

data class Money(val amount: Int) {

    init {
        require(amount in MINIMUM_AMOUNT..MAXIMUM_AMOUNT) { ERROR_MONEY_AMOUNT }
    }

    operator fun div(money: Money): Int = this.amount / money.amount
    operator fun times(number: Int) = Money(this.amount * number)
    operator fun minus(money: Money) = Money(this.amount - money.amount)

    companion object {
        private const val MINIMUM_AMOUNT = 0
        private const val MAXIMUM_AMOUNT = 100000
        private const val MINIMUM_AMOUNT_LENGTH = 1
        private const val MAXIMUM_AMOUNT_LENGTH = 6
        private const val ERROR_MONEY_AMOUNT = "돈은 ${MINIMUM_AMOUNT}원 이상 ${MAXIMUM_AMOUNT}원 이하의 값으로만 존재할 수 있습니다."
        private const val ERROR_MONEY_STRING =
            "돈을 문자열로 생성할 때 모든 문자는 숫자이고 길이는 $MINIMUM_AMOUNT_LENGTH 이상 $MAXIMUM_AMOUNT_LENGTH 이하여야 합니다."
    }
}
