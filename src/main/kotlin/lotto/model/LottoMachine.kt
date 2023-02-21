package lotto.model

import lotto.entity.LottoCount
import lotto.entity.ProfitRate
import lotto.entity.PurchaseMoney
import lotto.entity.PurchasedLottos
import lotto.entity.WinStatistics

class LottoMachine(
    private val purchaseMoney: PurchaseMoney,
    val lottoManualCount: LottoCount,
    manualLottos: PurchasedLottos
) {
    val purchasedLottos: PurchasedLottos
    val lottoAutoCount: LottoCount
        get() = purchaseMoney.calculateLottoCount(lottoManualCount)

    init {
        val lottoAutoCount = purchaseMoney.calculateLottoCount(lottoManualCount)
        purchasedLottos = makePurchasedLotto(manualLottos, lottoAutoCount)
    }

    private fun makePurchasedLotto(manualLottos: PurchasedLottos, lottoAutoCount: LottoCount): PurchasedLottos {
        val manualPurchasedLottos = PurchasedLottos.from(
            LottoCount(manualLottos.value.size), SequentialLottoNumberGenerator(manualLottos.value)
        )
        val autoPurchasedLottos = PurchasedLottos.from(lottoAutoCount, RandomLottoGenerator())
        return manualPurchasedLottos.merge(autoPurchasedLottos)
    }

    fun makeProfitRate(winStatistics: WinStatistics): ProfitRate {
        val profitRateCalculator = LottoProfitRateCalculator()
        val winMoney = winStatistics.calculateWinMoney()
        return profitRateCalculator.calculate(purchaseMoney, winMoney)
    }
}
