package lotto.controller

import lotto.entity.Game
import lotto.entity.Lotto
import lotto.entity.LottoNumber
import lotto.entity.LottoPrice
import lotto.entity.ProfitRate
import lotto.entity.PurchaseMoney
import lotto.entity.WinLotto
import lotto.entity.WinStatistics
import lotto.misc.tryAndRerun
import lotto.model.GameGenerator
import lotto.model.LottoProfitRateCalculator
import lotto.model.LottoRankDeterminer
import lotto.model.RandomLottoGenerator
import lotto.view.InputView
import lotto.view.LottoWinStatisticsFormatter
import lotto.view.OutputView

class World {
    private val lottoPrice = LottoPrice(DEFAULT_LOTTO_PRICE)
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
            LottoNumber(inputView.readInt())
        } as LottoNumber
    }

    private fun initLottos(purchaseMoney: PurchaseMoney): Game {
        val lottoGenerator = RandomLottoGenerator()
        val gameGenerator = GameGenerator(lottoGenerator)
        val game = gameGenerator.generate(purchaseMoney, lottoPrice)
        outputView.printMessage(OutputView.MESSAGE_PURCHASE_COUNT, game.value.size)
        outputView.gameResult(game)
        return game
    }

    fun processLotto() {
        val purchaseMoney = initPurchaseMoney()
        val winStatistics = makeWinStatistics(purchaseMoney)
        outputView.winStatisticsResult(winStatistics, LottoWinStatisticsFormatter())
        val profitRate = makeProfitRate(purchaseMoney, winStatistics)
        outputView.profitRateResult(profitRate)
    }

    private fun makeWinStatistics(purchaseMoney: PurchaseMoney): WinStatistics {
        val lottos = initLottos(purchaseMoney)
        val winNumber = initWinNumber()
        val bonus = initBonus()
        val winLotto = WinLotto(winNumber, bonus)
        return LottoRankDeterminer(lottos, winLotto).determine()
    }

    private fun makeProfitRate(purchaseMoney: PurchaseMoney, winStatistics: WinStatistics): ProfitRate {
        val profitRateCalculator = LottoProfitRateCalculator(purchaseMoney, winStatistics)
        return profitRateCalculator.calculate()
    }

    companion object {
        private const val DEFAULT_LOTTO_PRICE = 1000
    }
}
