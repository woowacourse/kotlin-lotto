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
import lotto.model.LottoMachine
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

    private fun initLottoManualCount(purchaseMoney: PurchaseMoney): LottoCount {
        return tryAndRerun {
            outputView.printMessage(OutputView.MESSAGE_LOTTO_MANUAL_COUNT)
            LottoCount.from(inputView.readInt(), purchaseMoney)
        } as LottoCount
    }

    private fun initSingleLotto(): Lotto {
        return tryAndRerun {
            Lotto(inputView.readIntList())
        } as Lotto
    }

    private fun initManualLottos(lottoCount: LottoCount): List<Lotto> {
        outputView.printMessage(OutputView.MESSAGE_LOTTO_MANUAL)
        return (0 until lottoCount.value).map {
            initSingleLotto()
        }
    }

    private fun initWinLotto(): WinLotto {
        return tryAndRerun {
            val winNumber = initWinNumber()
            val bonus = initBonus()
            WinLotto(winNumber, bonus)
        } as WinLotto
    }

    fun processLotto() {
        val lottoMachine = makeLottoMachine()
        purchasedResult(lottoMachine)

        val winStatistics = makeWinStatistics(lottoMachine)
        winStatisticsResult(winStatistics, LottoWinStatisticsFormatter())

        val profitRate = lottoMachine.makeProfitRate(winStatistics)
        profitRateResult(profitRate)
    }

    private fun makeLottoMachine(): LottoMachine {
        val purchaseMoney = initPurchaseMoney()
        val lottoManualCount = initLottoManualCount(purchaseMoney)
        val manualLottos = PurchasedLottos(initManualLottos(lottoManualCount))
        return LottoMachine(purchaseMoney, lottoManualCount, manualLottos)
    }

    private fun makeWinStatistics(lottoMachine: LottoMachine): WinStatistics {
        val winLotto = initWinLotto()
        return lottoMachine.purchasedLottos.makeWinStatistics(winLotto)
    }

    private fun purchasedResult(lottoMachine: LottoMachine) {
        outputView.gameResult(lottoMachine)
    }

    private fun winStatisticsResult(winStatistics: WinStatistics, winStatisticsFormatter: WinStatisticsFormatter) {
        outputView.winStatisticsResult(winStatistics, winStatisticsFormatter)
    }

    private fun profitRateResult(profitRate: ProfitRate) {
        outputView.profitRateResult(profitRate)
    }
}
