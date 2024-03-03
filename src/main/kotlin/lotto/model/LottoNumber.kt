package lotto.model

@JvmInline
value class LottoNumber(val number: Int) {
    init {
        require(number in LOTTO_NUM_RANGE) { EXCEPTION_NUM_RANGE }
    }

    constructor(number: Int, winningLotto: Lotto) : this(number) {
        val winningLottoNumbers = winningLotto.numbers.map { it.number }
        require(number !in winningLottoNumbers) { EXCEPTION_DUPLICATE_BONUS_NUMBER_FORMAT }
    }

    companion object {
        private const val MIN_LOTTO_NUMBER = 1
        private const val MAX_LOTTO_NUMBER = 45
        private const val EXCEPTION_NUM_RANGE = "로또 번호는 ${MIN_LOTTO_NUMBER}부터 ${MAX_LOTTO_NUMBER}까지만 가능합니다."
        private val LOTTO_NUM_RANGE = MIN_LOTTO_NUMBER..MAX_LOTTO_NUMBER
        private const val EXCEPTION_DUPLICATE_BONUS_NUMBER_FORMAT = "보너스 번호는 우승 로또 번호와 중복될 수 없습니다."
    }
}
