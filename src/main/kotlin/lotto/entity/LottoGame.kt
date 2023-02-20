package lotto.entity

import lotto.model.LottoGenerator
import lotto.model.Rank

class LottoGame private constructor(val value: List<Lotto>) {
    fun makeWinStatistics(winLotto: WinLotto): WinStatistics = WinStatistics(value.map { Rank.determine(it, winLotto) })

    companion object {
        fun from(lottoCount: Int, lottoGenerator: LottoGenerator): LottoGame {
            return LottoGame(
                (0 until lottoCount).map {
                    lottoGenerator.generate()
                }
            )
        }
    }
}
