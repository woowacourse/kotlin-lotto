package domain.service

import domain.model.Lotto
import domain.model.LottoResult
import domain.model.Rank
import domain.model.WinningLotto

class LottoMatchCalculator {
    fun calculate(
        purchaseLotto: List<Lotto>,
        winningLotto: WinningLotto,
    ): LottoResult {
        val lottoRanks = purchaseLotto.map { winningLotto.match(it) }
        val defaultRankResult = Rank.entries.reversed().associateWith { 0 }
        return LottoResult(defaultRankResult + lottoRanks.groupingBy { it }.eachCount())
    }
}
