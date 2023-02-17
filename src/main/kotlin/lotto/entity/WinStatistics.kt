package lotto.entity

import lotto.model.Rank

class WinStatistics private constructor(val value: List<Rank>) {
    companion object {
        fun from(lottoGame: LottoGame, winLotto: WinLotto): WinStatistics =
            WinStatistics(lottoGame.value.map { Rank.determine(it, winLotto) })
    }
}
