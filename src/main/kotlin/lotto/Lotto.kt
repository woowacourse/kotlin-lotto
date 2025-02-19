package lotto

class Lotto(val numbers: List<LottoNumber>) {
    init {
        require(numbers.size == 6) {
            COUNT_ERROR_MESSAGE
        }

        require(numbers.distinctBy { it.value }.size == numbers.size) {
            DUPLICATE_ERROR_MESSAGE
        }

        require(numbers.zipWithNext().all { (a, b) -> a.value < b.value }) {
            SORT_ERROR_MESSAGE
        }
    }

    fun countMatchingNumber(lotto: Lotto): Int = this.numbers.toSet().intersect(lotto.numbers.toSet()).size

    fun checkBonusNumber(lottoNumber: LottoNumber): Boolean = this.numbers.contains(lottoNumber)

    companion object {
        private const val COUNT_ERROR_MESSAGE = "[ERROR] 로또 번호는 6개여야 합니다."
        private const val DUPLICATE_ERROR_MESSAGE = "[ERROR] 로또 번호는 중복될 수 없습니다."
        private const val SORT_ERROR_MESSAGE = "[ERROR] 로또 번호는 1~45 사이여야 합니다."
    }
}
