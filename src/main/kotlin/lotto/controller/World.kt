package lotto.controller

import lotto.entity.Lotto
import lotto.entity.LottoCount
import lotto.entity.LottoNumber
import lotto.entity.LottoTicket
import lotto.entity.Lottos
import lotto.entity.ProfitRate
import lotto.entity.PurchaseMoney
import lotto.entity.WinLotto
import lotto.entity.WinStatistics
import lotto.model.LottoProfitRateCalculator
import lotto.model.LottoStore
import lotto.view.InputView
import lotto.view.LottoWinStatisticsFormatter
import lotto.view.OutputView

class World(
    private val inputView: InputView,
    private val outputView: OutputView
) {

    fun processLotto() {
        val purchaseMoney = initPurchaseMoney()
        val manualLottoCount = initManualLottoCount(purchaseMoney)
        val lottos = initLottos(purchaseMoney, manualLottoCount)
        val winStatistics = makeWinStatistics(lottos, makeWinLotto())
        val profitRate = makeProfitRate(purchaseMoney, winStatistics)

        outputView.winStatisticsResult(winStatistics, LottoWinStatisticsFormatter())
        outputView.profitRateResult(profitRate)
    }

    private fun initPurchaseMoney(): PurchaseMoney {
        return inputView.getPurchaseMoney()
    }

    private fun initManualLottoCount(purchaseMoney: PurchaseMoney): LottoCount {
        return inputView.getManualLottoCount(purchaseMoney)
    }

    private fun initLottos(purchaseMoney: PurchaseMoney, manualLottoCount: LottoCount): Lottos {
        val store = LottoStore(purchaseMoney, initManualLotto(manualLottoCount))

        val totalLottos = store.makeLottos()

        outputView.printMessage(OutputView.MESSAGE_PURCHASE_COUNT, manualLottoCount.value, store.getAutoLottoCount().value)
        outputView.lottosResult(totalLottos)

        return totalLottos
    }

    private fun initManualLotto(manualLottoCount: LottoCount): Array<LottoTicket> {
        return inputView.getManualLottos(manualLottoCount)
    }

    private fun initWinNumber(): Lotto {
        return inputView.getWinNumber()
    }

    private fun initBonus(winNumber: Lotto): LottoNumber {
        return inputView.getBonus(winNumber)
    }

    private fun makeWinLotto(): WinLotto {
        val winNumber = initWinNumber()
        val bonus = initBonus(winNumber)
        return WinLotto(winNumber, bonus)
    }

    private fun makeWinStatistics(lottos: Lottos, winLotto: WinLotto): WinStatistics {
        return lottos.determineLottosResult(winLotto)
    }

    private fun makeProfitRate(purchaseMoney: PurchaseMoney, winStatistics: WinStatistics): ProfitRate {
        val profitRateCalculator = LottoProfitRateCalculator(purchaseMoney, winStatistics)
        return profitRateCalculator.calculate()
    }
}
