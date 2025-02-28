package lotto

interface SortStrategy {
    fun sort(numberList: List<LottoNumber>): List<LottoNumber>
}

class RandomSort : SortStrategy {
    override fun sort(numberList: List<LottoNumber>): List<LottoNumber> = numberList.shuffled()
}

class Lotto private constructor(
    val numberList: List<LottoNumber>,
) {
    init {
        require(numberList.sortedBy { it.value } == numberList) { LOTTO_NOT_SORTED }
    }

    companion object {
        const val LOTTO_NUMBER_QUANTITY = 6
        const val LOTTO_NOT_SORTED = "[ERROR] 로또 번호가 정렬되지 않았습니다."
        const val NUMBER_COUNT_ERROR = "[ERROR] 로또 번호 갯수가 ${LOTTO_NUMBER_QUANTITY}가 아닙니다"
        const val NUMBER_DUPLICATE_ERROR = "[ERROR] 로또 번호가 중복됩니다."
        private val LOTTO_NUMBERS: List<LottoNumber> = (1..45).map { it -> LottoNumber.valueOf(it) }

        fun valueOf(numberList: List<LottoNumber>): Lotto {
            require(numberList.size == LOTTO_NUMBER_QUANTITY) { NUMBER_COUNT_ERROR }
            require(numberList.distinctBy { it.value }.size == numberList.size) { NUMBER_DUPLICATE_ERROR }
            return Lotto(numberList)
        }

        fun createOrNull(numberList: List<LottoNumber>): Lotto? =
            when {
                numberList.size != LOTTO_NUMBER_QUANTITY -> null
                numberList.distinctBy { it.value }.size != numberList.size -> null
                else -> Lotto(numberList)
            }

        fun createRandom(sortStrategy: SortStrategy = RandomSort()): Lotto =
            Lotto(
                sortStrategy.sort(LOTTO_NUMBERS).slice(0..5).sortedBy {
                    it.value
                },
            )
    }
}
