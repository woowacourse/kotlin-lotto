package lotto.domain

class Lotto(
    numbers: List<LottoNumber>,
) {
    private val _numbers: Set<LottoNumber> = numbers.toSet()
    val numbers: Set<LottoNumber>
        get() = _numbers.toSet()

    init {
        require(this._numbers.size == SIZE) { ERROR_MESSAGE_INCORRECT_LOTTO_NUMBERS }
        require(this._numbers.size == numbers.size) { ERROR_MESSAGE_LOTTO_NUMBER_MUST_BE_UNIQUE }
    }

    constructor(vararg numbers: Int) : this(numbers.map(LottoNumber::from))

    companion object {
        const val PRICE = 1_000
        const val SIZE = 6

        private const val ERROR_MESSAGE_INCORRECT_LOTTO_NUMBERS = "로또는 ${SIZE}개의 숫자를 가지고 있어야 합니다."
        private const val ERROR_MESSAGE_LOTTO_NUMBER_MUST_BE_UNIQUE = "로또 번호는 중복될 수 없습니다."
    }
}
