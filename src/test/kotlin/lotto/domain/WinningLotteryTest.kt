package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

private fun Lottery(vararg numbers: Int): Lottery {
    return Lottery(numbers.map { LotteryNumber.from(it) })
}

class WinningLotteryTest {
    @Test
    fun `당첨 로또를 가진다`() {
        val lottery = Lottery(1, 10, 20, 30, 40, 45)
        val bonusNumber = LotteryNumber.from(5)
        val winningLottery = WinningLottery(lottery, bonusNumber)
        val expected = listOf(
            LotteryNumber.from(1),
            LotteryNumber.from(10),
            LotteryNumber.from(20),
            LotteryNumber.from(30),
            LotteryNumber.from(40),
            LotteryNumber.from(45)
        )

        assertThat(winningLottery.lottery.numbers).containsAll(expected)
    }

    @Test
    fun `보너스 번호를 가진다`() {
        val lottery = Lottery(1, 10, 20, 30, 40, 45)
        val bonusNumber = LotteryNumber.from(8)
        val winningLottery = WinningLottery(lottery, bonusNumber)

        assertThat(winningLottery.bonusNumber).isEqualTo(LotteryNumber.from(8))
    }

    @Test
    fun `당첨 로또 번호와 보너스 번호가 중복되면 에러가 발생한다`() {
        val lottery = Lottery(1, 10, 20, 30, 40, 45)
        val bonusNumber = LotteryNumber.from(10)

        assertThrows<IllegalArgumentException> { WinningLottery(lottery, bonusNumber) }
    }
}
