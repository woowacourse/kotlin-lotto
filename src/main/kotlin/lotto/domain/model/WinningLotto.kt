package lotto.domain.model

sealed class WinningLottoCreationResult {
    data class Success(
        val winningLotto: WinningLotto,
    ) : WinningLottoCreationResult()

    sealed class Failure : WinningLottoCreationResult() {
        object BonusNumberDuplicated : Failure()

        object DuplicatedNumbers : Failure()

        object NumberSizeError : Failure()
    }
}

class WinningLotto private constructor(
    private val lottoNumbers: List<LottoNumber>,
    private val bonusNumber: LottoNumber,
) {
    init {
        require(lottoNumbers.size == WINNING_LOTTO_NUMBER_QUANTITY) { throw IllegalArgumentException("NumberSizeError") }
        require(lottoNumbers.distinctBy { it.value }.size == lottoNumbers.size) { throw IllegalArgumentException("DuplicatedNumbers") }
        require(!lottoNumbers.contains(bonusNumber)) { throw IllegalArgumentException("BonusNumberDuplicated") }
    }

    fun findRank(lotto: Lotto): Rank {
        val countOfMatch = lottoNumbers.intersect(lotto.numberList).size
        val bonusMatched = lotto.numberList.contains(bonusNumber)
        return Rank.valueOf(countOfMatch, bonusMatched)
    }

    companion object {
        const val WINNING_LOTTO_NUMBER_QUANTITY = 6

        fun valueOf(numbers: List<LottoNumber>, bonusNumber: LottoNumber): WinningLottoCreationResult {
            return runCatching { WinningLotto(numbers, bonusNumber) }
                .map { WinningLottoCreationResult.Success(it) }
                .getOrElse { exception ->
                    when (exception.message) {
                        "NumberSizeError" -> WinningLottoCreationResult.Failure.NumberSizeError
                        "DuplicatedNumbers" -> WinningLottoCreationResult.Failure.DuplicatedNumbers
                        "BonusNumberDuplicated" -> WinningLottoCreationResult.Failure.BonusNumberDuplicated
                        else -> throw exception
                    }
                }
        }

        fun valueOfOrNull(numbers: List<LottoNumber>, bonusNumber: LottoNumber): WinningLotto? =
            runCatching { WinningLotto(numbers, bonusNumber) }.getOrNull()
    }
}

