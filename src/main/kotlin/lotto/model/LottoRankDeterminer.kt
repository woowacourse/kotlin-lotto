package lotto.model

import lotto.entity.Lottos
import lotto.entity.WinLotto
import lotto.entity.WinStatistics

class LottoRankDeterminer(private val lottos: Lottos, private val winLotto: WinLotto) {
    fun determine(): WinStatistics = WinStatistics(lottos.value.map { Rank.determine(it, winLotto) })
}
