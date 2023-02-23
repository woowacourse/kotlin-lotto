package lotto.controller

import lotto.entity.Lotto
import lotto.entity.LottoCount
import lotto.entity.LottoNumber
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

    private fun <T> getIntWithMessage(message: String, factory: (Int) -> T): T {
        return tryAndRerun {
            outputView.printMessage(message)
            factory(inputView.readInt())
        }
    }

    private fun <T> getIntList(factory: (List<Int>) -> T): T {
        return tryAndRerun {
            factory(inputView.readIntList())
        }
    }

    private fun <T> getIntListWithMessage(message: String, factory: (List<Int>) -> T): T {
        return tryAndRerun {
            outputView.printMessage(message)
            factory(inputView.readIntList())
        }
    }

    private fun initWinLotto(): WinLotto {
        return tryAndRerun {
            val winNumber = getIntListWithMessage(OutputView.MESSAGE_WIN_NUMBER) { Lotto(it) }
            val bonus = getIntWithMessage(OutputView.MESSAGE_BONUS) { LottoNumber.from(it) }
            WinLotto(winNumber, bonus)
        }
    }

    fun processLotto() {
        val purchaseMoney = getIntWithMessage(OutputView.MESSAGE_INPUT_MONEY) { PurchaseMoney(it) }
        val lottoManualCount = getIntWithMessage(OutputView.MESSAGE_LOTTO_MANUAL_COUNT) { LottoCount(it) }
        val lottoAutoCount = purchaseMoney.calculateAutoLottoCount(lottoManualCount)

        val purchasedLottos = makePurchasedLottos(lottoManualCount, lottoAutoCount)
        outputView.gameResult(purchasedLottos, lottoManualCount, lottoAutoCount)

        val winStatistics = makeWinStatistics(purchasedLottos)
        outputView.winStatisticsResult(winStatistics, LottoWinStatisticsFormatter())

        val profitRate = winStatistics.calculateProfitRate(purchaseMoney)
        outputView.profitRateResult(profitRate)
    }

    private fun makePurchasedLottos(lottoManualCount: LottoCount, lottoAutoCount: LottoCount): PurchasedLottos {
        val manualLottoGenerator = InputLottoGenerator { getIntList { Lotto(it) } }
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
}
