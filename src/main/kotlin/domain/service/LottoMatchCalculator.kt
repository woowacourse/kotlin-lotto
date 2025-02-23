package domain.service

import domain.model.Lotto
import domain.model.LottoResult
import domain.model.WinningLotto

class LottoMatchCalculator {
    fun calculate(
        purchaseLotto: List<Lotto>,
        winningLotto: WinningLotto,
    ): LottoResult {
        val lottoRanks = purchaseLotto.map { winningLotto.match(it) }
        return LottoResult(lottoRanks.groupingBy { it }.eachCount())
    }
}
