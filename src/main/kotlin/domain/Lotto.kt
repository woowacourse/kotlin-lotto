package domain

data class Lotto(private val numbers: List<LottoNumber>) {

    init {
        require(numbers.size == NUMBER_SIZE) { ERROR_NUMBER_SIZE.format(numbers.size) }
        require(numbers.isNotDuplicated()) { ERROR_NUMBER_DUPLICATED.format(numbers) }
        require(numbers.sortedBy { it.number } == numbers) { ERROR_NUMBER_SEQUENCE }
    }

    private fun List<LottoNumber>.isNotDuplicated(): Boolean = this.distinct().size == this.size
    fun contains(lottoNumber: LottoNumber): Boolean = numbers.contains(lottoNumber)
    fun count(predicate: (LottoNumber) -> Boolean): Int = numbers.count { predicate(it) }

    companion object {
        const val NUMBER_SIZE = 6
        private const val ERROR_NUMBER_SIZE = "로또 번호의 개수는 ${NUMBER_SIZE}개여야 합니다. \n입력된 로또 번호 개수: %d"
        private const val ERROR_NUMBER_DUPLICATED = "로또 번호는 중복되어선 안됩니다. \n입력된 로또 번호: %s"
        private const val ERROR_NUMBER_SEQUENCE = "로또 번호는 정렬되어야 합니다."
        fun create(numbers: List<Int>): Lotto = Lotto(numbers.map { LottoNumber.valueOf(it) })
    }
}
