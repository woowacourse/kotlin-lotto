package lotto.domain.model

import lotto.constants.LottoConstants
import lotto.domain.value.LottoPayInfo
import lotto.enums.Rank

data class Lottos(
    val tickets: List<Lotto>,
) {
    fun getLottoWinningStats(winningLotto: WinningLotto): LottoWinningStats =
        LottoWinningStats(getWinningStatsInfo(winningLotto), getLottoPayInfo())

    private fun getLottoPayInfo(): LottoPayInfo = LottoPayInfo(tickets.size * LottoConstants.LOTTO_PRICE)

    private fun getWinningStatsInfo(winningLotto: WinningLotto): Map<Rank, Int> =
        tickets.map { winningLotto.getRank(it) }.groupingBy { it }.eachCount()
}
