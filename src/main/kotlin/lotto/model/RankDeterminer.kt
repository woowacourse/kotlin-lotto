package lotto.model

import lotto.entity.Lotto
import lotto.entity.WinLotto

interface RankDeterminer {
    fun determine(lotto: Lotto, winLotto: WinLotto): Rank
}
