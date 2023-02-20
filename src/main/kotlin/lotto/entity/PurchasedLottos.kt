package lotto.entity

import lotto.model.LottoGenerator

class PurchasedLottos(val value: List<Lotto>) {
    fun makeWinStatistics(winLotto: WinLotto): WinStatistics = WinStatistics(value.map { winLotto.determineRank(it) })

    companion object {
        fun from(purchaseMoney: PurchaseMoney, lottoGenerator: LottoGenerator): PurchasedLottos {
            val lottoCount = purchaseMoney.calculateLottoCount()
            return PurchasedLottos(
                (0 until lottoCount).map {
                    lottoGenerator.generate()
                }
            )
        }
    }
}
