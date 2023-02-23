package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class WinningLotteryTest {

    @Test
    fun `당첨 로또를 가진다`() {
        val lottery = Lottery(
            listOf(
                LotteryNumber(1),
                LotteryNumber(10),
                LotteryNumber(20),
                LotteryNumber(30),
                LotteryNumber(40),
                LotteryNumber(45)
            )
        )
        val bonusNumber = LotteryNumber(5)
        val winningLottery = WinningLottery(lottery, bonusNumber)
        val expected = listOf(
            LotteryNumber(1),
            LotteryNumber(10),
            LotteryNumber(20),
            LotteryNumber(30),
            LotteryNumber(40),
            LotteryNumber(45)
        )

        assertThat(winningLottery.lottery.numbers).containsAll(expected)
    }

    @Test
    fun `보너스 번호를 가진다`() {
        val lottery = Lottery(
            listOf(
                LotteryNumber(1),
                LotteryNumber(10),
                LotteryNumber(20),
                LotteryNumber(30),
                LotteryNumber(40),
                LotteryNumber(45)
            )
        )
        val bonusNumber = LotteryNumber(8)
        val winningLottery = WinningLottery(lottery, bonusNumber)

        assertThat(winningLottery.bonusNumber).isEqualTo(LotteryNumber(8))
    }

    @Test
    fun `당첨 로또 번호와 보너스 번호가 중복되면 에러가 발생한다`() {
        val lottery = Lottery(
            listOf(
                LotteryNumber(1),
                LotteryNumber(10),
                LotteryNumber(20),
                LotteryNumber(30),
                LotteryNumber(40),
                LotteryNumber(45)
            )
        )
        val bonusNumber = LotteryNumber(10)

        assertThrows<IllegalArgumentException> { WinningLottery(lottery, bonusNumber) }
    }

    @Test
    fun `로또들을 받아 당첨 결과를 반환한다`() {
        val winningLottery = WinningLottery(
            Lottery(
                listOf(
                    LotteryNumber(1),
                    LotteryNumber(10),
                    LotteryNumber(20),
                    LotteryNumber(30),
                    LotteryNumber(40),
                    LotteryNumber(45)
                )
            ),
            LotteryNumber(9)
        )

        val lotteries =
            listOf(
                Lottery(
                    listOf(
                        LotteryNumber(1),
                        LotteryNumber(10),
                        LotteryNumber(20),
                        LotteryNumber(30),
                        LotteryNumber(40),
                        LotteryNumber(45)
                    )
                )
            )

        val expectedWinningResult = winningLottery.createResult(lotteries, 5000)

        assertThat(winningLottery.createResult(lotteries, 5000)[Rank.FIRST])
            .isEqualTo(expectedWinningResult[Rank.FIRST])
    }
}
