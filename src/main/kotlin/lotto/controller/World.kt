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
import lotto.model.RandomLottoGenerator
import lotto.model.Rank
import lotto.view.InputView
import lotto.view.LottoWinStatisticsFormatter
import lotto.view.OutputView

class World {
    private val lottoPrice = LottoPrice(DEFAULT_LOTTO_PRICE)

    fun processLotto() {
        val purchaseMoney = initPurchaseMoney()
        val lottos = initLottos(purchaseMoney)
        val winNumber = initWinNumber()
        val bonus = initBonus(winNumber)
        val winLotto = WinLotto(winNumber, bonus)
        val winStatistics = makeWinStatistics(lottos, winLotto)
        OutputView.winStatisticsResult(winStatistics, LottoWinStatisticsFormatter())
        val profitRate = makeProfitRate(purchaseMoney, winStatistics)
        OutputView.profitRateResult(profitRate)
    }

    private fun initPurchaseMoney(): PurchaseMoney {
        return tryAndRerun {
            OutputView.printMessage(OutputView.MESSAGE_INPUT_MONEY)
            InputView.readPurchaseMoney(lottoPrice)
        } as PurchaseMoney
    }

    private fun initLottos(purchaseMoney: PurchaseMoney): Lottos {
        val lottoGenerator = RandomLottoGenerator()
        val lottos = Lottos(purchaseMoney, lottoPrice, lottoGenerator)
        OutputView.printMessage(OutputView.MESSAGE_PURCHASE_COUNT, lottos.value.size)
        OutputView.lottosResult(lottos)
        return lottos
    }

    private fun initWinNumber(): Lotto {
        return tryAndRerun {
            OutputView.printMessage(OutputView.MESSAGE_WIN_NUMBER)
            InputView.readWinNumber()
        } as Lotto
    }

    private fun initBonus(winNumber: Lotto): Bonus {
        return tryAndRerun {
            OutputView.printMessage(OutputView.MESSAGE_BONUS)
            val bonus = InputView.readBonus()
            WinLotto(winNumber, bonus)
            bonus
        } as Bonus
    }

    private fun makeWinStatistics(lottos: Lottos, winLotto: WinLotto): WinStatistics {
        return tryAndRerun {
            WinStatistics(
                lottos.value.map {
                    Rank.valueOf(
                        it.numbers.intersect(winLotto.winNumber.numbers.toSet()).size,
                        it.numbers.contains(winLotto.bonus.value)
                    )
                }
            )
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
