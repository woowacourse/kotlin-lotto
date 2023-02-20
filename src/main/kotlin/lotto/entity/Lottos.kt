package lotto.entity

import lotto.model.LottoGenerator

class Lottos private constructor(val value: List<Lotto>) {

    fun determineLottosResult(winLotto: WinLotto): WinStatistics {
        return WinStatistics(
            value.map {
                it.determineRank(it.determineCountOfMatch(winLotto.winNumber), it.determineMatchBonus(winLotto.bonus))
            }
        )
    }

    companion object {
        fun from(lottoCount: Int, lottoGenerator: LottoGenerator): Lottos {
            return Lottos(
                (0 until lottoCount).map {
                    lottoGenerator.generate()
                }
            )
        }
    }
}
