package lotto.view

import lotto.domain.Lotto
import lotto.domain.Rank
import lotto.domain.RankScoreBoard
import lotto.domain.UserInput
import lotto.global.LottoException
import lotto.global.UserInputResult

class LottoView {
    fun getBuyAmount(): UserInputResult<Int> {
        println(ASK_AMOUNT)
        val input = readln()
        LottoException.ERR_INVALID_FORMAT.userInput = input
        val result = input.toIntOrNull() ?: return UserInputResult.Failure(LottoException.ERR_INVALID_FORMAT)
        return UserInputResult.Success(result)
    }

    fun getManualLottoCount(): UserInputResult<Int> {
        val input = readln()
        println(ASK_MANUAL_LOTTO_AMOUNT)
        LottoException.ERR_INVALID_FORMAT.userInput = input
        val result = input.toIntOrNull() ?: return UserInputResult.Failure(LottoException.ERR_INVALID_FORMAT)
        return UserInputResult.Success(result)
    }

    fun getManualLotto(): UserInputResult<List<List<Int>>> {
        println(ASK_MANUAL_LOTTO)
        val input = readln()
        LottoException.ERR_INVALID_FORMAT.userInput = input
        val result =
            runCatching {
                input.split("\\n").map {
                    it.split(",").map { it.toInt() }
                }
            }.getOrNull() ?: return UserInputResult.Failure(LottoException.ERR_INVALID_FORMAT)
        return UserInputResult.Success(result)
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
        println(ASK_WINNING_LOTTO)
        val input = readln()
        LottoException.ERR_INVALID_FORMAT.userInput = input
        val userInput =
            runCatching {
                input.split(",").map { it.toInt() }
            }.getOrNull() ?: return UserInputResult.Failure(LottoException.ERR_INVALID_FORMAT)
        return UserInputResult.Success(userInput)
    }

    fun getBonusNum(): UserInputResult<Int> {
        println(ASK_BONUS_BALL)
        val input = readln()
        LottoException.ERR_INVALID_FORMAT.userInput = input
        val userInput = input.toIntOrNull() ?: return UserInputResult.Failure(LottoException.ERR_INVALID_FORMAT)
        return UserInputResult.Success(userInput)
    }

    fun printResult(rankScoreBoard: RankScoreBoard) {
        val rate = rankScoreBoard.getRate()
        println(
            """
            당첨 통계
            ---------
            ${Rank.FIFTH.countOfMatch}개 일치 (${Rank.FIFTH.winningMoney}원)- ${rankScoreBoard.map[Rank.FIFTH]}개
            ${Rank.FOURTH.countOfMatch}개 일치 (${Rank.FOURTH.winningMoney}원)- ${rankScoreBoard.map[Rank.FOURTH]}개
            ${Rank.THIRD.countOfMatch}개 일치 (${Rank.THIRD.winningMoney}원)- ${rankScoreBoard.map[Rank.THIRD]}개
            ${Rank.SECOND.countOfMatch}개 일치, 보너스 볼 일치(${Rank.SECOND.winningMoney}원) - ${rankScoreBoard.map[Rank.SECOND]}개
            ${Rank.FIRST.countOfMatch}개 일치 (${Rank.FIRST.winningMoney}원)- ${rankScoreBoard.map[Rank.FIRST]}개
            총 수익률은 ${rate}입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)
            """.trimIndent(),
        )
    }

    fun printMessage(lottoException: LottoException) {
        println(lottoException.msg + ". 입력 값 : ${lottoException.userInput}")
    }

    companion object {
        private const val ASK_AMOUNT = "구입금액을 입력해 주세요."
        private const val ASK_WINNING_LOTTO = "지난 주 당첨 번호를 입력해 주세요."
        private const val ASK_BONUS_BALL = "보너스 볼을 입력해 주세요."
        private const val ASK_MANUAL_LOTTO_AMOUNT = "수동으로 구매할 로또 수를 입력해 주세요."
        private const val ASK_MANUAL_LOTTO = "수동으로 구매할 번호를 입력해 주세요."
    }
}
