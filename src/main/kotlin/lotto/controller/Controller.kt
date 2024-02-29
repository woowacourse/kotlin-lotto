package lotto.controller

import lotto.model.Lotto
import lotto.model.LottoGenerator
import lotto.model.Lottos
import lotto.model.WinningNumber
import lotto.view.inputBonusNumber
import lotto.view.inputCostMessage
import lotto.view.inputLottoNumbers
import lotto.view.inputManualLottoNumbers
import lotto.view.inputManualNumber
import lotto.view.inputWinningLottoNumbers
import lotto.view.outputCalculationOfYield
import lotto.view.outputLottos
import lotto.view.outputNewLine
import lotto.view.outputPurchaseCount
import lotto.view.outputWinningNumber
import lotto.view.outputWinningStatistics

class Controller {

    fun run() {
        val charge = inputCostMessage()
        val lottoCount = charge / Lotto.LOTTO_PRICE.toInt()

        val manualLottoCount = inputManualNumber(lottoCount)
        val autoLottoCount = lottoCount - manualLottoCount

        outputNewLine()
        val lottos = Lottos()
        if (manualLottoCount > 0) {
            inputManualLottoNumbers()
            val manualLottos = List(manualLottoCount) {
                inputLottoNumbers()
            }
            lottos.purchaseLottos(manualLottos)
        }

        val autoLottos = List(autoLottoCount) {
            LottoGenerator.generateLotto()
        }
        lottos.purchaseLottos(autoLottos)

        outputPurchaseCount(manualLottoCount, autoLottoCount)
        outputLottos(lottos.getLottos())

        outputNewLine()
        inputWinningLottoNumbers()
        val winningLottos = inputLottoNumbers()
        outputNewLine()
        val bonusNumber = inputBonusNumber(winningLottos)
        val winningNumber = WinningNumber(
            lotto = winningLottos,
            bonusNumber = bonusNumber
        )

        val prize = lottos.matchLottos(winningNumber)
        outputWinningStatistics()
        outputWinningNumber(prize.getUserPrize())
        val prizeCalculate = prize.prizeRateCalculate(
            prize = prize.prizeCalculate(),
            charge = charge.toDouble()
        )
        prizeCalculate?.let {
            outputCalculationOfYield(it)
        }
    }
}
