package lotto.controller

import lotto.model.Lotto
import lotto.model.LottoGenerator
import lotto.model.LottoNumber
import lotto.model.Lottos
import lotto.model.WinningNumber
import lotto.util.Constant.LOTTO_PRICE
import lotto.view.calculationOfYield
import lotto.view.insertBonusNumbers
import lotto.view.insertCostMessage
import lotto.view.insertManuallyLotto
import lotto.view.insertManuallyLottoCount
import lotto.view.insertWinNumbers
import lotto.view.outputWinningNumber
import lotto.view.printCombinedLottos
import lotto.view.purchaseCountMessage
import lotto.view.winningStatistics

class Controller {
    private val lottoGenerator = LottoGenerator()

    fun run() {
        insertCostMessage()
        val charge = readCharge()
        insertManuallyLottoCount()
        val manualCount = readHowManyManually(charge)
        val autocount = (charge / LOTTO_PRICE).toInt() - manualCount
        insertManuallyLotto()
        val manualLottos = makeManualLottos(List(manualCount) { readManualLottoNumber() })
        purchaseCountMessage(manualCount, autocount)
        val lottos = makeLottos(autocount)
        val combinedLottos = lottos.combineLottos(manualLottos, lottos)
        printCombinedLottos(combinedLottos)
        insertWinNumbers()
        val winning = readLottoNumber()
        insertBonusNumbers()
        val bonusNumber = LottoNumber.of(readLottoBonusNumber())
        val winningNumber = WinningNumber(lotto = winning, bonusNumber = bonusNumber)
        val prize = combinedLottos.matchlottos(winningNumber)
        winningStatistics()
        outputWinningNumber(prize)
        calculationOfYield(prize, charge.toDouble())
    }

    private fun makeLottos(count: Int): Lottos {
        return Lottos(
            List(count) {
                val lotto = lottoGenerator.generateLotto()
                lotto
            },
        )
    }

    private fun makeManualLottos(lottos: List<Lotto>): Lottos {
        return Lottos(lottos)
    }
}
