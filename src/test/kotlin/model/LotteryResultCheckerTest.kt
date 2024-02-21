package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class LotteryResultCheckerTest {
    @Test
    fun `로또 번호와 당첨번호, 보너스 번호를 비교하여 당첨 결과를 알려준다`() {
        val lotteryResultChecker = LotteryResultChecker()
        val lottery = Lottery(
            listOf(
                LotteryNumber(1),
                LotteryNumber(2),
                LotteryNumber(3),
                LotteryNumber(4),
                LotteryNumber(5),
                LotteryNumber(6),
            )
        )
        val winningLottery = Lottery(
            listOf(
                LotteryNumber(1),
                LotteryNumber(2),
                LotteryNumber(3),
                LotteryNumber(4),
                LotteryNumber(5),
                LotteryNumber(7),
            )
        )
        val bonusNumber = LotteryNumber(6)

        val winningResult = lotteryResultChecker.judge(lottery, winningLottery, bonusNumber)
        assertThat(winningResult).isEqualTo(WinningRank.SECOND)
    }

    @Test
    fun `로또와 당첨번호가 일치하는 숫자의 갯수를 알려준다`() {
        val lotteryResultChecker = LotteryResultChecker()
        val lottery = Lottery(
            listOf(
                LotteryNumber(1),
                LotteryNumber(2),
                LotteryNumber(3),
                LotteryNumber(4),
                LotteryNumber(5),
                LotteryNumber(6),
            )
        )
        val winningLottery = Lottery(
            listOf(
                LotteryNumber(1),
                LotteryNumber(2),
                LotteryNumber(3),
                LotteryNumber(4),
                LotteryNumber(8),
                LotteryNumber(9),
            )
        )
        assertThat(lotteryResultChecker.match(lottery, winningLottery))
            .isEqualTo(4)
    }

    @Test
    fun `로또에 보너스번호가 있는지 여부를 알려준다`() {
        val lotteryResultChecker = LotteryResultChecker()
        val lottery = Lottery(
            listOf(
                LotteryNumber(1),
                LotteryNumber(2),
                LotteryNumber(3),
                LotteryNumber(4),
                LotteryNumber(5),
                LotteryNumber(6),
            )
        )
        val winningLottery = Lottery(
            listOf(
                LotteryNumber(1),
                LotteryNumber(2),
                LotteryNumber(3),
                LotteryNumber(4),
                LotteryNumber(8),
                LotteryNumber(9),
            )
        )
        val bonusNumber = LotteryNumber.bonusNumber(winningLottery, 6)
        assertTrue(lotteryResultChecker.bonusMatch(lottery, bonusNumber))
    }
}
