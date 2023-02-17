package domain

object Judgement {
    fun compareLottoBundle(winningNumbers: WinningNumbers, lottoBundle: LottoBundle): WinningResult {
        return WinningResult(lottoBundle.lottos.map { compareLotto(winningNumbers, it) })
    }

    private fun compareLotto(winningNumbers: WinningNumbers, lotto: Lotto): ComparingResult {
        val matchedCount: Int = lotto.countSameNumber(winningNumbers.lotto)
        val isBonusMatched: Boolean = lotto.has(winningNumbers.bonusNumber)

        return ComparingResult(matchedCount, isBonusMatched)
    }
}
