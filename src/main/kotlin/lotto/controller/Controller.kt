package lotto.controller

import lotto.model.*
import lotto.util.Constant
import lotto.view.*
import java.io.BufferedReader
import java.io.InputStreamReader

private val br = BufferedReader(InputStreamReader(System.`in`))

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

    private fun makeLottos(count: Int): Lottos {
        return Lottos(
            lottos = List(count) {
                val lotto = lottoGenerator.generateLotto()
                showLotto(lotto)
                lotto
            }
        )
    }
}
