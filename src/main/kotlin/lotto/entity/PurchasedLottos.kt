package lotto.entity

import lotto.model.LottoGenerator

class PurchasedLottos(val value: List<Lotto>) {
    fun makeWinStatistics(winLotto: WinLotto): WinStatistics = WinStatistics(value.map { winLotto.determineRank(it) })

    operator fun plus(other: PurchasedLottos): PurchasedLottos = PurchasedLottos(value + other.value)

    companion object {
        fun from(lottoCount: LottoCount, lottoGenerator: LottoGenerator): PurchasedLottos {
            return PurchasedLottos(
                (0 until lottoCount.value).map {
                    lottoGenerator.generate()
                }
            )
        }
    }
}
