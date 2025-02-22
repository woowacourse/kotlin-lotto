package domain.service

import domain.model.LottoResult
import domain.model.LottoTicket
import domain.model.WinningLotto

class LottoMatchCalculator() {
    fun calculate(
        purchaseLotto: LottoTicket,
        winningLotto: WinningLotto,
    ): LottoResult {
        val lottoRanks = purchaseLotto.values.map { winningLotto.match(it) }
        return LottoResult(lottoRanks.groupingBy { it }.eachCount())
    }
}
