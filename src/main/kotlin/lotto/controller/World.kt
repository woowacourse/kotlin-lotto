package lotto.controller

import lotto.entity.Bonus
import lotto.entity.Lotto
import lotto.entity.LottoPrice
import lotto.entity.Lottos
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

    private fun initPurchaseMoney(): PurchaseMoney {
        return tryAndRerun {
            OutputView.printMessage(OutputView.MESSAGE_INPUT_MONEY)
            PurchaseMoney(InputView.readInt())
        } as PurchaseMoney
    }

    private fun initWinNumber(): Lotto {
        return tryAndRerun {
            OutputView.printMessage(OutputView.MESSAGE_WIN_NUMBER)
            Lotto(InputView.readIntList())
        } as Lotto
    }

    private fun initBonus(): Bonus {
        return tryAndRerun {
            OutputView.printMessage(OutputView.MESSAGE_BONUS)
            Bonus(InputView.readInt())
        } as Bonus
    }

    private fun initLottos(purchaseMoney: PurchaseMoney): Lottos {
        val lottoGenerator = RandomLottoGenerator()
        val lottos = Lottos(purchaseMoney, lottoPrice, lottoGenerator)
        OutputView.printMessage(OutputView.MESSAGE_PURCHASE_COUNT, lottos.value.size)
        OutputView.lottosResult(lottos)
        return lottos
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
