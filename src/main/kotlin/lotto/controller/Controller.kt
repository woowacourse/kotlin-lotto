package lotto.controller

import lotto.model.Lottos
import lotto.model.WinningNumber
import lotto.util.LottoRule
import lotto.view.outputCalculationOfYield
import lotto.view.inputBonusNumbers
import lotto.view.inputCostMessage
import lotto.view.inputWinNumbers
import lotto.view.outputWinningNumber
import lotto.view.outputPurchaseCount
import lotto.view.outputLotto
import lotto.view.outputWinningStatistics

class Controller {

    fun run() {
        inputCostMessage()
        val charge = inputCharge()
        val count = charge / LottoRule.LOTTO_PRICE.toInt()
        outputPurchaseCount(count)
        val lottos = Lottos(count)
        lottos.getLottos().forEach {
            outputLotto(it)
        }

        inputWinNumbers()
        val winning = inputWinning()
        inputBonusNumbers()
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
