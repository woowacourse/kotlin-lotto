package lotto.controller

import lotto.model.Lotto
import lotto.model.Lottos
import lotto.model.WinningNumber
import lotto.view.inputBonusNumber
import lotto.view.inputCostMessage
import lotto.view.inputWinNumbers
import lotto.view.outputCalculationOfYield
import lotto.view.outputLottos
import lotto.view.outputNewLine
import lotto.view.outputPurchaseCount
import lotto.view.outputWinningNumber
import lotto.view.outputWinningStatistics

class Controller {

    fun run() {
        val charge = inputCostMessage()
        val count = charge / Lotto.LOTTO_PRICE.toInt()
        outputPurchaseCount(count)
        val lottos = Lottos(count)
        outputLottos(lottos.getLottos())

        outputNewLine()
        val winning = inputWinNumbers()
        outputNewLine()
        val bonusNumber = inputBonusNumber()
        val winningNumber = WinningNumber(
            lotto = Lotto(winning),
            bonusNumber = bonusNumber
        )

        val prize = lottos.matchLottos(winningNumber)
        outputWinningStatistics()
        outputWinningNumber(prize.getUserPrize())
        val prizeCalculate = prize.prizeRateCalculate(
            prize = prize.prizeCalculate(),
            charge = charge.toDouble()
        )
        outputCalculationOfYield(prizeCalculate)
    }
}
