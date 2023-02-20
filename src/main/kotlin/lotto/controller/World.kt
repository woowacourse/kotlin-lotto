package lotto.controller

import lotto.entity.Lotto
import lotto.entity.LottoCount
import lotto.entity.LottoNumber
import lotto.entity.PurchaseMoney
import lotto.entity.PurchasedLottos
import lotto.entity.WinLotto
import lotto.entity.WinStatistics
import lotto.misc.tryAndRerun
import lotto.model.LottoProfitRateCalculator
import lotto.model.RandomLottoGenerator
import lotto.model.SequentialLottoNumberGenerator
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
        val purchaseMoney = initPurchaseMoney()
        val lottoManualCount = initLottoManualCount(purchaseMoney)
        val lottoAutoCount = purchaseMoney.calculateLottoCount(lottoManualCount)
        val manualLottos = initManualLottos(lottoManualCount)
        val purchasedLottos = makePurchasedLotto(manualLottos, lottoAutoCount)
        processLottoGame(purchasedLottos, lottoManualCount, lottoAutoCount)
        processResult(purchasedLottos, purchaseMoney)
    }

    private fun makePurchasedLotto(manualLottos: List<Lotto>, lottoAutoCount: LottoCount): PurchasedLottos {
        val manualPurchasedLottos =
            PurchasedLottos.from(LottoCount(manualLottos.size), SequentialLottoNumberGenerator(manualLottos))
        val autoPurchasedLottos = PurchasedLottos.from(lottoAutoCount, RandomLottoGenerator())
        return manualPurchasedLottos.merge(autoPurchasedLottos)
    }

    private fun processResult(purchasedLottos: PurchasedLottos, purchaseMoney: PurchaseMoney) {
        val winLotto = initWinLotto()
        val winStatistics = purchasedLottos.makeWinStatistics(winLotto)
        val winStatisticsFormatter = LottoWinStatisticsFormatter()
        processWinStatistics(winStatistics, winStatisticsFormatter)
        processProfitRate(winStatistics, purchaseMoney)
    }

    private fun processLottoGame(
        purchasedLottos: PurchasedLottos,
        lottoManualCount: LottoCount,
        lottoAutoCount: LottoCount
    ) {
        outputView.printMessage(OutputView.MESSAGE_PURCHASE_COUNT, lottoManualCount.value, lottoAutoCount.value)
        outputView.gameResult(purchasedLottos)
    }

    private fun processWinStatistics(winStatistics: WinStatistics, winStatisticsFormatter: WinStatisticsFormatter) {
        outputView.winStatisticsResult(winStatistics, winStatisticsFormatter)
    }

    private fun processProfitRate(winStatistics: WinStatistics, purchaseMoney: PurchaseMoney) {
        val profitRateCalculator = LottoProfitRateCalculator()
        val winMoney = winStatistics.calculateWinMoney()
        val profitRate = profitRateCalculator.calculate(purchaseMoney, winMoney)
        outputView.profitRateResult(profitRate)
    }
}
