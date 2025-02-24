package lotto.domain.model

import lotto.constants.LottoConstants
import lotto.domain.value.LottoPayInfo
import lotto.enums.Rank

data class Lottos(
    val tickets: List<Lotto>,
    val lottoPayInfo: LottoPayInfo = LottoPayInfo(tickets.size * LottoConstants.LOTTO_PRICE, 0),
) {
    fun getLottoWinningStats(winningLotto: WinningLotto): LottoWinningStats =
        LottoWinningStats(getWinningStatsInfo(winningLotto), lottoPayInfo)

    private fun getWinningStatsInfo(winningLotto: WinningLotto): Map<Rank, Int> =
        tickets.map { winningLotto.getRank(it) }.groupingBy { it }.eachCount()
}
