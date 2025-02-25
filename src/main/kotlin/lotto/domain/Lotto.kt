package lotto.domain

class Lotto(
    val numbers: List<LottoNumber>,
) {
    init {
        require(numbers.size == SIZE) { ERROR_MESSAGE_INCORRECT_LOTTO_NUMBERS }
        require(numbers.size == numbers.toSet().size) { ERROR_MESSAGE_LOTTO_NUMBERS_MUST_BE_UNIQUE }
    }

    constructor(vararg numbers: Int) : this(numbers.map(::LottoNumber))

    companion object {
        const val PRICE = 1_000
        const val SIZE = 6

        private const val ERROR_MESSAGE_INCORRECT_LOTTO_NUMBERS = "로또는 ${SIZE}개의 숫자를 가지고 있어야 합니다."
        private const val ERROR_MESSAGE_LOTTO_NUMBERS_MUST_BE_UNIQUE = "로또 번호는 중복될 수 없습니다."
    }
}
