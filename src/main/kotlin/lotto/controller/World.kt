package lotto.controller

import lotto.entity.Lotto
import lotto.entity.LottoNumber
import lotto.entity.LottoPrice
import lotto.entity.Lottos
import lotto.entity.ProfitRate
import lotto.entity.PurchaseMoney
import lotto.entity.WinLotto
import lotto.entity.WinStatistics
import lotto.model.LottoProfitRateCalculator
import lotto.model.RandomLottoGenerator
import lotto.view.InputView
import lotto.view.LottoWinStatisticsFormatter
import lotto.view.OutputView

class World(
    private val inputView: InputView,
    private val outputView: OutputView
) {
    private val lottoPrice = LottoPrice(LottoPrice.DEFAULT_LOTTO_PRICE)

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
        outputView.printMessage(OutputView.MESSAGE_INPUT_MONEY)
        return inputView.readPurchaseMoney(lottoPrice)
    }

    private fun initLottos(purchaseMoney: PurchaseMoney): Lottos {
        val lottoGenerator = RandomLottoGenerator()
        val lottos = Lottos(purchaseMoney, lottoPrice, lottoGenerator)
        outputView.printMessage(OutputView.MESSAGE_PURCHASE_COUNT, lottos.value.size)
        outputView.lottosResult(lottos)
        return lottos
    }

    private fun initWinNumber(): Lotto {
        outputView.printMessage(OutputView.MESSAGE_WIN_NUMBER)
        return inputView.readWinNumber()
    }

    private fun initBonus(winNumber: Lotto): LottoNumber {
        outputView.printMessage(OutputView.MESSAGE_BONUS)
        return inputView.readBonus(winNumber)
    }

    private fun makeWinStatistics(lottos: Lottos, winLotto: WinLotto): WinStatistics {
        return lottos.determineLottosResult(winLotto)
    }

    private fun makeProfitRate(purchaseMoney: PurchaseMoney, winStatistics: WinStatistics): ProfitRate {
        val profitRateCalculator = LottoProfitRateCalculator(purchaseMoney, winStatistics)
        return profitRateCalculator.calculate()
    }
}
