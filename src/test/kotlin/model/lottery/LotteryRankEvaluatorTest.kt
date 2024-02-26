package model.lottery

import WinningLottery
import model.WinningRank
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LotteryRankEvaluatorTest {
    @Test
    fun `로또 번호와 당첨번호, 보너스 번호를 비교하여 당첨 결과를 알려준다_리팩토링 이전`() {
        val lotteryRankEvaluator = LotteryRankEvaluator()
        val lottery = Lottery.of(1, 2, 3, 4, 5, 6)
        val winningLottery = Lottery.of(1, 2, 3, 4, 5, 7)
        val bonusNumber = LotteryNumber(6)

        val winningResult = lotteryRankEvaluator.evaluate(lottery, winningLottery, bonusNumber)
        assertThat(winningResult).isEqualTo(WinningRank.SECOND)
    }

    @Test
    fun `로또 번호와 당첨번호, 보너스 번호를 비교하여 당첨 결과를 알려준다`() {
        val lotteryRankEvaluator = LotteryRankEvaluator()

        val lottery = Lottery.of(1, 2, 3, 4, 5, 6)
        val winningLottery =
            WinningLottery(
                Lottery.of(1, 2, 3, 4, 5, 7),
                LotteryNumber(6),
            )

        val winningRank = lotteryRankEvaluator.evaluate(lottery, winningLottery)
        assertThat(winningRank).isEqualTo(WinningRank.SECOND)
    }
}
