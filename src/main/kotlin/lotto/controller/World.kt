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
        val lottoAutoCount = purchaseMoney.calculateAutoLottoCount(lottoManualCount)

        val purchasedLottos = makePurchasedLottos(lottoManualCount, lottoAutoCount)
        purchasedResult(purchasedLottos, lottoManualCount, lottoAutoCount)

        val winStatistics = makeWinStatistics(purchasedLottos)
        winStatisticsResult(winStatistics)

        val profitRate = winStatistics.calculateProfitRate(purchaseMoney)
        profitRateResult(profitRate)
    }

    private fun makePurchasedLottos(lottoManualCount: LottoCount, lottoAutoCount: LottoCount): PurchasedLottos {
        val manualLottoGenerator = InputLottoGenerator { initSingleLotto() }
        val autoLottoGenerator = RandomLottoGenerator()
        outputView.printMessage(OutputView.MESSAGE_LOTTO_MANUAL)
        return LottoMachine(manualLottoGenerator, autoLottoGenerator).makePurchasedLottos(
            lottoManualCount, lottoAutoCount
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

    private fun winStatisticsResult(winStatistics: WinStatistics) {
        outputView.winStatisticsResult(winStatistics, LottoWinStatisticsFormatter())
    }

    private fun profitRateResult(profitRate: ProfitRate) {
        outputView.profitRateResult(profitRate)
    }
}
