package lotto.controller

import lotto.model.LottoGenerator
import lotto.model.Lottos
import lotto.util.Constant
import lotto.view.insertCostMessage
import lotto.view.insertWinNumbers
import lotto.view.purchaseCountMessage
import lotto.view.showLotto
import java.io.BufferedReader
import java.io.InputStreamReader

private val br = BufferedReader(InputStreamReader(System.`in`))

class Controller {
    private val lottoGenerator = LottoGenerator()
    fun run() {
        insertCostMessage()
        val charge = inputCharge()
        val count = charge / Constant.LOTTO_PRICE.toInt()
        purchaseCountMessage(count)
        val lottos = makeLottos(count) ?: return

        insertWinNumbers()
    }

    private fun makeLottos(count: Int): Lottos? {
        return try {
            Lottos(
                lottos = List(count) {
                    val lotto = lottoGenerator.generateLotto()
                    showLotto(lotto)
                    lotto
                }
            )
        } catch (e: IllegalArgumentException) {
            println(e)
            return null
        }
    }

    private fun inputCharge(): Int {
        return try {
            readlnOrNull()?.toIntOrNull()
                ?: throw (IllegalArgumentException("잘못 된 입력 값입니다."))
        } catch (e: IllegalArgumentException) {
            println(e.message)
            inputCharge()
        }
    }
}
