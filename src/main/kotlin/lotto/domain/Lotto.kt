package lotto.domain

class Lotto(
    val numbers: Set<LottoNumber>,
) {
    init {
        require(numbers.size == LOTTO_SIZE) { ERROR_MESSAGE_LOTTO_NEEDS_6_DIFFERENT_NUMBERS }
    }

    constructor(vararg numbers: Int): this(numbers.map { number: Int -> LottoNumber(number) }.toSet())

    companion object {
        const val LOTTO_PRICE = 1_000
        const val LOTTO_NUMBER_MIN = 1
        const val LOTTO_NUMBER_MAX = 45

        const val LOTTO_SIZE = 6
        private const val ERROR_MESSAGE_LOTTO_NEEDS_6_DIFFERENT_NUMBERS = "로또는 6개의 중복되지 않는 숫자를 갖고 있어야 합니다."
    }
}
