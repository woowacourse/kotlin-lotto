package lotto.entity

import lotto.model.LottoGenerator

class LottoGame private constructor(val value: List<Lotto>) {
    fun makeWinStatistics(winLotto: WinLotto): WinStatistics = WinStatistics(value.map { winLotto.determineRank(it) })

    companion object {
        fun from(purchaseMoney: PurchaseMoney, lottoGenerator: LottoGenerator): LottoGame {
            val lottoCount = purchaseMoney.calculateLottoCount()
            return LottoGame(
                (0 until lottoCount).map {
                    lottoGenerator.generate()
                }
            )
        }
    }
}
