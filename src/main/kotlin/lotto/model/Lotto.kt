package lotto.model

class Lotto private constructor(private val numbers: List<LottoNumber>) {
    fun getMatchingCount(otherLotto: Lotto) = otherLotto.numbers.intersect(numbers).size

    fun contains(lottoNumber: LottoNumber) = numbers.contains(lottoNumber)

    override fun toString() = numbers.toString()

    companion object {
        const val SIZE = 6
        const val PRICE = 1000
        private const val INVALID_SIZE = "${SIZE}개의 로또 번호를 입력해 주세요."
        private const val INVALID_DUPLICATION = "중복되지 않는 로또 번호를 입력해 주세요."

        fun create(numbers: List<Int>): Lotto {
            require(numbers.isValidSize()) { INVALID_SIZE }
            require(numbers.isNotDuplicate()) { INVALID_DUPLICATION }
            return Lotto(numbers.sorted().map { LottoNumber(it) })
        }

        private fun List<Int>.isValidSize() = size == SIZE

        private fun List<Int>.isNotDuplicate() = distinct().size == SIZE
    }
}
