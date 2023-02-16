package lotto.model

import lotto.entity.Game
import lotto.entity.WinLotto
import lotto.entity.WinStatistics

class LottoRankDeterminer(private val game: Game, private val winLotto: WinLotto) {
    fun determine(): WinStatistics = WinStatistics(game.value.map { Rank.determine(it, winLotto) })
}
