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
        object InvalidCount : Failure()

        object DuplicatedNumbers : Failure()
    }
}

class Lotto private constructor(
    val numberList: List<LottoNumber>,
) {
    init {
        require(numberList.size == LOTTO_NUMBER_QUANTITY) { "[ERROR] 로또 번호는 ${LOTTO_NUMBER_QUANTITY}개여야 합니다." }
        require(numberList.distinctBy { it.value }.size == numberList.size) { "[ERROR] 중복된 로또 번호가 있습니다." }
    }

    companion object {
        const val LOTTO_NUMBER_QUANTITY = 6
        private val LOTTO_NUMBERS: List<LottoNumber> = (1..45).map { LottoNumber.valueOf(it) }

        fun valueOf(numberList: List<LottoNumber>): LottoCreationResult =
            runCatching { Lotto(numberList.sortedBy { it.value }) }
                .fold(
                    onSuccess = { LottoCreationResult.Success(it) },
                    onFailure = { throwable ->
                        when (throwable.message) {
                            "[ERROR] 로또 번호는 ${LOTTO_NUMBER_QUANTITY}개여야 합니다." -> LottoCreationResult.Failure.InvalidCount
                            "[ERROR] 중복된 로또 번호가 있습니다." -> LottoCreationResult.Failure.DuplicatedNumbers
                            else -> LottoCreationResult.Failure.InvalidCount
                        }
                    },
                )

        fun createRandom(sortStrategy: SortStrategy = RandomSort()): Lotto =
            Lotto(sortStrategy.sort(LOTTO_NUMBERS).take(LOTTO_NUMBER_QUANTITY).sortedBy { it.value })
    }
}
