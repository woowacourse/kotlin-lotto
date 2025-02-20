package lotto

import lotto.global.Message
import lotto.global.Rank
import java.lang.IllegalArgumentException

class LottoView {
    fun getLottoAmount(): Int {
        println(Message.ASK_AMOUNT.msg)
        val userInput = checkLottoAmount(readln())
        println("${userInput}개를 구매했습니다.")
        return userInput
    }

    fun printLotto(manyLotto: List<List<Int>>) {
        for (lotto in manyLotto) println(lotto)
    }

    fun getWinningLotto(): List<Int> {
        println(Message.ASK_WINNING_LOTTO.msg)
        return checkValidLotto(readln())
    }

    fun getBonusNum(): Int {
        println(Message.ASK_BONUS_BALL.msg)
        return checkValidBonusNum(readln())
    }

    fun printResult(rankMap: Map<Rank, Int>) {
        val rate = LottoService.getRate(rankMap)
        println(
            """
            당첨 통계
            ---------
            3개 일치 (5000원)- ${rankMap[Rank.FIFTH]}개
            4개 일치 (50000원)- ${rankMap[Rank.FOURTH]}개
            5개 일치 (1500000원)- ${rankMap[Rank.THIRD]}개
            5개 일치, 보너스 볼 일치(30000000원) - ${rankMap[Rank.SECOND]}개
            6개 일치 (2000000000원)- ${rankMap[Rank.FIRST]}개
            총 수익률은 ${rate}입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)
            """.trimIndent(),
        )
    }

    companion object {
        fun checkLottoAmount(input: String): Int {
            try {
                return input.toInt() / 1000
            } catch (e: NumberFormatException) {
                throw IllegalArgumentException(Message.ERR_INVALID_FORMAT.msg)
            }
        }

        fun checkValidLotto(input: String): List<Int> {
            try {
                var winningLotto = input.split(",").map { number -> number.toInt() }
                if (winningLotto.size != 6) throw IllegalArgumentException(Message.ERR_NOT_SIX_ELEMENTS.msg)
                winningLotto = winningLotto.filter { number -> number in 1..45 }
                if (winningLotto.size != 6) throw IllegalArgumentException(Message.ERR_NOT_IN_RANGE.msg)
                winningLotto = winningLotto.toSet().toList()
                if (winningLotto.size != 6) throw IllegalArgumentException(Message.ERR_ELEMENT_DUPLICATED.msg)

                return winningLotto
            } catch (e: NumberFormatException) {
                throw IllegalArgumentException(Message.ERR_INVALID_FORMAT.msg)
            }
        }

        fun checkValidBonusNum(input: String): Int {
            try {
                val rawInput = input.toInt()
                if (rawInput !in 1..45) throw IllegalArgumentException(Message.ERR_NOT_IN_RANGE.msg)
                return rawInput
            } catch (e: NumberFormatException) {
                throw IllegalArgumentException(Message.ERR_INVALID_FORMAT.msg)
            }
        }
    }
}
