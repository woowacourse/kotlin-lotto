package lotto.controller

import lotto.entity.Lotto
import lotto.entity.LottoNumber
import lotto.entity.LottoPrice
import lotto.entity.Lottos
import lotto.entity.ProfitRate
import lotto.entity.PurchaseMoney
import lotto.entity.WinLotto
import lotto.entity.WinStatistics
import lotto.misc.tryAndRerun
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
        return tryAndRerun {
            outputView.printMessage(OutputView.MESSAGE_INPUT_MONEY)
            inputView.readPurchaseMoney(lottoPrice)
        } as PurchaseMoney
    }

    private fun initLottos(purchaseMoney: PurchaseMoney): Lottos {
        val lottoGenerator = RandomLottoGenerator()
        val lottos = Lottos(purchaseMoney, lottoPrice, lottoGenerator)
        outputView.printMessage(OutputView.MESSAGE_PURCHASE_COUNT, lottos.value.size)
        outputView.lottosResult(lottos)
        return lottos
    }

    private fun initWinNumber(): Lotto {
        return tryAndRerun {
            outputView.printMessage(OutputView.MESSAGE_WIN_NUMBER)
            inputView.readWinNumber()
        } as Lotto
    }

    private fun initBonus(winNumber: Lotto): LottoNumber {
        return tryAndRerun {
            outputView.printMessage(OutputView.MESSAGE_BONUS)
            val bonus = inputView.readBonus()
            WinLotto(winNumber, bonus)
            bonus
        } as LottoNumber
    }

    private fun makeWinStatistics(lottos: Lottos, winLotto: WinLotto): WinStatistics {
        return tryAndRerun {
            WinStatistics(
                lottos.determineLottosResult(winLotto)
            )
        } as WinStatistics
    }

    private fun makeProfitRate(purchaseMoney: PurchaseMoney, winStatistics: WinStatistics): ProfitRate {
        val profitRateCalculator = LottoProfitRateCalculator(purchaseMoney, winStatistics)
        return profitRateCalculator.calculate()
    }
}
