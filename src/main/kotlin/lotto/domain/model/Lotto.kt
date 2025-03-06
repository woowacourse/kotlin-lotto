package lotto.domain.model

interface SortStrategy {
    fun sort(numberList: List<LottoNumber>): List<LottoNumber>
}

class RandomSort : SortStrategy {
    override fun sort(numberList: List<LottoNumber>): List<LottoNumber> = numberList.shuffled()
}

sealed class LottoCreationResult {
    data class Success(
        val lotto: Lotto,
    ) : LottoCreationResult()

    sealed class Failure : LottoCreationResult() {
        object NotSorted : Failure()

        object InvalidCount : Failure()

        object DuplicatedNumbers : Failure()
    }
}

class Lotto private constructor(
    val numberList: List<LottoNumber>,
) {
    init {
        require(numberList.sortedBy { it.value } == numberList) { "[ERROR] 로또 번호는 정렬된 상태여야 합니다." }
    }

    companion object {
        const val LOTTO_NUMBER_QUANTITY = 6
        private val LOTTO_NUMBERS: List<LottoNumber> = (1..45).map { LottoNumber.valueOf(it) }

        fun valueOf(numberList: List<LottoNumber>): LottoCreationResult =
            when {
                numberList.size != LOTTO_NUMBER_QUANTITY -> LottoCreationResult.Failure.InvalidCount
                numberList.distinctBy { it.value }.size != numberList.size -> LottoCreationResult.Failure.DuplicatedNumbers
                else -> LottoCreationResult.Success(Lotto(numberList.sortedBy { it.value }))
            }

        fun valueOfOrNull(numberList: List<LottoNumber>): Lotto? = runCatching { Lotto(numberList.sortedBy { it.value }) }.getOrNull()

        fun createRandom(sortStrategy: SortStrategy = RandomSort()): Lotto =
            Lotto(sortStrategy.sort(LOTTO_NUMBERS).take(LOTTO_NUMBER_QUANTITY).sortedBy { it.value })
    }
}
