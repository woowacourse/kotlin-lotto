package lotto.controller

import lotto.entity.Lotto
import lotto.entity.LottoGame
import lotto.entity.LottoNumber
import lotto.entity.Money
import lotto.entity.ProfitRate
import lotto.entity.PurchaseMoney
import lotto.entity.WinLotto
import lotto.entity.WinMoney
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
        val lottoGame = makeLottoGame(purchaseMoney.value / Money.LOTTO_PRICE)

        processLottoGame(lottoGame)

        val winLotto = initWinLotto()
        val winStatistics = makeWinStatistics(lottoGame, winLotto)
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
        val winMoney = WinMoney.from(winStatistics)
        val profitRate = makeProfitRate(profitRateCalculator, purchaseMoney, winMoney)
        outputView.profitRateResult(profitRate)
    }

    private fun makeLottoGame(lottoCount: Int): LottoGame =
        LottoGame.from(lottoCount, RandomLottoGenerator())

    private fun makeWinStatistics(lottoGame: LottoGame, winLotto: WinLotto): WinStatistics =
        WinStatistics.from(lottoGame, winLotto)

    private fun makeProfitRate(
        profitRateCalculator: LottoProfitRateCalculator,
        purchaseMoney: PurchaseMoney,
        winMoney: WinMoney
    ): ProfitRate =
        profitRateCalculator.calculate(purchaseMoney, winMoney)
}
