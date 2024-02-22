package model.lottery

import model.WinningRank
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class LotteryRankEvaluatorTest {
    @Test
    fun `로또 번호와 당첨번호, 보너스 번호를 비교하여 당첨 결과를 알려준다`() {
        val lotteryRankEvaluator = LotteryRankEvaluator()
        val lottery = Lottery.of(1, 2, 3, 4, 5, 6)
        val winningLottery = Lottery.of(1, 2, 3, 4, 5, 7)
        val bonusNumber = LotteryNumber(6)

        val winningResult = lotteryRankEvaluator.evaluate(lottery, winningLottery, bonusNumber)
        assertThat(winningResult).isEqualTo(WinningRank.SECOND)
    }

    @Test
    fun `로또와 당첨번호가 일치하는 숫자의 갯수를 알려준다`() {
        val lotteryRankEvaluator = LotteryRankEvaluator()
        val lottery = Lottery.of(1, 2, 3, 4, 5, 6)

        val winningLottery = Lottery.of(1, 2, 3, 4, 8, 9)
        assertThat(lotteryRankEvaluator.match(lottery, winningLottery))
            .isEqualTo(4)
    }

    @Test
    fun `로또에 보너스번호가 있는지 여부를 알려준다`() {
        val lotteryRankEvaluator = LotteryRankEvaluator()
        val lottery = Lottery.of(1, 2, 3, 4, 5, 6)
        val winningLottery = Lottery.of(1, 2, 3, 4, 8, 9)

        val bonusNumber = LotteryNumber.bonusNumber(winningLottery, "6")
        assertTrue(lotteryRankEvaluator.bonusMatch(lottery, bonusNumber))
    }
}
