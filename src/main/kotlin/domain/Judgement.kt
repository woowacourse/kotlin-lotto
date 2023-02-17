package domain

import domain.lotto.Lotto
import domain.lotto.LottoBundle

object Judgement {
    fun compareLottoBundle(winningNumbers: WinningNumbers, lottoBundle: LottoBundle): WinningResult {
        return WinningResult(lottoBundle.lottos.map { compareLotto(winningNumbers, it) })
    }

    private fun compareLotto(winningNumbers: WinningNumbers, lotto: Lotto): ComparingResult {
        return winningNumbers.compareLotto(lotto)
    }
}
