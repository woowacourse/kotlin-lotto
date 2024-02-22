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
        val charge = inputCharge()
        val count = charge / Constant.LOTTO_PRICE.toInt()
        purchaseCountMessage(count)
        val lottos = makeLottos(count) ?: return
        insertWinNumbers()
        val winning = inputWinning()
        insertBonusNumbers()
        val bonusNumber = inputBonusNumber()

        val winningNumber = WinningNumber(
            lotto = winning,
            bonusNumber = bonusNumber
        )
        val prize = lottos.matchlottos(winningNumber)
        winningStatistics()
        outputWinningNumber(prize)
        calculationOfYield(prize,charge.toDouble())
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

    private fun inputWinning(): Lotto {
        return try {
            Lotto(
                readlnOrNull()
                    ?.split(SEPARATOR)
                    ?.map {
                        it.trim().toIntOrNull() ?: throw (IllegalArgumentException("로또 넘버는 숫자를 입력해야 합니다."))
                    }?.toSet()
                    ?: throw (IllegalArgumentException("잘못 된 로또 값입니다."))
            )
        } catch (e: IllegalArgumentException) {
            println(e.message)
            inputWinning()
        }
    }

    private fun inputBonusNumber(): Int {
        return try {
            readlnOrNull()?.toIntOrNull()
                ?: throw (IllegalArgumentException("잘못 된 입력 값입니다."))
        } catch (e: IllegalArgumentException) {
            println(e.message)
            inputBonusNumber()
        }
    }
}
