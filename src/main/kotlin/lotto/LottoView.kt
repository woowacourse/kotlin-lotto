package lotto

import lotto.domain.Lotto
import lotto.global.Config.LOTTO_PRICE
import lotto.global.LottoValidator.requireLottoAmount
import lotto.global.LottoValidator.requireValidBonusNum
import lotto.global.Message
import lotto.global.Rank

class LottoView {
    fun getLottoAmount(): Int {
        println(Message.ASK_AMOUNT.msg)
        val userInput = requireLottoAmount(readln())
        println("${userInput}개를 구매했습니다.")
        return userInput.toInt() / LOTTO_PRICE
    }

    fun printLotto(manyLotto: List<Lotto>) {
        for (lotto in manyLotto) println(lotto)
    }

    fun getWinningLotto(): Lotto {
        println(Message.ASK_WINNING_LOTTO.msg)
        return Lotto(readln())
    }

    fun getBonusNum(): Int {
        println(Message.ASK_BONUS_BALL.msg)
        return requireValidBonusNum(readln()).toInt()
    }

    fun printResult(rankMap: Map<Rank, Int>) {
        val rate = LottoService.getRate(rankMap)
        println(
            """
            당첨 통계
            ---------
            ${Rank.FIFTH.countOfMatch}개 일치 (${Rank.FIFTH.winningMoney}원)- ${rankMap[Rank.FIFTH]}개
            ${Rank.FOURTH.countOfMatch}개 일치 (${Rank.FOURTH.winningMoney}원)- ${rankMap[Rank.FOURTH]}개
            ${Rank.THIRD.countOfMatch}개 일치 (${Rank.THIRD.winningMoney}원)- ${rankMap[Rank.THIRD]}개
            ${Rank.SECOND.countOfMatch}개 일치, 보너스 볼 일치(${Rank.SECOND.winningMoney}원) - ${rankMap[Rank.SECOND]}개
            ${Rank.FIRST.countOfMatch}개 일치 (${Rank.FIRST.winningMoney}원)- ${rankMap[Rank.FIRST]}개
            총 수익률은 ${rate}입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)
            """.trimIndent(),
        )
    }
}
