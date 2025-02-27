package lotto.view

import lotto.domain.Lotto
import lotto.domain.Rank
import lotto.domain.ScoreRankMap
import lotto.domain.UserInput
import lotto.global.Message
import lotto.global.UserInputResult
import kotlin.system.exitProcess

class LottoView {
    fun getBuyAmount(): UserInputResult<Int> {
        println(Message.ASK_AMOUNT.msg)
        val input = readln().toIntOrNull() ?: return UserInputResult.Failure(Message.ERR_INVALID_FORMAT)
        val validAmount =
            UserInput.getValidBuyAmountOrNull(input)
                ?: return UserInputResult.Failure(Message.ERR_LESS_THAN_MINIMUM_PRICE)
        return UserInputResult.Success(validAmount)
    }

    fun getManualLottoCount(buyAmount: Int): UserInputResult<Int> {
        println(Message.ASK_MANUAL_LOTTO_AMOUNT.msg)
        val input = readln().toIntOrNull() ?: return UserInputResult.Failure(Message.ERR_INVALID_FORMAT)
        val validManualLottoCount =
            UserInput.getValidManualLottoCountOrNull(input, buyAmount)
                ?: return UserInputResult.Failure(Message.ERR_TOO_MANY_MANUAL_LOTTO)
        return UserInputResult.Success(validManualLottoCount)
    }

    fun getManualLotto(manualLottoCount: Int): UserInputResult<List<List<Int>>> {
        println(Message.ASK_MANUAL_LOTTO.msg)
        val input =
            runCatching {
                readln().split("\\n").map {
                    it.split(",").map { it.toInt() }
                }
            }.getOrNull() ?: return UserInputResult.Failure(Message.ERR_INVALID_FORMAT)
        val validManualLotto =
            UserInput.getValidManualLottoSizeOrNull(manualLottoCount, input)
                ?: return UserInputResult.Failure(Message.ERR_TOO_MANY_MANUAL_LOTTO)
        return UserInputResult.Success(validManualLotto)
    }

    fun printLotto(
        userInput: UserInput,
        autoLotto: List<Lotto>,
    ) {
        println("수동으로 ${userInput.manualLottoCount}장, 자동으로 ${userInput.automaticLottoCount}장을 구매했습니다")
        val manyLotto = autoLotto + userInput.manualLotto
        for (lotto in manyLotto) println(lotto.value.toString())
    }

    fun getWinningLotto(): UserInputResult<List<Int>> {
        println(Message.ASK_WINNING_LOTTO.msg)
        val userInput =
            runCatching {
                readln().split(",").map { it.toInt() }
            }.getOrNull() ?: return UserInputResult.Failure(Message.ERR_INVALID_FORMAT)
        return UserInputResult.Success(userInput)
    }

    fun getBonusNum(): UserInputResult<Int> {
        println(Message.ASK_BONUS_BALL.msg)
        val userInput = readln().toIntOrNull() ?: return UserInputResult.Failure(Message.ERR_INVALID_FORMAT)
        return UserInputResult.Success(userInput)
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

    fun exit(message: Message) {
        println(message.msg)
        exitProcess(0)
    }
}
