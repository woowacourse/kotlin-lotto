package model

class Amount(val price: Int) {
    constructor(input: String) : this(
        input.toIntOrNull() ?: throw IllegalArgumentException(EXCEPTION_IS_NOT_NUMBER)
    )

    init {
        require(validateIsPositive(price)) { EXCEPTION_IS_NOT_POSITIVE }
        require(validatePriceIsThousand(price)) { EXCEPTION_IS_NOT_THOUSAND_UNIT }
    }

    private fun validatePriceIsThousand(price: Int) = price % 1000 == 0

    private fun validateIsPositive(price: Int) = price > 0

    companion object {
        const val EXCEPTION_IS_NOT_NUMBER = "숫자만 입력하셔야 합니다"
        const val EXCEPTION_IS_NOT_POSITIVE = "양수를 입력하셔야 합니다"
        const val EXCEPTION_IS_NOT_THOUSAND_UNIT = "로또 한 장의 가격은 천원 단위여야 합니다"
    }
}
