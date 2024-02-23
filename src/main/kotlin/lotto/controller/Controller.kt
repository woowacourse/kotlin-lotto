package lotto.controller

import lotto.model.Lottos
import lotto.model.WinningNumber
import lotto.util.LottoRule
import lotto.view.inputBonusNumber
import lotto.view.inputCostMessage
import lotto.view.inputWinNumbers
import lotto.view.outputCalculationOfYield
import lotto.view.outputLotto
import lotto.view.outputPurchaseCount
import lotto.view.outputWinningNumber
import lotto.view.outputWinningStatistics

class Controller {

    fun run() {
        val charge = inputCostMessage()
        val count = charge / LottoRule.LOTTO_PRICE.toInt()
        outputPurchaseCount(count)
        val lottos = Lottos(count)
        lottos.getLottos().forEach {
            outputLotto(it)
        }

        println()
        val winning = inputWinNumbers()
        println()
        val bonusNumber = inputBonusNumber()
        val winningNumber = WinningNumber(
            lotto = winning,
            bonusNumber = bonusNumber
        )

        val prize = lottos.matchLottos(winningNumber)
        outputWinningStatistics()
        outputWinningNumber(prize)
        outputCalculationOfYield(prize, charge.toDouble())
    }
}
