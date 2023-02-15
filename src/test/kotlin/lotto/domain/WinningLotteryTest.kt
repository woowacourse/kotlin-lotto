package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

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
        val bonusNumber = LotteryNumber(10)
        val winningLottery = WinningLottery(lottery, bonusNumber)

        assertThat(winningLottery.lottery.numbers).containsAll(lottery.numbers)
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
}
