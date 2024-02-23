package lotto.controller

import lotto.model.LottoGenerator
import lotto.model.LottoNumber
import lotto.model.Lottos
import lotto.model.WinningNumber
import lotto.util.Constant
import lotto.view.calculationOfYield
import lotto.view.insertBonusNumbers
import lotto.view.insertCostMessage
import lotto.view.insertWinNumbers
import lotto.view.outputWinningNumber
import lotto.view.purchaseCountMessage
import lotto.view.showLotto
import lotto.view.winningStatistics

class Controller {
    private val lottoGenerator = LottoGenerator()

    fun run() {
        insertCostMessage()
        val charge = Verifier.inputCharge()
        val count = charge / Constant.LOTTO_PRICE.toInt()
        purchaseCountMessage(count)
        val lottos = makeLottos(count)
        insertWinNumbers()
        val winning = Verifier.inputWinning()
        insertBonusNumbers()
        val bonusNumber = LottoNumber(Verifier.inputBonusNumber())
        val winningNumber =
            WinningNumber(
                lotto = winning,
                bonusNumber = bonusNumber,
            )
        val prize = lottos.matchlottos(winningNumber)
        winningStatistics()
        outputWinningNumber(prize)
        calculationOfYield(prize, charge.toDouble())
    }

    private fun makeLottos(count: Int): Lottos {
        return Lottos(
            lottos =
                List(count) {
                    val lotto = lottoGenerator.generateLotto()
                    showLotto(lotto)
                    lotto
                },
        )
    }
}
