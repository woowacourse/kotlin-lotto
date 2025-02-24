package lotto

import lotto.domain.Lotto
import lotto.domain.Rank
import lotto.domain.ScoreRankMap
import lotto.global.Config.LOTTO_PRICE
import lotto.global.LottoUtil
import lotto.global.LottoValidator.requireLottoAmount
import lotto.global.LottoValidator.requireValidBonusNum
import lotto.global.LottoValidator.requireValidLotto
import lotto.global.Message

class LottoView {
    fun getLottoAmount(): Int {
        println(Message.ASK_AMOUNT.msg)
        val userInput = requireLottoAmount(readln()).toInt() / LOTTO_PRICE
        println("${userInput}개를 구매했습니다.")
        return userInput
    }

    fun printLotto(manyLotto: List<Lotto>) {
        for (lotto in manyLotto) println(lotto)
    }

    fun getWinningLotto(): List<Int> {
        println(Message.ASK_WINNING_LOTTO.msg)
        return requireValidLotto(readln()).split(",").map { it.toInt() }
    }

    fun getBonusNum(): Int {
        println(Message.ASK_BONUS_BALL.msg)
        return requireValidBonusNum(readln()).toInt()
    }

    fun printResult(scoreRankMap: ScoreRankMap) {
        val rate = LottoUtil.getRate(scoreRankMap)
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
