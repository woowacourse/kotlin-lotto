package lotto.model

class Lotto private constructor(
    val numbers: List<LottoNumber>,
) {
    init {
        validateLottoNumbersCount(numbers)
        validateLottoNumbersDuplicate(numbers)
    }

    private fun validateLottoNumbersCount(numbers: List<LottoNumber>) {
        require(numbers.size == LOTTO_NUMBER_SIZE) {
            "[ERROR] 로또는 ${LOTTO_NUMBER_SIZE}개의 번호만 가질 수 있습니다."
        }
    }

    private fun validateLottoNumbersDuplicate(lottoNumbers: List<LottoNumber>) {
        val uniqueNumbers = lottoNumbers.map { it.number }.toSet()
        require(uniqueNumbers.size == lottoNumbers.size) {
            "[ERROR] 로또 번호는 중복될 수 없습니다."
        }
    }

    companion object {
        const val LOTTO_NUMBER_SIZE = 6

        fun from(numbers: List<Int>): Lotto = Lotto(numbers.map { number -> LottoNumber.from(number) })
    }
}
