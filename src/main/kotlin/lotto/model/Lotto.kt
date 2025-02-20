package lotto.model

class Lotto(val numbers: List<LottoNumber>) {
    init {
        require(numbers.size == LOTTO_NUMBER_COUNT) {
            COUNT_ERROR_MESSAGE
        }

        require(numbers.distinct().size == numbers.size) {
            DUPLICATE_ERROR_MESSAGE
        }
    }

    fun countMatchingNumber(lotto: Lotto): Int = this.numbers.count { number -> lotto.numbers.contains(number) }

    fun checkBonusNumber(lottoNumber: LottoNumber): Boolean = this.numbers.contains(lottoNumber)

    companion object {
        private const val LOTTO_NUMBER_COUNT = 6
        private const val COUNT_ERROR_MESSAGE = "[ERROR] 로또 번호는 6개여야 합니다."
        private const val DUPLICATE_ERROR_MESSAGE = "[ERROR] 로또 번호는 중복될 수 없습니다."
    }
}
