package lotto.model

class Lotto(
    val numbers: Set<LottoNumber>,
) {
    init {
        validateLottoNumbersCount(numbers)
    }

    fun validateLottoNumbersCount(numbers: Set<LottoNumber>) {
        require(numbers.size == LOTTO_NUMBER_SIZE) {
            "[ERROR] 로또는 ${LOTTO_NUMBER_SIZE}개의 번호만 가질 수 있습니다."
        }
    }

    companion object {
        const val LOTTO_NUMBER_SIZE = 6
        const val LOTTO_NUMBER_MIN_RANGE = 1
        const val LOTTO_NUMBER_MAX_RANGE = 45

        fun from(lottoNumbers: Set<Int>): Lotto = Lotto(lottoNumbers.map { LottoNumber(it) }.toSet())
    }
}
