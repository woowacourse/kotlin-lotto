package model

class Amount(val money: Int) {
    constructor(input: String) : this(
        input.toIntOrNull() ?: throw IllegalArgumentException(EXCEPTION_IS_NOT_NUMBER),
    )

    init {
        require(validateIsPositive(money)) { EXCEPTION_IS_NOT_POSITIVE }
        require(validatePriceIsThousand(money)) { EXCEPTION_IS_NOT_THOUSAND_UNIT }
    }

    private fun validatePriceIsThousand(money: Int) = money % LOTTO_TICKET_PRICE == 0

    private fun validateIsPositive(money: Int) = money > 0

    companion object {
        const val EXCEPTION_IS_NOT_NUMBER = "숫자만 입력하셔야 합니다"
        const val EXCEPTION_IS_NOT_POSITIVE = "양수를 입력하셔야 합니다"
        const val EXCEPTION_IS_NOT_THOUSAND_UNIT = "로또 한 장의 가격은 천원 단위여야 합니다"
        const val LOTTO_TICKET_PRICE = 1000
    }
}
