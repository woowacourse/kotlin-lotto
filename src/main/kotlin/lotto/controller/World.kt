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

    private fun initWinLotto(): WinLotto {
        return tryAndRerun {
            val winNumber = initWinNumber()
            val bonus = initBonus()
            WinLotto(winNumber, bonus)
        } as WinLotto
    }

    private fun generateGame(purchaseMoney: PurchaseMoney): Game {
        val lottoGenerator = RandomLottoGenerator()
        val gameGenerator = GameGenerator(lottoGenerator)
        return gameGenerator.generate(purchaseMoney, lottoPrice)
    }

    fun processLotto() {
        val purchaseMoney = initPurchaseMoney()

        val game = generateGame(purchaseMoney)
        outputView.printMessage(OutputView.MESSAGE_PURCHASE_COUNT, game.value.size)
        outputView.gameResult(game)

        val winLotto = initWinLotto()

        val winStatistics = makeWinStatistics(game, winLotto)
        outputView.winStatisticsResult(winStatistics, LottoWinStatisticsFormatter())

        val profitRate = makeProfitRate(LottoProfitRateCalculator(purchaseMoney, winStatistics))
        outputView.profitRateResult(profitRate)
    }

    private fun makeWinStatistics(game: Game, winLotto: WinLotto): WinStatistics =
        LottoRankDeterminer(game, winLotto).determine()

    private fun makeProfitRate(profitRateCalculator: LottoProfitRateCalculator): ProfitRate =
        profitRateCalculator.calculate()

    companion object {
        private const val DEFAULT_LOTTO_PRICE = 1000
    }
}
