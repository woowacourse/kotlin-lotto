package lotto.domain.service

import lotto.constants.LottoConstants
import lotto.domain.model.Lotto
import lotto.domain.model.LottoWinningStats
import lotto.domain.model.WinningLotto
import lotto.domain.value.LottoPayInfo
import lotto.enums.Rank

class LottoCalculator(
    private val winningLotto: WinningLotto,
    private val lottos: List<Lotto>,
) {
    fun calculateWinningStats(): LottoWinningStats {
        val winningStats = getWinningStats()
        val purchaseAmount = getPurchaseAmount()
        return LottoWinningStats(winningStats, purchaseAmount)
    }

    private fun getWinningStats(): Map<Rank, Int> = lottos.map { winningLotto.getRank(it) }.groupingBy { it }.eachCount()

    private fun getPurchaseAmount(): LottoPayInfo {
        val amount = lottos.size * LottoConstants.LOTTO_PRICE
        return LottoPayInfo(amount)
    }
}
