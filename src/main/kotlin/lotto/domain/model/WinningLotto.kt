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
    fun findRank(lotto: Lotto): Rank {
        val countOfMatch = lottoNumbers.intersect(lotto.numberList).size
        val bonusMatched = lotto.numberList.contains(bonusNumber)
        return Rank.valueOf(countOfMatch, bonusMatched)
    }

    companion object {
        const val WINNING_LOTTO_NUMBER_QUANTITY = 6

        fun create(
            numbers: List<LottoNumber>,
            bonusNumber: LottoNumber,
        ): WinningLottoCreationResult =
            when {
                numbers.size != WINNING_LOTTO_NUMBER_QUANTITY -> WinningLottoCreationResult.Failure.NumberSizeError
                numbers.distinctBy { it.value }.size != numbers.size -> WinningLottoCreationResult.Failure.DuplicatedNumbers
                numbers.contains(bonusNumber) -> WinningLottoCreationResult.Failure.BonusNumberDuplicated
                else -> WinningLottoCreationResult.Success(WinningLotto(numbers, bonusNumber))
            }
    }
}
