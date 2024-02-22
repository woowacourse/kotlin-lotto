package model

class Amount(val money: Int) {
    constructor(input: String) : this(
        input.toIntOrNull() ?: throw IllegalArgumentException(EXCEPTION_IS_NOT_NUMBER),
    )

    init {
        require(validatePriceBound(money)) { EXCEPTION_LESS_THAN_THOUSAND }
    }

    private fun validatePriceBound(money: Int) = money >= LOTTO_TICKET_PRICE

    companion object {
        const val EXCEPTION_IS_NOT_NUMBER = "숫자만 입력하셔야 합니다"
        const val LOTTO_TICKET_PRICE = 1000
        const val EXCEPTION_LESS_THAN_THOUSAND = "로또 구입 금액은 ${LOTTO_TICKET_PRICE}원 이상이어야 합니다"
    }
}
