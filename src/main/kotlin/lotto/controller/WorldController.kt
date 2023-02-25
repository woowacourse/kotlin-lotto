package lotto.controller

import lotto.entity.LottoCount
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

class WorldController {
    private val outputView = OutputView()
    private val inputView = InputView(outputView)
    private val manualLottoController = ManualLottoController(inputView)

    private fun initWinLotto(): WinLotto {
        return tryAndRerun {
            val winNumber = inputView.readWinNumber(OutputView.MESSAGE_WIN_NUMBER)
            val bonus = inputView.readBonus(OutputView.MESSAGE_BONUS)
            WinLotto(winNumber, bonus)
        }
    }

    fun processLotto() {
        val purchaseMoney = inputView.readPurchaseMoney(OutputView.MESSAGE_INPUT_MONEY)
        val lottoManualCount = inputView.readLottoManualCount(OutputView.MESSAGE_LOTTO_MANUAL_COUNT)
        val lottoAutoCount = purchaseMoney.calculateAutoLottoCount(lottoManualCount)

        outputView.printMessage(OutputView.MESSAGE_LOTTO_MANUAL)
        val purchasedLottos = makePurchasedLottos(lottoManualCount, lottoAutoCount)
        outputView.gameResult(purchasedLottos, lottoManualCount, lottoAutoCount)

        val winStatistics = makeWinStatistics(purchasedLottos)
        outputView.winStatisticsResult(winStatistics, LottoWinStatisticsFormatter())

        val profitRate = winStatistics.calculateProfitRate(purchaseMoney)
        outputView.profitRateResult(profitRate)
    }

    private fun makePurchasedLottos(lottoManualCount: LottoCount, lottoAutoCount: LottoCount): PurchasedLottos {
        val manualLottoGenerator = InputLottoGenerator(manualLottoController)
        val autoLottoGenerator = RandomLottoGenerator()
        return LottoMachine(manualLottoGenerator, autoLottoGenerator).producePurchasedLottos(
            lottoManualCount, lottoAutoCount
        )
    }

    private fun makeWinStatistics(purchasedLottos: PurchasedLottos): WinStatistics {
        val winLotto = initWinLotto()
        return winLotto.makeWinStatistics(purchasedLottos)
    }
}
