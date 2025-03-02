package lotto.domain.model

sealed class WinningNumbersResult {
    fun getSuccessOrThrow(): WinningNumbers {
        require(this is Success)
        return this.winningNumbers
    }

    data class Success(val winningNumbers: WinningNumbers) : WinningNumbersResult()

    data class InvalidHasBonusNumber(val lottoNumbers: List<Int>, val bonusNumber: Int) : WinningNumbersResult()
}

class WinningNumbers private constructor(private val winningLotto: Lotto, private val bonusNumber: LottoNumber) {
    fun calculateLottoRanks(lottos: Lottos): LottoYieldCalculator {
        val purchaseLottoRanks = getPurchaseLottoRanks(lottos)
        return LottoYieldCalculator(LottoRank.entries.associateWith { rank -> getLottoRankCount(rank, purchaseLottoRanks) })
    }

    private fun getLottoRankCount(
        rank: LottoRank,
        purchaseLottoRanks: List<LottoRank>,
    ): Int {
        return purchaseLottoRanks.count { purchaseLottoRank -> purchaseLottoRank == rank }
    }

    private fun getPurchaseLottoRanks(purchaseLotto: Lottos): List<LottoRank> {
        val lottoRank = LottoRank
        return purchaseLotto.lottos.map { lotto ->
            lottoRank.calculate(
                lotto.getSameNumberCount(winningLotto),
                lotto.hasLottoNumber(bonusNumber),
            )
        }
    }

    companion object {
        fun from(
            numbers: Lotto,
            bonusNumber: LottoNumber,
        ): WinningNumbersResult {
            if (numbers.hasLottoNumber(bonusNumber)) {
                return WinningNumbersResult.InvalidHasBonusNumber(
                    numbers.numbers,
                    bonusNumber.number,
                )
            }
            return WinningNumbersResult.Success(WinningNumbers(numbers, bonusNumber))
        }
    }
}
