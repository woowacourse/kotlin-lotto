package lotto.view

import lotto.domain.Lotto
import lotto.domain.Rank
import lotto.domain.ScoreRankMap
import lotto.domain.UserInput
import lotto.global.Message
import java.lang.IllegalArgumentException

class LottoView {
    fun getUserInput(): UserInput {
        val buyAmount = getBuyAmount()
        UserInput.requireBuyAmount(buyAmount)
        val manualLottoCount = getManualLottoCount()
        UserInput.requireMaxManualLottoCount(manualLottoCount, buyAmount)
        if (manualLottoCount != 0) {
            val manualLotto = getManualLotto()
            UserInput.requireIsEqualToManualLottoSize(manualLottoCount, manualLotto)
            return UserInput(buyAmount, manualLottoCount, manualLotto)
        }
        return UserInput(buyAmount, manualLottoCount)
    }

    private fun getBuyAmount(): Int {
        println(Message.ASK_AMOUNT.msg)
        val userInput = readln()
        require(userInput.toIntOrNull() != null) { Message.ERR_INVALID_FORMAT.msg }

        return userInput.toInt()
    }

    private fun getManualLottoCount(): Int {
        println(Message.ASK_MANUAL_LOTTO_AMOUNT.msg)
        val userInput = readln()
        require(userInput.toIntOrNull() != null) { Message.ERR_INVALID_FORMAT.msg }
        return userInput.toInt()
    }

    private fun getManualLotto(): List<List<Int>> {
        println(Message.ASK_MANUAL_LOTTO.msg)
        val userInput = readln()
        runCatching {
            userInput.split("\n").map {
                it.split(",").map { it.toInt() }
            }
        }.getOrNull() ?: throw IllegalArgumentException(Message.ERR_INVALID_FORMAT.msg)
        return userInput.split("\n").map {
            it.split(",").map { it.toInt() }
        }
    }

    fun printLotto(manyLotto: List<Lotto>) {
        for (lotto in manyLotto) println(lotto.value.toString())
    }

    fun getWinningLotto(): List<Int> {
        println(Message.ASK_WINNING_LOTTO.msg)
        val userInput = readln()
        runCatching {
            userInput.split(",").map { it.toInt() }
        }.getOrNull() ?: throw IllegalArgumentException(Message.ERR_INVALID_FORMAT.msg)
        return userInput.split(",").map { it.toInt() }
    }

    fun getBonusNum(): Int {
        println(Message.ASK_BONUS_BALL.msg)
        val userInput = readln()
        require(userInput.toIntOrNull() != null) { Message.ERR_INVALID_FORMAT.msg }
        return userInput.toInt()
    }

    fun printResult(scoreRankMap: ScoreRankMap) {
        val rate = scoreRankMap.getRate()
        println(
            """
            당첨 통계
            ---------
            ${Rank.FIFTH.countOfMatch}개 일치 (${Rank.FIFTH.winningMoney}원)- ${scoreRankMap.map[Rank.FIFTH]}개
            ${Rank.FOURTH.countOfMatch}개 일치 (${Rank.FOURTH.winningMoney}원)- ${scoreRankMap.map[Rank.FOURTH]}개
            ${Rank.THIRD.countOfMatch}개 일치 (${Rank.THIRD.winningMoney}원)- ${scoreRankMap.map[Rank.THIRD]}개
            ${Rank.SECOND.countOfMatch}개 일치, 보너스 볼 일치(${Rank.SECOND.winningMoney}원) - ${scoreRankMap.map[Rank.SECOND]}개
            ${Rank.FIRST.countOfMatch}개 일치 (${Rank.FIRST.winningMoney}원)- ${scoreRankMap.map[Rank.FIRST]}개
            총 수익률은 ${rate}입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)
            """.trimIndent(),
        )
    }
}
