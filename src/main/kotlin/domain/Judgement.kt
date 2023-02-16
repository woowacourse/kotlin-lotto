package domain

object Judgement {
    fun compareLottoBundle(winningNumbers: WinningNumbers, lottoBundle: LottoBundle): WinningResult {
        return WinningResult(lottoBundle.lottos.map { compareLotto(winningNumbers, it) })
    }

    private fun compareLotto(winningNumbers: WinningNumbers, lotto: Lotto): ComparingResult {
        val matchedCount: Int = lotto.lottoNumbers.count { winningNumbers.lotto.lottoNumbers.contains(it) }
        val isBonusMatched: Boolean = lotto.lottoNumbers.contains(winningNumbers.bonusNumber)

        return ComparingResult(matchedCount, isBonusMatched)
    }
}