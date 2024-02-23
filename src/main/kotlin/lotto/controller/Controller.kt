package lotto.controller

import lotto.model.Lottos
import lotto.model.WinningNumber
import lotto.util.Constant
import lotto.view.calculationOfYield
import lotto.view.inputBonusNumbers
import lotto.view.inputCostMessage
import lotto.view.inputWinNumbers
import lotto.view.outputWinningNumber
import lotto.view.purchaseCountMessage
import lotto.view.showLotto
import lotto.view.winningStatistics

class Controller {

    fun run() {
        inputCostMessage()
        val charge = Verifier.inputCharge()
        val count = charge / Constant.LOTTO_PRICE.toInt()
        purchaseCountMessage(count)
        val lottos = Lottos(count)
        lottos.getLottos().forEach {
            showLotto(it)
        }
        inputWinNumbers()
        val winning = Verifier.inputWinning()
        inputBonusNumbers()
        val bonusNumber = Verifier.inputBonusNumber()

        val winningNumber = WinningNumber(
            lotto = winning,
            bonusNumber = bonusNumber
        )
        val prize = lottos.matchlottos(winningNumber)
        winningStatistics()
        outputWinningNumber(prize)
        calculationOfYield(prize, charge.toDouble())
    }
}
