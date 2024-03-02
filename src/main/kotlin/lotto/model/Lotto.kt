package lotto.model

class Lotto private constructor(val numbers: Set<LottoNumber>) {
    init {
        require(numbers.isNotDuplicate()) { INVALID_DUPLICATION }
    }

    private fun Set<LottoNumber>.isNotDuplicate() = size == SIZE

    fun getMatchingCount(otherLotto: Lotto) = otherLotto.numbers.intersect(numbers).size

    fun contains(lottoNumber: LottoNumber) = numbers.contains(lottoNumber)

    companion object {
        const val SIZE = 6
        const val PRICE = 1000
        private const val INVALID_SIZE = "${SIZE}개의 로또 번호를 입력해 주세요."
        private const val INVALID_DUPLICATION = "중복되지 않는 로또 번호를 입력해 주세요."

        fun create(numbers: List<Int>): Lotto {
            require(numbers.isValidSize()) { INVALID_SIZE }
            return Lotto(numbers.sorted().map { LottoNumber.from(it) }.toSet())
        }

        private fun List<Int>.isValidSize() = size == SIZE
    }
}
