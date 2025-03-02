package lotto.model

class Lotto(
    val numbers: List<LottoNumber>,
) {
    init {
        validateLottoNumbersCount()
        validateLottoNumbersDuplicate()
    }

    fun countMatchNumbers(other: Lotto): Int = numbers.count { number -> number in other.numbers }

    fun contains(number: LottoNumber): Boolean = numbers.contains(number)

    private fun validateLottoNumbersCount() {
        require(numbers.size == LOTTO_NUMBER_SIZE) {
            "[ERROR] 로또는 ${LOTTO_NUMBER_SIZE}개의 번호만 가질 수 있습니다."
        }
    }

    private fun validateLottoNumbersDuplicate() {
        require(numbers.distinct().size == numbers.size) {
            "[ERROR] 로또 번호는 중복될 수 없습니다."
        }
    }

    companion object {
        const val LOTTO_NUMBER_SIZE = 6

        fun from(numbers: List<Int>): Lotto = Lotto(numbers.map { number -> LottoNumber.from(number) })
    }
}
