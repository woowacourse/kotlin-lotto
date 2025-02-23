package lotto.model

class Lotto(
    val numbers: List<LottoNumber>,
) {
    init {
        validateLottoNumbersCount(numbers)
        validateLottoNumbersDuplicate(numbers)
    }

    fun validateLottoNumbersCount(numbers: List<LottoNumber>) {
        require(numbers.size == LOTTO_NUMBER_SIZE) {
            "[ERROR] 로또는 ${LOTTO_NUMBER_SIZE}개의 번호만 가질 수 있습니다."
        }
    }

    fun validateLottoNumbersDuplicate(numbers: List<LottoNumber>) {
        val lottoNumbers = numbers.map { it.number }
        require(lottoNumbers.size == lottoNumbers.toSet().size) {
            "[ERROR] 로또 번호는 중복될 수 없습니다."
        }
    }

    companion object {
        const val LOTTO_NUMBER_SIZE = 6
        const val LOTTO_NUMBER_MIN_RANGE = 1
        const val LOTTO_NUMBER_MAX_RANGE = 45

        fun from(lottoNumbers: List<Int>): Lotto = Lotto(lottoNumbers.map { LottoNumber(it) })
    }
}
