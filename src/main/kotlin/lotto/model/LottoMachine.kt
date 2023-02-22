package lotto.model

import lotto.entity.Lotto
import lotto.entity.LottoCount
import lotto.entity.ProfitRate
import lotto.entity.PurchaseMoney
import lotto.entity.PurchasedLottos
import lotto.entity.WinStatistics

class LottoMachine(
    private val purchaseMoney: PurchaseMoney,
    val lottoManualCount: LottoCount,
    manualLottos: () -> Lotto
) {
    val lottoAutoCount: LottoCount = purchaseMoney.calculateAutoLottoCount(lottoManualCount)
    val purchasedLottos: PurchasedLottos = makeManualPurchasedLottos(manualLottos) + makeAutoPurchasedLottos()

    private fun makeAutoPurchasedLottos(): PurchasedLottos =
        PurchasedLottos.from(lottoAutoCount, RandomLottoGenerator())

    private fun makeManualPurchasedLottos(manualLottos: () -> Lotto): PurchasedLottos =
        PurchasedLottos((0 until lottoManualCount.value).map { manualLottos() })

    fun makeProfitRate(winStatistics: WinStatistics): ProfitRate {
        val profitRateCalculator = LottoProfitRateCalculator()
        val winMoney = winStatistics.calculateWinMoney()
        return profitRateCalculator.calculate(purchaseMoney, winMoney)
    }
}
