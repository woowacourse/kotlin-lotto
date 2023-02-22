package lotto.controller

import lotto.entity.Lotto
import lotto.entity.LottoCount
import lotto.entity.LottoNumber
import lotto.entity.ProfitRate
import lotto.entity.PurchaseMoney
import lotto.entity.PurchasedLottos
import lotto.entity.WinLotto
import lotto.entity.WinStatistics
import lotto.misc.tryAndRerun
import lotto.model.InputLottoGenerator
import lotto.model.LottoMachine
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

    private fun initLottoManualCount(): LottoCount {
        return tryAndRerun {
            outputView.printMessage(OutputView.MESSAGE_LOTTO_MANUAL_COUNT)
            LottoCount(inputView.readInt())
        } as LottoCount
    }

    private fun initSingleLotto(): Lotto {
        return tryAndRerun {
            Lotto(inputView.readIntList())
        } as Lotto
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
        val lottoManualCount = initLottoManualCount()

        val purchasedLottos = makePurchasedLottos(purchaseMoney, lottoManualCount)
        purchasedResult(purchasedLottos, lottoManualCount, purchaseMoney.calculateAutoLottoCount(lottoManualCount))

        val winStatistics = makeWinStatistics(purchasedLottos)
        winStatisticsResult(winStatistics, LottoWinStatisticsFormatter())

        profitRateResult(profitRate)
    }

    private fun makePurchasedLottos(purchaseMoney: PurchaseMoney, lottoManualCount: LottoCount): PurchasedLottos {
        val manualLottoGenerator = InputLottoGenerator { initSingleLotto() }
        val autoLottoGenerator = RandomLottoGenerator()
        outputView.printMessage(OutputView.MESSAGE_LOTTO_MANUAL)
        return LottoMachine(manualLottoGenerator, autoLottoGenerator).makePurchasedLottos(
            purchaseMoney, lottoManualCount
        )
    }

    private fun makeWinStatistics(purchasedLottos: PurchasedLottos): WinStatistics {
        val winLotto = initWinLotto()
        return winLotto.makeWinStatistics(purchasedLottos)
    }

    private fun purchasedResult(
        purchasedLottos: PurchasedLottos,
        lottoManualCount: LottoCount,
        lottoAutoCount: LottoCount
    ) {
        outputView.gameResult(purchasedLottos, lottoManualCount, lottoAutoCount)
    }

    private fun winStatisticsResult(winStatistics: WinStatistics, winStatisticsFormatter: WinStatisticsFormatter) {
        outputView.winStatisticsResult(winStatistics, winStatisticsFormatter)
    }

    private fun profitRateResult(profitRate: ProfitRate) {
        outputView.profitRateResult(profitRate)
    }
}
