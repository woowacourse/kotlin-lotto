package lotto.controller

import lotto.entity.Lotto
import lotto.entity.LottoNumber
import lotto.entity.Lottos
import lotto.entity.ProfitRate
import lotto.entity.PurchaseMoney
import lotto.entity.WinLotto
import lotto.entity.WinStatistics
import lotto.misc.tryAndRerun
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
        val lottos = initLottos(purchaseMoney)
        val winNumber = initWinNumber()
        val bonus = initBonus(winNumber)
        val winLotto = WinLotto(winNumber, bonus)
        val winStatistics = makeWinStatistics(lottos, winLotto)
        outputView.winStatisticsResult(winStatistics, LottoWinStatisticsFormatter())
        val profitRate = makeProfitRate(purchaseMoney, winStatistics)
        outputView.profitRateResult(profitRate)
    }

    private fun initPurchaseMoney(): PurchaseMoney {
        return tryAndRerun {
            outputView.printMessage(OutputView.MESSAGE_INPUT_MONEY)
            PurchaseMoney(inputView.readPurchaseMoney())
        } as PurchaseMoney
    }

    private fun initLottos(purchaseMoney: PurchaseMoney): Lottos {
        val store = LottoStore()
        val lottoCount = purchaseMoney.getPurchaseLottoCount()
        val autoLottos = store.buyAutoLotto(lottoCount)
        outputView.printMessage(OutputView.MESSAGE_PURCHASE_COUNT, lottoCount)
        outputView.lottosResult(autoLottos)
        return autoLottos
    }

    private fun initWinNumber(): Lotto {
        return tryAndRerun {
            outputView.printMessage(OutputView.MESSAGE_WIN_NUMBER)
            Lotto.from(inputView.readWinNumber().map { LottoNumber(it) })
        } as Lotto
    }

    private fun initBonus(winNumber: Lotto): LottoNumber {
        return tryAndRerun {
            outputView.printMessage(OutputView.MESSAGE_BONUS)
            val bonus = LottoNumber(inputView.readBonus())
            WinLotto(winNumber, bonus)
            bonus
        } as LottoNumber
    }

    private fun makeWinStatistics(lottos: Lottos, winLotto: WinLotto): WinStatistics {
        return lottos.determineLottosResult(winLotto)
    }

    private fun makeProfitRate(purchaseMoney: PurchaseMoney, winStatistics: WinStatistics): ProfitRate {
        val profitRateCalculator = LottoProfitRateCalculator(purchaseMoney, winStatistics)
        return profitRateCalculator.calculate()
    }
}
