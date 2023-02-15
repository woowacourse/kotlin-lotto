package lotto.controller

import lotto.entity.Bonus
import lotto.entity.LottoPrice
import lotto.entity.Lottos
import lotto.entity.ProfitRate
import lotto.entity.PurchaseMoney
import lotto.entity.WinLotto
import lotto.entity.WinNumber
import lotto.entity.WinStatistics
import lotto.misc.tryAndRerun
import lotto.model.LottoProfitRateCalculator
import lotto.model.LottoRankDeterminer
import lotto.model.RandomLottoGenerator
import lotto.view.InputView
import lotto.view.LottoWinStatisticsFormatter
import lotto.view.OutputView

class World {
    private val lottoPrice = LottoPrice(DEFAULT_LOTTO_PRICE)

    private fun initPurchaseMoney(): PurchaseMoney {
        return tryAndRerun {
            OutputView.printMessage(OutputView.MESSAGE_INPUT_MONEY)
            InputView.readPurchaseMoney(lottoPrice)
        } as PurchaseMoney
    }

    private fun initLottos(purchaseMoney: PurchaseMoney): Lottos {
        val lottoCount = purchaseMoney.value / lottoPrice.value
        OutputView.printMessage(OutputView.MESSAGE_PURCHASE_COUNT, lottoCount)
        val lottoGenerator = RandomLottoGenerator()
        val lottos = Lottos(purchaseMoney, lottoPrice, lottoGenerator)
        OutputView.lottosResult(lottos)
        return lottos
    }

    private fun initWinNumber(): WinNumber {
        return tryAndRerun {
            OutputView.printMessage(OutputView.MESSAGE_WIN_NUMBER)
            InputView.readWinNumber()
        } as WinNumber
    }

    private fun initBonus(winNumber: WinNumber): Bonus {
        return tryAndRerun {
            OutputView.printMessage(OutputView.MESSAGE_BONUS)
            InputView.readBonus(winNumber)
        } as Bonus
    }

    fun processLotto() {
        val purchaseMoney = initPurchaseMoney()
        val winStatistics = makeWinStatistics(purchaseMoney)
        OutputView.winStatisticsResult(winStatistics, LottoWinStatisticsFormatter())
        val profitRate = makeProfitRate(purchaseMoney, winStatistics)
        OutputView.profitRateResult(profitRate)
    }

    private fun makeWinStatistics(purchaseMoney: PurchaseMoney): WinStatistics {
        val lottos = initLottos(purchaseMoney)
        val winNumber = initWinNumber()
        return tryAndRerun {
            val bonus = initBonus(winNumber)
            val winLotto = WinLotto(winNumber, bonus)
            WinStatistics(lottos.value.map { LottoRankDeterminer().determine(it, winLotto) })
        } as WinStatistics
    }

    private fun makeProfitRate(purchaseMoney: PurchaseMoney, winStatistics: WinStatistics): ProfitRate {
        val profitRateCalculator = LottoProfitRateCalculator(purchaseMoney, winStatistics)
        return profitRateCalculator.calculate()
    }

    companion object {
        private const val DEFAULT_LOTTO_PRICE = 1000
    }
}
