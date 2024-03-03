package model.lottery

import model.lottery.generator.ExplicitNumbersGenerator
import model.winning.WinningRank
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class WinningLotteryTest {
    @Test
    fun `보너스 번호가 당첨 번호에 포함된다면 예외를 던진다_리팩터링 이전`() {
        assertThrows<IllegalArgumentException> {
            WinningLottery(
                Lottery.of(1, 3, 5, 7, 9, 11),
                LotteryNumber.of(11),
            )
        }
    }

    @Test
    fun `보너스 번호가 당첨 번호에 포함되지 않으면 통과한다_리팩터링 이전`() {
        assertDoesNotThrow {
            WinningLottery(
                Lottery.of(1, 3, 5, 7, 9, 11),
                LotteryNumber.of(13),
            )
        }
    }

    @Test
    fun `로또 번호와 당첨번호, 보너스 번호를 비교하여 당첨 결과를 알려준다_리팩터링 이전`() {
        val winningLottery =
            WinningLottery(
                Lottery.of(1, 2, 3, 4, 5, 7),
                LotteryNumber.of(6),
            )
        val lottery = Lottery.of(1, 2, 3, 4, 5, 6)

        val winningRank = winningLottery.evaluateWinningRank(lottery)
        Assertions.assertThat(winningRank).isEqualTo(WinningRank.SECOND)
    }

    @Test
    fun `보너스 번호가 당첨 번호에 포함된다면 예외를 던진다`() {
        assertThrows<IllegalArgumentException> {
            WinningLottery(
                Lottery.of(ExplicitNumbersGenerator(listOf(1, 5, 3, 7, 11, 9))),
                LotteryNumber.of(11),
            )
        }
    }

    @Test
    fun `보너스 번호가 당첨 번호에 포함되지 않으면 통과한다`() {
        assertDoesNotThrow {
            WinningLottery(
                Lottery.of(ExplicitNumbersGenerator(listOf(1, 5, 3, 9, 7, 11))),
                LotteryNumber.of(13),
            )
        }
    }

    @Test
    fun `로또 번호와 당첨번호, 보너스 번호를 비교하여 당첨 결과를 알려준다`() {
        val winningLottery =
            WinningLottery(
                Lottery.of(ExplicitNumbersGenerator(listOf(4, 5, 7, 1, 2, 3))),
                LotteryNumber.of(6),
            )
        val lottery = Lottery.of(ExplicitNumbersGenerator(listOf(4, 5, 6, 1, 2, 3)))

        val winningRank = winningLottery.evaluateWinningRank(lottery)
        Assertions.assertThat(winningRank).isEqualTo(WinningRank.SECOND)
    }
}
