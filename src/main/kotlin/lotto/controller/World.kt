package lotto.controller

import lotto.entity.Lotto
import lotto.entity.LottoGame
import lotto.entity.LottoNumber
import lotto.entity.LottoPrice
import lotto.entity.ProfitRate
import lotto.entity.PurchaseMoney
import lotto.entity.WinLotto
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

    fun processLotto() {
        val purchaseMoney = initPurchaseMoney()

        val lottoGame = makeLottoGame(purchaseMoney.value / lottoPrice.value)
        outputView.printMessage(OutputView.MESSAGE_PURCHASE_COUNT, lottoGame.value.size)
        outputView.gameResult(lottoGame)

        val winLotto = initWinLotto()

        val winStatistics = makeWinStatistics(lottoGame, winLotto)
        outputView.winStatisticsResult(winStatistics, LottoWinStatisticsFormatter())

        val profitRate = makeProfitRate(LottoProfitRateCalculator(purchaseMoney, winStatistics))
        outputView.profitRateResult(profitRate)
    }

    private fun makeLottoGame(lottoCount: Int): LottoGame =
        LottoGame.from(lottoCount, RandomLottoGenerator())

    private fun makeWinStatistics(lottoGame: LottoGame, winLotto: WinLotto): WinStatistics =
        LottoRankDeterminer(lottoGame, winLotto).determine()

    private fun makeProfitRate(profitRateCalculator: LottoProfitRateCalculator): ProfitRate =
        profitRateCalculator.calculate()

    companion object {
        private const val DEFAULT_LOTTO_PRICE = 1000
    }
}
