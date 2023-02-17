package lotto.model

import lotto.entity.LottoGame
import lotto.entity.WinLotto
import lotto.entity.WinStatistics

class LottoRankDeterminer(private val lottoGame: LottoGame, private val winLotto: WinLotto) {
    fun determine(): WinStatistics = WinStatistics(lottoGame.value.map { Rank.determine(it, winLotto) })
}
