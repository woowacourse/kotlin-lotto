package lotto.controller

import lotto.entity.Lotto
import lotto.entity.LottoGame
import lotto.entity.LottoNumber
import lotto.entity.PurchaseMoney
import lotto.entity.WinLotto
import lotto.entity.WinStatistics
import lotto.misc.tryAndRerun
import lotto.model.LottoProfitRateCalculator
import lotto.model.RandomLottoGenerator
import lotto.view.InputView
import lotto.view.LottoWinStatisticsFormatter
import lotto.view.OutputView
import lotto.view.WinStatisticsFormatter

class World {
    private val inputView = InputView()
    private val outputView = OutputView()

    private fun initPurchaseMoney(): PurchaseMoney {
        return tryAndRerun {
            outputView.printMessage(OutputView.MESSAGE_INPUT_MONEY)
            PurchaseMoney(inputView.readInt())
        } as PurchaseMoney
    }

    private fun initWinNumber(): Lotto {
        return tryAndRerun {
            outputView.printMessage(OutputView.MESSAGE_WIN_NUMBER)
            Lotto(inputView.readIntList())
        } as Lotto
    }

    private fun initBonus(): LottoNumber {
        return tryAndRerun {
            outputView.printMessage(OutputView.MESSAGE_BONUS)
            LottoNumber.from(inputView.readInt())
        } as LottoNumber
    }

    private fun initWinLotto(): WinLotto {
        return tryAndRerun {
            val winNumber = initWinNumber()
            val bonus = initBonus()
            WinLotto(winNumber, bonus)
        } as WinLotto
    }

    fun processLotto() {
        val purchaseMoney = initPurchaseMoney()
        val lottoGame = LottoGame.from(purchaseMoney, RandomLottoGenerator())

        processLottoGame(lottoGame)

        val winLotto = initWinLotto()
        val winStatistics = lottoGame.makeWinStatistics(winLotto)
        val winStatisticsFormatter = LottoWinStatisticsFormatter()

        processWinStatistics(winStatistics, winStatisticsFormatter)
        processProfitRate(winStatistics, purchaseMoney)
    }

    private fun processLottoGame(lottoGame: LottoGame) {
        outputView.printMessage(OutputView.MESSAGE_PURCHASE_COUNT, lottoGame.value.size)
        outputView.gameResult(lottoGame)
    }

    private fun processWinStatistics(winStatistics: WinStatistics, winStatisticsFormatter: WinStatisticsFormatter) {
        outputView.winStatisticsResult(winStatistics, winStatisticsFormatter)
    }

    private fun processProfitRate(winStatistics: WinStatistics, purchaseMoney: PurchaseMoney) {
        val profitRateCalculator = LottoProfitRateCalculator()
        val winMoney = winStatistics.calculateWinMoney()
        val profitRate = profitRateCalculator.calculate(purchaseMoney, winMoney)
        outputView.profitRateResult(profitRate)
    }
}
